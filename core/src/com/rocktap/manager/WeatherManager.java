package com.rocktap.manager;


import com.rocktap.Animation.RainEffect;
import com.rocktap.screen.PlayScreen;

public class WeatherManager {

    private RainEffect rainEffect;
    private PlayScreen screen;

    public WeatherManager(PlayScreen screen){
        this.screen = screen;
        rainEffect = new RainEffect();
    }

    /**
     * Use particle instead
     */
    public void addRain(){
        screen.getLayer0GraphicObject().addActor(rainEffect);
    }

    public void removeRain(){
        screen.getLayer0GraphicObject().removeActor(rainEffect);

    }
}
