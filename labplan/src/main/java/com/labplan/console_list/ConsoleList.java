package com.labplan.console_list;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.Patient;
import com.labplan.persistence.exceptions.ConnectionFailedException;
import com.labplan.persistence.sql.SqlLabListDao;

/**
 * A small application that prints to the console in a tabular fashion all the
 * {@link LabList} and their corresponding {@link LabResult}.
 * 
 * @author Elian Doran
 *
 */
public class ConsoleList {
	/**
	 * The main entry point of the application.
	 * 
	 * @param args
	 *            The arguments to be passed to the application.
	 */
	public static void main(String[] args) {
		Set<LabList> lists = getLists();

		displayLists(lists);

		for (LabList list : lists) {
			try {
				displayList(list.getId());
			} catch (ConnectionFailedException e) {
				System.out.println("<Connection error>");
			}
		}
	}

	/**
	 * Obtains all the {@link LabList} from the data source.
	 * 
	 * @return
	 */
	private static Set<LabList> getLists() {
		SqlLabListDao labListDao = new SqlLabListDao();
		return labListDao.readAll();
	}

	/**
	 * Given a set of {@link LabList} displays a table containing the IDs, the full
	 * name of the list's patient and the creation date of the list.
	 * 
	 * @param lists
	 *            A set of {@link LabList} to be displayed.
	 */
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

		ListTablePrinting.displayListTable(ids, firstNames, lastNames, creationDates);
	}

	/**
	 * Given the ID of a {@link LabList}, displays information about the list (like
	 * the ID, full name of the patient, creation date) and all the
	 * {@link LabResult}s of the {@link LabList} (name of the test, result value,
	 * minimum and maximum value).
	 * 
	 * @param listId
	 */
	@SuppressWarnings("deprecation")
	private static void displayList(Integer listId) {
		LabList list = (new SqlLabListDao()).read(listId, true);

		System.out.println("\n\nList: " + list.getId() + "\t Patient: " + list.getPatient().getEntity().getFirstName()
				+ " " + list.getPatient().getEntity().getLastName() + "\t Creation date: "
				+ list.getCreationDate().toLocaleString());

		List<LabResult> results = list.getResults();
		int count = results.size();
		int index = 0;

		String[] testNames = new String[count];
		Number[] values = new Float[count];
		Number[] minimums = new Float[count];
		Number[] maximums = new Float[count];

		for (LabResult result : results) {
			testNames[index] = result.getTest().getName();
			values[index] = result.getValue();
			minimums[index] = result.getTest().getValueMin();
			maximums[index] = result.getTest().getValueMax();
			index++;
		}

		ListTablePrinting.displaySingleListTable(testNames, values, minimums, maximums);
	}
}
