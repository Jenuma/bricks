package io.whitegoldlabs.bricks.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick extends Actor
{
	private Sound sfxBreak = Gdx.audio.newSound(Gdx.files.internal("break.wav"));
	
	//
	// Constructor
	//
	public Brick(Vector2 location)
	{
		sprite = new Texture(Gdx.files.internal("brick.png"));
		drawRect = new Rectangle(location.x, location.y, sprite.getWidth(), sprite.getHeight());
	}
	
	//
	// Public Methods
	//
	public void breakBrick()
	{
		sfxBreak.play();
	}
	
	public void dispose()
	{
		super.dispose();
		sfxBreak.dispose();
	}
}
