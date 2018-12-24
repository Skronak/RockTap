package com.rocktap.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rocktap.Animation.AnimatedBaseActor;
import com.rocktap.actor.StationActor;

/**
 * Created by Skronak on 17/07/2017.
 * Module element from a station
 *
 */
public class ModuleElementActor extends AnimatedBaseActor {
    public StationActor stationActor;
    public ModuleElement moduleElement;

    public ModuleElementActor(StationActor stationActor, ModuleElement moduleElement) {
        this.stationActor = stationActor;
        this.moduleElement=moduleElement;
        init();
    }

    public void init(){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture("sprites/upgrade/"+moduleElement.getSprite())));
        storeAnimation("idle", new Animation(0.5f, frames));
    }
    public void act(float delta) {
        super.act(delta);
        this.setY(stationActor.getY()+moduleElement.getPosY());
    }
//*****************************************************
//                  GETTER & SETTER
// ****************************************************
}
