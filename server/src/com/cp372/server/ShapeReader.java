package com.cp372.server;

import java.util.List;

import com.cp372.server.models.Shape;
import com.cp372.server.models.Triangle;

/**
 * Responsible for reading the shapes from storage and responding to various
 * different queries. This provides an abstraction layer over top of the direct
 * storage.
 * 
 * @author Vaughan Hilts
 * 
 */
public class ShapeReader {

	public ShapeReader() {

	}

	public Iterable<Shape> processQuery(String query, List<String> params)
			throws Exception {

		switch (query) {

		case "ALL":
			return getAllShapes();
		default:
			throw new Exception("Unknown query");

		}

	}

	private Iterable<Shape> getAllShapes() {
		return null;
	}

}