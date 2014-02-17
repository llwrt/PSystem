package entity;
import imaging.Screen;

import java.awt.Color;
import java.util.Random;

import math.Vector2D;

public class Particle {

	public Vector2D origin;
	public Movable mover;
	int life, size;
	Color color;
	private boolean dead = false;
	Random r = new Random();

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

	public void update(int milliseconds_since_last_update, Screen s){
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
		mover.update();
		int red = color.getRed()-2;
		int green = color.getGreen()-2;
		int blue = color.getBlue()-2;
		color = new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
		if (life <= 0) { dead = true; }
		else { life--; }
	}

	public void draw(Screen screen) {
		/*if(dead)
			return;*/
		int extra = size/2;
		for(int i=0 - extra; i<size; i++){
			for(int j=0-extra; j<size; j++){
				if(screen.onScreen(new Vector2D((int) mover.location.x + i, (int) mover.location.y + j)))
					screen.pixels[(int) mover.location.x + i][(int) mover.location.y + j] += color.getRGB();
			}
		}
	}

	public boolean isDead() {
		return dead;
	}
	
}
