package entity;
import imaging.Screen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import math.Vector2D;
import entity.Emitter.Type;


public class ParticleSystem {
	
	private static BufferedImage IMG; // Don't edit this
	private static Screen screen;
	private ArrayList<Particle> Particles = new ArrayList<Particle>();
	private ArrayList<Emitter> Emitters = new ArrayList<Emitter>();
	Random r = new Random();
	Vector2D gravity = new Vector2D(0, .1);
	public Particle.Type type = Particle.Type.Firework;
	private boolean paused = false;

	public ParticleSystem(Screen s) {
		screen = s;
		IMG = new BufferedImage(screen.WIDTH, screen.HEIGHT, 1);
	}
	
	public synchronized void update(int milliseconds_since_last_update){
		if (paused) return;
		//System.out.println("particles: " + Particles.size() + " emitters:" + Emitters.size());
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
		// particles get to decide when they are dead or not. just clean up if they mark themselves.
		for(int i=0; i<Emitters.size(); i++)
			if(Emitters.get(i).isDead())
				Emitters.remove(i);
	}

	private synchronized void removeDeadParticles() {
		// particles get to decide when they are dead or not.  just clean up if they mark themselves.
		for(int i=0; i<Particles.size(); i++)
			if(Particles.get(i).isDead())
				Particles.remove(i);
	}

	public void render(Graphics2D g) {
		screen.drawOntoImage(IMG);
		g.drawImage(IMG , 0, 0, screen.WIDTH, screen.HEIGHT, null);
		//screen.dim(); // or screen.clear() if you don't want motion blur
		if(!paused){
			screen.dim();
		}
	}

	public synchronized void spawnEmitter(Emitter.Type type, Vector2D location) {
		if(type == Emitter.Type.Firework){
			Emitters.add(new Firework(new Vector2D(location), Particles, Emitters));
		}
	}

	public synchronized void clear() {
		// get rid of all particles, emitters, etc
		Particles = new ArrayList<Particle>();
		Emitters = new ArrayList<Emitter>();
	}

	public synchronized void spawn(Vector2D location) {
		if(type == Particle.Type.Firework){
			Emitters.add(new Firework(location, Particles, Emitters));
		}
	}

	public void togglePause() {
		this.paused = !paused;
	}

	

}
