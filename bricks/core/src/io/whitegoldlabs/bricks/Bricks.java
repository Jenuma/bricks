package io.whitegoldlabs.bricks;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import io.whitegoldlabs.bricks.model.Ball;
import io.whitegoldlabs.bricks.model.Brick;
import io.whitegoldlabs.bricks.model.Flipper;

public class Bricks extends ApplicationAdapter
{
	SpriteBatch batch;
	BitmapFont font;
	GlyphLayout gameOverText;
	GlyphLayout playAgainText;
	Sound sfxBlip;
	
	Flipper flipper;
	Ball ball;
	List<Brick> bricks;
	
	int score;
	boolean gameOver;
	boolean hovering;
	
	@Override
	public void create()
	{
		batch = new SpriteBatch();
		
		font = new BitmapFont(Gdx.files.internal("pressstart2p.fnt"));
		font.setColor(0.2f, 0.3f, 0, 1);
		
		sfxBlip = Gdx.audio.newSound(Gdx.files.internal("blip.wav"));
		
		initGame();
	}
	
	public void initGame()
	{
		gameOverText = new GlyphLayout(font, "Game Over");
		playAgainText = new GlyphLayout(font, "Play Again?");
		
		flipper = new Flipper(new Vector2((Gdx.graphics.getWidth() / 2), 50f));
		ball = new Ball(new Vector2((Gdx.graphics.getWidth() / 2), 73f));
		
		// Create bricks.
		bricks = new ArrayList<>();
		bricks.add(new Brick(new Vector2(20, 565)));
		bricks.add(new Brick(new Vector2(130, 565)));
		bricks.add(new Brick(new Vector2(240, 565)));
		bricks.add(new Brick(new Vector2(350, 565)));
		bricks.add(new Brick(new Vector2(460, 565)));
		bricks.add(new Brick(new Vector2(570, 565)));
		bricks.add(new Brick(new Vector2(680, 565)));
		
		bricks.add(new Brick(new Vector2(75, 535)));
		bricks.add(new Brick(new Vector2(185, 535)));
		bricks.add(new Brick(new Vector2(295, 535)));
		bricks.add(new Brick(new Vector2(405, 535)));
		bricks.add(new Brick(new Vector2(515, 535)));
		bricks.add(new Brick(new Vector2(625, 535)));
		
		bricks.add(new Brick(new Vector2(20, 505)));
		bricks.add(new Brick(new Vector2(130, 505)));
		bricks.add(new Brick(new Vector2(240, 505)));
		bricks.add(new Brick(new Vector2(350, 505)));
		bricks.add(new Brick(new Vector2(460, 505)));
		bricks.add(new Brick(new Vector2(570, 505)));
		bricks.add(new Brick(new Vector2(680, 505)));
		
		bricks.add(new Brick(new Vector2(75, 475)));
		bricks.add(new Brick(new Vector2(185, 475)));
		bricks.add(new Brick(new Vector2(295, 475)));
		bricks.add(new Brick(new Vector2(405, 475)));
		bricks.add(new Brick(new Vector2(515, 475)));
		bricks.add(new Brick(new Vector2(625, 475)));
		
		score = 0;
		gameOver = false;
		hovering = false;
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0.4f, 0.6f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		font.draw(batch, "Bricks v0.1 by Jenuma, 2017", 5, 15);
		font.draw(batch, "Score: " + score, 620, 15);
		
		// Draw Game Over menu.
		if(gameOver)
		{
			font.draw(batch, gameOverText,
					(Gdx.graphics.getWidth() - gameOverText.width) / 2,
					(Gdx.graphics.getHeight() - gameOverText.height) / 2);
			
			font.draw(batch, playAgainText,
					(Gdx.graphics.getWidth() - playAgainText.width) / 2,
					((Gdx.graphics.getHeight() - playAgainText.height) / 2) - 32);
			
			int mouseX = Gdx.input.getX();
			int mouseY = Gdx.graphics.getHeight() - 1 - Gdx.input.getY();
			
			if(mouseX >= (Gdx.graphics.getWidth() - playAgainText.width) / 2 &&
				mouseX <= (Gdx.graphics.getWidth() + playAgainText.width) / 2 &&
				mouseY >= ((Gdx.graphics.getHeight() - playAgainText.height) / 2) - 45 &&
				mouseY <= ((Gdx.graphics.getHeight() + playAgainText.height) / 2) - 45)
			{
				if(!hovering)
				{
					hovering = true;
					sfxBlip.play();
				}
				
				font.setColor(0.27f, 0.4f, 0, 1);
				playAgainText.setText(font, "Play Again?");
				font.setColor(0.2f, 0.3f, 0, 1);

				if(Gdx.input.isTouched())
				{
					initGame();
				}
			}
			else
			{
				hovering = false;
				font.setColor(0.2f, 0.3f, 0, 1);
				playAgainText.setText(font, "Play Again?");
			}
		}
		// Draw game entities.
		else
		{
			flipper.draw(batch);
			ball.draw(batch);
			
			for(Brick brick : bricks)
			{
				brick.draw(batch);
			}
		}
		
		batch.end();
		
		// Update logic.
		if(!gameOver)
		{
			// Check for lose.
			if(ball.lost())
			{
				ball.dispose();
				gameOver = true;
			}
			
			// Check for win.
			if(bricks.size() == 0)
			{
				gameOver = true;
				gameOverText.setText(font, "You Win!");
			}
			
			// Move ball.
			if(ball.isMoving())
			{
				// Check for flipper collision.
				Rectangle ballHitbox = ball.getHitbox();
				Rectangle flipperHitbox = flipper.getHitbox();
				
				if(ballHitbox.overlaps(flipperHitbox))
				{
					if(ballHitbox.x >= flipperHitbox.x + 66 &&
						ballHitbox.x <= flipperHitbox.x + 133)
					{
						ball.bounce(5, 1.5f);
					}
					else
					{
						ball.bounce(3, 2.0f);
					}
				}
				
				// Check for brick collision.
				for(Brick brick : bricks)
				{
					if(ballHitbox.overlaps(brick.getHitbox()))
					{
						brick.breakBrick();
						ball.breakBounce();
						bricks.remove(brick);
						score += 100;
						break;
					}
				}
				
				ball.move();
			}
			
			// Move flipper if holding it.
			if(flipper.isHeld() && Gdx.input.isTouched())
			{
				flipper.move(Gdx.input.getX());
			}
			else if(flipper.isHeld() && !Gdx.input.isTouched())
			{
				flipper.letGo();
			}
			else if(!flipper.isHeld() && Gdx.input.isTouched())
			{
				if(flipper.clickedOn(Gdx.input.getX(), Gdx.input.getY()))
				{
					flipper.hold();
					ball.startMoving();
				}
			}
		}
	}
	
	@Override
	public void dispose()
	{
		batch.dispose();
		flipper.dispose();
		ball.dispose();
		sfxBlip.dispose();
		
	}
}
