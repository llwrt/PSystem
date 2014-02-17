package entity;
import imaging.Screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import math.Vector2D;


public class ParticleSystem {
	
	private static BufferedImage IMG; // Don't edit this
	private static Screen screen;
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private ArrayList<Particle> Particles = new ArrayList<Particle>();
	private ArrayList<Emitter> Emitters = new ArrayList<Emitter>();
	Random r = new Random();
	Vector2D gravity = new Vector2D(0, .1);

	public ParticleSystem(Screen s) {
		screen = s;
		IMG = new BufferedImage(screen.WIDTH, screen.HEIGHT, 1);
	}
	
	public synchronized void update(int milliseconds_since_last_update){
		//System.out.println("current particles: " + Particles.size());
		for(Particle p : Particles){
			p.update(milliseconds_since_last_update, screen);
			p.draw(screen);
		}
		for(Emitter e : Emitters){
			e.update(milliseconds_since_last_update);
			e.draw(screen);
		}
		removeDeadParticles();
		removeDeadEmitters();
	}

	private synchronized void removeDeadEmitters() {
		for(int i=0; i<Emitters.size(); i++)
			if(Emitters.get(i).isDead() || !screen.onScreen(Emitters.get(i).mover.location))
				Emitters.remove(i);
	}

	private synchronized void removeDeadParticles() {
		for(int i=0; i<Particles.size(); i++)
			if(Particles.get(i).isDead() )//|| !screen.onScreen(Particles.get(i).mover.location))
				Particles.remove(i);
	}

	public void render(Graphics2D g) {
		screen.drawOntoImage(IMG);
		g.drawImage(IMG , 0, 0, screen.WIDTH, screen.HEIGHT, null);
		//screen.clear(0x000000); // black
		screen.dim();
	}

	public synchronized void spawnEmitter(Emitter.Type type, Vector2D location, int n) {
		for(int i=0; i<n; i++)
			Emitters.add(new Emitter(type, new Vector2D(location), Particles, Emitters));
	}

	

}
