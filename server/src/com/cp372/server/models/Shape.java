package com.cp372.server.models;

import java.util.List;

/**
 * An abstract collection of points that represent a shape in their totality. 
 * 
 * @author Vaughan Hilts
 *
 */
public abstract class Shape {

	// A list of vertices mapped in memory
	protected List<Point> _vertices;

	public Shape(List<Point> vertices) {

		// We should sort out list of points before actually doing anything with
		// them
		// List<Point> sorted = getSortedClockwiseVertices(vertices);
		_vertices = vertices;
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


	public String toString() {
		String stuff = "";
		for (Point vertex : _vertices) {
			stuff += vertex;
		}

		return stuff;
	}

	@Override
	public boolean equals(Object other) {

		Shape otherShape = (Shape) other;

		if (otherShape == null)
			return false;

		if (this._vertices.size() != otherShape._vertices.size())
			return false;

		for (Point p : this._vertices) {
			boolean found = false;
			for (Point q : otherShape._vertices) {
				if (p.equals(q))
					found = true;
			}

			if (!found)
				return false;
		}

		return true;
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
