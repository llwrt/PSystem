package entity;

import imaging.Screen;

import java.awt.Color;

import math.RandomGenerator;
import math.Vector2D;

public class Firework extends Particle{

	private ParticleSystem PS;

	public Firework(Vector2D location, ParticleSystem ps) {
		super(getFireworkMoveable(location));
		this.PS = ps;
		this.BouncesOffWalls = true;
	}

	private static Movable getFireworkMoveable(Vector2D location) {
		// the nextDouble()*2 is used to get a random double between 0-2 to vary the x and y base speeds of 1, -16
		double x_vel_spread = (RandomGenerator.nextDouble() * 2) - 1; // angle of firework
		double y_vel_spread = (RandomGenerator.nextDouble() * 4) + -17; // y velocity of firework (has to be negative to go up)
		Vector2D firework_velocity = new Vector2D(x_vel_spread, y_vel_spread);
		return new Movable(new Vector2D(location), firework_velocity , GravityVector);
	}
	
	public void update(int millis_since_last_update, Screen s){
		super.update(millis_since_last_update, s);
		if((mover.velocity.y > 1) && !dead){ // moving slightly down when y-velocity is 1
			spawn(300);
			dead = true;
		}
	}
	
	public void spawn(int number){
		Movable tmp_m;
		Color tmp_c = RandomGenerator.rainbowColor();
		for(int i=0; i<number; i++){
			tmp_m = new Movable(new Vector2D(mover.location), RandomGenerator.randomVector(0, 30), ZeroVector);
			PS.spawnFireworkParticle(tmp_m, tmp_c);
		}
	}
	
	public void draw(Screen screen) {
		if(screen.onScreen(this.mover.location) && screen.onScreen(previousloc)){
			screen.drawline(
					(int)mover.location.x, 
					(int)mover.location.y, 
					(int)previousloc.x, 
					(int)previousloc.y, 
					0xFFFFFFFF);
		}
	}
	
}
