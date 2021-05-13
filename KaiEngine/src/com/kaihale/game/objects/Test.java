package com.kaihale.game.objects;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import com.kaihale.engine.GameContainer;
import com.kaihale.engine.GameObject;
import com.kaihale.engine.Renderer;
import com.kaihale.engine.gfx.Image;
import com.kaihale.game.GameManager;

public class Test extends GameObject {

	public boolean test = false;
	private Image testimage = new Image("/sprites/sPlayer.png",0);
	public int tileX = 0;
	public int tileY = 0;
	public float offX = 0;
	public float offY = 0;
	public int t = 0;
	public int xv = 0;
	public int yv = 0;
	public int spd = 1;
	
	public Test(int posX, int posY)
	{
		this.tag = "test";
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
	public void update(GameContainer gc, GameManager gm, float dt) {
		// TODO Auto-generated method stub
		if(gc.getInput().isKeyDown(KeyEvent.VK_K))
		{
			test = true;
		}
		else
		{
			test = false;
		}
		//offX = gc.getInput().getMouseX() + gm.camera.getOffX();
		//offY = gc.getInput().getMouseY() + gm.camera.getOffY();
		//offX = gc.MoveTowardsX(offX, 40, 0.5f);
		//offY = gc.MoveTowardsY(offY, 100, 0.75f);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		//if(test == true)
		//{
		//r.drawImage(testimage, (int)offX, (int)offY);
		//}
	}
	@Override
	public void collision(GameObject other) {
		// TODO Auto-generated method stub
		
	}
	
}
