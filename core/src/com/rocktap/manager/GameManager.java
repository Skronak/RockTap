package com.rocktap.manager;

import com.rocktap.entity.GameInformation;
import com.rocktap.entity.StationActor;
import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.Constants;
import com.rocktap.utils.GameState;

/**
 * Created by Skronak on 30/07/2017.
 *
 * Classe de gestion globale du jeu
 * gerant le lien entre PlayScreen et gameInformation
 */
public class GameManager {
    // Information persistentes
    private GameInformation gameInformation;

    private StationActor stationActor;

    private AssetManager assetManager;

    private PlayScreen playScreen;

    // Etat du jeu
    private GameState currentState;

    public GameManager(GameInformation gameInformation, PlayScreen playScreen) {
        this.gameInformation = gameInformation;
        currentState = GameState.IN_GAME;
        assetManager = new AssetManager();
        this.playScreen = playScreen;
    }

    /**
     * Generation d'une stationActor en fonction des informations du compte
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param animSpeed
     * @return
     */
    public StationActor initStationActor(int posX, int posY, int width, int height, float animSpeed) {
        stationActor = new StationActor(posX,posY,width,height,animSpeed, this);
        return stationActor;
    }
    /**
     * methode d'ajout d'or
     */
    public void increaseGold(){
        gameInformation.setCurrentGold(gameInformation.getCurrentGold() + gameInformation.getGenGold());
    }

    public void calculateRestReward() {
        long diff = System.currentTimeMillis() - gameInformation.getLastLogin();
        float hours   = (diff / (1000*60*60));

        if (hours >= Constants.DELAY_HOURS_REWARD) {
            // Calculer reward afk
        }
    }

    // Methode d'ajout d'or lors d'un critique
    public void increaseGoldCritical() {
        gameInformation.setCurrentGold(gameInformation.getCurrentGold() + getCriticalValue());
    }

    /**
     * Value d'un coup critique
     * @return
     */
    public int getCriticalValue(){
        return (gameInformation.getGenGold() * gameInformation.getCriticalRate());
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

    public GameInformation getGameInformation() {
        return gameInformation;
    }

    public void setGameInformation(GameInformation gameInformation) {
        this.gameInformation = gameInformation;
    }

    public StationActor getStationActor() {
        return stationActor;
    }

    public void setStationActor(StationActor stationActor) {
        this.stationActor = stationActor;
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
}
