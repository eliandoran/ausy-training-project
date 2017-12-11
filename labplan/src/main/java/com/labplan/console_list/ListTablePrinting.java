package com.labplan.console_list;

import java.text.DateFormat;
import java.util.Date;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.Patient;

import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Precision;
import io.bretty.console.table.Table;

class ListTablePrinting {
	/**
	 * Displays a table showing information about multiple {@link LabList}s.
	 * 
	 * @param ids
	 *            A {@link Number[]} containing the IDs of each {@link LabList}.
	 * @param firstNames
	 *            A {@link String[]) containing the first names of the
	 *            {@link Patient} of each {@link LabList}.
	 * @param lastNames
	 *            A {@link String[]) containing the last names of the
	 *            {@link Patient} of each {@link LabList}.
	 * @param creationDates
	 *            A {@link Date[]} containing the creation dates of every
	 *            {@link LabList}.
	 */
	public static void displayListTable(Number[] ids, String[] firstNames, String[] lastNames, Date[] creationDates) {
		DateFormat df = DateFormat.getDateTimeInstance();

		Table.Builder builder = new Table.Builder("ID", ids,
				ColumnFormatter.number(Alignment.RIGHT, 5, Precision.ZERO));
		builder.addColumn("First Name", firstNames, ColumnFormatter.text(Alignment.LEFT, 15));
		builder.addColumn("Last Name", lastNames, ColumnFormatter.text(Alignment.LEFT, 15));
		builder.addColumn("Creation date", creationDates, ColumnFormatter.dateTime(Alignment.LEFT, 20, df));
		System.out.println(builder.build());
	}

	/**
	 * Displays a table showing information about a single {@link LabList}.
	 * 
	 * @param testNames
	 *            A {@link String[]} containing the name of the {@link LabTest} of
	 *            every {@link LabResult}.
	 * @param values
	 *            A {@link Number[]} containing the {@code value} of every
	 *            {@link LabResult}.
	 * @param minimums
	 *            A {@link Number[]} containing the {@code minimumValue} of the
	 *            {@link LabTest} of every {@link LabResult}.
	 * @param maximums
	 *            A {@link Number[]} containing the {@code maximumValue} of the
	 *            {@link LabTest} of every {@link LabResult}.
	 */
	public static void displaySingleListTable(String[] testNames, Number[] values, Number[] minimums,
			Number[] maximums) {
		Table.Builder builder = new Table.Builder("Test Name", testNames, ColumnFormatter.text(Alignment.LEFT, 40));
		builder.addColumn("Min", minimums, ColumnFormatter.number(Alignment.LEFT, 5, Precision.TWO));
		builder.addColumn("Max", maximums, ColumnFormatter.number(Alignment.LEFT, 5, Precision.TWO));
		builder.addColumn("Value", values, ColumnFormatter.number(Alignment.LEFT, 5, Precision.TWO));
		System.out.println(builder.build());
	}
}
