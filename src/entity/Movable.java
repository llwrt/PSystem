package entity;

import math.Vector2D;

public class Movable {

	public Vector2D location, velocity, acceleration;
	
	public Movable(Vector2D loc, Vector2D vel, Vector2D acc){
		location = new Vector2D(loc);
		velocity = new Vector2D(vel);
		acceleration = new Vector2D(acc);
	}

	public Movable(Movable m) {
		location = m.location;
		velocity = m.velocity;
		acceleration = m.acceleration;
	}

	public void update() {
		//System.out.println("updating mover:" + this);
		velocity.add(acceleration);
		location.add(velocity);
		//System.out.println("updated:" + this);
	}
	
	public String toString(){
		return String.format("loc(%f,%f); vel(%f,%f); acc(%f,%f);", 
				location.x, location.y, 
				velocity.x, velocity.y, 
				acceleration.x, acceleration.y);
	}
	
}
