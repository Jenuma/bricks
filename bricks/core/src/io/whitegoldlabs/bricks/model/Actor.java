package io.whitegoldlabs.bricks.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public abstract class Actor implements Disposable
{
	Texture sprite;
	Vector2 location;
	Rectangle drawRect;
	
	//
	// Public Methods
	//
	public Rectangle getHitbox()
	{
		return drawRect;
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(sprite, drawRect.x, drawRect.y);
	}
	
	public void dispose()
	{
		sprite.dispose();
	}
	
	//
	// Protected Methods
	//
	protected Rectangle getDrawRect(Vector2 location)
	{
		return new Rectangle(location.x - (sprite.getWidth() / 2),
				location.y - (sprite.getHeight() / 2),
				sprite.getWidth(),
				sprite.getHeight());
	}
}
