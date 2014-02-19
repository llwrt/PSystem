package entity;

import imaging.Screen;

import java.awt.Color;

import math.RandomGenerator;

public class FireworkParticle extends Particle{

	public FireworkParticle(Movable m, Color c) {
		super(new Movable(m), RandomGenerator.nextInt(100), c, RandomGenerator.nextInt(2)+1);
	}
	
	public FireworkParticle(Movable m) {
		this(m, RandomGenerator.redWhiteOrBlue());
	}

	public void update(int milliseconds_since_last_update, Screen s){
		//this.color = color.darker();
		super.update(milliseconds_since_last_update, s);
	}
	
}
