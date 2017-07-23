package com.rocktap.station;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Skronak on 23/07/2017.
 */

public class stationActor {
    /**
     * Acteur possendant une animation
     */
    //TODO rendre la classe generique (module) et ajouter prise en charge atlas
    public static class Station extends Actor {

        private Animation idleAnimation;
        private Array<TextureRegion> frames;
        private float deltatime;
        private int width;
        private int height;
        private List<StationUpgrade> upgradeList;
        private float animSpeed;
        private TextureRegion currentFrame;
        //TODO ajouter le beam

        public Station (int posX, int posY, int width, int height, float animSpeed) {
            deltatime = 0;
            this.width = width;
            this.height = height;
            this.animSpeed = animSpeed;
            this.setPosition(posX, posY);
            this.upgradeList = demoMode();

            frames = new Array<TextureRegion>();
            frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/ship.png"))));
            frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/ship1.png"))));
            frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/ship2.png"))));
            frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/ship3.png"))));
            idleAnimation = new Animation(animSpeed, frames);
            idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        }

        private List<StationUpgrade> demoMode() {
            List<StationUpgrade> stationUpgradeList = new ArrayList<StationUpgrade>();
            int[] cost = {100,200,400,600,800,1200};
            StationUpgrade stationUpgrade = new StationUpgrade(1,0,100, 94, 30, 25, cost, "antenna.png");
            StationUpgrade stationUpgrade2 = new StationUpgrade(1,0,80, -18, 42, 25, cost, "beamup2.png");
            stationUpgradeList.add(stationUpgrade);
            stationUpgradeList.add(stationUpgrade2);
            return stationUpgradeList;
        }

        @Override
        public void draw (Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            currentFrame = (TextureRegion) idleAnimation.getKeyFrame(deltatime, true);
            for (StationUpgrade upgrade : upgradeList) {
                batch.draw(upgrade.getTexture(), getX()+ upgrade.getPosX(), getY() + upgrade.getPosY(), upgrade.getWidth(), upgrade.getHeight());
            }
            batch.draw(currentFrame,getX(),getY(),width,height);
        }

        @Override
        public void act(float deltaTime)
        {
            super.act(deltaTime);
            deltatime += deltaTime;
        }
    }
}
