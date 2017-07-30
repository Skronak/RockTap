package com.rocktap.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.rocktap.station.StationActor;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Skronak on 30/01/2017.
 *
 * Classe de l'etat du compte du Joueur
 */
public class AccountInformation {
    // Total d'or
    private int currentGold;
    // dernier login
    private Date lastLogin;
    // generation pasive d'or
    private int genGold;
    // multiplicateur d'or lors de critique
    private int criticalRate;
    // fichier de preference Android
    private Preferences prefs;
    // indicateur de premier lancment du jeu
    private boolean firstPlay;
    // id de station utilise
    private int stationId;
    // niveau de l'upgrade1 de la station
    private int upgradeLevel1;
    // niveau de l'upgrade2 de la station
    private int upgradeLevel2;
    // niveau de l'upgrade3 de la station
    private int upgradeLevel3;
    // niveau de l'upgrade4 de la station
    private int upgradeLevel4;
    // niveau de l'upgrade5 de la station
    private int upgradeLevel5;
    // niveau de l'upgrade6 de la station
    private int upgradeLevel6;
    // niveau de l'upgrade7 de la station
    private int upgradeLevel7;
    // niveau de l'upgrade8 de la station
    private int upgradeLevel8;
    // Station du joueur
    private StationActor station;

    public AccountInformation() {
        prefs = Gdx.app.getPreferences("rockTapPreferences");
        if (!prefs.contains("lastLogin")) {
            Gdx.app.debug("AccountInformation","Initialisation du compte par defaut");
            currentGold = 0;
            criticalRate = 5;
            lastLogin = new Timestamp(System.currentTimeMillis());
            genGold = 2;
            firstPlay = true;
            stationId = 1;
            upgradeLevel1 = 0;
            upgradeLevel2 = 0;
            upgradeLevel3 = 0;
            upgradeLevel4 = 0;
            upgradeLevel5 = 0;
            upgradeLevel6 = 0;
            upgradeLevel7 = 0;
            upgradeLevel8 = 0;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            currentGold = prefs.getInteger("currentGold");
            criticalRate = prefs.getInteger("criticalRate");
            genGold = prefs.getInteger("genGold");
            stationId = prefs.getInteger("stationId");
            upgradeLevel1=prefs.getInteger("upgradeLevel1");
            upgradeLevel2=prefs.getInteger("upgradeLevel2");
            upgradeLevel3=prefs.getInteger("upgradeLevel3");
            upgradeLevel4=prefs.getInteger("upgradeLevel4");
            upgradeLevel5=prefs.getInteger("upgradeLevel5");
            upgradeLevel6=prefs.getInteger("upgradeLevel6");
            upgradeLevel7=prefs.getInteger("upgradeLevel7");
            upgradeLevel8=prefs.getInteger("upgradeLevel8");
            try {
                lastLogin = dateFormat.parse(prefs.getString("lastLogin"));
            } catch (ParseException e) {
                Gdx.app.log("error",e.getMessage());
            }
        }
    }

    /**
     * Sauvegarde les informations courantes
     * dans le fichier de pref
     * TODO : ne pas tt sauvegarder chaque fois
     * TODO: gerer plusieurs type de sauvegarde
     */
    public void saveInformation() {
        prefs.putString("lastLogin", String.valueOf(new Date()));
        prefs.putInteger("currentGold", currentGold);
        prefs.putInteger("genGold", genGold);
        prefs.putInteger("criticalRate", criticalRate);
        prefs.putInteger("stationId", stationId);
        prefs.putInteger("upgradeLevel1", upgradeLevel1);
        prefs.putInteger("upgradeLevel2", upgradeLevel2);
        prefs.putInteger("upgradeLevel3", upgradeLevel3);
        prefs.putInteger("upgradeLevel4", upgradeLevel4);
        prefs.putInteger("upgradeLevel5", upgradeLevel5);
        prefs.putInteger("upgradeLevel6", upgradeLevel6);
        prefs.putInteger("upgradeLevel7", upgradeLevel7);
        prefs.putInteger("upgradeLevel8", upgradeLevel8);
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

    public boolean isFirstPlay() {
        return firstPlay;
    }

    public void setFirstPlay(boolean firstPlay) {
        this.firstPlay = firstPlay;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getUpgradeLevel1() {
        return upgradeLevel1;
    }

    public void setUpgradeLevel1(int upgradeLevel1) {
        this.upgradeLevel1 = upgradeLevel1;
    }

    public int getUpgradeLevel2() {
        return upgradeLevel2;
    }

    public void setUpgradeLevel2(int upgradeLevel2) {
        this.upgradeLevel2 = upgradeLevel2;
    }

    public int getUpgradeLevel3() {
        return upgradeLevel3;
    }

    public void setUpgradeLevel3(int upgradeLevel3) {
        this.upgradeLevel3 = upgradeLevel3;
    }

    public int getUpgradeLevel4() {
        return upgradeLevel4;
    }

    public void setUpgradeLevel4(int upgradeLevel4) {
        this.upgradeLevel4 = upgradeLevel4;
    }

    public int getUpgradeLevel5() {
        return upgradeLevel5;
    }

    public void setUpgradeLevel5(int upgradeLevel5) {
        this.upgradeLevel5 = upgradeLevel5;
    }

    public int getUpgradeLevel6() {
        return upgradeLevel6;
    }

    public void setUpgradeLevel6(int upgradeLevel6) {
        this.upgradeLevel6 = upgradeLevel6;
    }

    public int getUpgradeLevel7() {
        return upgradeLevel7;
    }

    public void setUpgradeLevel7(int upgradeLevel7) {
        this.upgradeLevel7 = upgradeLevel7;
    }

    public int getUpgradeLevel8() {
        return upgradeLevel8;
    }

    public void setUpgradeLevel8(int upgradeLevel8) {
        this.upgradeLevel8 = upgradeLevel8;
    }
}
