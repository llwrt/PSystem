package entity;
import imaging.Screen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import math.RandomGenerator;
import math.Vector2D;


public class ParticleSystem {
		
	// this is a buffer for emitters to add particles to the system while the system is updating.
	// once per update, the particles are moved from this buffer to the main Particles list.
	private ArrayList<Particle> PendingParticles = new ArrayList<Particle>();
	
	private ArrayList<Particle> Particles = new ArrayList<Particle>();
	
	private LinkedList<String> Type;
	
	private Vector2D MousePos = new Vector2D(0, 0);
	

	public ParticleSystem() {
		Type = new LinkedList<String>(); 
		Type.add("Firework");
		Type.add("Water");
		Type.add("Rain");
		Type.add("Snow");
		Type.add("Followers");
		Type.add("Repellers");
	}
	
	public synchronized void update(int milliseconds_since_last_update, Screen s){
		for(Particle p : Particles){
			p.update(milliseconds_since_last_update, s);
		}
		removeDeadParticles();
		
		// pending particles are particles that are added during this update method by emitters
		for(Particle p : PendingParticles)
			Particles.add(p);
		PendingParticles.clear();
	}


	private synchronized void removeDeadParticles() {
		// particles get to decide when they are dead or not.  just clean up if they mark themselves.
		for(int i=0; i<Particles.size(); i++)
			if(Particles.get(i).isDead())
				Particles.remove(i);
	}

	public synchronized void render(Screen s) {
		for(Particle p : Particles)
			p.draw(s);
	}

	public synchronized void clear() {
		Particles.clear();
		PendingParticles.clear();
	}

	public void spawnFirework(Vector2D location) {
		int spawnCount = 3; // number of fireworks per click
		for(int i=0; i<spawnCount; i++)
			PendingParticles.add(new Firework(new Vector2D(location), this));
	}

	public void spawnFireworkParticle(Movable m, Color color) {
		// one single ray of light that eminates from a firework. Fireworks spawn hundreds of these
		PendingParticles.add(new Particle(m, RandomGenerator.nextInt(20), color));
	}

	public void spawnParticle(Vector2D location){
		if(Type.peek() == "Firework"){
			spawnFirework(location);
		} else if(Type.peek() == "Water"){
			spawnWater(location);
		} else if(Type.peek() == "Followers"){
			spawnFollowers(location);
		} else if(Type.peek() == "Rain"){
			spawnRain(location);
		} 
	}
	
	
	
	private void spawnRain(Vector2D location) {
		
	}

	private synchronized void spawnFollowers(Vector2D location) {
		for(int i=0; i<300; i++) // each click spawns 3 followers
			PendingParticles.add(new FollowerParticle(location, this));
	}

	private synchronized void spawnWater(Vector2D location) {
		PendingParticles.add(new WaterEmitter(location, this));
	}
	
	public synchronized void spawnRaindrop(Movable m){
		PendingParticles.add(new WaterDrop(m));
	}

	public synchronized void changeParticle() {
		Type.addLast(Type.pop()); // move current particle type to back of queue
		clear(); // remove all particles on screen when changing types
	}

	public String getParticleType(){
		return Type.peek();
	}

	public void setMouse(int x, int y) {
		MousePos.x = x;
		MousePos.y = y;
	}
	
	public Vector2D getMouse(){
		return new Vector2D(MousePos);
	}
	
}
