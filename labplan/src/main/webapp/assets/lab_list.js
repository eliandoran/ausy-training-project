var resultsTable = document.querySelector("#results-table");
var templateRow = document.querySelector("#results-template");

var templateRowValue = templateRow.querySelector(".value");
var templateRowType = templateRow.querySelector(".type");

templateRowType.onchange = newRow;
templateRowValue.onchange = newRow;

function newRow() {
	if (templateRowType.value == "" || templateRowValue.value == "")
		return;
	
	var templateClone = templateRow.cloneNode(true);
	templateClone.id = templateClone.className = "";
	resultsTable.querySelector("tbody").append(templateClone);
	
	templateClone.querySelector(".type").value = templateRowType.value;
	
	templateRowType.value = "";
	templateRowValue.value = "";
	
	console.log("Changed!");
}