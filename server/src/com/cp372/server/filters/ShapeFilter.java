package com.cp372.server.filters;

import com.cp372.server.ShapeEntry;
import com.cp372.server.exceptions.BadRequestException;

/**
 * Provides a generic interface for filters that must be implemented against
 * different shapes.
 * 
 * @author Vaughan Hilts
 *
 */
public interface ShapeFilter {

	/**
	 * Filters a set of shape entries using the given context. It is up to the
	 * implementor to decide on the context of which it will be used.
	 * 
	 * @param shapes
	 *            A simple iterable set of shapes that can be filtered.
	 * @param context
	 *            A data object decided by the implemented class that is used as
	 *            context for the filtering.
	 * @return
	 * @throws BadRequestException
	 */
	public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
			Object context) throws BadRequestException;

}
