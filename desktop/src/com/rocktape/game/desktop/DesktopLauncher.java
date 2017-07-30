package com.rocktape.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rocktap.utils.Constants;
import com.rocktap.game.RocktapeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= Constants.V_WIDTH;
		config.height=Constants.V_HEIGHT;
		new LwjglApplication(new RocktapeGame(), config);
	}
}
