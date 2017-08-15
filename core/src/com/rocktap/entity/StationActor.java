package com.rocktap.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.rocktap.Animation.AnimatedActor;
import com.rocktap.manager.GameManager;

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
    private List<UpgradeActor> upgradeList;
    private float animSpeed;
    private TextureRegion currentFrame;
    private AnimatedActor beamActor;
    private GameManager gameManager;

    public StationActor (int posX, int posY, int width, int height, float animSpeed, GameManager gameManager) {
        deltatime = 0;
        this.width = width;
        this.height = height;
        this.animSpeed = animSpeed;
        this.setPosition(posX, posY);
        this.gameManager = gameManager;
        upgradeList = loadUpgrade();
        beamActor = initBeam();

        //Animation par defaut de la station
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ gameManager.getGameInformation().getStationId()+"_0.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ gameManager.getGameInformation().getStationId()+"_1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ gameManager.getGameInformation().getStationId()+"_2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ gameManager.getGameInformation().getStationId()+"_3.png"))));
        idleAnimation = new Animation(animSpeed, frames);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Generation des upgrade de la station
     * @return
     */
    public List<UpgradeActor> loadUpgrade() {
        this.upgradeList = new ArrayList<UpgradeActor>();
        for (int i=0;i<gameManager.getGameInformation().getUpgradeLevelList().size();i++) {
            if (gameManager.getGameInformation().getUpgradeLevelList().get(i) > 0) {
                upgradeList.add(gameManager.getAssetManager().getUpgradeFile().get(i));
                upgradeList.get(i).setTextureRegion(new TextureRegion(new Texture(Gdx.files.internal(("sprites/upgrade/"+upgradeList.get(i).getSprite())))));
            }
        }
        return upgradeList;
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
        //TODO upgradeActor s'affichent eux meme
        for (int i=0;i<upgradeList.size();i++) {
            batch.draw(upgradeList.get(i).getTextureRegion(), getX()+ upgradeList.get(i).getPosX(), getY() + upgradeList.get(i).getPosY(), upgradeList.get(i).getWidth(), upgradeList.get(i).getHeight());
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
