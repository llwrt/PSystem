

import java.awt.Canvas;
import java.awt.Menu;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import math.Vector2D;
import entity.Emitter;
import entity.ParticleSystem;

public class Inputs implements KeyListener, MouseListener, MouseMotionListener{
	
	protected int Mouse_x, Mouse_y;
	ParticleSystem PS;
	Menus MENU;
	
	protected static class BUTTONS{
		public static boolean SPACE = false;
		public static boolean ENTER = false;
		public static boolean UP = false;
		public static boolean DOWN = false;
		public static boolean LEFT = false;
		public static boolean RIGHT = false;
	}
	
	public Inputs(Canvas c, ParticleSystem ps, Menus m){
		this.PS = ps;
		this.MENU = m;
		c.addKeyListener(this);
		c.addMouseMotionListener(this);
		c.addMouseListener(this);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			PS.clear();
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	
	boolean firstclick = false;
	public void mousePressed(MouseEvent e) {
		if(!firstclick){
			firstclick = true;
			MENU.toggleHelp();
			return;
		}
		if(e.getButton() == MouseEvent.BUTTON1){
			PS.spawnEmitter(Emitter.Type.Firework, new Vector2D(e.getX(), e.getY()));
		} else{
			for(int i=0; i<15; i++)
				PS.spawnEmitter(Emitter.Type.Firework, new Vector2D(e.getX(), e.getY()));
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		// Update mouse_x, mouse_y
		//ps.spawnEmitter(Emitter.Type.Mouse, new Vector2D(e.getX(),  e.getY()));
	}


}
