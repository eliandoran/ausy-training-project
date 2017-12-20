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
	
	templateClone.querySelector(".type").value = templateRowType.value;
	templateClone.querySelector(".index").innerHTML = resultsTable.querySelectorAll("tbody tr").length + 1;
	
	resultsTable.querySelector("tbody").append(templateClone);
	
	
	templateRowType.value = "";
	templateRowValue.value = "";
	
	console.log("Changed!");
}