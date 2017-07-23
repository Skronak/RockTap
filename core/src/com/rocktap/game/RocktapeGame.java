package com.rocktap.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rocktap.menu.MenuScreen;
import com.rocktap.screen.PlayScreen;
import com.rocktap.screen.SplashScreen;

public class RocktapeGame extends Game {

	PlayScreen playScreen;
	SplashScreen splashScreen;
	MenuScreen menuScreen;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		splashScreen=new SplashScreen(this);
		menuScreen = new MenuScreen();
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
