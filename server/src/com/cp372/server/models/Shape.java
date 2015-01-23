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

		// We should sort out list of points before actually doing anything with them
		List<Point> sorted = getSortedClockwiseVertices(vertices);
		_vertices = sorted;
	}
	
	public void printPoints() {
		for(Point vertex : _vertices) {
			System.out.println(vertex.toString());
		}
	}

	private List<Point> getSortedClockwiseVertices(List<Point> vertices) {

		Point center = getCentroidPoint(vertices);
		List<Tuple> weights = new ArrayList<Tuple>();

		for (int i = 0; i < vertices.size(); i++) {
			Point vertex = vertices.get(i);
			double angle = Math.atan2(vertex.getY() - center.getY(),
					vertex.getX() - center.getX());

			weights.add(new Tuple(angle, vertex));
		}

		Comparator<Tuple> comparator = new Comparator<Tuple>() {
			public int compare(Tuple tupleA, Tuple tupleB) {
				return tupleA.x.compareTo(tupleB.x);
			}
		};

		// Sort the points going into a standard order
		Collections.sort(weights, comparator);

		List<Point> sortedVertices = new ArrayList<Point>();	
		for(int i = 0; i < weights.size(); i++) {
			sortedVertices.add(weights.get(i).y);
		}
		
		return sortedVertices;
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

		int x = 0;
		int y = 0;
		int k = 0;

		for (Point p : vertices) {
			x += p.getX();
			y += p.getY();
			k++;
		}

		x /= k;
		y /= k;

		return new Point(x, y);
	}

}
