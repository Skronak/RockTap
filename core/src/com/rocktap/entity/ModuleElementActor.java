package com.rocktap.entity;

import com.rocktap.Animation.AnimatedBaseActor;
import com.rocktap.actor.StationActor;

/**
 * Created by Skronak on 17/07/2017.
 * Module element from a station
 *
 */
public class ModuleElementActor extends AnimatedBaseActor {
    public StationActor stationActor;

    public ModuleElementActor(StationActor stationActor) {
        this.stationActor = stationActor;
    }

    public void act(float delta) {
        super.act(delta);
        this.setY(stationActor.getY());
    }
//*****************************************************
//                  GETTER & SETTER
// ****************************************************
}
