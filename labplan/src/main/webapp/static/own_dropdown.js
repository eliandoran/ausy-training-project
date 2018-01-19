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
		
		hiddenEl = document.createElement("input");
		hiddenEl.type = "hidden";
		
		textEl = document.createElement("span");
		
		el.append(hiddenEl);
		el.append(textEl);
		
		el.value = null;
		el.previousValue = null;
		
		el.refresh = refresh;
		
		enableNavigation();
		enableSearch();
		
		refresh();
	}
	
	function open() {
		isOpen = true;
		reposition();
		loadOptions();
		emitEvent("focus");
		searchBox.focus();
	}
	
	function close() {
		isOpen = false;
		container.style.display = "none";
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
			newOption.className = (option.disabled ? "disabled" : "");
			
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
			
			if (el.value !== null)
				el.previousValue = el.value;
			el.value = id;

			refresh();
			emitEvent("change");
		}
		
		close();
	}
	
	function refresh() {
		var id = el.value;
		var option;
		
		if (id != null) {
			hiddenEl.value = id;
			option = data[id];
		}
		
		textEl.innerHTML = (option != null ? option.name : "&nbsp;");
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
	
	function emitEvent(eventName) {
		if ("createEvent" in document) {
			var event = document.createEvent("HTMLEvents");
			event.initEvent(eventName, false, true);
			el.dispatchEvent(event);
		} else {
			el.fireEvent("on" + eventName);
		}
	}
	
	function enableNavigation() {
		container.onkeydown = function(e) {
			if (e.key === "Escape") {
				close();
				return false;
			}
			
			return true;
		};
		
		searchBox.onkeydown = function(e) {
			if (e.key === "ArrowDown") {
				optionsList.querySelector("a").focus();
				return false;
			}
			
			return true;
		};
		
		optionsList.onkeydown = function(e) {
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
			
			if (!(newEl instanceof HTMLElement) && e.key == "ArrowUp")
				searchBox.focus();
		};
	}
	
	function enableSearch() {
		function tokenize() {
			for (var optionId in data) {
				var option = data[optionId];
				
				option.tokens = option.name.toLowerCase().split(" ");
			}
		}
		
		searchBox.value = "";
		
		searchBox.oninput = function(e) {
			if (searchInterval !== undefined)
				clearInterval(searchInterval);
			
			searchInterval = setTimeout(function() {
				loadOptions(searchBox.value);
			}, 100);
		};
		
		tokenize();
	}
	
	init();
}