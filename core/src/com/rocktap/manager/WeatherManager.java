package com.rocktap.manager;
import com.rocktap.Animation.RainEffect;
import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.SnowEffectActor;

import java.util.Random;

public class WeatherManager {

    private RainEffect rainEffect;
    private PlayScreen screen;
    private SnowEffectActor snowEffectActor;
    private Random random;


    public WeatherManager(PlayScreen screen){
        this.screen = screen;
        rainEffect = new RainEffect();
        random = new Random();
        snowEffectActor = new SnowEffectActor(screen);
        snowEffectActor.allowCompletion();

        screen.getLayer1GraphicObject().addActor(snowEffectActor);

    }

    /**
     *
     */
    public void addRandomWeather() {
        int val = random.nextInt(2);
        if (val==0){
            addSnow();
        }
    }

    public void addSnow() {
        snowEffectActor.start();
    }

    public void removeSnow(){
        snowEffectActor.stop();
    }
        /**
         * Use particle instead
         */
    public void addRain(){
        screen.getLayer1GraphicObject().addActor(rainEffect);
    }

    public void removeRain(){
        screen.getLayer1GraphicObject().removeActor(rainEffect);
    }

    public boolean isComplete(){
        return snowEffectActor.particleEffect.isComplete();
    }

 }
