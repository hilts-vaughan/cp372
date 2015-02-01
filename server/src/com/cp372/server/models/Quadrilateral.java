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
		
		//TODO: Implement the actual flags...
		
	}
	
	public Quadrilateral(List<Point> vertices) {
		super(vertices);
		
		setFlags();
	}

	
	
}
