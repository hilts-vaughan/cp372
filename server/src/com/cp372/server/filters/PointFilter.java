package com.cp372.server.filters;

import java.util.ArrayList;
import java.util.List;

import com.cp372.server.ShapeEntry;
import com.cp372.server.models.Point;

public class PointFilter implements ShapeFilter {

	@Override
	public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes,
			Object context) {

		List<ShapeEntry> results = new ArrayList<ShapeEntry>();
		
		@SuppressWarnings("unchecked")
		List<Point> shares = (ArrayList<Point>) context;

		// Filter out those that don't share points
		for (ShapeEntry entry : shapes) {
			if (entry.getShape().hasPoints(shares)) {
				results.add(entry);
			}
		}

		return results;
	}

}
