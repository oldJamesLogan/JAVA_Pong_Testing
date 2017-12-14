package pong.test.entities;

import java.awt.Graphics;

public abstract class Entity
{
	protected float x;
	protected float y;
	protected float speedX;
	protected float speedY;
	protected int width;
	protected int height;
	
	public Entity()
	{
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}
