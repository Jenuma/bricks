package io.whitegoldlabs.bricks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.whitegoldlabs.bricks.Bricks;
import io.whitegoldlabs.bricks.constants.Constants;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Bricks";
		config.width = Constants.WINDOW_WIDTH;
		config.height = Constants.WINDOW_HEIGHT;
		
		new LwjglApplication(new Bricks(), config);
	}
}
