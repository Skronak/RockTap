package com.rocktap.manager;

import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.RainEffectActor;
import com.rocktap.utils.SnowEffectActor;

import java.util.Random;

public class WeatherManager {

    private RainEffectActor rainEffectActor;
    private PlayScreen screen;
    private SnowEffectActor snowEffectActor;
    private Random random;


    public WeatherManager(PlayScreen screen){
        this.screen = screen;
        rainEffectActor = new RainEffectActor(screen);

        random = new Random();
        snowEffectActor = new SnowEffectActor(screen);

        screen.getLayer1GraphicObject().addActor(snowEffectActor);
        screen.getLayer1GraphicObject().addActor(rainEffectActor);
    }

    /**
     *
     */
    public void addRandomWeather() {
        if (snowEffectActor.isComplete() && rainEffectActor.isComplete()) {
            int val = random.nextInt(3);
            if (val == 2) {
                addSnow();
            } else if (val==0) {
                addRain();
            }
        } else {
            stopAll();
        }
    }

    /**
     * Stop all climatic effect
     */
    public void stopAll(){
        snowEffectActor.stop();
        rainEffectActor.stop();
    }

    public void addSnow() {
        snowEffectActor.start();
    }

    public void removeSnow(){
        snowEffectActor.stop();
    }

    public void addRain(){
        rainEffectActor.start();
    }

    public void removeRain(){
        rainEffectActor.stop();
    }

    public boolean isComplete(){
        return snowEffectActor.particleEffect.isComplete()&& rainEffectActor.isComplete();
    }

 }
