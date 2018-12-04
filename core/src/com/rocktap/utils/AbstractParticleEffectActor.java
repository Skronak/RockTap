package com.rocktap.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Generic actor containing a particle emitter
 */
abstract class AbstractParticleEffectActor extends Actor {
    public ParticleEffect particleEffect;

    public AbstractParticleEffectActor() {
        super();
        this.particleEffect =  new ParticleEffect();
    }

    @Override
    public void draw (Batch spriteBatch, float delta) {
        particleEffect.draw(spriteBatch, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        particleEffect.update(delta);
    }

    public void start() {
        particleEffect.start();
    }

    public void allowCompletion() {
        particleEffect.allowCompletion();
    }

    public void disableCompletion(){
    }

    public void stop(){
        particleEffect.getEmitters().get(0).allowCompletion();
    }

}