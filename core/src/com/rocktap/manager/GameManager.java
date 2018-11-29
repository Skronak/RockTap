package com.rocktap.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.rocktap.actor.StationActor;
import com.rocktap.entity.GameInformation;
import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.Constants;
import com.rocktap.utils.GameState;
import com.rocktap.utils.LargeMath;
import com.rocktap.utils.ValueDTO;

/**
 * Created by Skronak on 30/07/2017.
 *
 * Classe de gestion globale du jeu
 * gerant le lien entre PlayScreen et gameInformation
 */
public class GameManager {
    private StationActor stationActor;

    private AssetManager assetManager;

    private PlayScreen playScreen;

    private LargeMath largeMath;

    // Etat du jeu
    private GameState currentState;

    public GameManager(PlayScreen playScreen) {
        currentState = GameState.IN_GAME;
        assetManager = new AssetManager();
        this.playScreen = playScreen;
        largeMath = new LargeMath();
    }

    public void initStation(){
        stationActor = new StationActor();
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
        stationActor.setPosition(70,400);
    }
    /**
     * methode d'ajout d'or au tap
     */
    public void increaseGoldActive(){
        ValueDTO newValue = largeMath.increaseValue(GameInformation.INSTANCE.getCurrentGold(), GameInformation.INSTANCE.getCurrency(), GameInformation.INSTANCE.getGenGoldActive(), GameInformation.INSTANCE.getGenCurrencyActive());
        GameInformation.INSTANCE.setCurrentGold(newValue.getValue());
        GameInformation.INSTANCE.setCurrency(newValue.getCurrency());
        largeMath.formatGameInformation();
    }

    /**
     * Ajout d'or passif
     */
    public void increaseGoldPassive(){
        ValueDTO newValue = largeMath.increaseValue(GameInformation.INSTANCE.getCurrentGold(), GameInformation.INSTANCE.getCurrency(), GameInformation.INSTANCE.getGenGoldPassive(), GameInformation.INSTANCE.getGenCurrencyPassive());
        GameInformation.INSTANCE.setCurrentGold(newValue.getValue());
        GameInformation.INSTANCE.setCurrency(newValue.getCurrency());
        largeMath.formatGameInformation();
    }

    public void calculateRestReward() {
        long diff = System.currentTimeMillis() - GameInformation.INSTANCE.getLastLogin();
        float hours   = (diff / (1000*60*60));

        if (hours >= Constants.DELAY_HOURS_REWARD) {
            // Calculer reward afk
        }
    }

    // Methode d'ajout d'or lors d'un critique
    public void increaseGoldCritical() {
//        gameInformation.setCurrentGold(gameInformation.getCurrentGold() + getCriticalValue());
    }

    /**
     * Value d'un coup critique
     * @return
     */
    public float getCriticalValue(){
        return (GameInformation.INSTANCE.getGenGoldActive() * GameInformation.INSTANCE.getCriticalRate());
    }

    //*****************************************************
//                  GETTER & SETTER
// ****************************************************
    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }

    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public LargeMath getLargeMath() {
        return largeMath;
    }

    public void setLargeMath(LargeMath largeMath) {
        this.largeMath = largeMath;
    }

    public StationActor getStationActor() {
        return stationActor;
    }
}
