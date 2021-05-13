package com.kaihale.engine;

import com.kaihale.game.GameManager;

public class AABBComponent extends Component {
	
	private GameObject parent;
	private int centerX, centerY;
	private int halfWidth, halfHeight;
	private int lastCenterX, lastCenterY;
	
	public AABBComponent(GameObject parent)
	{
		this.parent = parent;
		this.tag = "aabb";
	}
	
	@Override
	public void update(GameContainer gc, GameManager gm, float dt) {
		// TODO Auto-generated method stub
		lastCenterX = centerX;
		lastCenterY = centerY;
		centerX = (int) (parent.getPosX() + (parent.getWidth() / 2));
		centerY = (int) (parent.getPosY() + (parent.getHeight() / 2) + (parent.getPaddingTop() / 2));
		halfWidth = (parent.getWidth() / 2) - parent.getPadding();
		halfHeight = (parent.getHeight() / 2) - (parent.getPaddingTop()/2);
		
		
		Physics.addAABBComponent(this);
	}

	public int getLastCenterX() {
		return lastCenterX;
	}

	public void setLastCenterX(int lastCenterX) {
		this.lastCenterX = lastCenterX;
	}

	public int getLastCenterY() {
		return lastCenterY;
	}

	public void setLastCenterY(int lastCenterY) {
		this.lastCenterY = lastCenterY;
	}

	public GameObject getParent() {
		return parent;
	}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getHalfWidth() {
		return halfWidth;
	}

	public void setHalfWidth(int halfWidth) {
		this.halfWidth = halfWidth;
	}

	public int getHalfHeight() {
		return halfHeight;
	}

	public void setHalfHeight(int halfHeight) {
		this.halfHeight = halfHeight;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		// TODO Auto-generated method stub
		r.drawRect(centerX - halfWidth, centerY - halfHeight, halfWidth * 2, halfHeight * 2, 0xffffffff);
	}
	
}
