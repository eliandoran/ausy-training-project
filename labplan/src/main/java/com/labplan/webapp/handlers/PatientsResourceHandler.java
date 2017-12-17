package com.labplan.webapp.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labplan.webapp.ResourceHandler;

public class PatientsResourceHandler extends ResourceHandler {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().println("Patient handler here.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
