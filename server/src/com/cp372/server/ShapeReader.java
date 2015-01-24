package com.cp372.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cp372.server.filters.OccurenceFilter;
import com.cp372.server.filters.PointFilter;
import com.cp372.server.filters.PolygonFilter;
import com.cp372.server.filters.TypeFilter;
import com.cp372.server.models.Shape;

/**
 * Responsible for reading the shapes from storage and responding to various
 * different queries. This provides an abstraction layer over top of the direct
 * storage.
 * 
 * @author Vaughan Hilts
 * 
 */
public class ShapeReader {

	private PolygonFilter _polygonFilter = new PolygonFilter();
	private OccurenceFilter _occurenceFilter = new OccurenceFilter();
	private TypeFilter _typeFilter = new TypeFilter();
	private PointFilter _pointFilter = new PointFilter();

	private final static String HEADER_OCCURENCES = "OCCURENCES";
	private final static String HEADER_POINT = "POINTS";
	private final static String HEADER_TYPE = "TYPE";

	public ShapeReader() {

	}

	public Iterable<Shape> processQuery(String type, Map<String, String> params)
			throws Exception {

		// Do an initial filter by type. Next, we simply filter based on the
		// decided attributes from the headers
		Iterable<ShapeEntry> results = _polygonFilter.filter(ShapeStorage
				.getInstance().getShapes(), type);

		for (Map.Entry<String, String> entry : params.entrySet()) {
			switch (entry.getKey()) {
			case HEADER_OCCURENCES:
				int occurences = Integer.parseInt(entry.getValue());
				results = _occurenceFilter.filter(results, occurences);
				break;
			default: // ignore attributes that aren't implemented
				break;
			}
		}

		// Fetch all entries shapes, put into list
		List<Shape> shapes = new ArrayList<Shape>();
		for (ShapeEntry entry : results) {
			shapes.add(entry.getShape());
		}

		return shapes;
	}

}
