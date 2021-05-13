package com.kaihale.game.objects;

import java.awt.event.KeyEvent;

import com.kaihale.engine.AABBComponent;
import com.kaihale.engine.GameContainer;
import com.kaihale.engine.GameObject;
import com.kaihale.engine.Renderer;
import com.kaihale.engine.gfx.ImageTile;
import com.kaihale.game.GameManager;

public class Player extends GameObject {
	
	private int tileX, tileY;
	private float offX, offY;
	private float speed = 100;
	private ImageTile playerImage = new ImageTile("/sprites/sPlayer.png",16,16);
	private int facing = 0;
	private float anim = 0;
	
	public Player(int posX, int posY)
	{
		this.tag = "player";
		this.tileX = posX;
		this.tileY = posY;
		this.offX = 0;
		this.offY = 0;
		this.posX = posX * GameManager.TS;
		this.posY = posY * GameManager.TS;
		this.width = 16;
		this.height = 16;
		padding = 0;
		paddingTop = 0;
		
		this.addComponent(new AABBComponent(this));
	}
	
	@Override
	public void update(GameContainer gc,GameManager gm, float dt) {
		//Left & Right
		if(gc.getInput().isKey(KeyEvent.VK_D))
		{
			if(gm.getCollision(tileX + 1, tileY) || gm.getCollision(tileX + 1,  tileY + (int)Math.signum((int)offY)))
			{
					offX += dt * speed;
					if(offX > padding)
					{
						offX = padding;
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
					offX -= dt * speed;
					if(offX < -padding)
					{
						offX = -padding;
					}
			}
			else
			{
				offX -= dt * speed;
			}
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_S))
		{
			if(gm.getCollision(tileX,  tileY + 1) || gm.getCollision(tileX,  tileY + (int)Math.signum((int)offY)))
			{
					offY -= dt * speed;
					if(offY < -paddingTop)
					{
						offY = -paddingTop;
					}
			}
			else
			{
				offY += dt * speed;
			}
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_W))
		{
			if(gm.getCollision(tileX,  tileY - 1) || gm.getCollision(tileX,  tileY + (int)Math.signum((int)offY)))
			{
					offY -= dt * speed;
					if(offY < paddingTop)
					{
						offY = paddingTop;
					}
			}
			else
			{
				offY -= dt * speed;
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
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_UP))
		{
			gm.addObject(new Bullet(tileX,tileY,offX+width/2,offY+height/2,2));
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_RIGHT))
		{
			gm.addObject(new Bullet(tileX,tileY,offX+width/2,offY+height/2,1));
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_DOWN))
		{
			gm.addObject(new Bullet(tileX,tileY,offX+width/2,offY+height/2,0));
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_LEFT))
		{
			gm.addObject(new Bullet(tileX,tileY,offX+width/2,offY+height/2,3));
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_D))
		{
			facing = 0;
			anim += dt * 5;
			if(anim >= 4)
				anim = 0;
		}
		else if(gc.getInput().isKey(KeyEvent.VK_A))
		{
			facing = 1;
			anim += dt * 5;
			if(anim >= 4)
				anim = 0;
		}
		else if(gc.getInput().isKey(KeyEvent.VK_S))
		{
			facing = 0;
			anim += dt * 5;
			if(anim >= 4)
				anim = 0;
		}
		else if(gc.getInput().isKey(KeyEvent.VK_W))
		{
			facing = 0;
			anim += dt * 5;
			if(anim >= 4)
				anim = 0;
		}
		else
		{
			facing = 2;
			anim = 0;
		}
		
		this.updateComponents(gc, gm, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		//r.drawFillRect((int)posX, (int)posY, width, height, 0xff00ff00);
		r.drawImageTile(playerImage,(int)posX, (int)posY,(int)anim,facing);
		this.renderComponents(gc, r);
	}

	@Override
	public void collision(GameObject other) {
		// TODO Auto-generated method stub
	        if (other.getTag().equalsIgnoreCase("platform"))
	        {
	            AABBComponent myC = (AABBComponent) this.findComponent("aabb");
	            AABBComponent otherC = (AABBComponent) other.findComponent("aabb");
	 
	            //Note this code is also from episode 10 in the series as well.
	 
	            if (Math.abs(myC.getLastCenterX() - otherC.getLastCenterX()) < myC.getHalfWidth() + otherC.getHalfWidth())
	            {
	                if (myC.getCenterY() < otherC.getCenterY())
	                {
	                    int distance = (myC.getHalfHeight() + otherC.getHalfHeight()) - (otherC.getCenterY() - myC.getCenterY());
	                    offY -= distance;
	                    posY -= distance;
	                    myC.setCenterY(myC.getCenterY() - distance);
	                }
	 
	                if (myC.getCenterY() > otherC.getCenterY())
	                {
	                    int distance = (myC.getHalfHeight() + otherC.getHalfHeight()) - (myC.getCenterY() - otherC.getCenterY());
	                    offY += distance;
	                    posY += distance;
	                    myC.setCenterY(myC.getCenterY() + distance);
	                }
	            }
	            else
	            {
	                if (myC.getCenterX() < otherC.getCenterX())
	                {
	                    int distance = (myC.getHalfWidth() + otherC.getHalfWidth()) - (otherC.getCenterX() - myC.getCenterX());
	                    offX -= distance;
	                    posX -= distance;
	                    myC.setCenterX(myC.getCenterX() - distance);
	                }
	 
	                
	                if (myC.getCenterX() > otherC.getCenterX())
	                {
	                    int distance = (myC.getHalfWidth() + otherC.getHalfWidth()) - (myC.getCenterX() - otherC.getCenterX());
	                    offX += distance;
	                    posX += distance;
	                    myC.setCenterX(myC.getCenterX() + distance);
	                }
	                
	            }
	        }
	}
	
}
