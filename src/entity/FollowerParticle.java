package entity;

import java.awt.Color;

import imaging.Screen;
import math.RandomGenerator;
import math.Vector2D;

public class FollowerParticle extends Particle {
	
	ParticleSystem PS;
	double max_acceleration = 3;
	double max_velocity = 18;
	double spread = 2;

	public FollowerParticle(Vector2D location, ParticleSystem ps) {
		super(new Movable(location, RandomGenerator.randomVector(40), new Vector2D(0, 0)));
		this.color = new Color(0xFF80451B);
		this.PS = ps;
		this.kill_offscreen = false;
		this.BouncesOffWalls = true;
	}
	
	public void update(int milliseconds_since_last_update, Screen s){
		//System.out.println("follower: " + mover);
		this.mover.acceleration = new Vector2D(mover.location, PS.getMouse(), max_acceleration);
		super.update(milliseconds_since_last_update, s);
		// slow it down if it is moving super fast
		this.mover.velocity.limit(max_velocity);
		
		// add a little variation in the speeds to make them spread out a little
		this.mover.velocity.x += RandomGenerator.nextDouble() * 2*spread - spread;
		this.mover.velocity.y += RandomGenerator.nextDouble() * 2*spread - spread;
		this.mover.acceleration.x += RandomGenerator.nextDouble() * 2*spread - spread;
		this.mover.acceleration.y += RandomGenerator.nextDouble() * 2*spread - spread;

	}

}
