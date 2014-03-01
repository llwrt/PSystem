package Main;
import imaging.Screen;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.ParticleSystem;

public class Main implements Runnable{

	private final int WIDTH = 600;
	private final int HEIGHT = 600;
	private long desiredFPS = 30;
	private long calculatedFPS = 0;
	private JFrame frame = new JFrame("Particle System");
	private Canvas canvas = new Canvas();
	private BufferStrategy bufferStrategy;
	private boolean running = true;
	private Screen screen = new Screen(WIDTH, HEIGHT);
	private static BufferedImage IMG;
	private ParticleSystem ps = new ParticleSystem();
	private Menus Menu = new Menus();
	private Inputs input = new Inputs(canvas, ps, Menu);

	public Main(){
		setUpFrame();
		setUpCanvas();
		IMG = new BufferedImage(WIDTH,HEIGHT,1);  // temporary buffer for screen to draw to
	}
	
	public void setUpCanvas(){
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
	}
	
	public void setUpFrame(){
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		panel.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void run(){
		int millis_since_last_update; // used to do time dependent updates, not fps dependent
		long loop_duration_target = (1000*1000*1000)/desiredFPS; // desired nanoseconds per gameloop
		long start_of_loop; // used to slow the gameloop
		long lastUpdateTime, currentUpdateTime = System.nanoTime(); //calculate update times									
		int update_count = 0; long second_ticker = 0;; // used to calculate fps
		
		while(running){
			start_of_loop = System.nanoTime(); // used for sleep calculation
			
			// paint 
			render();
			
			// update particles
			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			millis_since_last_update = (int) ((currentUpdateTime - lastUpdateTime)/(1000*1000));
			update(millis_since_last_update);
			
			// calculate fps
			update_count++;
			second_ticker += millis_since_last_update;
			if(second_ticker >= 1000){ // if a second has passed
				calculatedFPS = update_count;
				second_ticker = 0; update_count = 0;
			}
			
			// sleep
			sleep(System.nanoTime() - start_of_loop, loop_duration_target);
		}
	}
	
	private static void sleep(long loop_duration, long loop_target){
		if(loop_duration <= loop_target) // if running faster than target, sleep for one update
			sleep((loop_target - loop_duration)/(1000*1000));
	}
	
	private static void sleep(long nanoseconds){
		try{Thread.sleep(nanoseconds);}
		catch(InterruptedException e){}
	}
	
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	protected void update(int milliseconds_since_last_update){
		ps.update(milliseconds_since_last_update, screen);
		// maybe turn motion blur on here
	}
	
	protected void render(Graphics2D g){
		ps.render(screen);
		screen.drawOntoImage(IMG);
		g.drawImage(IMG , 0, 0, screen.WIDTH, screen.HEIGHT, null);
		Menu.drawMenus(g, WIDTH, HEIGHT, (int) calculatedFPS, ps.getParticleType());
	}
	
	public static void main(String [] args){
		Main m = new Main();
		new Thread(m).start();
	}
	
}