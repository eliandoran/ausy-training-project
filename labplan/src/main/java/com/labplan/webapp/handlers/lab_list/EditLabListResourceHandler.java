package com.labplan.webapp.handlers.lab_list;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.exceptions.ValidationException;
import com.labplan.helpers.NumericUtils;
import com.labplan.helpers.Pair;
import com.labplan.persistence.generic.LabTestDao;
import com.labplan.persistence.sql.SqlLabListDao;
import com.labplan.persistence.sql.SqlLabTestDao;
import com.labplan.services.LabListService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.Message;
import com.labplan.webapp.ResourceHandler;

public class EditLabListResourceHandler implements ResourceHandler {
	private SqlLabListDao listDao;
	private LabListService listService;

	public EditLabListResourceHandler() {
		listDao = new SqlLabListDao();
		listService = new LabListService(listDao);
	}

	@Override
	public String getPath() {
		return "lists/edit";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		Integer listId = NumericUtils.tryParseInteger(params.getRequest().getParameter("id"));

		if (listId == null)
			return false;

		LabList list = listDao.read(listId, true);

		if (list == null)
			return false;

		List<Pair<String, String>> fields = getFields(list);

		HttpServletRequest request = params.getRequest();
		request.setAttribute("list", list);
		request.setAttribute("tests", getTests());
		request.setAttribute("fieldCount", fields.size());
		request.setAttribute("fields", fields);

		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/lists/edit.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());

		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		Integer listId = NumericUtils.tryParseInteger(params.getRequest().getParameter("id"));

		if (listId == null)
			return false;

		LabList list = listDao.read(listId);

		if (list == null)
			return false;

		HttpServletRequest request = params.getRequest();
		String data = request.getParameter("data");
		Message message = new Message();

		try {
			LabList parsedList = listService.parse(list.getPatient().getKey().toString(), data);

			if (parsedList != null) {
				parsedList.setId(listId);
				parsedList.setCreationDate(list.getCreationDate());
				listDao.update(parsedList);

				message.setContent("Lab list updated successfully.");
				message.setType(Message.MessageType.MSG_SUCCESS);

				HttpSession session = params.getRequest().getSession(true);
				session.setAttribute("message", message);
				params.getResponse().sendRedirect(
						params.getContext().getContextPath() + "/lists/?patient=" + parsedList.getPatient().getKey());
			}
		} catch (ValidationException e) {
			message.setContent(e.toString());
			message.setType(Message.MessageType.MSG_ERROR);

			request.setAttribute("message", message);
			request.setAttribute("tests", getTests());
			request.setAttribute("list", list);

			LabList parsedList = (LabList) e.getResultingObject();
			if (parsedList != null) {
				LinkedList<Pair<String, String>> fields = getFields(parsedList);

				request.setAttribute("fieldCount", fields.size());
				request.setAttribute("fields", fields);
			}

			RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/lists/edit.jsp");
			dispatcher.forward(params.getRequest(), params.getResponse());
		}

		return true;
	}

	private LinkedList<Pair<String, String>> getFields(LabList list) {
		LinkedList<Pair<String, String>> fields = new LinkedList<>();
		for (LabResult result : list.getResults()) {
			fields.add(new Pair<>(result.getId().getKey().toString(), result.getValue().toString()));
		}

		return fields;
	}

	private List<LabTest> getTests() {
		LabTestDao testDao = new SqlLabTestDao();
		List<LabTest> testsList = new LinkedList<>();
		testsList.addAll(testDao.readAll());
		return testsList;
	}
}
