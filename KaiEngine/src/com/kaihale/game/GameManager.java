package com.kaihale.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.kaihale.engine.AbstractGame;
import com.kaihale.engine.GameContainer;
import com.kaihale.engine.Renderer;
import com.kaihale.engine.gfx.Image;
import com.kaihale.game.objects.Player;

public class GameManager extends AbstractGame {
	
	public static final int TS = 16;
	private boolean[] collision;
	private int levelW, levelH;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public GameManager()
	{
		objects.add(new Player(4,4));
		loadLevel("/levels/level0.png");
	}
	
	@Override
	public void update(GameContainer gc, float dt) { //Add GameManager gm
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_A))
		{
			
		}
		
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).update(gc, this, dt);
			if(objects.get(i).isDead())
			{
				objects.remove(i);
				i--;
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		for(int y = 0; y < levelH; y++)
		{
			for(int x = 0; x < levelW; x++)
			{
				if(collision[x + y * levelW])
				{
					r.drawFillRect(x * TS, y * TS, TS, TS, 0xff0f0f0f);
				}
				else
				{
					r.drawFillRect(x * TS, y * TS, TS, TS, 0xfff9f9f9);
				}
			}
		}
		
		for(GameObject obj : objects)
		{
			obj.render(gc, r);
		}
	}
	
	public void loadLevel(String path)
	{
		Image levelImage = new Image(path);
		levelW = levelImage.getW();
		levelH = levelImage.getH();
		collision = new boolean[levelW * levelH];
		
		
		for(int y = 0; y < levelImage.getH(); y++)
		{
			for(int x = 0; x < levelImage.getW(); x++)
			{
				if(levelImage.getP()[x + y * levelImage.getW()] == 0xff000000)
				{
					collision[x + y * levelImage.getW()] = true;
				}
				else
				{
					collision[x + y * levelImage.getW()] = false;
				}
			}
		}
	}
	
	public boolean getCollision(int x, int y)
	{
		if(x < 0 || x >= levelW || y < 0 || y >= levelH)
			return true;
		return collision[x + y * levelW];
	}
	
	public static void main(String args[])
	{
		GameContainer gc = new GameContainer(new GameManager());
		gc.setTitle("Game Test in My Beautiful Engine :D");
		gc.start();
	}

	@Override
	public void init(GameContainer gc) {
		gc.getRenderer().setAmbientColour(-1);
	}
}
