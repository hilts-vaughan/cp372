package com.cp372.server.exceptions;

/**
 * A custom exception that throws when the client has made a bad request to the
 * server.
 * 
 * @author Vaughan Hilts
 *
 */
public class BadRequestException extends Exception {

	/**
	 * Generated serial ID by the IDE
	 */
	private static final long serialVersionUID = -332750519693488002L;

	public BadRequestException(String message) {
		super(message);
	}

}
