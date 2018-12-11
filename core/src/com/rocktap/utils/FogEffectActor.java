package com.rocktap.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rocktap.screen.PlayScreen;

/**
 * Created by Skronak on 23/08/2017.
 */

public class FogEffectActor extends AbstractParticleEffectActor {

    private PlayScreen playScreen;
    private Image fog;

    public FogEffectActor(PlayScreen screen){
        super();
        playScreen = screen;

        particleEffect.load(Gdx.files.internal("particles/fog.party"),Gdx.files.internal("particles"));
        particleEffect.getEmitters().first().setPosition(Constants.V_WIDTH/2, Constants.V_HEIGHT/3);
        particleEffect.scaleEffect(0.5f);

        this.stop();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
