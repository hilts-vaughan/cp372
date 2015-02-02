package com.cp372.server.models;

import java.util.EnumSet;
import java.util.List;

import com.cp372.server.models.Triangle.TriangleProperty;

public class Quadrilateral extends Shape {

	private EnumSet<QuadProperty > _properties = EnumSet
			.noneOf(QuadProperty .class);

	public enum QuadProperty {
		SQUARE,
		RECTANGLE,
		RHOMBUS,
		PARALLELOGRAM,
		TRAPEZOID
	};
	
	public boolean hasProperty(QuadProperty property) {
		return _properties.contains(property);
	}

	private void addProperty(QuadProperty property) {
		_properties.add(property);
	}
	
	
	private void setFlags() {

		Point p1 = this._vertices.get(0);
		Point p2 = this._vertices.get(1);
		Point p3 = this._vertices.get(2);
		Point p4 = this._vertices.get(3);
		
		double a = p1.distanceFrom(p2);
		double b = p2.distanceFrom(p3);
		double c = p3.distanceFrom(p4);
		double d = p4.distanceFrom(p1);

		double center1 = p1.distanceFrom(p3);
		double center2 = p2.distanceFrom(p4);

		
		
		double angleA = 0;
		double angleB = 0;
		double angleC = 0;
		double angleD = 0;
		
		angleA= Math.acos((Math.pow(d, 2)+Math.pow(a, 2)-Math.pow(center2, 2))/(2*d*a));
		angleB= Math.acos((Math.pow(a, 2)+Math.pow(b, 2)-Math.pow(center1, 2))/(2*a*b));
		angleC= Math.acos((Math.pow(b, 2)+Math.pow(c, 2)-Math.pow(center2, 2))/(2*b*c));
		angleD= Math.acos((Math.pow(c, 2)+Math.pow(d, 2)-Math.pow(center1, 2))/(2*c*d));
		
		
				
		angleA = Math.toDegrees(angleA);
		angleB = Math.toDegrees(angleB);
		angleC = Math.toDegrees(angleC);
		angleD = Math.toDegrees(angleD);
		
		// Normalize due to floating math fail...
		
		angleA = (int) Math.round(angleA);
		angleB = (int) Math.round(angleB);
		angleC = (int) Math.round(angleC);
		angleD = (int) Math.round(angleD);
		
		
		
		//TODO: Implement the actual flags...
			if(a==b && a==c && a==d && angleA == 90 && 90==angleB && angleC==90&& angleD==90){
				this.addProperty(QuadProperty.SQUARE);
			}
			if(a==c&&b==d&&angleA==90& angleB==90 && angleA == 90 && 90==angleB && angleC==90&& angleD==90){
				this.addProperty(QuadProperty.RECTANGLE);
			}
			if(a==c && b==d && angleA==angleC & angleB==angleD ){
				this.addProperty(QuadProperty.PARALLELOGRAM);
			}
			if(a==c && b==d && a==d && angleA==angleC & angleB==angleD ){
				this.addProperty(QuadProperty.RHOMBUS);
			}
			
			if((angleA+angleB==180&&angleC+angleD==180)||(angleB+angleC==180&&angleD+angleA==180))
			{
				this.addProperty(QuadProperty.TRAPEZOID);
			}
		
		
		
		System.out.println(this._properties);
		System.out.println("-----------");
		
	}
	
	public Quadrilateral(List<Point> vertices) {
		super(vertices);
		
		setFlags();
	}

	@Override
	protected String getPackedProperties() {

		
		
		String packed = "";
		
		for(QuadProperty property : this._properties) {
			packed += property.toString() + ",";
		}
		
		//packed = packed.substring(0, packed.length() - 1);
		
		
		//TODO: Return packed
		return "";
		
	}

	
	
	
}
