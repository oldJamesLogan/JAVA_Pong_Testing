package pong.test.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game implements Runnable
{
	public static final String TITLE = "Java Pong";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private volatile boolean running = false;
	private Thread thread;
	
	private JFrame frame;
	private Canvas canvas;
	
	public Game()
	{
		init();
		thread = new Thread(this);
	}
	
	private void init()
	{
		frame = new JFrame(TITLE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void run()
	{
		
	}
	
	public synchronized void start()
	{
		
	}
	
	public synchronized void stop()
	{
		
	}

}
