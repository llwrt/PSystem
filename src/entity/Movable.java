package entity;

import math.Vector2D;

public class Movable {

	public Vector2D location, velocity, acceleration;
	
	public Movable(Vector2D loc, Vector2D vel, Vector2D acc){
		location = loc;
		velocity = vel;
		acceleration = acc;
	}

	public Movable(Movable m) {
		location = m.location;
		velocity = m.velocity;
		acceleration = m.acceleration;
	}

	public void update() {
		location.add(velocity);
		velocity.add(acceleration);
	}
	
	public String toString(){
		return String.format("loc(%f,%f); vel(%f,%f); acc(%f,%f);\n", 
				location.x, location.y, 
				velocity.x, velocity.y, 
				acceleration.x, acceleration.y);
	}
	
}
