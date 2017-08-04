package com.rocktap.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rocktap.screen.PlayScreen;
import com.rocktap.screen.SplashScreen;

/**
 * classe Game de RockTap
 */
public class RocktapeGame extends Game {

	private PlayScreen playScreen;
	private SplashScreen splashScreen;
	private boolean devMode;

	public RocktapeGame(boolean devMode) {
		this.devMode = devMode;
	}

	@Override
	public void create () {
		if(devMode) {
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
			playScreen = new PlayScreen();
			setScreen(playScreen);
		} else {
			Gdx.app.setLogLevel(Application.LOG_ERROR);
			splashScreen=new SplashScreen(this);
			setScreen(splashScreen);
		}
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		playScreen.dispose();
	}
}
