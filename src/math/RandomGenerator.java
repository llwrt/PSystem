package math;
import imaging.Screen;

import java.awt.Color;
import java.util.Random;

/** creates a bunch of useful random objects, like colors, vectors, etc. */
public class RandomGenerator {

	private static Random r = new Random();
	
	public static Color randomColor(){
		return new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
	}
	
	public Vector2D randomVector(int high){ 
		return randomVector(0, high);
	}
	
	public Vector2D randomVector(int low, int high){
		double angle = r.nextDouble() * 360; // random double between 0 and 360
		double magnitude = (r.nextDouble() * (high - low)) + low; // random double between [low, high]
		return new Vector2D(Math.cos(angle) * magnitude, Math.sin(angle) * magnitude);
	}

	public static Vector2D randomLocation(Screen s){
		return new Vector2D(r.nextInt(s.WIDTH), r.nextInt(s.HEIGHT));
	}
	
	public static Double nextDouble(){
		return r.nextDouble();
	}
	
	public static int nextInt(int n){
		return r.nextInt(n);
	}
	
	private static Color RED_WHITE_BLUE[] = {Color.RED, Color.WHITE, new Color(0xFF3A7FFF)};
	public static Color redWhiteOrBlue(){
		return RED_WHITE_BLUE[r.nextInt(3)];
	}
	
	public static Color rainbowColor(){
		return Color.getHSBColor(r.nextFloat(), 0.9f, 1.0f);
	}
}
