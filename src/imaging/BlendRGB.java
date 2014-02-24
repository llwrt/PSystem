package imaging;


public class BlendRGB {
	
	public BlendRGB(){}
	
	public static int makeColor(int red, int green, int blue){
		// makes a full-alpha color composed of 
		// 3 ints between 0-255
		return 0xFF000000 |  red << 16 | green << 8 | blue;
	}
	
	public static int addBytes(int a, int b){
		// a and b are between 0-255
		// returns their sum, or 255 if the sum is over 255
		int result = a + b;
		return (result >= 0xFF) ? 0xFF : result;
	}
	
	private static int subtractBytes(int a, int b) {
		int result = a - b;
		return (result < 0) ? 0 : result;
	}
	
	public static int addColors(int color1, int color2){
		// creates full alpha color by bitwise adding r,g,b channels
		int red1 = color1 >> 16 & 0x000000FF;
		int green1 = color1 >> 8 & 0x000000FF;
		int blue1 = color1 & 0x000000FF;
		
		int red2 = color2 >> 16 & 0x000000FF;
		int green2 = color2 >> 8 & 0x000000FF;
		int blue2 = color2 & 0x000000FF;
		
		return makeColor(addBytes(red1, red2), addBytes(green1, green2), addBytes(blue1, blue2));
	}
	
	public static int subtractColors(int color1, int color2){
		int red1 = color1 >> 16 & 0x000000FF;
		int green1 = color1 >> 8 & 0x000000FF;
		int blue1 = color1 & 0x000000FF;
		
		int red2 = color2 >> 16 & 0x000000FF;
		int green2 = color2 >> 8 & 0x000000FF;
		int blue2 = color2 & 0x000000FF;
		return makeColor(subtractBytes(red1, red2), subtractBytes(green1, green2), subtractBytes(blue1, blue2));
	}
	

	/**
	 * Color-adds the second integer array to the first using the BlendRGB.colorAdd() function
	 */
	public static void add(int[] target, int[] blend){
		assert target.length == blend.length;
		for(int i=0; i<target.length; i++){
			target[i] = BlendRGB.addColors(blend[i], blend[i]);
		}
	}
	
	private static int screenPixel(int a, int b){
		return 0;
	}
	
	public static void screen(int[] result, int[] blend){
		for(int i=0; i<result.length; i++){
			result[i] = screenPixel(result[i], blend[i]);
		}
	}
	
	public static void multiply(int[] pixels, int width, int height){
	}
	
	// takes a double between 0-1 and decreases all color channels by that percentage
	public static int multiply(double amount, int color) {
		int red = (int) ((color >> 16 & 0x000000FF) * amount);
		int green = (int) ((color >> 8 & 0x000000FF) * amount);
		int blue = (int) ((color & 0x000000FF) * amount);
		return makeColor(red,green,blue);
	}

	public static void overlay(int[] pixels, int width, int height){
		
	}
	
}
