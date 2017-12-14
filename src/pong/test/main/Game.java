package pong.test.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import pong.test.entities.Ball;
import pong.test.entities.Player;
import pong.test.input.Input;

public class Game implements Runnable
{
	public static final String TITLE = "Java Pong";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private volatile boolean running = false;
	private Thread thread;
	
	private JFrame frame;
	private Canvas canvas;
	
	private Graphics g;
	private BufferStrategy bs;
	
	private int ticks = 0, renders = 0;
	String str = "";
	
	private Input input;
	private Ball ball;
	private Player player;
	
	public Game()
	{
		init();
		thread = new Thread(this);
	}
	
	private void init()
	{
		input = new Input();
		
		frame = new JFrame(TITLE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocus();
		frame.addKeyListener(input);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
		
		player = new Player(this);
		ball = new Ball(this);
	}
	
	private void tick()
	{
		ticks++;
		input.tick();
		ball.tick();
		player.tick();
	}
	
	private void render()
	{
		renders++;
		bs = canvas.getBufferStrategy();
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Draw Begin
		
		g.setColor(Color.WHITE);
		ball.render(g);
		player.render(g);
//		g.drawString(str, 15, 15);
		
		//Draw End
		
		bs.show();
		g.dispose();
	}

	@Override
	public void run()
	{
		int fps = 60;
		double timePerTick = 1_000_000_000 / fps;
		double delta = 0;
		long now = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				
				delta--;
			}
			
			render();
			
			if(timer >= 1_000_000_000)
			{
				str = "Ticks: " + ticks + " Render: " + renders;
				setNewTitle(TITLE + " " + str);
				timer = 0;
				ticks = 0;
				renders = 0;
			}
		}
		stop();
	}
	
	public synchronized void start()
	{
		if(running)return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running) return;
		running = false;
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	private void setNewTitle(String newTitle)
	{
		frame.setTitle(newTitle);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public Input getInput()
	{
		return input;
	}

}
