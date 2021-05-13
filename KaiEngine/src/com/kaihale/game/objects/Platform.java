package com.kaihale.game.objects;

import com.kaihale.engine.AABBComponent;
import com.kaihale.engine.GameContainer;
import com.kaihale.engine.GameObject;
import com.kaihale.engine.Renderer;
import com.kaihale.game.GameManager;

public class Platform extends GameObject {

	private int colour = 0xffffffff;
	public Platform()
	{
		this.tag = "platform";
		this.width = 32;
		this.height = 16;
		this.padding = 0;
		this.paddingTop = 0;
		this.posX = 176;
		this.posY = 150;
		
		this.addComponent(new AABBComponent(this));
	}
	
	float temp = 0;
	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		// TODO Auto-generated method stub
		temp += dt;
		
		posY += Math.cos(temp) * 2;
		this.updateComponents(gc,gm,dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		r.drawRect((int)posX, (int)posY, width, height, colour);
		this.renderComponents(gc, r);
	}

	@Override
	public void collision(GameObject other) {
		// TODO Auto-generated method stub
		colour = (int) (Math.random() * Integer.MAX_VALUE);
	}
}
