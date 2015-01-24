package com.cp372.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cp372.server.exceptions.InvalidVerticesException;
import com.cp372.server.models.Point;
import com.cp372.server.models.Quadrilateral;
import com.cp372.server.models.Shape;
import com.cp372.server.models.Triangle;
	
public class ShapeFactory {

	/**
	 * Given a list of points, generates the according shape required.
	 * @param points
	 * @throws InvalidVerticesException 
	 */
	public static Shape createShape(List<Point> points) throws InvalidVerticesException {		
		
		// Remove duplicates silently before putting into the factory
		points = getPointsWithDuplicatesRemoved(points);
		
		if(points.size() == 3) {
			return new Triangle(points);
		}
		else if(points.size() == 4) {
			return new Quadrilateral(points);
		}
		else {
			throw new InvalidVerticesException(points);
		}		
	}
	
	/**
	 * Using a set, converts all 
	 * @param points
	 * @return
	 */
	private static List<Point> getPointsWithDuplicatesRemoved(List<Point> points) {
			return new ArrayList<Point>(new HashSet<Point>(points));
	}
	
}
