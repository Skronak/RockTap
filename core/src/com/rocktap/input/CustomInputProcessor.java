package com.rocktap.input;

import com.badlogic.gdx.InputProcessor;
import com.rocktap.utils.Constants;
import com.rocktap.manager.GameManager;
import com.rocktap.screen.PlayScreen;

import java.util.Random;

/**
 * Created by Skronak on 29/01/2017.
 * Listener des input sur le PLayscreen
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
        playScreen.processHit();
        if (randCritical == 1) {
            gameManager.increaseGoldCritical();
            playScreen.processCriticalHit(gameManager.getCriticalValue());
        } else {
            gameManager.increaseGold();
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
