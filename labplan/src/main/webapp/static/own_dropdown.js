function ownDropdown(el, data) {
	var parent = document.querySelector("body > main");
	var container = document.getElementById("own-dropdown--container");
	var optionsList = document.getElementById("own-dropdown--options");
	var searchBox = document.getElementById("own-dropdown--search").querySelector("input");
	var isOpen = false;
	var searchInterval;
	
	function init() {
		window.onresize = onResize;
		optionsList.onkeydown = onKeyDown;
		searchBox.oninput = startSearching;
		searchBox.value = "";
		open();
	}
	
	function open() {
		isOpen = true;
		reposition();
		loadOptions();
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
				var tokens = option.name.toLowerCase().split(" ");
				var found = false;
				
				for (tokenIndex=0; tokenIndex<tokens.length; tokenIndex++) {
					var token = tokens[tokenIndex];
					if (!token.length) continue;
					
					for (filterTokenIndex=0; filterTokenIndex<filterTokens.length; filterTokenIndex++) {
						var filterToken = filterTokens[filterTokenIndex];
						if (!filterToken.length) continue;
						
						if (token.startsWith(filterToken))
							found |= true;
					}
				}
					
				if (!found)
					continue;
			}
			
			var newOption = document.createElement("li");
			
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
	
	function select(el) {
		console.log(el);
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
	
	init();
}