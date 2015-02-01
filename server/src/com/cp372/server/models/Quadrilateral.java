package com.cp372.server.models;

import java.util.EnumSet;
import java.util.List;

import com.cp372.server.models.Triangle.TriangleProperty;

public class Quadrilateral extends Shape {

	private EnumSet<QuadProperty > _properties = EnumSet
			.noneOf(QuadProperty .class);

	public enum QuadProperty {
		SQUARE,
		RECTANGLE,
		RHOMBUS,
		PARALLELOGRAM,
		TRAPEZOID
	};
	
	public boolean hasProperty(QuadProperty property) {
		return _properties.contains(property);
	}

	private void addProperty(QuadProperty property) {
		_properties.add(property);
	}
	
	
	private void setFlags() {

		Point p1 = this._vertices.get(0);
		Point p2 = this._vertices.get(1);
		Point p3 = this._vertices.get(2);
		Point p4 = this._vertices.get(3);
		
		double a = p1.distanceFrom(p2);
		double b = p2.distanceFrom(p3);
		double c = p3.distanceFrom(p4);
		double d = p4.distanceFrom(p1);

		double center1 = p1.distanceFrom(p3);
		double center2 = p2.distanceFrom(p4);

		
		
		double angleA = 0;
		double angleB = 0;
		double angleC = 0;
		double angleD = 0;

		System.out.println("------------");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(c);
		System.out.println(center2);
		System.out.println("------------");
		
		angleA= Math.acos((Math.pow(d, 2)+Math.pow(a, 2)-Math.pow(center2, 2))/(2*d*a));
		angleB= Math.acos((Math.pow(c, 2)+Math.pow(b, 2)-Math.pow(center1, 2))/(2*c*b));
		angleC= Math.acos((Math.pow(d, 2)+Math.pow(c, 2)-Math.pow(center2, 2))/(2*d*c));
		angleD= Math.acos((Math.pow(a, 2)+Math.pow(d, 2)-Math.pow(center1, 2))/(2*a*d));
		
		
				
		angleA = Math.toDegrees(angleA);
		angleB = Math.toDegrees(angleB);
		angleC = Math.toDegrees(angleC);
		angleD = Math.toDegrees(angleD);

		// Normalize due to floating math fail...
		angleA = Math.round(angleA);
		angleB = Math.round(angleB);
		angleC = Math.round(angleC);
		angleD = Math.round(angleD);

		System.out.println("------------");
		System.out.println(angleA);
		System.out.println(angleB);
		System.out.println(angleC);
		System.out.println(angleD);
		System.out.println("------------");
		//TODO: Implement the actual flags...
		
	}
	
	public Quadrilateral(List<Point> vertices) {
		super(vertices);
		
		setFlags();
	}

	@Override
	protected String getPackedProperties() {

		
		
		String packed = "";
		
		for(QuadProperty property : this._properties) {
			packed += property.toString() + ",";
		}
		
		//packed = packed.substring(0, packed.length() - 1);
		
		
		//TODO: Return packed
		return "";
		
	}

	
	
	
}
