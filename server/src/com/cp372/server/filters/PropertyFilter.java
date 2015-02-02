package com.cp372.server.filters;

import java.util.ArrayList;
import java.util.List;

import com.cp372.server.ShapeEntry;
import com.cp372.server.models.Point;

/**
 * Filters on enumeration types against the different shapes
 * @author Vaughan Hilts
 *
 */
public class PropertyFilter implements ShapeFilter {
	
	@Override
	public Iterable<ShapeEntry> filter(Iterable<ShapeEntry> shapes, Object context) {
		//TODO: Apply some filtering logic
		
		List<ShapeEntry> results = new ArrayList<ShapeEntry>();
		List<String> shares = (ArrayList<String>) context;

		// Filter out those that don't chare points
		for (ShapeEntry entry : shapes) {
			
			String p = entry.getShape().getPackedProperties();
			String[] properties = p.split(",");
			
			// Grab a list so we can use containsAll
			List<String> listProperties = new ArrayList<String>();
			for(String s : properties) {
				listProperties.add(s);
			}
			
			if (shares.containsAll(listProperties)) {
				results.add(entry);
			}
		}

		return results;
		
	}

}
