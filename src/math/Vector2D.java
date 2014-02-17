package math;

public class Vector2D {

	public double x, y;
	
	// create a vector from 0,0 to given point
	public Vector2D(double _x, double _y) {
		this.x = _x;
		this.y = _y;
	}

	// create a vector from that goes between two points
	public Vector2D(double x1, double y1, double x2, double y2){
		this(x2-x1, y2-y1);
	}
	
	public double getMagnitude() {
		return Math.sqrt(x*x + y*y); //pythagorean theorem
	}

	public void add(Vector2D v) {
		x = x + v.x;
		y = y + v.y;
	}
	
	public void dot(Vector2D v){
	}
	
	public void cross(Vector2D v){
	}

	public Vector2D(Vector2D v){
		this.x = v.x;
		this.y = v.y;
	}

	public String toString(){
		return String.format("x:%f y:%f", x, y);
	}
	
}
