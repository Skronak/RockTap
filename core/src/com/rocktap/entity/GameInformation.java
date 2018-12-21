package com.rocktap.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

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
    private int achiev1,achiev2,achiev3,achiev4,achiev5,achiev6,achiev7,achiev8,achiev9,achiev10,achiev11,achiev12,achiev13,achiev14,achiev15,achiev16,achiev17,achiev18,achiev19,achiev20;
    private boolean optionWeather, optionSound, optionFps;


    GameInformation() {
        upgradeLevelList = new ArrayList<Integer>();
        prefs = Gdx.app.getPreferences("rockTapPreferences");

        if (!prefs.contains("lastLogin")) {
            Gdx.app.debug("GameInformation", "Initialisation du compte par defaut");
            initGameInformation();
            initGamePreference();
        } else {
            currentGold = prefs.getFloat("currentGold");
            currency = prefs.getInteger("currentCurrency");
            genGoldActive = prefs.getFloat("genGoldActive");
            genGoldPassive = prefs.getFloat("genGoldPassive");
            genCurrencyPassive = prefs.getInteger("genCurrencyPassive");
            genCurrencyActive = prefs.getInteger("genCurrencyActive");
            criticalRate = prefs.getInteger("criticalRate");
            stationId = prefs.getInteger("stationId");
            upgradeLevelList.add(prefs.getInteger("upgradeLevel1"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel2"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel3"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel4"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel5"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel6"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel7"));
            upgradeLevelList.add(prefs.getInteger("upgradeLevel8"));
            lastLogin = prefs.getLong("lastLogin");
            totalGameTime = prefs.getLong("totalGameTime");
            totalTapNumber = prefs.getInteger("totalTapNumber");
            factionExp = prefs.getInteger("factionExp");
            factionId = prefs.getInteger("factionId");
            factionLvl = prefs.getInteger("factionLvl");
            achiev1 = prefs.getInteger("achiev1");
            achiev2 = prefs.getInteger("achiev2");
            achiev3 = prefs.getInteger("achiev3");
            achiev4 = prefs.getInteger("achiev4");
            achiev5 = prefs.getInteger("achiev5");
            achiev6 = prefs.getInteger("achiev6");
            achiev7 = prefs.getInteger("achiev7");
            achiev8 = prefs.getInteger("achiev8");
            achiev9 = prefs.getInteger("achiev9");
            achiev10 = prefs.getInteger("achiev10");
            achiev11 = prefs.getInteger("achiev11");
            achiev12 = prefs.getInteger("achiev12");
            achiev13 = prefs.getInteger("achiev13");
            achiev14 = prefs.getInteger("achiev14");
            achiev15 = prefs.getInteger("achiev15");
            achiev16 = prefs.getInteger("achiev16");
            achiev17 = prefs.getInteger("achiev17");
            achiev18 = prefs.getInteger("achiev18");
            achiev19 = prefs.getInteger("achiev19");
            achiev20 = prefs.getInteger("achiev20");
            optionSound=prefs.getBoolean("optionSound");
            optionWeather=prefs.getBoolean("optionWeather");
            optionFps=prefs.getBoolean("optionFps");
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
        prefs.putInteger("upgradeLevel1", upgradeLevelList.get(0));
        prefs.putInteger("upgradeLevel2", upgradeLevelList.get(1));
        prefs.putInteger("upgradeLevel3", upgradeLevelList.get(2));
        prefs.putInteger("upgradeLevel4", upgradeLevelList.get(3));
        prefs.putInteger("upgradeLevel5", upgradeLevelList.get(4));
        prefs.putInteger("upgradeLevel6", upgradeLevelList.get(5));
        prefs.putInteger("upgradeLevel7", upgradeLevelList.get(6));
        prefs.putInteger("upgradeLevel8", upgradeLevelList.get(7));
        prefs.putLong("lastLogin", System.currentTimeMillis());
        prefs.putLong("totalGameTime", totalGameTime + (System.currentTimeMillis() - lastLogin));
        prefs.putInteger("totalTapNumber", totalTapNumber);
        prefs.putInteger("factionExp", factionExp);
        prefs.putInteger("factionId", factionId);
        prefs.putInteger("factionLvl", factionLvl);
        prefs.putInteger("achiev1", achiev1);
        prefs.putInteger("achiev2", achiev2);
        prefs.putInteger("achiev3", achiev3);
        prefs.putInteger("achiev4", achiev4);
        prefs.putInteger("achiev5", achiev5);
        prefs.putInteger("achiev6", achiev6);
        prefs.putInteger("achiev7", achiev7);
        prefs.putInteger("achiev8", achiev8);
        prefs.putInteger("achiev9", achiev9);
        prefs.putInteger("achiev10",achiev10);
        prefs.putInteger("achiev11",achiev11);
        prefs.putInteger("achiev12",achiev12);
        prefs.putInteger("achiev13",achiev13);
        prefs.putInteger("achiev14",achiev14);
        prefs.putInteger("achiev15",achiev15);
        prefs.putInteger("achiev16",achiev16);
        prefs.putInteger("achiev17",achiev17);
        prefs.putInteger("achiev18",achiev18);
        prefs.putInteger("achiev19",achiev19);
        prefs.putInteger("achiev20",achiev20);
        prefs.putBoolean("optionSound", optionSound);
        prefs.putBoolean("optionWeather", optionWeather);
        prefs.putBoolean("optionFps", optionFps);
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
        upgradeLevelList.set(0,0);
        upgradeLevelList.set(1,0);
        upgradeLevelList.set(2,0);
        upgradeLevelList.set(3,0);
        upgradeLevelList.set(4,0);
        upgradeLevelList.set(5,0);
        upgradeLevelList.set(6,0);

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

    public int getAchiev1() {
        return achiev1;
    }

    public void setAchiev1(int achiev1) {
        this.achiev1 = achiev1;
    }

    public int getAchiev2() {
        return achiev2;
    }

    public void setAchiev2(int achiev2) {
        this.achiev2 = achiev2;
    }

    public int getAchiev3() {
        return achiev3;
    }

    public void setAchiev3(int achiev3) {
        this.achiev3 = achiev3;
    }

    public int getAchiev4() {
        return achiev4;
    }

    public void setAchiev4(int achiev4) {
        this.achiev4 = achiev4;
    }

    public int getAchiev5() {
        return achiev5;
    }

    public void setAchiev5(int achiev5) {
        this.achiev5 = achiev5;
    }

    public int getAchiev6() {
        return achiev6;
    }

    public void setAchiev6(int achiev6) {
        this.achiev6 = achiev6;
    }

    public int getAchiev7() {
        return achiev7;
    }

    public void setAchiev7(int achiev7) {
        this.achiev7 = achiev7;
    }

    public int getAchiev8() {
        return achiev8;
    }

    public void setAchiev8(int achiev8) {
        this.achiev8 = achiev8;
    }

    public int getAchiev9() {
        return achiev9;
    }

    public void setAchiev9(int achiev9) {
        this.achiev9 = achiev9;
    }

    public int getAchiev10() {
        return achiev10;
    }

    public void setAchiev10(int achiev10) {
        this.achiev10 = achiev10;
    }

    public int getAchiev11() {
        return achiev11;
    }

    public void setAchiev11(int achiev11) {
        this.achiev11 = achiev11;
    }

    public int getAchiev12() {
        return achiev12;
    }

    public void setAchiev12(int achiev12) {
        this.achiev12 = achiev12;
    }

    public int getAchiev13() {
        return achiev13;
    }

    public void setAchiev13(int achiev13) {
        this.achiev13 = achiev13;
    }

    public int getAchiev14() {
        return achiev14;
    }

    public void setAchiev14(int achiev14) {
        this.achiev14 = achiev14;
    }

    public int getAchiev15() {
        return achiev15;
    }

    public void setAchiev15(int achiev15) {
        this.achiev15 = achiev15;
    }

    public int getAchiev16() {
        return achiev16;
    }

    public void setAchiev16(int achiev16) {
        this.achiev16 = achiev16;
    }

    public int getAchiev17() {
        return achiev17;
    }

    public void setAchiev17(int achiev17) {
        this.achiev17 = achiev17;
    }

    public int getAchiev18() {
        return achiev18;
    }

    public void setAchiev18(int achiev18) {
        this.achiev18 = achiev18;
    }

    public int getAchiev19() {
        return achiev19;
    }

    public void setAchiev19(int achiev19) {
        this.achiev19 = achiev19;
    }

    public int getAchiev20() {
        return achiev20;
    }

    public void setAchiev20(int achiev20) {
        this.achiev20 = achiev20;
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
}
