package com.cp372.server.filters;

import java.util.ArrayList;
import java.util.List;

import com.cp372.server.ShapeEntry;
import com.cp372.server.models.Quadrilateral;
import com.cp372.server.models.Triangle;

/**
 * Filters by a given instance type given a string for the type.
 * Allows filtering by specific groups of shapes.
 * @author Vaughan Hilts
 *
 */
public class PolygonFilter implements ShapeFilter {

	private final static String SHAPE_TYPE_TRIANGLE = "TRIANGLE";
	private final static String SHAPE_TYPE_QUAD = "QUAD";

	@Override
	public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
			Object context) {

		String type = (String) context;
		List<ShapeEntry> results = new ArrayList<ShapeEntry>();

		for (ShapeEntry entry : shapes) {
			switch (type) {
			case SHAPE_TYPE_TRIANGLE:
				if (entry.getShape() instanceof Triangle)
					results.add(entry);
				break;
			case SHAPE_TYPE_QUAD:
				if (entry.getShape() instanceof Quadrilateral)
					results.add(entry);
				break;
			default: // otherwise, it's both so allow that
				results.add(entry);
			}
		}

		return shapes;
	}

}
