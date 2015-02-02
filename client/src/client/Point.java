package client;

/**
 * A basic 2D point that allows the representation of a single vertex in 2D
 * space. Contains simple methods to get the cordinates. This object is
 * immutable.
 * 
 * @author Vaughan Hilts & Brandon Smith
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

}