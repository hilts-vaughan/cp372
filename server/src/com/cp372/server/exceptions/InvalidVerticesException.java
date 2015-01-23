package com.cp372.server.exceptions;

import java.util.List;

import com.cp372.server.models.Point;

/**
 * A general exception that is thrown when an amount of vertices that is sent
 * isn't a valid one. This is generally a misbehaving client or unknown behaviour.
 * 
 * @author Vaughan Hilts
 *
 */
public class InvalidVerticesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidVerticesException(List<Point> vertices) {
		
	}
	
}
