package com.labplan.webapp;

import java.util.HashMap;
import java.util.Map;

/**
 * A collection of {@link ResourceHandler} in which
 * {@link ResourceHandler#getPath()} is used for quick retrieval.
 * 
 * @author elian
 *
 */
public class HandlerContainer {
	private Map<String, ResourceHandler> handlers;

	/**
	 * Creates a blank {@link HandlerContainer}.
	 */
	public HandlerContainer() {
		handlers = new HashMap<>();
	}

	/**
	 * Adds a new {@link ResourceHandler} to this {@link HandlerContainer}.
	 * 
	 * @param handler
	 *            The {@link ResourceHandler} to add.
	 * @return {@code true} if the {@code handler} was added, {@code false} if its
	 *         path is already registered.
	 */
	public boolean register(ResourceHandler handler) {
		String name = handler.getPath();

		if (handlers.containsKey(name))
			return false;

		handlers.put(name, handler);
		return true;
	}

	/**
	 * Adds multiple {@link ResourceHandler} to this {@link HandlerContainer}.
	 * 
	 * @param handlers
	 *            A number of {@link ResourceHandler} to add.
	 * @return {@code true} if all the {@code handlers} were added, {@code false} if
	 *         at least one has a path that is already registered.
	 */
	public boolean registerAll(ResourceHandler... handlers) {
		boolean result = true;

		for (ResourceHandler handler : handlers) {
			result &= register(handler);
		}

		return result;
	}

	/**
	 * Retrieves a {@link ResourceHandler} by its {@link ResourceHandler#getPath()}.
	 * 
	 * @param path
	 *            The path of the {@link ResourceHandler} to retrieve.
	 * @return The {@link ResourceHandler} with the matching path, {@code null} if
	 *         none found.
	 */
	public ResourceHandler obtain(String path) {
		if (!handlers.containsKey(path))
			return null;

		return handlers.get(path);
	}
}
