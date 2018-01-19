function ownDropdown(el, data) {
	var parent = document.querySelector("body > main");
	var container = document.getElementById("own-dropdown--container");
	var optionsList = document.getElementById("own-dropdown--options");
	var searchBox = document.getElementById("own-dropdown--search").querySelector("input");
	var isOpen = false;
	var searchInterval;
	
	var hiddenEl, textEl;
	
	function init() {
		el.onclick = open;
		window.onresize = onResize;
		optionsList.onkeydown = onKeyDown;
		searchBox.oninput = startSearching;
		searchBox.value = "";
		tokenize();
		
		hiddenEl = document.createElement("input");
		hiddenEl.type = "hidden";
		
		textEl = document.createElement("span");
		textEl.innerHTML = "&nbsp;";
		
		el.append(hiddenEl);
		el.append(textEl);
	}
	
	function open() {
		isOpen = true;
		reposition();
		loadOptions();
	}
	
	function close() {
		isOpen = false;
		container.style.display = "none";
	}
	
	function tokenize() {
		for (var optionId in data) {
			var option = data[optionId];
			
			option.tokens = option.name.toLowerCase().split(" ");
		}
	}
	
	function startSearching() {
		if (searchInterval !== undefined)
			clearInterval(searchInterval);
		
		searchInterval = setTimeout(function() {
			loadOptions(searchBox.value);
		}, 100);
	}
	
	function popAt(x, y, width) {
		container.style.display = "block";
		container.style.left = x;
		container.style.top = y;
		container.style.width = width;
	}
	
	function coordToPixel(coord) {
		return Math.round(coord) + "px";
	}
	
	function reposition() {		
		var clientRect = el.getBoundingClientRect();
		var parentRect = parent.getBoundingClientRect();
		
		var x = clientRect.x - parentRect.x;
		var y = clientRect.bottom - parentRect.y;
		var width = clientRect.right - clientRect.left;
		
		popAt(coordToPixel(x), coordToPixel(y), coordToPixel(width));
	}
	
	function loadOptions(filter) {
		var filterTokens;
		if (filter !== undefined) 
			filterTokens = filter.toLowerCase().split(" ");
		
		optionsList.innerHTML = "";
		
		for (var optionId in data) {
			var option = data[optionId];
			
			if (filter !== undefined && filter != "") {
				var tokens = option.tokens;
				var found = false;
				
				for (tokenIndex=0; tokenIndex<tokens.length; tokenIndex++) {
					var token = tokens[tokenIndex];
					if (!token.length) continue;
					
					for (filterTokenIndex=0; filterTokenIndex<filterTokens.length; filterTokenIndex++) {
						var filterToken = filterTokens[filterTokenIndex];
						if (!filterToken.length) continue;
						
						if (token.search(filterToken) !== -1) {
							found = true;
							break;
						}
					}
					
					if (found)
						break;
				}
					
				if (!found)
					continue;
			}
			
			var newOption = document.createElement("li");
			newOption.dataset.id = optionId;
			
			var linkEl= document.createElement("a");
			linkEl.href = "#";
			linkEl.onclick = onItemClick;
			
			var nameEl = document.createElement("span");
			nameEl.className = "name";
			nameEl.innerHTML = option.name;
			
			linkEl.append(nameEl);
			newOption.append(linkEl);
			optionsList.append(newOption);
		}
	}
	
	function select(selectedEl) {
		if (selectedEl instanceof HTMLElement) {
			var id = selectedEl.dataset.id;
			var option = data[id];
			hiddenEl.value = id;
			el.value = id;
			textEl.innerHTML = option.name;
			fireOnChange();
		}
		
		close();
	}
	
	function onResize() {
		if (!isOpen) return;
		reposition();
	}
	
	function onItemClick(e) {
		var node = e.target;
		
		while (node.tagName != "LI")
			node = node.parentElement;
		
		select(node);
	}
	
	function getActiveInput() {
		var activeEl = document.activeElement;
		
		if (!optionsList.contains(activeEl))
			return null;
		
		return activeEl;
	}
	
	function onKeyDown(e) {
		if (e.key != "ArrowUp" && e.key != "ArrowDown")
			return true;
		
		var activeLink = getActiveInput();
		var activeItem = activeLink.parentElement;
		var newEl;
		
		if (e.key == "ArrowUp")
			newEl = activeItem.previousSibling;
		else if (e.key == "ArrowDown")
			newEl = activeItem.nextSibling;
		
		if (newEl instanceof HTMLElement) {
			newEl.querySelector("a").focus();
			return false;
		}
	}
	
	function fireOnChange() {
		if ("createEvent" in document) {
			var event = document.createEvent("HTMLEvents");
			event.initEvent("change", false, true);
			el.dispatchEvent(event);
		} else {
			el.fireEvent("onchange");
		}
	}
	
	init();
}