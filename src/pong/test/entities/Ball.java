package pong.test.entities;

import java.awt.Graphics;

import pong.test.main.Game;

public class Ball extends Entity
{
	private Game game;
	public Ball(Game game)
	{
		this.game = game;
		resetBall();
		width = 25;
		height = 25;
		speedX = 5;
		speedY = 5;
	}

	@Override
	public void tick()
	{
		x += speedX;
		y += speedY;
		
		if((x + width) >= Game.WIDTH)
		{
			speedX = -speedX;
//			resetBall();
		}
		if(x <= 0)
		{
			speedX = -speedX;
			resetBall();
		}
		
		if((y + height) >= Game.HEIGHT)
		{
			speedY = -speedY;
		}
		if(y <= 0)
		{
			speedY = -speedY;
		}
		
		if(x <= (game.getPlayer().getWidth() + game.getPlayer().getX()))
		{
			if((y + height) >= game.getPlayer().getY() && y <= (game.getPlayer().getY() + game.getPlayer().getHeight()))
			{
				speedX = -speedX;
				System.out.println("Hit");
				return;
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
//		g.fillRect((int)x, (int)y, width, height);
		g.fillOval((int)x, (int)y, width, height);
	}
	
	private void resetBall()
	{
		x = (Game.WIDTH - width) / 2;
		y = (Game.HEIGHT - height) / 2;
	}

}
