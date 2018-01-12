package com.labplan.exceptions;

/**
 * Thrown when a connection to the data source cannot be obtained, either due to
 * misconfiguration of authentication data or a problem with the server.
 * 
 * <p>
 * It is recommended to use {@link ConnectionFailedException} as a wrapper to an
 * underlying error (i.e. SQL connection error) by using
 * {@link ConnectionFailedException#addSuppressed(Throwable)}.
 * </p>
 * 
 * @author Elian Doran
 *
 */
public class ConnectionFailedException extends RuntimeException {
	/**
	 * Creates a new instance of the exception without specifying a message.
	 */
	public ConnectionFailedException() {

	}

	/**
	 * Creates a new instance of the exception with the given {@code message}.
	 * 
	 * @param message
	 */
	public ConnectionFailedException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -4385061762347641614L;
}
