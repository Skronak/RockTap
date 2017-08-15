package com.rocktap.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Skronak on 30/01/2017.
 *
 * Classe de l'etat du compte du Joueur
 */
public class GameInformation {
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
    // liste des niveau d'upgrade du joueur pour faciliter son acces
    private List<Integer> upgradeLevelList;

    // Station du joueur
    private StationActor station;

    public GameInformation() {
        upgradeLevelList = new ArrayList<Integer>();

        prefs = Gdx.app.getPreferences("rockTapPreferences");
        if (!prefs.contains("lastLogin")) {
            Gdx.app.debug("GameInformation", "Initialisation du compte par defaut");
            currentGold = 0;
            criticalRate = 5;
            lastLogin = new Timestamp(System.currentTimeMillis());
            genGold = 2;
            firstPlay = true;
            stationId = 1;
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
            upgradeLevelList.add(0);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            currentGold = prefs.getInteger("currentGold");
            criticalRate = prefs.getInteger("criticalRate");
            genGold = prefs.getInteger("genGold");
            stationId = prefs.getInteger("stationId");
            upgradeLevelList.add(prefs.getInteger("upgradeLevel1"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel2"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel3"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel4"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel5"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel6"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel7"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel8"));
            try {
                lastLogin = dateFormat.parse(prefs.getString("lastLogin"));
            } catch (ParseException e) {
                Gdx.app.log("error", e.getMessage());
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
        prefs.putInteger("upgradeLevel1", upgradeLevelList.get(0));
        prefs.putInteger("upgradeLevel2", upgradeLevelList.get(1));
        prefs.putInteger("upgradeLevel3", upgradeLevelList.get(2));
        prefs.putInteger("upgradeLevel4", upgradeLevelList.get(3));
        prefs.putInteger("upgradeLevel5", upgradeLevelList.get(4));
        prefs.putInteger("upgradeLevel6", upgradeLevelList.get(5));
        prefs.putInteger("upgradeLevel7", upgradeLevelList.get(6));
        prefs.putInteger("upgradeLevel8", upgradeLevelList.get(7));
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

    public List<Integer> getUpgradeLevelList() {
        return upgradeLevelList;
    }

    public void setUpgradeLevelList(List<Integer> upgradeLevelList) {
        this.upgradeLevelList = upgradeLevelList;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

}
