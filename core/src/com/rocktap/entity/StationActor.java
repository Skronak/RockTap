package com.rocktap.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.rocktap.Animation.AnimatedActor;
import com.rocktap.game.AccountInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skronak on 23/07/2017.
 */
//TODO rendre la classe generique (module) et ajouter prise en charge atlas
public class StationActor extends Actor {
    private Animation idleAnimation;
    private Array<TextureRegion> frames;
    private float deltatime;
    private int width;
    private int height;
    private List<StationUpgrade> upgradeList;
    private float animSpeed;
    private TextureRegion currentFrame;
    private AnimatedActor beamActor;
    private AccountInformation accountInformation;

    public StationActor (int posX, int posY, int width, int height, float animSpeed, AccountInformation accountInformation) {
        deltatime = 0;
        this.width = width;
        this.height = height;
        this.animSpeed = animSpeed;
        this.setPosition(posX, posY);
        this.accountInformation = accountInformation;
        this.upgradeList = initUpgrade();
        beamActor = initBeam();

        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+accountInformation.getStationId()+"_0.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+accountInformation.getStationId()+"_1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+accountInformation.getStationId()+"_2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+accountInformation.getStationId()+"_3.png"))));
        idleAnimation = new Animation(animSpeed, frames);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Generation des upgrade de la station
     * @return
     */
    private List<StationUpgrade> initUpgrade() {
        List<StationUpgrade> stationUpgradeList = new ArrayList<StationUpgrade>();
        int[] cost = {100,200,400,600,800,1200};
        if (accountInformation.getUpgradeLevel1()>0) {
            StationUpgrade stationUpgrade = new StationUpgrade(1,0,100, 94, 30, 25, cost, "sprites/upgrade/upgrade1.png");
            stationUpgradeList.add(stationUpgrade);
        }
        if (accountInformation.getUpgradeLevel2()>0) {
            StationUpgrade stationUpgrade = new StationUpgrade(1,0,80, -18, 42, 25, cost, "sprites/upgrade/upgrade2.png");
            stationUpgradeList.add(stationUpgrade);
        }
        if (accountInformation.getUpgradeLevel3()>0) {
            StationUpgrade stationUpgrade = new StationUpgrade(1,0,100, 94, 30, 25, cost, "sprites/upgrade/upgrade3.png");
            stationUpgradeList.add(stationUpgrade);
        }
        return stationUpgradeList;
    }


    /**
     * Initialisation du beamActor de la station
     * @return
     */
    private AnimatedActor initBeam() {
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b3.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b4.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b5.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b6.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b7.png"))));
        beamActor = new AnimatedActor(157,50, 28,270, 0.2f, frames);
        beamActor.setVisible(false);
        return beamActor;
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

//*****************************************************
//                  GETTER & SETTER
//****************************************************
    public AnimatedActor getBeamActor() {
        return beamActor;
    }

    public void setBeamActor(AnimatedActor beamActor) {
        this.beamActor = beamActor;
    }

}
