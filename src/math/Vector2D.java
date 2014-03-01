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
	
	private double magnitude(double _x, double _y){
		return Math.sqrt(_x*_x + _y*_y);
	}
	
	public void limit(double limit){
		if(magnitude(x, y) < limit) return;
		
		this.normalize(); // make the vector into a unit vector
		this.x *= limit; 
		this.y *= limit;
		// this method will still exceed the magnitude, need to use tan

	}
	
	private void normalize() {
		double magnitude = magnitude(x, y); //pythagorean theorem
		
		// wouldn't want to divid by zero, now.
		if(magnitude == 0){ 
			this.x = 0; 
			this.y = 0; 
			return;
		}
		
		this.x /= magnitude;
		this.y /= magnitude;
	}

	public void add(Vector2D v) {
		x = x + v.x;
		y = y + v.y;
	}
	
	public double dotProduct(Vector2D v){
		return x*v.x + y*v.y;
	}

	public Vector2D(Vector2D v){
		this.x = v.x;
		this.y = v.y;
	}
	
	public Vector2D(Vector2D location, Vector2D origin) {
		this(location.x, location.y, origin.x, origin.y);
	}
	
	public Vector2D(Vector2D location, Vector2D origin, double limit){
		// makes a vector that points from location to origin
		// that does not exceed value
		this(location, origin);
		limit(limit);
	}

	public String toString(){
		return String.format("x:%f y:%f", x, y);
	}
	
}
