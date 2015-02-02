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
		for(int i = 0; i < pointPieces.length; i+= 2) {
			
			String pointPiece = pointPieces[i];
			String pointPiece2 = pointPieces[i + 1];
			
			int x = Integer.parseInt(pointPiece.trim());
			int y = Integer.parseInt(pointPiece2.trim());
			
			_vertices.add(new Point(x, y));
		}
		
		this._properties = sequences[1];
		this._count = Integer.parseInt(sequences[2].replace("\r", "").replace("\n", ""));
		
	}
	

	public String toString() {
		String name = getType();
		
		return name + " with points " + this._vertices.toString() + "";
	}


	private String getType() {
		int vertexCount = this._vertices.size();
		String name = vertexCount == 3 ? "Triangle" : "Quadrilateral";
		return name;
	}
	
	public String getInfo() {
		String info = "Type: " + getType() + "\n";
		info += "Occurences: " + _count + "\n";
		info += "Properties: " + _properties + "\n";
		info += "Points: " + _vertices;
		
		return info;
	}
	
	
	
}

