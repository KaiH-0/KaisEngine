package com.kaihale.game.objects;

import java.awt.event.KeyEvent;

import com.kaihale.engine.GameContainer;
import com.kaihale.engine.Renderer;
import com.kaihale.game.GameManager;
import com.kaihale.game.GameObject;

public class Player extends GameObject {
	
	private int tileX, tileY;
	private float offX, offY;
	private float speed = 100;
	private float fallSpeed = 10;
	private float jump = -4;
	private float fallDistance = 0;
	private boolean ground = false;
	
	public Player(int posX, int posY)
	{
		this.tag = "player";
		this.tileX = posX;
		this.tileY = posY;
		this.offX = 0;
		this.offY = 0;
		this.posX = posX * GameManager.TS;
		this.posY = posY * GameManager.TS;
		this.width = GameManager.TS;
		this.height = GameManager.TS;
	}
	
	@Override
	public void update(GameContainer gc,GameManager gm, float dt) {
		
		//Left & Right
		if(gc.getInput().isKey(KeyEvent.VK_D))
		{
			if(gm.getCollision(tileX + 1, tileY) || gm.getCollision(tileX + 1,  tileY + (int)Math.signum((int)offY)))
			{
				if(offX <= 0)
				{
					offX += dt * speed;
					if(offX > 0)
					{
						offX = 0;
					}
				}
				else
				{
					offX = 0;
				}
			}
			else
			{
				offX += dt * speed;
			}
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_A))
		{
			if(gm.getCollision(tileX - 1,  tileY) || gm.getCollision(tileX - 1,  tileY + (int)Math.signum((int)offY)))
			{
				if(offX > 0)
				{
					offX -= dt * speed;
					if(offX < 0)
					{
						offX = 0;
					}
				}
				else
				{
					offX = 0;
				}
			}
			else
			{
				offX -= dt * speed;
			}
		}
		
		//Jump & Gravity
		fallDistance += dt * fallSpeed;
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_W) && ground)
		{
			fallDistance = jump;
			ground = false;
		}
		
		offY += fallDistance;
		
		if(fallDistance < 0)
		{
			if((gm.getCollision(tileX,tileY - 1) || gm.getCollision(tileX + (int)Math.signum((int)offX),tileY - 1)) && offY < 0)
			{
				fallDistance = 0;
				offY = 0;
			}
		}
		
		if(fallDistance > 0)
		{
			if((gm.getCollision(tileX,tileY + 1) || gm.getCollision(tileX + (int)Math.signum((int)offX),tileY + 1)) && offY > 0)
			{
				fallDistance = 0;
				offY = 0;
				ground = true;
			}
		}
		
		if(offY > GameManager.TS / 2)
		{
			tileY++;
			offY -= GameManager.TS;
		}
		
		if(offY < -GameManager.TS / 2)
		{
			tileY--;
			offY += GameManager.TS;
		}
		
		
		
		if(offX > GameManager.TS / 2)
		{
			tileX++;
			offX -= GameManager.TS;
		}
		
		if(offX < -GameManager.TS / 2)
		{
			tileX--;
			offX += GameManager.TS;
		}
		
		posX = tileX * GameManager.TS + offX;
		posY = tileY * GameManager.TS + offY;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawFillRect((int)posX, (int)posY, width, height, 0xff00ff00);
		r.
	}
	
}
