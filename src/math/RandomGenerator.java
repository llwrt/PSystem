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
	
	public static Vector2D randomVector(int high){ 
		return randomVector(0, high);
	}
	
	public static Vector2D raindropVector(){
		// spread slightly positive and negative
		double x_velocity = r.nextDouble() * (r.nextInt(4)+1);
		if(r.nextBoolean()) x_velocity *= -1;
		
		// little variance, all downwards
		double y_velocity = r.nextDouble() * (r.nextInt(6));
		return new Vector2D(x_velocity, y_velocity);
	}
	
	public static Vector2D randomVector(int low, int high){
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
	
	public static Color rainbowColor(){
		return Color.getHSBColor(r.nextFloat(), 0.9f, 1.0f);
	}
	
	private static int[] rainColors = {
		0xFFA5D8F2, 0xFF099FEB, 0xFF076EA3, 0xFF053852, 0xFF5E95F2, 0xFF1060EB, 
		0xFF0540A6, 0xFF052B6B, 0xFF4055F5, 0xFF0016BD };
	
	public static Color rainColor(){
		return new Color(rainColors[r.nextInt(rainColors.length)]);
	}
}
