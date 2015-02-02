package com.cp372.server;

import java.util.ArrayList;
import java.util.List;

import com.cp372.server.models.Shape;

/**
 * A basic storage singelton for managing shapes and their data.
 * 
 * @author Vaughan Hilts
 *
 */
public class ShapeStorage {
	private static ShapeStorage instance = null;

	protected ShapeStorage() {
	}

	public static ShapeStorage getInstance() {
		if (instance == null) {
			instance = new ShapeStorage();
		}
		return instance;
	}

	// Non-singelton specific implementation stuff
	private List<ShapeEntry> _shapes = new ArrayList<ShapeEntry>();

	public Iterable<ShapeEntry> getShapes() {
		return _shapes;
	}

	/**
	 * Given a shape and a number of occurences, finds a shape that has occured
	 * that many times exactly.
	 * 
	 * @param shape
	 *            The shape to search for
	 * @param occurences
	 *            The number of occurences to look for
	 * @return An iterable list of shapes that meet the criteria
	 */
	public Iterable<Shape> getShapeByOccurence(Shape shape, int occurences) {
		List<Shape> occuredShapes = new ArrayList<Shape>();

		for (ShapeEntry entry : _shapes) {
			if (entry.getCount() == occurences)
				occuredShapes.add(entry.getShape());
		}

		return occuredShapes;
	}

	/**
	 * Performs an insertion of a shape if cannot be found -- otherwise,
	 * increment the counter
	 * 
	 * @param shape
	 */
	public synchronized void insertOrUpdateShape(Shape shape) {

		for (ShapeEntry entry : _shapes) {
			if (entry.getShape().equals(shape)) {
				entry.incrementCount();
				System.out.println("A shape already existed; incrementing counter.");
				return;
			}
		}

		System.out.println("A new shape was detected. Inserting into storage.");
		
		// Otherwise, we couldn't find it so we'll insert
		_shapes.add(new ShapeEntry(shape));
	}

}