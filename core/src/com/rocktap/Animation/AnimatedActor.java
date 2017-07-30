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

        private Animation animation;
        private float deltatime;
        private TextureRegion currentFrame;

        public AnimatedActor (int posX, int posY, int width, int height, float animSpeed, Array<TextureRegion> frames) {
            deltatime = 0;
            this.setWidth(width);
            this.setHeight(height);
            this.setPosition(posX, posY);
            animation = new Animation(animSpeed, frames);
            animation.setPlayMode(Animation.PlayMode.LOOP);
        }

        @Override
        public void draw (Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            Color color = getColor();
            batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            currentFrame = (TextureRegion) animation.getKeyFrame(deltatime, true);
            batch.draw(currentFrame,getX(),getY(),getWidth(),getHeight());
        }

        @Override
        public void act(float deltaTime)
        {
            super.act(deltaTime);
            deltatime += deltaTime;
        }


    public Animation getIdleAnimation() {
        return animation;
    }

    public void increaseSpeed(float value) {
            //if (idleAnimation.getFrameDuration() > 0.05f){
            //    Gdx.app.log("speed",String.valueOf(idleAnimation.getFrameDuration()));
        animation.setFrameDuration(value);
            }

    public void decreaseSpeed(float value){
//        if (idleAnimation.getFrameDuration() <= 0.2f){
//            Gdx.app.log("speed",String.valueOf(idleAnimation.getFrameDuration()));
        animation.setFrameDuration(value);
    }
}
