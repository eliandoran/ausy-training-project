package com.labplan.webapp;

import java.util.HashMap;
import java.util.Map;

public class HandlerContainer {
	private Map<String, ResourceHandler> handlers;

	public HandlerContainer() {
		handlers = new HashMap<>();
	}

	public boolean register(ResourceHandler handler) {
		String name = handler.getPath();
		
		if (handlers.containsKey(name))
			return false;

		handlers.put(name, handler);
		return true;
	}

	public ResourceHandler obtain(String path) {
		if (!handlers.containsKey(path))
			return null;

		return handlers.get(path);
	}
}
