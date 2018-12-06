package com.rocktap.manager;

import com.rocktap.entity.GameInformation;
import com.rocktap.entity.OldStationActor;
import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.Constants;
import com.rocktap.utils.GameState;
import com.rocktap.utils.LargeMath;
import com.rocktap.utils.ValueDTO;

/**
 * Created by Skronak on 30/07/2017.
 *
 * Classe de gestion globale du jeu
 * gerant le lien entre PlayScreen et GameInformation
 */
public class GameManager {
    private OldStationActor oldStationActor;

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

    /**
     * Generation d'une oldStationActor en fonction des informations du compte
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param animSpeed
     * @return
     */
    public OldStationActor initStationActor(int posX, int posY, int width, int height, float animSpeed) {
        oldStationActor = new OldStationActor(posX,posY,width,height,animSpeed, this);
        return oldStationActor;
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
//        GameInformation.INSTANCE.setCurrentGold(GameInformation.INSTANCE.getCurrentGold() + getCriticalValue());
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

    public OldStationActor getOldStationActor() {
        return oldStationActor;
    }

    public void setOldStationActor(OldStationActor oldStationActor) {
        this.oldStationActor = oldStationActor;
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
}
