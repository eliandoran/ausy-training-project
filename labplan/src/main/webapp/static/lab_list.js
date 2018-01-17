var form = document.querySelector(".test-form");

var resultsTable = document.querySelector("#results-table");
var templateRow = document.querySelector("#results-template");

var templateRowValue = templateRow.querySelector(".value");
var templateRowType = templateRow.querySelector(".type");

templateRowType.oninput = newRow;
templateRowValue.oninput = newRow;

form.onsubmit = submit;
var existingRows = resultsTable.querySelectorAll("tbody tr");
var disabledIndices = {};

for (var index = 0; index < existingRows.length; index++) {
	var row = existingRows[index];
	var typeSelect = row.querySelector(".type select");
	
	applySelectEvents(typeSelect);
	disabledIndices[typeSelect.value] = true;
	
	row.querySelector(".delete").onclick = deleteRow;
}

applySelectEvents(templateRowType);

var testsJSON = document.querySelector("#testsJSON");
var tests = JSON.parse(testsJSON.innerHTML);
console.log(tests);

ownDropdown(templateRowType, tests);

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
	applySelectEvents(templateCloneType);
	
	templateCloneValue = templateClone.querySelector(".value");
	templateCloneValue.name = clonePrefix + "-value";
	
	resultsTable.querySelector("tbody").append(templateClone);
	
	templateRowType.value = "";
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
		var type = row.cells[1].querySelector("select").value;
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
	(function() {
		var previousValue = null;
		
		el.onfocus = function() {
			previousValue = this.value;
		};
		
		el.onchange = function() {
			var row = this.parentElement.parentElement;
			
			if (previousValue != null)
				disabledIndices[previousValue] = false;
			
			disabledIndices[this.value] = true;
		};
		
		el.onclick = function() {
			for (var index in disabledIndices) {
				var option = el.querySelector('option[value="' + index + '"]');
				option.disabled = (disabledIndices[index] == true && option.value !== el.value);
			}
		};
	})();
}

function renumber() {
	var existingRows = resultsTable.querySelectorAll("tbody tr");
	
	for (var index = 0; index < existingRows.length; index++) {
		var row = existingRows[index];
		
		row.cells[0].innerHTML = index + 1;
	}
}