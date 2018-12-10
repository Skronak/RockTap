package com.rocktap.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rocktap.actor.BeamActor;
import com.rocktap.actor.StationActor;
import com.rocktap.manager.AssetManager;
import com.rocktap.manager.GameManager;
import com.rocktap.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Gdx.files;

public class StationEntity {
    public StationActor stationActor;
    public List<ModuleElementDTO> moduleToDraw;
    public BeamActor beamActor;
    public GameManager gameManager;

    public StationEntity(GameManager gameManager){
        this.gameManager=gameManager;

        initModule();
        initStation();
        initBeam();
    }

    public void initStation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ GameInformation.INSTANCE.getStationId()+"_0.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ GameInformation.INSTANCE.getStationId()+"_1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ GameInformation.INSTANCE.getStationId()+"_2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ GameInformation.INSTANCE.getStationId()+"_3.png"))));
        Animation idleAnimation = new Animation(2f, frames);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);

        stationActor = new StationActor();
        stationActor.storeAnimation("idle",idleAnimation);
        stationActor.setSize(200,100);
        stationActor.setPosition(70, Constants.STATION_ANIMATION_MAX_ALTITUDE);
    }

    public void initModule() {
        moduleToDraw=new ArrayList<ModuleElementDTO>();
        for (int i = 0; i < GameInformation.INSTANCE.getUpgradeLevelList().size(); i++) {
            // Pour les upgrade 1-8, si lvl i > 0 alors ajoute dans liste a afficher
            if (GameInformation.INSTANCE.getUpgradeLevelList().get(i) > 0) {
                ModuleElementDTO moduleElementDTO = AssetManager.INSTANCE.getModuleElementList().get(i);
                moduleElementDTO.setTextureRegion(new TextureRegion(new Texture(Gdx.files.internal(("sprites/upgrade/" + moduleElementDTO.getLevel().get(GameInformation.INSTANCE.getUpgradeLevelList().get(i)).getSprite())))));
                moduleToDraw.add(moduleElementDTO);
            }
        }
    }
    private void initBeam() {
        beamActor = new BeamActor(stationActor);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b3.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b4.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b5.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b6.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b7.png"))));
        beamActor.storeAnimation("idle",new Animation(0.2f,frames,Animation.PlayMode.LOOP));

        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(files.internal("sprites/beamCritical.png"))));
        beamActor.storeAnimation("critical", new Animation(0.2f,frames,Animation.PlayMode.LOOP));

        beamActor.setVisible(false);
        beamActor.setSize(28,250);
        beamActor.setVisible(false);

    }

}
