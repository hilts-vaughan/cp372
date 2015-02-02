package com.cp372.server.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cp372.server.Tuple;

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
		for(int i = 0; i < vertices.size(); i++) {
			Point x = vertices.get(i);
			
			if(x.getX() < vPointOnHull.getX()) {
				vPointOnHull = x;
			}
			
			else if(x.getX() == vPointOnHull.getX()) {
				if(x.getY() < vPointOnHull.getY()) {
					vPointOnHull = x;
				}
			}
				
		}
		
		
		Point vEndpoint;

		do {

			hull.add(vPointOnHull);
			vEndpoint = vertices.get(0);
			
			for(int i = 1; i < vertices.size(); i++) {
				
				if ((vPointOnHull == vEndpoint)
                        || (orientation(vPointOnHull, vEndpoint, vertices.get(i)) == -1))
                    {
                        vEndpoint = vertices.get(i);
                    }
			}
			
            vPointOnHull = vEndpoint;

			
			
		} while (vEndpoint != hull.get(0));

		// return;

		// Point center = getCentroidPoint(vertices);
		// List<Tuple> weights = new ArrayList<Tuple>();
		//
		// for (int i = 0; i < vertices.size(); i++) {
		// Point vertex = vertices.get(i);
		// double angle = Math.atan2(vertex.getY() - center.getY(),
		// vertex.getX() - center.getX());
		//
		// weights.add(new Tuple(Math.toDegrees(angle), vertex));
		// }
		//
		// Comparator<Tuple> comparator = new Comparator<Tuple>() {
		// public int compare(Tuple tupleA, Tuple tupleB) {
		//
		// if(tupleA.x < tupleB.x)
		// return 1;
		// else if(tupleB.x < tupleA.x)
		// return -1;
		//
		// // It must be a tie if we're here...
		// if(tupleA.y.getX() > tupleB.y.getX())
		// return 1;
		// else
		// return -1;
		// }
		// };
		//
		// // Sort the points going into a standard order
		// Collections.sort(weights, comparator);
		//
		// List<Point> sortedVertices = new ArrayList<Point>();
		// for(int i = 0; i < weights.size(); i++) {
		// sortedVertices.add(weights.get(i).y);
		// }
		//
		// return sortedVertices;

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

	/**
	 * Computes the centroid of a set of vertices given in the parameters set.
	 * This centroid implementation has a small bias drift
	 * 
	 * See: http://en.wikipedia.org/wiki/Centroid#Of_a_finite_set_of_points
	 * 
	 * @param vertices
	 *            The set of vertices to compute the centroid point for
	 * @return
	 */
	private Point getCentroidPoint(Iterable<Point> vertices) {

		double x = 0;
		double y = 0;
		int k = 0;

		for (Point p : vertices) {
			x += p.getX();
			y += p.getY();
			k++;
		}

		x /= k;
		y /= k;

		return new Point(0, 0);
	}

	protected abstract String getPackedProperties();

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
