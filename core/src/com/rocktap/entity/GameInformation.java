package com.rocktap.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.rocktap.manager.AssetManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skronak on 30/01/2017.
 *
 * Classe de stat & information sur le compte du jeu
 * // TODO store all current ModuleElement
 */
public enum GameInformation {
    INSTANCE;

    // dernier login
    private Long lastLogin;
    // Total d'or
    private float currentGold;
    // generation pasive d'or
    private float genGoldPassive;
    // generation active d'or
    private float genGoldActive;
    // Currency (A=1, B=2, ... jusqua 9, AA=9+1
    private int currency;
    // currency de gengoldPassive
    private int genCurrencyPassive;
    // currency de gengoldActive
    private int genCurrencyActive;
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
    // Total gameTime
    private Long totalGameTime;
    // Tap number
    private int totalTapNumber;
    private int factionId;
    private int factionLvl;
    private int factionExp;
    private int depth;
    private List<Integer> achievList; //0: locked 1: unlocked,2: claimed
    private boolean optionWeather, optionSound, optionFps;
    private int skillPoint;

    GameInformation() {
        upgradeLevelList = new ArrayList<Integer>();
        achievList = new ArrayList<Integer>();
        prefs = Gdx.app.getPreferences("rockTapPreferences");

        if (!prefs.contains("lastLogin")) {
            Gdx.app.debug("GameInformation", "Initialisation du compte par defaut");
            initGameInformation();
            initGamePreference();
        } else {
            retrieveAllGameInformation();
        }
    }

    /**
     * Sauvegarde les informations courantes
     * dans le fichier de pref
     * TODO : ne pas tt sauvegarder chaque fois
     * TODO: gerer plusieurs type de sauvegarde
     */
    public void saveInformation() {
        prefs.putFloat("currentGold", currentGold);
        prefs.putInteger("currentCurrency", currency);
        prefs.putFloat("genGoldActive", genGoldActive);
        prefs.putInteger("genCurrencyActive", genCurrencyActive);
        prefs.putFloat("genGoldPassive", genGoldPassive);
        prefs.putInteger("genCurrencyPassive", genCurrencyPassive);
        prefs.putInteger("criticalRate", criticalRate);
        prefs.putInteger("stationId", stationId);
        for (int i=0;i<AssetManager.INSTANCE.getModuleElementList().size();i++){
            prefs.putInteger("upgradeLevel"+i, upgradeLevelList.get(i));
        }
        prefs.putLong("lastLogin", System.currentTimeMillis());
        prefs.putLong("totalGameTime", totalGameTime + (System.currentTimeMillis() - lastLogin));
        prefs.putInteger("totalTapNumber", totalTapNumber);
        prefs.putInteger("factionExp", factionExp);
        prefs.putInteger("factionId", factionId);
        prefs.putInteger("factionLvl", factionLvl);
        for (int i=0;i<AssetManager.INSTANCE.getAchievementElementList().size();i++){
            prefs.putInteger("achiev"+i, achievList.get(i));
        }
        prefs.putBoolean("optionSound", optionSound);
        prefs.putBoolean("optionWeather", optionWeather);
        prefs.putBoolean("optionFps", optionFps);
        prefs.putInteger("skillPoint",skillPoint);
        prefs.flush();
    }

    public void reset(){
        initGameInformation();
        saveInformation();
    }

    public void initGameInformation(){
        currentGold = 0;
        currency = 0;
        genGoldPassive = 2;
        genGoldActive = 2;
        genCurrencyPassive = 0;
        genCurrencyActive = 0;
        criticalRate = 5;
        stationId = 0;
        skillPoint = 0;

        for (int i=0;i<AssetManager.INSTANCE.getModuleElementList().size();i++){
            upgradeLevelList.add(0);
        }
        for (int i=0;i<AssetManager.INSTANCE.getAchievementElementList().size();i++){
            achievList.add(0);
        }

        lastLogin = System.currentTimeMillis();
        totalTapNumber=0;
        totalGameTime=0l;
        factionLvl=0;
        factionId=0;
        factionExp=0;
    }

    public void initGamePreference(){
        firstPlay = true;
        optionSound=true;
        optionWeather=true;
        optionFps=false;
    }

    private void retrieveAllGameInformation() {
        currentGold = prefs.getFloat("currentGold");
        currency = prefs.getInteger("currentCurrency");
        genGoldActive = prefs.getFloat("genGoldActive");
        genGoldPassive = prefs.getFloat("genGoldPassive");
        genCurrencyPassive = prefs.getInteger("genCurrencyPassive");
        genCurrencyActive = prefs.getInteger("genCurrencyActive");
        criticalRate = prefs.getInteger("criticalRate");
        stationId = prefs.getInteger("stationId");
        skillPoint = prefs.getInteger("skillPoint");
        for (int i=0;i<AssetManager.INSTANCE.getModuleElementList().size();i++){
            upgradeLevelList.add(prefs.getInteger("upgradeLevel"+i));
        }
        lastLogin = prefs.getLong("lastLogin");
        totalGameTime = prefs.getLong("totalGameTime");
        totalTapNumber = prefs.getInteger("totalTapNumber");
        factionExp = prefs.getInteger("factionExp");
        factionId = prefs.getInteger("factionId");
        factionLvl = prefs.getInteger("factionLvl");
        for (int i=0;i<AssetManager.INSTANCE.getAchievementElementList().size();i++){
            achievList.add(prefs.getInteger("achiev"+i));
        }
        optionSound=prefs.getBoolean("optionSound");
        optionWeather=prefs.getBoolean("optionWeather");
        optionFps=prefs.getBoolean("optionFps");
    }
//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public float getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(float currentGold) {
        this.currentGold = currentGold;
    }

    public float getGenGoldPassive() {
        return genGoldPassive;
    }

    public void setGenGoldPassive(float genGoldPassive) {
        this.genGoldPassive = genGoldPassive;
    }

    public float getGenGoldActive() {
        return genGoldActive;
    }

    public void setGenGoldActive(float genGoldActive) {
        this.genGoldActive = genGoldActive;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getGenCurrencyPassive() {
        return genCurrencyPassive;
    }

    public void setGenCurrencyPassive(int genCurrencyPassive) {
        this.genCurrencyPassive = genCurrencyPassive;
    }

    public int getGenCurrencyActive() {
        return genCurrencyActive;
    }

    public void setGenCurrencyActive(int genCurrencyActive) {
        this.genCurrencyActive = genCurrencyActive;
    }

    public int getCriticalRate() {
        return criticalRate;
    }

    public void setCriticalRate(int criticalRate) {
        this.criticalRate = criticalRate;
    }

    public Preferences getPrefs() {
        return prefs;
    }

    public void setPrefs(Preferences prefs) {
        this.prefs = prefs;
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

    public List<Integer> getUpgradeLevelList() {
        return upgradeLevelList;
    }

    public void setUpgradeLevelList(List<Integer> upgradeLevelList) {
        this.upgradeLevelList = upgradeLevelList;
    }

    public Long getTotalGameTime() {
        return totalGameTime;
    }

    public void setTotalGameTime(Long totalGameTime) {
        this.totalGameTime = totalGameTime;
    }

    public int getTotalTapNumber() {
        return totalTapNumber;
    }

    public void setTotalTapNumber(int totalTapNumber) {
        this.totalTapNumber = totalTapNumber;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public List<Integer> getAchievList() {
        return achievList;
    }

    public void setAchievList(List<Integer> achievList) {
        this.achievList = achievList;
    }

    public boolean isOptionWeather() {
        return optionWeather;
    }

    public void setOptionWeather(boolean optionWeather) {
        this.optionWeather = optionWeather;
    }

    public boolean isOptionSound() {
        return optionSound;
    }

    public void setOptionSound(boolean optionSound) {
        this.optionSound = optionSound;
    }

    public boolean isOptionFps() {
        return optionFps;
    }

    public void setOptionFps(boolean optionFps) {
        this.optionFps = optionFps;
    }

    public int getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(int skillPoint) {
        this.skillPoint = skillPoint;
    }
}
