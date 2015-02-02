package com.cp372.server.filters;

import java.util.ArrayList;
import java.util.List;

import com.cp372.server.ShapeEntry;

/**
 * Filters by a specific amount of occurences that have occured
 * @author Vaughan Hilts
 *
 */
public class OccurenceFilter implements ShapeFilter {

	@Override
	public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes, Object context) {

		int occurencesRequired = (Integer) context;
		
		List<ShapeEntry> results = new ArrayList<ShapeEntry>();

		for (ShapeEntry entry : shapes) {
				if(entry.getCount() >= occurencesRequired)
					results.add(entry);
		}
		
		return results;
	}

}
