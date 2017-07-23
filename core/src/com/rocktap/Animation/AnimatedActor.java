package com.rocktap.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Skronak on 09/02/2017.
 */
public class AnimatedActor extends Actor {

        private Animation idleAnimation;
        private float deltatime;
        private int width;
        private int height;
        private TextureRegion currentFrame;

        public AnimatedActor (int posX, int posY, int width, int height, float animSpeed, Array<TextureRegion> frames) {
            deltatime = 0;
            this.width = width;
            this.height = height;
            this.setPosition(posX, posY);
            idleAnimation = new Animation(animSpeed, frames);
            idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        }

        @Override
        public void draw (Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            Color color = getColor();
            batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            currentFrame = (TextureRegion) idleAnimation.getKeyFrame(deltatime, true);
            batch.draw(currentFrame,getX(),getY(),width,height);
        }

        @Override
        public void act(float deltaTime)
        {
            super.act(deltaTime);
            deltatime += deltaTime;
        }


    public Animation getIdleAnimation() {
        return idleAnimation;
    }

    public void increaseSpeed(float value) {
            //if (idleAnimation.getFrameDuration() > 0.05f){
            //    Gdx.app.log("speed",String.valueOf(idleAnimation.getFrameDuration()));
                idleAnimation.setFrameDuration(value);
            }

    public void decreaseSpeed(float value){
//        if (idleAnimation.getFrameDuration() <= 0.2f){
//            Gdx.app.log("speed",String.valueOf(idleAnimation.getFrameDuration()));
            idleAnimation.setFrameDuration(value);
    }
}
