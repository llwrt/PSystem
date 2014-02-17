package entity;

import java.util.ArrayList;

import math.Vector2D;

// TODO: this class, abstract away emitters

public class FireworkEmitter extends Emitter{

	public FireworkEmitter(Type type, Vector2D location, ArrayList<Particle> particles, ArrayList<Emitter> emitters) {
		super(type, location, particles, emitters);
		
	}

	
	
}
