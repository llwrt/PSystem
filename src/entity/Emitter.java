package entity;

import imaging.Screen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import math.GravityVector;
import math.RandomGenerator;
import math.Vector2D;
import math.ZeroVector;

public class Emitter {
	
	RandomGenerator rg = new RandomGenerator();
	ArrayList<Particle> Particles;
	ArrayList<Emitter> Emitters;
	Movable mover;
	Type t;
	boolean dead = false;
	
	public enum Type{
		Firework, water, fire, snow
	}
	
	public Emitter(Type type, Vector2D location, ArrayList<Particle> particles, ArrayList<Emitter> emitters) {
		this.Particles = particles;
		this.Emitters = emitters;
		this.t = type;
		
		// the nextDouble()*2 is used to get a random double between 0-2 to vary the x and y base speeds of 1, -16
		double x_vel_spread = (rg.nextDouble() * 2) - 1;
		double y_vel_spread = (rg.nextDouble() * 4) + -17;
		Vector2D firework_velocity = new Vector2D(x_vel_spread, y_vel_spread);
		this.mover = new Movable(new Vector2D(location), firework_velocity , new GravityVector());
	}
	
	public void spawn(int number){
		if(t == Type.Firework){
			spawnFireworkParticle(Particles, number);
		}
	}
	
	private void spawnFireworkParticle(ArrayList<Particle> Particles, int number){
		Movable m;
		Color c = RandomGenerator.redWhiteOrBlue();
		for(int i=0; i<number; i++){
			m = new Movable(new Vector2D(mover.location), rg.randomVector(10), new ZeroVector());
			Particles.add(new FireworkParticle(m, c));
		}
	}

	public void update(int milliseconds_since_last_update) {
		mover.update();
		if(mover.velocity.y > .8 && !dead){
			spawn(400);
			//spawnEmitters(5);
			dead = true;
		}
	}

	private void spawnEmitters(int n) {
		for(int i=0; i<n; i++)
			Emitters.add(new Emitter(Emitter.Type.Firework, new Vector2D(mover.location), Particles, Emitters));
	}

	public void draw(Screen screen) {
		if(screen.onScreen(mover.location)){
			screen.pixels[(int) mover.location.x][(int) mover.location.y] = Color.WHITE.getRGB();
		}
	}

	public boolean isDead() {
		return dead;
	}
	
}
