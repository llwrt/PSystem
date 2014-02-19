import java.awt.Color;
import java.awt.Graphics2D;


public class Menus {
	
	private boolean show_help = true;
	private boolean show_fps = true;
	
	public Menus(){
	}
	
	public void toggleFPS(){
		show_fps = !show_fps;
	}
	
	public void toggleHelp(){
		show_help = !show_help;
	}
	
	protected void drawMenus(Graphics2D g, int WIDTH, int HEIGHT, int fps, String type){
		if(show_fps){
			g.setColor(Color.WHITE);
			g.drawString("FPS: " + fps, 10, 35);
			g.drawString("Particle Type: " + type, 10, 20); // + ps.ParticleType
		}
		if(show_help){
			// TODO: this only looks good for 600x600, so maybe fix that
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			// I dont know how wide the strings will be, so I manually adjusted it to be in the middle
			g.drawString("left click   :\tmake particle", WIDTH/2 -70, HEIGHT/2 - 25); 
			g.drawString("right click :\tchange type", WIDTH/2 - 70, HEIGHT/2 - 10);
			g.setColor(Color.GRAY);
			g.drawString("- click anywhere -", WIDTH/2 - 50, HEIGHT/2 + 40);
		}
	}
}
