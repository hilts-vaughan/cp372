package client;

import java.util.ArrayList;
import java.util.List;



public class Shape {
	
	String _properties = "";
	private List<Point> _vertices = new ArrayList<Point>();
	int _count;
	
	// Unpack our string and extract our data string
	public Shape(String packedShapeString) {
		
		String[] sequences = packedShapeString.split(":");
		
		String[] pointPieces = sequences[0].split(",");
		
		// Convert pieces here
		for(String pointPiece : pointPieces) {
			
			int x = Integer.parseInt(pointPiece.trim());
			int y = Integer.parseInt(pointPiece.trim());
			
			_vertices.add(new Point(x, y));
		}
		
		this._properties = sequences[1];
		this._count = Integer.parseInt(sequences[2].replace("\r", "").replace("\n", ""));
		
	}
	

	public String toString() {
		int vertexCount = this._vertices.size();
		String name = vertexCount == 3 ? "Triangle" : "Quadrilateral";
		
		return name + " with points " + this._vertices.toString() + "";
	}
	
	
	
}


