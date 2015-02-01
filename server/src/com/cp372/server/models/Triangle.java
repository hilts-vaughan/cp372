package com.cp372.server.models;

import java.util.List;
import java.util.EnumSet;

public class Triangle extends Shape {

	private EnumSet<TriangleProperty> _properties = EnumSet
			.noneOf(TriangleProperty.class);

	public enum TriangleProperty {
		SCALENE, EQUILATERAL, ISOSCELES, ACUTE, RIGHT, OBTUSE
	};

	/**
	 * Verifies whether or not a property has a given flag.
	 * 
	 * @param property
	 * @return
	 */
	public boolean hasProperty(TriangleProperty property) {
		return _properties.contains(property);
	}

	/**
	 * Adds a property to the list of properties on this triangle.
	 * 
	 * @param property
	 */
	private void addProperty(TriangleProperty property) {
		_properties.add(property);
	}

	private void createFlags() {

		Point p1 = this._vertices.get(0);
		Point p2 = this._vertices.get(1);
		Point p3 = this._vertices.get(2);

		double a = p1.distanceFrom(p2);
		double b = p2.distanceFrom(p3);
		double c = p3.distanceFrom(p1);

		double angleA = 0;
		double angleB = 0;
		double angleC = 0;

		angleC = Math.acos((Math.pow(c, 2) - Math.pow(a, 2) - Math.pow(b, 2))
				/ (-2 * a * b));
		double cRatio = c / Math.sin(angleC);

		angleB = Math.asin(b / cRatio);

		// To degrees
		angleC = Math.toDegrees(angleC);
		angleB = Math.toDegrees(angleB);

		// Normalize due to floating math fail...
		angleC = Math.round(angleC);
		angleB = Math.round(angleB);

		// Triangles add up to 100 degrees
		angleA = 180 - angleC - angleB;
		angleA = Math.round(angleA);

		// Get our integer angles now to do our math as approximations
		int intAngleA = (int) angleA;
		int intAngleB = (int) angleB;
		int intAngleC = (int) angleC;

		int intA = (int) Math.round(a);
		int intB = (int) Math.round(b);
		int intC = (int) Math.round(c);

		// Check if it's a right triangle
		if (intAngleA == 90 || intAngleB == 90 || intAngleC == 90) {
			this.addProperty(TriangleProperty.RIGHT);
		}

		// Test for acute
		if (intAngleA < 90 && intAngleB < 90 && intAngleC < 90) {
			this.addProperty(TriangleProperty.ACUTE);
		}

		// Test for obtuse
		if (intAngleA > 90 || intAngleB > 90 || intAngleC > 90) {
			this.addProperty(TriangleProperty.OBTUSE);
		}

		// Check for scalene
		if ((intA != intB && intA != intC && intC != intB)
				&& (intAngleA != intAngleB && intAngleA != intAngleC && intAngleC != intAngleB)) {
			this.addProperty(TriangleProperty.SCALENE);
		}

		// Check if we're equilateral
		else if ((intAngleA == 60 || intAngleB == 60 || intAngleC == 60)
				&& intA == intB && intB == intC && intC == intA) {
			this.addProperty(TriangleProperty.EQUILATERAL);
		} else {
			this.addProperty(TriangleProperty.ISOSCELES); // otherwise, isoceles
		}

		System.out.println(this._properties.toString());

	}

	public Triangle(List<Point> vertices) {
		super(vertices);

		createFlags();
	}

	@Override
	protected String getPackedProperties() {

		String packed = "";
		
		for(TriangleProperty property : this._properties) {
			packed += property.toString() + ",";
		}
		
		packed = packed.substring(0, packed.length() - 1);
		
		return packed;
	}

}
