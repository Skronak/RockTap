package com.rocktap.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rocktap.screen.PlayScreen;
import com.rocktap.screen.SplashScreen;

public class RocktapeGame extends Game {

	PlayScreen playScreen;
	SplashScreen splashScreen;


	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
//		splashScreen=new SplashScreen(this);
//		setScreen(splashScreen);

		playScreen = new PlayScreen();
		setScreen(playScreen);
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
