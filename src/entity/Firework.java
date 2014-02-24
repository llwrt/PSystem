package entity;

import java.awt.Color;
import java.util.ArrayList;

import math.GravityVector;
import math.RandomGenerator;
import math.Vector2D;
import math.ZeroVector;

// TODO: this class, abstract away emitters

public class Firework extends Emitter{

	public Firework(Vector2D location, ArrayList<Particle> particles, ArrayList<Emitter> emitters) {
		super(null, particles, emitters);
		
		// the nextDouble()*2 is used to get a random double between 0-2 to vary the x and y base speeds of 1, -16
		double x_vel_spread = (RandomGenerator.nextDouble() * 2) - 1; // angle of firework
		double y_vel_spread = (RandomGenerator.nextDouble() * 4) + -17; // y velocity of firework (has to be negative to go up)
		Vector2D firework_velocity = new Vector2D(x_vel_spread, y_vel_spread);
		this.mover = new Movable(new Vector2D(location), firework_velocity , new GravityVector());
	}

	private int fuse = RandomGenerator.nextInt(3); // explodes fireworks at different pinnacles
	
	public void update(int millis_since_last_update){
		super.update(millis_since_last_update);
		
		if((mover.velocity.y > fuse) && !dead){
			spawn(600);
			//spawnEmitters(5);
			dead = true;
		}
	}
	
	public void spawn(int number){
		Movable m;
		Color c = RandomGenerator.rainbowColor();//RandomGenerator.redWhiteOrBlue();
		for(int i=0; i<number; i++){
			m = new Movable(new Vector2D(mover.location), rg.randomVector(0, 30), new ZeroVector());
			Particles.add(new FireworkParticle(m, c));
		}
	}
	
}
