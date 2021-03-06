package entity;

import imaging.Screen;
import math.RandomGenerator;
import math.Vector2D;

public class RainEmitter extends Particle{

	private ParticleSystem PS;

	public RainEmitter(ParticleSystem ps) {
		super(new Movable(ZeroVector, ZeroVector, ZeroVector)); // water emitters don't move, just spit rain
		this.PS = ps;
	}

	public void update(int millis_since_last_update, Screen s){
		super.update(millis_since_last_update, s);
		if(!dead){ // moving slightly down when y-velocity is 1
			makeRaindrop(3);
			//dead = true;
		}
	}

	public void makeRaindrop(int number){
		Movable tmp_m;
		for(int i=0; i<number; i++){
			tmp_m = new Movable(new Vector2D(mover.location), RandomGenerator.raindropVector(), GravityVector);
			PS.spawnRaindrop(tmp_m);
		}
	}
	
}
