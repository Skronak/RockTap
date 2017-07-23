package com.rocktap.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.rocktap.utils.GameState;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Skronak on 30/01/2017.
 */
public class AccountInformation {
    private int currentGold;
    private Date lastLogin;
    private int genGold;
    private int criticalRate;
    private Preferences prefs;
    private GameState currentState;

    public AccountInformation() {
        //Recupere information depuis preferences
        prefs = Gdx.app.getPreferences("rockTapPreferences");
        if (prefs.contains("lastLogin")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            currentGold = prefs.getInteger("currentGold");
            criticalRate = prefs.getInteger("criticalRate");
            genGold = prefs.getInteger("genGold");
            try {
                lastLogin = dateFormat.parse(prefs.getString("lastLogin"));
            } catch (ParseException e) {
                Gdx.app.log("error",e.getMessage());
            }
        } else {
            currentGold = 0;
            criticalRate = 5;
            lastLogin = new Timestamp(System.currentTimeMillis());
            genGold = 2;
        }

        currentState = GameState.IN_GAME;

    }

    public void increaseGold(){
        currentGold = currentGold + genGold;
    }

    public void increaseGoldCritical() {
        currentGold = currentGold + getCriticalValue();
    }

    public int getCriticalValue(){
        return (genGold * criticalRate);
    }

    /**
     * Sauvegarde les informations courantes
     * dans le fichier de pref
     */
    public void saveInformation() {
        prefs.putString("lastLogin", String.valueOf(new Date()));
        prefs.putInteger("currentGold", currentGold);
        prefs.putInteger("genGold", genGold);
        prefs.putInteger("criticalRate", criticalRate);
        prefs.flush();
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = currentGold;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getGenGold() {
        return genGold;
    }

    public void setGenGold(int genGold) {
        this.genGold = genGold;
    }

    public Preferences getPrefs() {
        return prefs;
    }

    public void setPrefs(Preferences prefs) {
        this.prefs = prefs;
    }

    public int getCriticalRate() {
        return criticalRate;
    }

    public void setCriticalRate(int criticalRate) {
        this.criticalRate = criticalRate;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }
}
