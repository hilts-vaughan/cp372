package com.cp372.server.models;

import java.util.List;

public abstract class Shape {
	
	// A list of vertices mapped in memory
	protected List<Point> _vertices;
	
	public Shape(List<Point> vertices) {
		_vertices = vertices;
	}
	
}
