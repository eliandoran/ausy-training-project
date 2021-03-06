var testsJSON = document.querySelector("#testsJSON");
var tests = JSON.parse(testsJSON.innerHTML);

var form = document.querySelector(".test-form");

var resultsTable = document.querySelector("#results-table");
var templateRow = document.querySelector("#results-template");

var templateRowValue = templateRow.querySelector(".value");
var templateRowType = templateRow.querySelector(".type");

templateRowType = ownDropdown(templateRowType, tests);

templateRowValue.oninput = newRow;
templateRowType.addEventListener("change", newRow);

form.onsubmit = submit;
var existingRows = resultsTable.querySelectorAll("tbody tr");

for (var index = 0; index < existingRows.length; index++) {
	var row = existingRows[index];
	var typeSelect = row.querySelector(".type");
	typeSelect = ownDropdown(typeSelect, tests);
	applySelectEvents(typeSelect);
	row.querySelector(".delete").onclick = deleteRow;
}

applySelectEvents(templateRowType);

function newRow() {
	if (templateRowType.value == "" || templateRowValue.value == "")
		return;
	
	var templateClone = templateRow.cloneNode(true);
	templateClone.id = templateClone.className = "";
	
	var index = resultsTable.querySelectorAll("tbody tr").length + 1;
	templateClone.querySelector(".index").innerHTML = index;
	
	var clonePrefix = "result-" + index;
	
	var templateCloneActions = templateClone.querySelector(".actions");
	templateCloneActions.className = "actions";
	templateCloneActions.querySelector(".delete").onclick = deleteRow;
	
	templateCloneType = templateClone.querySelector(".type");
	
	templateCloneType.value = templateRowType.value;
	templateCloneType.name = clonePrefix + "-type";
	templateCloneType = ownDropdown(templateCloneType, tests);
	applySelectEvents(templateCloneType);
	
	templateCloneValue = templateClone.querySelector(".value");
	templateCloneValue.name = clonePrefix + "-value";
	
	resultsTable.querySelector("tbody").append(templateClone);
	
	templateRowType.value = null;
	templateRowValue.value = "";
	
	moveCaretToEnd(templateCloneValue);
	templateCloneValue.scrollIntoView();
}

function deleteRow() {
	var row = this.parentElement.parentElement;
	var table = row.parentElement;
	
	table.removeChild(row);
	renumber();
}

function moveCaretToEnd(el) {
    el.focus();
    if (typeof el.selectionStart == "number") {
        el.selectionStart = el.selectionEnd = el.value.length;
    } else if (typeof el.createTextRange != "undefined") {
        var range = el.createTextRange();
        range.collapse(false);
        range.select();
    }
}

function submit() {
	var rows = resultsTable.querySelectorAll("tbody > tr");
	var data = {};

	for (var index = 0; index < rows.length; index++) {
		var row = rows[index];
		var type = row.cells[1].querySelector("input").value;
		var value = row.cells[2].querySelector("input").value;
		
		data[type] = value;
	}
	
	var dataInput = document.createElement("input");
	dataInput.setAttribute("type", "hidden");
	dataInput.setAttribute("name", "data");
	dataInput.setAttribute("value", JSON.stringify(data));
	form.appendChild(dataInput);
}

function applySelectEvents(el) {
	el.addEventListener("change", function(e) {
		var previousValue = this.previousValue;
		var value = this.value;
		
		if (previousValue !== undefined && previousValue) {
			console.log("Previous value: " + previousValue);
			tests[previousValue].disabled = false;
		}
		
		tests[value].disabled = true;
	});
}

function renumber() {
	var existingRows = resultsTable.querySelectorAll("tbody tr");
	
	for (var index = 0; index < existingRows.length; index++) {
		var row = existingRows[index];
		
		row.cells[0].innerHTML = index + 1;
	}
}