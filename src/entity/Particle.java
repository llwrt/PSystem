package entity;
import imaging.BlendRGB;
import imaging.Screen;

import java.awt.Color;
import java.util.Random;

import math.Vector2D;

public class Particle {

	public enum Type{
		Firework, FireworkStrand;
		public String toString(){ return "Firework";}
	}
	
	public Vector2D origin;
	public Movable mover;
	int life, size;
	Color color;
	public boolean dead = false;
	Random r = new Random();
	private boolean BouncesOffWalls = false;

	public Particle(Movable m, int _life, Color _color, int s){
		size = s;
		mover = m;
		life = _life;
		color = _color;
		origin = new Vector2D(m.location);
	}
	
	public Particle(Movable m, int _life, Color _color){
		size = 1;
		mover = m;
		life = _life;
		color = _color;
		origin = new Vector2D(m.location);
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
		if(!onScreen(s)){ dead = true; }
		
		mover.update();
		if (life <= 0) { dead = true; }
		else { life--; }
	}

	public void draw(Screen screen) {
		// maybe instead of drawing just the particle, draw a line between current and last positions 
		int current_screen_value = 0;
		int extra = size/2;
		
		// for exery pixel in the particle texture
		for(int i=0 - extra; i<size; i++){
			for(int j=0-extra; j<size; j++){
				// see if its location is on the screen
				int curr_x = (int) (mover.location.x + i);
				int curr_y = (int) (mover.location.y + j);
				if(screen.onScreen(curr_x, curr_y)){
					// paint it to the screen 
					current_screen_value = screen.pixels[curr_x][curr_y];
					screen.pixels[curr_x][curr_y] = BlendRGB.addColors(current_screen_value, this.color.getRGB());
				}
			}
		}
	}

	public Vector2D getOriginAccelerationVector(){
		return new Vector2D(mover.location, origin);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	
}
