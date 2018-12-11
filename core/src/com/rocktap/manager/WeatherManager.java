package com.rocktap.manager;

import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.FogEffectActor;
import com.rocktap.utils.RainEffectActor;
import com.rocktap.utils.SnowEffectActor;

import java.util.Random;

public class WeatherManager {

    private RainEffectActor rainEffectActor;
    private PlayScreen screen;
    private SnowEffectActor snowEffectActor;
    private FogEffectActor fogEffectActor;
    private Random random;

    public WeatherManager(PlayScreen screen){
        this.screen = screen;
        random = new Random();
        rainEffectActor = new RainEffectActor(screen);
        snowEffectActor = new SnowEffectActor(screen);
        fogEffectActor = new FogEffectActor(screen);

        screen.getLayer1GraphicObject().addActor(snowEffectActor);
        screen.getLayer1GraphicObject().addActor(rainEffectActor);
        screen.getLayer1GraphicObject().addActor(fogEffectActor);
    }

    /**
     * Start a random climatic effect
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

    public void addFog(){
        fogEffectActor.start();
    }

    public void stopFog(){
        fogEffectActor.stop();
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

    /**
     * Check if any climatic effect is occuring
     * @return
     */
    public boolean isComplete(){
        return snowEffectActor.particleEffect.isComplete()&& rainEffectActor.isComplete();
    }

 }
