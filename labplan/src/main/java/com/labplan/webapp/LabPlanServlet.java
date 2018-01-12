package com.labplan.webapp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.labplan.persistence.DatabaseConnectionFactory;
import com.labplan.webapp.handlers.DefaultResourceHandler;
import com.labplan.webapp.handlers.lab_list.AddLabListResourceHandler;
import com.labplan.webapp.handlers.lab_list.EditLabListResourceHandler;
import com.labplan.webapp.handlers.lab_list.ListLabListResourceHandler;
import com.labplan.webapp.handlers.lab_test.AddLabTestResourceHandler;
import com.labplan.webapp.handlers.lab_test.EditLabTestResourceHandler;
import com.labplan.webapp.handlers.lab_test.ListLabTestResourceHandler;
import com.labplan.webapp.handlers.patient.AddPatientResourceHandler;
import com.labplan.webapp.handlers.patient.EditPatientResourceHandler;
import com.labplan.webapp.handlers.patient.ListPatientResourceHandler;

public class LabPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LabPlanServlet.class.getName());

	private HandlerContainer handlers;

	@Override
	public void init() throws ServletException {
		DatabaseConnectionFactory.setProfile("production");

		handlers = new HandlerContainer();

		handlers.registerAll(new ListPatientResourceHandler(), new AddPatientResourceHandler(),
				new EditPatientResourceHandler());

		handlers.registerAll(new ListLabTestResourceHandler(), new AddLabTestResourceHandler(),
				new EditLabTestResourceHandler());

		handlers.registerAll(new ListLabListResourceHandler(), new AddLabListResourceHandler(),
				new EditLabListResourceHandler());
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		String path = getPath(request);
		ServletContext context = getServletContext();
		HandlerParameters params = new HandlerParameters(context, request, response, path);

		LOGGER.info("GET " + request.getRequestURI());

		processMessages(params);
		ResourceHandler handler = obtainHandler(path);

		if (handler != null) {
			if (!handler.doGet(params)) {
				response.sendError(404);
				LOGGER.info("Handler returned 404.");
			}
			;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		String path = getPath(request);
		ServletContext context = getServletContext();
		HandlerParameters params = new HandlerParameters(context, request, response, path);

		LOGGER.info("POST " + request.getRequestURI());
		processMessages(params);
		ResourceHandler handler = obtainHandler(path);

		if (handler != null) {
			if (!handler.doPost(params)) {
				response.sendError(404);
				LOGGER.info("Handler returned 404.");
			}
			;
		}
	}

	private void processMessages(HandlerParameters params) {
		HttpSession session = params.getRequest().getSession();
		Message message = (Message) session.getAttribute("message");

		if (message != null) {
			params.getRequest().setAttribute("message", message);
			session.removeAttribute("message");
			LOGGER.info("Got a message: " + message.getType() + ": " + message.getContent());
		}
	}

	private ResourceHandler obtainHandler(String path) {
		LOGGER.info("Searching for handler: " + path);

		ResourceHandler handler = handlers.obtain(path);

		if (handler != null) {
			LOGGER.info("Obtained handler: " + path);
			return handler;
		}

		LOGGER.info("Using default handler.");
		return new DefaultResourceHandler();
	}

	private String getPath(HttpServletRequest request) {
		try {
			URI basePath = new URI(request.getContextPath());
			URI currentPath = new URI(request.getRequestURI());
			URI relativePath = basePath.relativize(currentPath);

			String path = relativePath.toString();

			// The Java servlet container sometimes appends ";jsessionid=XYZ" to URLs which
			// will confuse the handlers. Removing it from the path is most useful.
			path = path.split(";")[0];

			return path;
		} catch (URISyntaxException e) {
			throw new RuntimeException("Unable to determine server base URI.");
		}
	}
}
