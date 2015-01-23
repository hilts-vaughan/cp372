package com.cp372.server;

import java.util.List;

import com.cp372.server.exceptions.InvalidVerticesException;
import com.cp372.server.models.*;
	
public class ShapeFactory {

	/**
	 * Given a list of points, generates the according shape required.
	 * @param points
	 * @throws InvalidVerticesException 
	 */
	public static Shape createShape(List<Point> points) throws InvalidVerticesException {		
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
	
}
