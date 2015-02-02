package com.cp372.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cp372.server.exceptions.BadRequestException;
import com.cp372.server.filters.OccurenceFilter;
import com.cp372.server.filters.PointFilter;
import com.cp372.server.filters.PolygonFilter;
import com.cp372.server.filters.PropertyFilter;
import com.cp372.server.models.Point;

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
	private PropertyFilter _propertyFilter = new PropertyFilter();
	private PointFilter _pointFilter = new PointFilter();

	private final static String HEADER_OCCURENCES = "OCCURRENCES";
	private final static String HEADER_POINT = "SHARES";
	private final static String HEADER_TYPE = "TYPE";

	public ShapeReader() {

	}

	public Iterable<ShapeEntry> processQuery(String type,
			Map<String, String> params) throws Exception {

		// Do an initial filter by type. Next, we simply filter based on the
		// decided attributes from the headers
		Iterable<ShapeEntry> results = _polygonFilter.filter(ShapeStorage
				.getInstance().getShapes(), type);

		for (Map.Entry<String, String> entry : params.entrySet()) {
			switch (entry.getKey().toUpperCase()) {

			case HEADER_OCCURENCES:

				try {
					int occurences = Integer.parseInt(entry.getValue());

					if (occurences < 0) {
						throw new BadRequestException(
								"A negative integer was passed to the OCCURRENCES header.");
					}

					results = _occurenceFilter.filter(results, occurences);
				} catch (Exception exception) {
					throw new BadRequestException(
							"An integer was not passed to the OCCURRENCES header.");
				}

				break;
			case HEADER_POINT:

				try {

					// Generate our points by using some string logic... ouch
					List<Point> points = new ArrayList<Point>();

					String[] pointPieces = entry.getValue().split(" ");

					// Convert pieces here
					for (int i = 0; i < pointPieces.length; i += 2) {

						String pointPiece = pointPieces[i];
						String pointPiece2 = pointPieces[i + 1];

						int x = Integer.parseInt(pointPiece.trim());
						int y = Integer.parseInt(pointPiece2.trim());

						points.add(new Point(x, y));
					}

					_pointFilter.filter(results, points);
				} catch (Exception exception) {
					throw new BadRequestException(
							"The points provided to the SHARES header is malformed or incorrect.");
				}

				break;
			case HEADER_TYPE:
				try {

					List<String> properties = new ArrayList<String>();

					String[] pieces = entry.getValue().split(" ");
					for (String piece : pieces) {
						properties.add(piece);
					}

					_propertyFilter.filter(results, properties);
				} catch (Exception exception) {
					throw new BadRequestException(
							"The properties provided to the TYPE header are not valid.");
				}
				break;
			default: // ignore attributes that aren't implemented; they could be
						// supported as extensions
				break;
			}
		}

		return results;
	}
}
