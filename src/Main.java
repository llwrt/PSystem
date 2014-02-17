import imaging.Screen;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import math.Vector2D;
import entity.Emitter;
import entity.ParticleSystem;

public class Main implements Runnable, MouseListener{

	private final int WIDTH = 600;
	private final int HEIGHT = 600;
	private long desiredFPS = 30;
	private JFrame frame = new JFrame("Particle System");
	private Canvas canvas;
	private BufferStrategy bufferStrategy;
	private ParticleSystem particles;
	private boolean running = true;
	private Screen screen = new Screen(WIDTH, HEIGHT);

	public Main(){
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);
		panel.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
		canvas.addMouseListener(this);
		particles = new ParticleSystem(screen);
	}

	public void run(){
		int millis_since_last_update; // used to do time dependent updates, not fps dependent
		long loop_duration_target = (1000*1000*1000)/desiredFPS; // desired nanoseconds per gameloop
		long start_of_loop; // used to slow the gameloop
		long lastUpdateTime, currentUpdateTime = System.nanoTime(); //calculate update times									// nanoseconds this loop took
		
		while(running){
			start_of_loop = System.nanoTime(); // used for sleep calculation
			
			// paint 
			render();
			
			// update
			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			millis_since_last_update = (int) ((currentUpdateTime - lastUpdateTime)/(1000*1000));
			update(millis_since_last_update);
			
			// sleep
			sleep(System.nanoTime() - start_of_loop, loop_duration_target);
		}
	}
	
	private static void sleep(long loop_duration, long target){
		// if running faster than target, sleep for one update
		if(loop_duration <= target){
			try{Thread.sleep((target - loop_duration)/(1000*1000));}
			catch(InterruptedException e){}
		}
	}

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	protected void update(int milliseconds_since_last_update){
		particles.update(milliseconds_since_last_update);
		
	}
	
	protected void render(Graphics2D g){
		particles.render(g);
	}

	public static void main(String [] args){
		Main ex = new Main();
		new Thread(ex).start();
	}


	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			particles.spawnEmitter(Emitter.Type.Firework, new Vector2D(e.getX(), e.getY()), 1);
		else
			particles.spawnEmitter(Emitter.Type.Firework, new Vector2D(e.getX(), e.getY()), 20);
	}
	
}