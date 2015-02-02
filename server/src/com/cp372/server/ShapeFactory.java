package com.cp372.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cp372.server.exceptions.BadRequestException;
import com.cp372.server.exceptions.InvalidVerticesException;
import com.cp372.server.models.Point;
import com.cp372.server.models.Quadrilateral;
import com.cp372.server.models.Shape;
import com.cp372.server.models.Triangle;

public class ShapeFactory {

	/**
	 * Given a list of points, generates the according shape required.
	 * 
	 * @param points
	 * @throws InvalidVerticesException
	 * @throws BadRequestException
	 */
	public static Shape createShape(List<Point> points)
			throws InvalidVerticesException, BadRequestException {

		// Remove duplicates silently before putting into the factory
		points = getPointsWithDuplicatesRemoved(points);

		int pointCountBeforeJarvis = points.size();
		List<Point> jarvisPoints;

		try {
			jarvisPoints = getSortedClockwiseVertices(points);
		} catch (Exception exception) {
			throw new BadRequestException(
					"The point winding order could not be determined. Are your points co-linear?");
		}

		int pointCountAfterJarvis = jarvisPoints.size();
		boolean concave = false;

		if (pointCountBeforeJarvis != pointCountAfterJarvis) {

			// If we ended up being concave,
			jarvisPoints = points;
			concave = true;
		}

		if (points.size() == 3) {
			return new Triangle(jarvisPoints);
		} else if (points.size() == 4) {

			// If we ended up here, Jarvis failed us and we're probavly concave.
			Quadrilateral q = new Quadrilateral(jarvisPoints, concave);
			return q;
		} else {
			throw new InvalidVerticesException(points);
		}
	}

	/**
	 * Using a set, converts all
	 * 
	 * @param points
	 * @return
	 */
	private static List<Point> getPointsWithDuplicatesRemoved(List<Point> points) {
		return new ArrayList<Point>(new HashSet<Point>(points));
	}

	/**
	 * Given a set of points, sorts them in clockwise order using the Jarvis
	 * algorithim.
	 * 
	 * @param vertices
	 *            The set of points to be sorted
	 * @return
	 */
	private static List<Point> getSortedClockwiseVertices(List<Point> vertices) {

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
						|| (getOrientation(vPointOnHull, vEndpoint,
								vertices.get(i)) == -1)) {
					vEndpoint = vertices.get(i);
				}
			}

			vPointOnHull = vEndpoint;

		} while (vEndpoint != hull.get(0));

		return hull;
	}

	private static int getOrientation(Point p1, Point p2, Point p) {
		// Determinant
		int orientation = ((p2.getX() - p1.getX()) * (p.getY() - p1.getY()) - (p
				.getX() - p1.getX()) * (p2.getY() - p1.getY()));

		if (orientation > 0)
			return -1;
		if (orientation < 0)
			return 1;

		return 0;
	}

}
