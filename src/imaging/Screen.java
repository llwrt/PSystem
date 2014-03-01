package imaging;

import java.awt.Color;
import java.awt.image.BufferedImage;

import math.Vector2D;

public class Screen {

	public int WIDTH, HEIGHT;
	public int[][] pixels;
	private boolean MotionBlur = true;

	public Screen(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH][HEIGHT];
	}

	public void drawOntoImage(BufferedImage img){
		for(int x=0; x<WIDTH; x++){
			for(int y=0; y<HEIGHT; y++){
				img.setRGB(x, y, pixels[x][y]);
			}
		}
		if(MotionBlur)
			dim();
		else
			clear(0x00000000);
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
				pixels[x][y] = BlendRGB.subtractColors(pixels[x][y], 0xFF3F3F3F);
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
		return onScreen(location.x, location.y);
	}
	
	public boolean onScreen(double x, double y){
		return (x>=0 && x<WIDTH && y>=0 && y<HEIGHT);
	}
	
	private int[][] blur(int[][] pixlels){
		// if this is too slow, blur a smaller version of pixels. 
		// no need to allocate new array. just (for x=0; x<width/2; x+2) ignor half the pixels
		int width = pixels.length;
		int height = pixels[0].length;
		int[][] blurred = new int[width][height];
		
		// blurr in x direction on every row
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				if(x>2 && x<(width-3)){
					blurred[x][y] += 
							BlendRGB.multiply(.4, pixels[x][y]) 
							+ BlendRGB.multiply(.3, pixels[x-1][y])
							+ BlendRGB.multiply(.3, pixels[x+1][y]);
					//blurred[x][y] = (int) ((.6 * pixels[x][y]) + (.2 * pixels[x-1][y]) + (.2 * pixels[x+1][y]));
				}
			}
		}
		// blur in y direction on every column
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				if(y>2 && y<(width-3)){
					blurred[x][y] += 
							BlendRGB.multiply(.4, pixels[x][y]) 
							+ BlendRGB.multiply(.3, pixels[x][y-1])
							+ BlendRGB.multiply(.3, pixels[x][y+1]);
					//blurred[x][y] += (int) ((.6 * pixels[x][y]) + (.2 * pixels[x][y-1]) + (.2 * pixels[x][y+1]));
				}
			}
		}
		
		return blurred;
	}

	public int[][] bloom() {
		int[][] blur = blur(pixels);
		int[][] blur2 = blur(blur);
		for(int x=0; x<WIDTH; x++){
			for(int y=0; y<HEIGHT; y++){
				blur2[x][y] = BlendRGB.addColors(pixels[x][y], blur[x][y]);
			}
		}
		return blur;
	}
	
	public void toggleMotionBlur() {
		MotionBlur = !MotionBlur;
	}
	
	public void drawLine(int x1, int y1, int x2, int y2){
		drawline(x1, y1, x2, y2, 0xFFFFFFFF);
	}

	
	public void drawline(int x,int y,int x2, int y2, int color) {
		//System.out.printf("from %d,%d to %d,%d\n", x, y, x2, y2);
	    int w = x2 - x ;
	    int h = y2 - y ;
	    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
	    if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
	    if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
	    if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
	    int longest = Math.abs(w) ;
	    int shortest = Math.abs(h) ;
	    if (!(longest>shortest)) {
	        longest = Math.abs(h) ;
	        shortest = Math.abs(w) ;
	        if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
	        dx2 = 0 ;            
	    }
	    int numerator = longest >> 1 ;
	    for (int i=0;i<=longest;i++) {
	        pixels[x][y] = BlendRGB.addColors(pixels[x][y], color);
	        numerator += shortest ;
	        if (!(numerator<longest)) {
	            numerator -= longest ;
	            x += dx1 ;
	            y += dy1 ;
	        } else {
	            x += dx2 ;
	            y += dy2 ;
	        }
	    }
	}

}
