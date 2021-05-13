package com.kaihale.game.objects;

import com.kaihale.engine.GameContainer;
import com.kaihale.engine.GameObject;
import com.kaihale.engine.Renderer;
import com.kaihale.engine.audio.SoundClip;
import com.kaihale.engine.gfx.Image;
import com.kaihale.game.GameManager;

public class Bullet extends GameObject {

	private int tileX, tileY;
	private float offX, offY;
	private int direction;
	private SoundClip snd = new SoundClip("/audio/test.wav");
	private float rot = 90;
	private Image image = new Image("/sprites/sPlayer.png",rot);
	private float speed = 200;
	
	public Bullet(int tileX, int tileY,float offX, float offY, int direction)
	{
		snd.play();
		this.direction = direction;
		this.tileX = tileX;
		this.tileY = tileY;
		this.offX = offX;
		this.offY = offY;
		this.padding = 0;
		this.paddingTop = 0;
		this.width = 4;
		this.height = 4;
	}
	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		// TODO Auto-generated method stub
		posX = tileX * GameManager.TS + offX;
		posY = tileY * GameManager.TS + offY;
		
		switch(direction)
		{
		case 0: offY += speed * dt; break;
		case 1: offX += speed * dt; break;
		case 2: offY -= speed * dt; break;
		case 3: offX -= speed * dt; break;
		}
		
		if(offY > GameManager.TS / 2)
		{
			tileY++;
			offY -= GameManager.TS;
		}
		
		if(offY < 0)
		{
			tileY--;
			offY += GameManager.TS;
		}
		
		
		
		if(offX > GameManager.TS / 2)
		{
			tileX++;
			offX -= GameManager.TS;
		}
		
		if(offX < 0)
		{
			tileX--;
			offX += GameManager.TS;
		}
		
		if(gm.getCollision(tileX, tileY))
		{
			this.dead = true;
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		r.drawImage(image,(int)posX, (int)posY);
	}
	@Override
	public void collision(GameObject other) {
		// TODO Auto-generated method stub

	}
}
