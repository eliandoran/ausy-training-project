function ownDropdown(el, data) {
	var parent = document.querySelector("body > main");
	var container = document.getElementById("own-dropdown--container");
	var isOpen = false;
	
	function init() {
		window.onresize = onResize;
		open();
	}
	
	function open() {
		isOpen = true;
		reposition();
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
	
	function onResize() {
		if (!isOpen) return;
		reposition();
	}
	
	init();
}