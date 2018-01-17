function ownDropdown(el, data) {
	var parent = document.querySelector("body > main");
	var container = document.getElementById("own-dropdown--container");
	var optionsList = document.getElementById("own-dropdown--options");
	var isOpen = false;
	
	function init() {
		window.onresize = onResize;
		optionsList.onkeydown = onKeyDown;
		open();
	}
	
	function open() {
		isOpen = true;
		reposition();
		loadOptions();
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
	
	function loadOptions() {
		for (var optionId in data) {
			var option = data[optionId];
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