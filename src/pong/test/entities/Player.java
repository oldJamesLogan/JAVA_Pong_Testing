package pong.test.entities;

import java.awt.Graphics;

import pong.test.main.Game;

public class Player extends Entity
{
	private float playerSpeed = 10;
	private Game game;

	public Player(Game game)
	{
		this.game = game;
		width = 25;
		height = 150;
		x = 5;
		y = (Game.HEIGHT - height) / 2;
	}

	@Override
	public void tick()
	{
		if(game.getInput().up)
		{
			if(y >= 6)
			{
				y -= playerSpeed;
			}
		}
		if(game.getInput().down)
		{
			if((y + height + 6) <= game.HEIGHT)
			{
				y += playerSpeed;
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

}
