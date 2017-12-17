package com.labplan.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.URIReferenceException;

public class LabPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private HandlerContainer handlers;

	   public void init() throws ServletException {
		   handlers = new HandlerContainer();
		   
		   handlers.register("patients", new PatientsResourceHandler());
	   }

	   public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {	      		   
	      // Set response content type
	      response.setContentType("text/html");	      	      

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      
	      String[] path = getPath(request);
	     
	      if (path.length > 0) {
	    	  String handlerName = path[0];
	    	  ResourceHandler handler = handlers.obtain(handlerName);
	    	  
	    	  if (handler != null) {
	    		  handler.doGet(request, response);
	    	  }
	      }
	   }
	   
	   String[] getPath(HttpServletRequest request) {
		   try {
		      URI basePath =  new URI(request.getContextPath());
		      URI currentPath = new URI(request.getRequestURI());
		      URI relativePath = basePath.relativize(currentPath);
		      
		      return relativePath.toString().split("/");
	      } catch (URISyntaxException e) {
	    	  throw new RuntimeException("Unable to determine server base URI.");
	      }
	   }

	   public void destroy() {
	      // do nothing.
	   }
}
