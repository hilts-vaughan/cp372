package com.cp372.server.filters;

import com.cp372.server.ShapeEntry;

/**
 * Filters on enumeration types against the different shapes
 * @author Vaughan Hilts
 *
 */
public class TypeFilter implements ShapeFilter {
	
	@Override
	public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes, Object context) {
		//TODO: Apply some filtering logic
		return shapes;
	}

}
