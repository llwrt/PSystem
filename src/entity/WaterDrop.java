package entity;

import math.RandomGenerator;
import imaging.BlendRGB;
import imaging.Screen;

public class WaterDrop extends Particle{

	private int size;
	
	public WaterDrop(Movable m) {
		super(m, 300, RandomGenerator.rainColor());
		this.size = 2;
		this.BouncesOffWalls = false;
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
	
}
