package com.cp372.server.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {

	// A list of vertices mapped in memory
	protected List<Point> _vertices;

	public Shape(List<Point> vertices) {

		// We should sort out list of points before actually doing anything with
		// them
		List<Point> sorted = getSortedClockwiseVertices(vertices);
		_vertices = sorted;
	}

	public void printPoints() {
		for (Point vertex : _vertices) {
			System.out.println(vertex.toString());
		}
	}

	/**
	 * Performs an equality test on the internal points stored to check if this
	 * shape shares the same points as those stored in the list.
	 * 
	 * @param points
	 *            The points to check against
	 * @return Returns true if the points are shared, false otherwise
	 */
	public boolean hasPoints(List<Point> points) {
		return this._vertices.containsAll(points);
	}

	private int orientation(Point p1, Point p2, Point p) {
		// Determinant
		int Orin = (int) ((int) (p2.getX() - p1.getX())
				* (p.getY() - p1.getY()) - (p.getX() - p1.getX())
				* (p2.getY() - p1.getY()));

		if (Orin > 0)
			return -1;
		if (Orin < 0)
			return 1;

		return 0;
	}

	private List<Point> getSortedClockwiseVertices(List<Point> vertices) {

		ArrayList<Point> hull = new ArrayList<Point>();

		Point vPointOnHull = vertices.get(0);

		// Get leftmost
		for (int i = 0; i < vertices.size(); i++) {
			Point x = vertices.get(i);

			if (x.getX() < vPointOnHull.getX()) {
				vPointOnHull = x;
			}

			else if (x.getX() == vPointOnHull.getX()) {
				if (x.getY() < vPointOnHull.getY()) {
					vPointOnHull = x;
				}
			}

		}

		Point vEndpoint;

		do {

			hull.add(vPointOnHull);
			vEndpoint = vertices.get(0);

			for (int i = 1; i < vertices.size(); i++) {

				if ((vPointOnHull == vEndpoint)
						|| (orientation(vPointOnHull, vEndpoint,
								vertices.get(i)) == -1)) {
					vEndpoint = vertices.get(i);
				}
			}

			vPointOnHull = vEndpoint;

		} while (vEndpoint != hull.get(0));

		return hull;
	}

	/**
	 * Returns true if the shape is convex, false otherwise.
	 */
	public boolean isConvex() {

		// TODO: Actually implement this...
		return false;
	}

	public String toString() {
		String stuff = "";
		for (Point vertex : _vertices) {
			stuff += vertex;
		}

		return stuff;
	}


	public abstract String getPackedProperties();

	public String getPackedString() {

		String packedPoints = "";

		// Extract our packed points
		for (Point p : this._vertices) {
			packedPoints += p.getX() + "," + p.getY() + ",";
		}

		// Get off the ending of the string
		packedPoints = packedPoints.substring(0, packedPoints.length() - 1);

		String packedProperties = getPackedProperties();

		return packedPoints + ":" + packedProperties;

	}

}
