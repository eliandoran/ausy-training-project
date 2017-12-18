package com.labplan.webapp;

import java.util.HashMap;
import java.util.Map;

public class HandlerContainer {
	private Map<String, ResourceHandler> handlers;

	public HandlerContainer() {
		handlers = new HashMap<>();
	}

	public boolean register(String name, ResourceHandler handler) {
		if (handlers.containsKey(name))
			return false;

		handlers.put(name, handler);
		return true;
	}

	public ResourceHandler obtain(String name) {
		if (!handlers.containsKey(name))
			return null;

		return handlers.get(name);
	}
}
