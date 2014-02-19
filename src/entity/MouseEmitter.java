package entity;

import java.util.ArrayList;

import math.RandomGenerator;
import math.Vector2D;
import math.ZeroVector;

// emits a small number of particles to trail the mouse
public class MouseEmitter extends Emitter{

	public MouseEmitter(Type type, Vector2D location, ArrayList<Particle> particles, ArrayList<Emitter> emitters) {
		super(type, null, particles, emitters);
		this.mover = new Movable(new Vector2D(location), new ZeroVector(), new ZeroVector());
	}
	
	public void update(int millis_since_last_update){
		super.update(millis_since_last_update);
		if(RandomGenerator.nextInt(20) == 0)
			dead = true;
	}
	
}
