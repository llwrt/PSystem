package entity;
import imaging.Screen;

import java.awt.Color;
import java.util.Random;

import math.Vector2D;

public class Particle {
	
	protected Vector2D origin, previousloc;
	protected Movable mover;
	private int life;
	Color color;
	public boolean dead = false;
	protected boolean kill_offscreen = true;
	Random r = new Random();
	protected boolean BouncesOffWalls = false;
	public static Vector2D ZeroVector = new Vector2D(0, 0);
	public static Vector2D GravityVector = new Vector2D(0, .3);

	public Particle(Movable m, int _life, Color _color){
		mover = m;
		life = _life;
		color = _color;
		origin = new Vector2D(m.location);
		previousloc = origin;
	}
	
	public Particle(Movable m){
		this(m, Integer.MAX_VALUE, Color.WHITE); // assumes longest life, color is white
	}

	private void bounce(Screen s){
		if(mover.location.x <0){
			mover.velocity.x = Math.abs(mover.velocity.x);
		}
		if(mover.location.x >= s.WIDTH){
			mover.velocity.x = Math.abs(mover.velocity.x) * -1;
		}
		if(mover.location.y <0){
			mover.velocity.y = Math.abs(mover.velocity.y);
		}
		if(mover.location.y >= s.HEIGHT){
			mover.velocity.y = Math.abs(mover.velocity.y) * -1;
		}
	}
	
	private boolean onScreen(Screen s){
		// when particles bounce off the walls, they temporarily go offscreen
		// and then their direction changes back toward the screen
		// so we need to prevent them from dying before they get a chance to bounce
		return BouncesOffWalls || s.onScreen(mover.location);
	}
	
	public void update(int milliseconds_since_last_update, Screen s){
		if(BouncesOffWalls){ bounce(s); }
		if(kill_offscreen && !onScreen(s)){ dead = true; }
		previousloc = new Vector2D(mover.location);
		mover.update();
		
		if (life <= 0) { dead = true; }
		else { life--; }
	}
	
	public void draw(Screen screen) {
		if(screen.onScreen(this.mover.location) && screen.onScreen(previousloc)){
			screen.drawline(
					(int)mover.location.x, 
					(int)mover.location.y, 
					(int)previousloc.x, 
					(int)previousloc.y, 
					this.color.getRGB());
		}
	}

	public Vector2D getOriginAccelerationVector(){
		return new Vector2D(mover.location, origin);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public String toString(){
		return String.format("mover:%s\n dead:%b\n", mover, dead);
	}
	
}
