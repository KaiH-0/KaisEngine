package com.kaihale.engine;

import com.kaihale.game.GameManager;

public abstract class Component {
	protected String tag;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public abstract void update(GameContainer gc, GameManager gm, float dt);
	public abstract void render(GameContainer gc, Renderer r);
}
