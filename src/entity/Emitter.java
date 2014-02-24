package entity;

import imaging.Screen;

import java.awt.Color;
import java.util.ArrayList;

import math.RandomGenerator;

public class Emitter{
	
	RandomGenerator rg = new RandomGenerator();
	ArrayList<Particle> Particles;
	ArrayList<Emitter> Emitters;
	Movable mover;
	Type t;
	boolean dead = false;
	
	public enum Type{
		Firework, Water, Fire, Snow, Mouse
	}
	
	public Emitter(Movable m, ArrayList<Particle> particles, ArrayList<Emitter> emitters) {
		this.Particles = particles;
		this.Emitters = emitters;
		this.mover = m;
	}
	
	public void spawn(int number){
	}

	public void update(int milliseconds_since_last_update) {
		mover.update();
	}
/*
	private void spawnEmitters(int n) {
		for(int i=0; i<n; i++)
			Emitters.add(new Emitter(Emitter.Type.Firework, new Vector2D(mover.location), Particles, Emitters));
	}*/

	public void draw(Screen screen) {
		if(screen.onScreen(mover.location)){
			screen.pixels[(int) mover.location.x][(int) mover.location.y] = Color.WHITE.getRGB();
		}
	}

	public boolean isDead() {
		return dead;
	}
	
	public String toString(){
		return String.format("type:%s dead:%b", t, dead);
	}
	
}
