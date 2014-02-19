package entity;

import java.util.ArrayList;

import math.GravityVector;
import math.RandomGenerator;
import math.Vector2D;

// TODO: this class, abstract away emitters

public class FireworkEmitter extends Emitter{

	public FireworkEmitter(Type type, Vector2D location, ArrayList<Particle> particles, ArrayList<Emitter> emitters) {
		super(type, null, particles, emitters);
		
		// the nextDouble()*2 is used to get a random double between 0-2 to vary the x and y base speeds of 1, -16
		double x_vel_spread = (RandomGenerator.nextDouble() * 2) - 1;
		double y_vel_spread = (RandomGenerator.nextDouble() * 4) + -17;
		Vector2D firework_velocity = new Vector2D(x_vel_spread, y_vel_spread);
		this.mover = new Movable(new Vector2D(location), firework_velocity , new GravityVector());
	}

	private int fuse = RandomGenerator.nextInt(3); // explodes fireworks at different pinnacles
	
	public void update(int millis_since_last_update){
		super.update(millis_since_last_update);
		
		if((mover.velocity.y > fuse) && !dead){
			spawn(400);
			//spawnEmitters(5);
			dead = true;
		}
	}
	
}
