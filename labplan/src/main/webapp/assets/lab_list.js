var resultsTable = document.querySelector("#results-table");
var templateRow = document.querySelector("#results-template");

templateRow.querySelector(".value").onchange = function() {
	var templateClone = templateRow.cloneNode(true);
	templateClone.id = templateClone.className = "";
	resultsTable.querySelector("tbody").append(templateClone);
	
	templateClone.querySelector(".type").value = templateRow.querySelector(".type").value;
	
	templateRow.querySelector(".type").value = "";
	templateRow.querySelector(".value").value = "";
	
	console.log("Changed!");
};