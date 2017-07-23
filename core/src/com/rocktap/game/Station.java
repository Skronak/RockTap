package com.rocktap.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

/**
 * Acteur possendant une animation
 */
//TODO rendre la classe generique (module) et ajouter prise en charge atlas
public class Station extends Actor {

    private Animation idleAnimation;
    private Array<TextureRegion> frames;
    private float deltatime;
    private int width;
    private int height;
    private float animSpeed;
    private TextureRegion currentFrame;

    public Station (int posX, int posY, int width, int height, float animSpeed) {
        Table table = new Table();
        deltatime = 0;
        this.width = width;
        this.height = height;
        this.animSpeed = animSpeed;
        this.setPosition(posX, posY);

        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/ship.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/ship1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/ship2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/ship3.png"))));
        idleAnimation = new Animation(animSpeed, frames);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        currentFrame = (TextureRegion) idleAnimation.getKeyFrame(deltatime, true);
        batch.draw(currentFrame,getX(),getY(),width,height);
    }

    @Override
    public void act(float deltaTime)
    {
        super.act(deltaTime);
        deltatime += deltaTime;
    }
}
