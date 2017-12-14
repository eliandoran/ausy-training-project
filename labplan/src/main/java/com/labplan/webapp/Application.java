package com.labplan.webapp;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.TracingConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import com.labplan.webapp.resource.PatientListing;

public class Application extends ResourceConfig {
	public Application() {
		packages(PatientListing.class.getPackage().getName());
		register(JspMvcFeature.class);
		register(LoggingFeature.class);
		property(ServerProperties.TRACING, TracingConfig.ON_DEMAND.name());
	}
}
