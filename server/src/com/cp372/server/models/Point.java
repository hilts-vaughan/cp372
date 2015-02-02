package com.cp372.server.models;

/**
 * A basic 2D point that allows the representation of a single vertex in 2D
 * space. Contains simple methods to get the cordinates. This object is
 * immutable.
 * 
 * @author Vaughan Hilts, Brandon Smith
 * 
 */
public class Point {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return ("(" + x + "," + y + ")");
	}

	@Override
	public boolean equals(Object other) {

		Point otherPoint = (Point) other;

		if (otherPoint == null)
			return false;

		if (otherPoint.x == this.x && otherPoint.y == this.y)
			return true;
		else
			return false;
	}

	/**
	 * Computes the distance between this point and another point.
	 * 
	 * @param point
	 *            The other point to compare to
	 * @return The distance between the two points
	 */
	public double distanceFrom(Point point) {

		double a = (this.getX() - point.getX());
		a = Math.pow(a, 2);

		double b = (this.getY() - point.getY());
		b = Math.pow(b, 2);

		return Math.sqrt(a + b);

	}

}
