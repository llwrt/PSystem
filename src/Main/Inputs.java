package Main;


import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import math.Vector2D;
import entity.Particle;
import entity.ParticleSystem;

public class Inputs implements KeyListener, MouseListener, MouseMotionListener{
	
	private ParticleSystem PS;
	private Menus MENU;
	protected Boolean PAUSED = true; // this value is read by Main

	public Inputs(Canvas c, ParticleSystem ps, Menus m){
		this.PS = ps;
		this.MENU = m;
		c.addKeyListener(this);
		c.addMouseMotionListener(this);
		c.addMouseListener(this);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			MENU.toggleHelp();
			PAUSED = !PAUSED;
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			PS.spawnParticle(new Vector2D(e.getX(), e.getY()));
		} else if(e.getButton() == MouseEvent.BUTTON3){
			PS.changeParticle();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		PS.setMouse(e.getX(), e.getY());
	}
	
	// need to implement these as part of the listener classes
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}

}
