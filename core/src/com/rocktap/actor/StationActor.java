package com.rocktap.actor;

import com.rocktap.Animation.AnimatedActor;
import com.rocktap.Animation.AnimatedBaseActor;
import com.rocktap.utils.Constants;

public class StationActor extends AnimatedBaseActor {

    private float stationAnimationTimer;
    private boolean stationAnimationUp; // indique si animation de montée ou descente
    private AnimatedActor beamActor;

    public StationActor(){
        stationAnimationTimer = 0f;
        stationAnimationUp = false;

    }

    public void updatePosition(){
    // station animation
        if(stationAnimationTimer >= 0.2f) {
        if (this.getY() >= Constants.STATION_ANIMATION_MAX_ALTITUDE && stationAnimationUp) {
            stationAnimationUp = false;
        }
        if (this.getY() <= Constants.STATION_ANIMATION_MIN_ALTITUDE && !stationAnimationUp) {
            stationAnimationUp = true;
        }

        if (stationAnimationUp) {
            this.moveBy(0,1);
        } else {
            this.moveBy(0,-1);
        }
        stationAnimationTimer = 0f;
    }
  }

    @Override
    public void act(float deltaTime)
    {
        super.act(deltaTime);
        stationAnimationTimer += deltaTime;
        updatePosition();
    }
}
