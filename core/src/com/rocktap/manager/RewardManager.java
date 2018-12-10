package com.rocktap.manager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rocktap.Animation.AnimatedBaseActor;
import com.rocktap.entity.GameInformation;
import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.Constants;

import static com.badlogic.gdx.Gdx.files;

/**
 * RewardManager
 */
public class RewardManager {
    PlayScreen playScreen;
    AnimatedBaseActor restRewardActor;
    boolean rewardToCollect;

    public RewardManager(PlayScreen playScreen){
        this.playScreen = playScreen;
    }

    public void calculateRestReward() {
        long diff = System.currentTimeMillis() - GameInformation.INSTANCE.getLastLogin();
        float hours = (diff / (1000 * 60 * 60));

        if (hours >= Constants.DELAY_HOURS_REWARD) {
            // Calculer reward afk
        }
        rewardToCollect=true;
    }

    public AnimatedBaseActor getRestRewardActor(){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(files.internal("sprites/reward/chest1.png"))));
        frames.add(new TextureRegion(new Texture(files.internal("sprites/reward/chest1.png"))));
        frames.add(new TextureRegion(new Texture(files.internal("sprites/reward/chest1.png"))));
        Animation openAnimation = new Animation(2f, frames);

        AnimatedBaseActor restRewardActor= new AnimatedBaseActor();
        restRewardActor.storeAnimation("idle",openAnimation);
        // add idle animation with chest shaking each x sec "comme un reveil"
        restRewardActor.setVisible(true);
        return restRewardActor;
    }
}
