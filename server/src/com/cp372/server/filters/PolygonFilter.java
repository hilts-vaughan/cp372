package com.cp372.server.filters;

import java.util.ArrayList;
import java.util.List;

import com.cp372.server.ShapeEntry;
import com.cp372.server.exceptions.BadRequestException;
import com.cp372.server.models.Quadrilateral;
import com.cp372.server.models.Triangle;

/**
 * Filters by a given instance type given a string for the type. Allows
 * filtering by specific groups of shapes.
 * 
 * @author Vaughan Hilts
 *
 */
public class PolygonFilter implements ShapeFilter {

	private final static String SHAPE_TYPE_TRIANGLE = "T";
	private final static String SHAPE_TYPE_QUAD = "Q";
	private final static String SHAPE_TYPE_BOTH = "B";

	@Override
	public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
			Object context) throws BadRequestException {

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
			case SHAPE_TYPE_BOTH: // otherwise, it's both so allow that
				results.add(entry);
				break;
			default:
				throw new BadRequestException(
						"The provided shape qualifier is illegal");
			}
		}

		return results;
	}

}
