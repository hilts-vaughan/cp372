package com.cp372.server;

import com.cp372.server.models.Shape;

/**
 * An entry inside of a storage that simply contains a simple map
 * 
 * @author Vaughan Hilts
 *
 */
public class ShapeEntry {

	private final Shape _shape;
	private int _count;

	public ShapeEntry(Shape _shape) {
		super();
		this._shape = _shape;
		this._count = 1;
	}

	public int getCount() {
		return _count;
	}

	public void incrementCount() {
		this._count++;
	}

	public Shape getShape() {
		return _shape;
	}

}
