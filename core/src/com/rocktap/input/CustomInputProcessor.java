package com.rocktap.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rocktap.game.AccountInformation;
import com.rocktap.game.Constants;
import com.rocktap.screen.PlayScreen;

import java.util.Random;

/**
 * Created by Skronak on 29/01/2017.
 */
public class CustomInputProcessor implements InputProcessor {

    private Stage stage;
    private Random random;
    private PlayScreen playScreen;
    private AccountInformation accountInformation;

    public CustomInputProcessor(Stage stage, PlayScreen playScreen) {
        this.stage = stage;
        this.playScreen = playScreen;
        this.random = new Random();
        this.accountInformation = playScreen.getAccountInformation();
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
            playScreen.processCriticalHit();
            accountInformation.increaseGoldCritical();
        } else {
            playScreen.processNormalHit();
            accountInformation.increaseGold();
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
