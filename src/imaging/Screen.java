package imaging;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import math.Vector2D;

public class Screen {

	public int WIDTH, HEIGHT;
	public int[][] pixels;

	public Screen(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH][HEIGHT];
	}

	public void drawOntoImage(BufferedImage img){
		//blur(img);
		for(int x=0; x<WIDTH; x++){
			for(int y=0; y<HEIGHT; y++){
				img.setRGB(x, y, pixels[x][y]);
			}
		}
	}

	public int lowerAlpha(int color){
		int mask = 0x000000FF;
		Color c = new Color((color >> 16 & mask)/2, (color >> 8 & mask)/2, (color >> 0 & mask)/2);
		//Color b = new Color((int) (c.getRed()/1.1), (int) (c.getGreen()/1.1), (int)(c.getBlue()/1.1));
		return c.getRGB();//.darker().getRGB();
		//return b.getRGB();
	}
	
	public void dim(){
		for(int x=0; x<HEIGHT; x++){
			for(int y=0; y<WIDTH; y++){
				pixels[x][y] = lowerAlpha(pixels[x][y]);
			}
		}
	}
	
	public void clear(int color){
		for(int x=0; x<HEIGHT; x++){
			for(int y=0; y<WIDTH; y++){
				pixels[x][y] = color;
			}
		}
	}

	public boolean onScreen(Vector2D location){
		if(location.x < 0 || location.x >= WIDTH)
			return false;
		if(location.y < 0 || location.y >= HEIGHT)
			return false;
		return true;
	}

}
