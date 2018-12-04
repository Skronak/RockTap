package com.rocktap.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.rocktap.manager.GameManager;
import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.Constants;

import java.util.Random;

/**
 * Created by Skronak on 29/01/2017.
 * Listener des input sur le Playscreen
 */
public class CustomInputProcessor implements InputProcessor {

    private Random random;
    private PlayScreen playScreen;
    private GameManager gameManager;

    public CustomInputProcessor(PlayScreen playScreen) {
        this.playScreen = playScreen;
        this.random = new Random();
        this.gameManager = playScreen.getGameManager();
    }

    @Override
    public boolean keyDown(int keycode) {
        if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) )
            gameManager.getGameInformation().saveInformation();
            Gdx.app.debug("Closing application", "close");
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int randCritical = random.nextInt(Constants.CRITICAL_CHANCE) + 1;
        this.gameManager.getGameInformation().setTotalTapNumber(this.gameManager.getGameInformation().getTotalTapNumber()+1);
        playScreen.processHit();
        if (randCritical == 1) {
            gameManager.increaseGoldCritical();
            playScreen.processCriticalHit(gameManager.getCriticalValue());
        } else {
            gameManager.increaseGoldActive();
            playScreen.processNormalHit();
        }
        playScreen.processPointerHitAnimation(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
