package com.labplan.console_list;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.labplan.entities.*;
import com.labplan.persistence.sql.*;

import io.bretty.console.table.*;

public class ConsoleList {
	public static void main(String[] args) {
		Set<LabList> lists = getLists();
		
		displayLists(lists);
		
		for (LabList list : lists) {
			displayList(list.getId());
		}
	}
	
	private static void displayLists(Set<LabList> lists) {
		int count = lists.size();
		
		Number[] ids = new Number[count];
		String[] firstNames = new String[count];
		String[] lastNames = new String[count];
		Date[] creationDates = new Date[count];
		
		int index = 0;
		
		for (LabList list : lists) {
			Patient patient = list.getPatient().getEntity();
			
			ids[index] = list.getId();
			firstNames[index] = patient.getFirstName();
			lastNames[index] = patient.getLastName();
			creationDates[index] = list.getCreationDate();
			
			index++;
		}
		
		displayListTable(ids, firstNames, lastNames, creationDates);
	}
	
	private static void displayList(Integer listId) {
		LabList list = (new LabListDao()).read(listId, true);
		
		System.out.println("\n\nList: " + list.getId()
			+ "\t Patient: " + list.getPatient().getEntity().getFirstName() + " " + list.getPatient().getEntity().getLastName()
			+ "\t Creation date: " + list.getCreationDate().toGMTString());
		
		List<LabResult> results = list.getResultsList();
		int count = results.size(), index = 0;
		
		String[] testNames = new String[count];
		Number[] values = new Float[count];
		Number[] minimums = new Float[count];
		Number[] maximums = new Float[count];
		
		for (LabResult result : results) {
			testNames[index] = result.getId().getEntity().getName();
			values[index] = result.getValue();
			minimums[index] = result.getId().getEntity().getValueMin();
			maximums[index] = result.getId().getEntity().getValueMax();
			index++;
		}
		
		displaySingleListTable(testNames, values, minimums, maximums);
	}
	
	private static Set<LabList> getLists() {
		LabListDao labListDao = new LabListDao();
		return labListDao.readAll();
	}
	
	private static void displayListTable(Number[] ids, String[] firstNames, String[] lastNames, Date[] creationDates) {
		DateFormat df = DateFormat.getDateTimeInstance();
		
		Table.Builder builder = new Table.Builder("ID", ids, ColumnFormatter.number(Alignment.RIGHT, 5, Precision.ZERO));
		builder.addColumn("First Name", firstNames, ColumnFormatter.text(Alignment.LEFT, 15));
		builder.addColumn("Last Name", lastNames, ColumnFormatter.text(Alignment.LEFT, 15));
		builder.addColumn("Creation date", creationDates, ColumnFormatter.dateTime(Alignment.LEFT, 20, df));
		System.out.println(builder.build());
	}
	
	private static void displaySingleListTable(String[] testNames, Number[] values, Number[] minimums, Number[] maximums) {
		Table.Builder builder = new Table.Builder("Test Name", testNames, ColumnFormatter.text(Alignment.LEFT, 40));
		builder.addColumn("Min", minimums, ColumnFormatter.number(Alignment.LEFT, 5, Precision.TWO));
		builder.addColumn("Max", maximums, ColumnFormatter.number(Alignment.LEFT, 5, Precision.TWO));
		builder.addColumn("Value", values, ColumnFormatter.number(Alignment.LEFT, 5, Precision.TWO));
		System.out.println(builder.build());
	}
}
