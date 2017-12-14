package com.labplan.webapp;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.TracingConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import com.labplan.webapp.resource.Patient;

public class Application extends ResourceConfig {
	public Application() {
		packages(Patient.class.getPackage().getName());
		register(JspMvcFeature.class);
		register(LoggingFeature.class);
		property(ServerProperties.TRACING, TracingConfig.ON_DEMAND.name());
	}
}
