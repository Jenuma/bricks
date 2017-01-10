package io.whitegoldlabs.bricks.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Flipper extends Actor
{
	boolean isHeld;
	
	//
	// Constructor
	//
	public Flipper(Vector2 center)
	{
		sprite = new Texture(Gdx.files.internal("flipper.png"));
		location = new Vector2(center.x, center.y);
		drawRect = getDrawRect(location);
		
		isHeld = false;
	}
	
	//
	// Public Methods
	//
	public boolean clickedOn(int mouseX, int mouseY)
	{
		mouseY = Gdx.graphics.getHeight() - 1 - mouseY;
		
		if(mouseX >= drawRect.x && mouseX <= drawRect.x + drawRect.width &&
			mouseY >= drawRect.y && mouseY <= drawRect.y + drawRect.height)
		{
			return true;
		}
		return false;
	}
	
	public void hold()
	{
		isHeld = true;
	}
	
	public void letGo()
	{
		isHeld = false;
	}
	
	public void move(float mouseX)
	{
		if(mouseX <= sprite.getWidth() / 2)
		{
			drawRect.setX(0);
		}
		else if(mouseX >= Gdx.graphics.getWidth() - (sprite.getWidth() / 2))
		{
			drawRect.setX(Gdx.graphics.getWidth() - sprite.getWidth());
		}
		else
		{
			drawRect.setX(mouseX - (sprite.getWidth() / 2));
		}
	}
	
	public boolean isHeld()
	{
		return isHeld;
	}
}
