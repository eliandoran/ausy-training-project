var resultsTable = document.querySelector("#results-table");
var templateRow = document.querySelector("#results-template");

templateRow.querySelector(".value").onchange = newRow;
templateRow.querySelector(".type").onchange = newRow;

function newRow() {
	if (templateRow.querySelector(".type").value == "" ||
		templateRow.querySelector(".value").value == "")
		return;
	
	var templateClone = templateRow.cloneNode(true);
	templateClone.id = templateClone.className = "";
	resultsTable.querySelector("tbody").append(templateClone);
	
	templateClone.querySelector(".type").value = templateRow.querySelector(".type").value;
	
	templateRow.querySelector(".type").value = "";
	templateRow.querySelector(".value").value = "";
	
	console.log("Changed!");
}