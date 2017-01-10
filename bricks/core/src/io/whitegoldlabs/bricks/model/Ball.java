package io.whitegoldlabs.bricks.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Actor
{
	private Sound sfxBounce = Gdx.audio.newSound(Gdx.files.internal("bounce.wav"));
	private Vector2 velocity;
	private float acceleration;
	private boolean isMoving;
	
	//
	// Constructor
	//
	public Ball(Vector2 center)
	{
		sprite = new Texture(Gdx.files.internal("ball.png"));
		location = new Vector2(center.x, center.y);
		velocity = new Vector2(-5, 5);
		acceleration = 1.5f;
		drawRect = getDrawRect(location);
		
		isMoving = false;
	}
	
	//
	// Public Methods
	//
	public void startMoving()
	{
		sfxBounce.play();
		isMoving = true;
	}
	
	public void move()
	{
		if(location.x <= 0 + sprite.getWidth() / 2)
		{
			sfxBounce.play();
			velocity.x = 5;
		}
		
		if(location.x >= Gdx.graphics.getWidth() - (sprite.getWidth() / 2))
		{
			sfxBounce.play();
			velocity.x = -5;
		}
		
		if(location.y >= Gdx.graphics.getHeight() - (sprite.getHeight() / 2))
		{
			sfxBounce.play();
			velocity.y = -5;
		}
		
		location.x += velocity.x * acceleration;
		location.y += velocity.y * acceleration;
		
		drawRect = getDrawRect(location);
	}
	
	public void bounce(int speed, float accel)
	{
		sfxBounce.play();
		velocity.y = speed;
		acceleration = accel;
	}
	
	public void breakBounce()
	{
		velocity.y = -5;
	}
	
	public boolean lost()
	{
		if(location.y <= 0 + sprite.getHeight() / 2)
		{
			return true;
		}
		return false;
	}
	
	public boolean isMoving()
	{
		return isMoving;
	}
	
	public void dispose()
	{
		super.dispose();
		sfxBounce.dispose();
	}
}
