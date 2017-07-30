package com.rocktap.manager;

import com.rocktap.game.AccountInformation;
import com.rocktap.game.GameState;
import com.rocktap.station.StationActor;

/**
 * Created by Skronak on 30/07/2017.
 *
 * Classe de gestion globale du jeu
 * gerant le lien entre PlayScreen et accountInformation
 */
public class GameManager {
    // Information persistentes
    private AccountInformation accountInformation;

    private StationActor stationActor;

    // Etat du jeu
    private GameState currentState;

    public GameManager(AccountInformation accountInformation) {
        this.accountInformation = accountInformation;
        currentState = GameState.IN_GAME;
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
        stationActor = new StationActor(posX,posY,width,height,animSpeed,accountInformation);
        return stationActor;
    }
    /**
     * methode d'ajout d'or
     */
    public void increaseGold(){
        accountInformation.setCurrentGold(accountInformation.getCurrentGold() + accountInformation.getGenGold());
    }

    // Methode d'ajout d'or lors d'un critique
    public void increaseGoldCritical() {
        accountInformation.setCurrentGold(accountInformation.getCurrentGold() + getCriticalValue());
    }

    /**
     * Value d'un coup critique
     * @return
     */
    public int getCriticalValue(){
        return (accountInformation.getGenGold() * accountInformation.getCriticalRate());
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public AccountInformation getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(AccountInformation accountInformation) {
        this.accountInformation = accountInformation;
    }
}
