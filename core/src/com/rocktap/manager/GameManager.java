package com.rocktap.manager;

import com.badlogic.gdx.Gdx;
import com.rocktap.entity.GameInformation;
import com.rocktap.entity.StationEntity;
import com.rocktap.screen.PlayScreen;
import com.rocktap.utils.Constants;
import com.rocktap.utils.GameState;
import com.rocktap.utils.LargeMath;
import com.rocktap.utils.ValueDTO;

import java.util.ArrayList;

/**
 * Created by Skronak on 30/07/2017.
 *
 * Classe de gestion globale du jeu
 * gerant le lien entre PlayScreen et GameInformation
 */
public class GameManager {

    public PlayScreen playScreen;

    public LargeMath largeMath;

    public WeatherManager weatherManager;

    public ModuleManager moduleManager;

    public AchievementManager achievementManager;

    public float autoSaveTimer,weatherTimer, increaseGoldTimer, logicTimer;

    public StationEntity stationEntity;

    public ArrayList<Integer> newModuleIdList;

    // Etat du jeu
    public GameState currentState;

    public GameManager(PlayScreen playScreen) {
        currentState = GameState.IN_GAME;
        this.playScreen = playScreen;
        largeMath = new LargeMath();
        newModuleIdList = new ArrayList<Integer>();
        weatherManager = new WeatherManager(playScreen);
        moduleManager = new ModuleManager(this);
        achievementManager = new AchievementManager(this);
        autoSaveTimer = 0f;
        increaseGoldTimer = 0f;
        weatherTimer = 0f;
        logicTimer = 0f;
    }

    public void initialiseGame(){
        moduleManager.initialize(playScreen.getHud().getModuleMenu());
    }

    public StationEntity initializeStationEntity(){
        stationEntity = new StationEntity(this);
        return stationEntity;
    }
    /**
     * Modification de letat du jeu en fonction
     * du temps passe
     */
    public void updateLogic(float delta) {
        autoSaveTimer += Gdx.graphics.getDeltaTime();
        increaseGoldTimer += Gdx.graphics.getDeltaTime();
        weatherTimer += Gdx.graphics.getDeltaTime();
        logicTimer += Gdx.graphics.getDeltaTime();

        if(newModuleIdList.size()>0){
            if (currentState.equals(GameState.IN_GAME)){
                for (int i=0;i<newModuleIdList.size();i++) {
                    stationEntity.addModule(newModuleIdList.get(i));
                }
                newModuleIdList.clear();
            }
        }
        switch (currentState) {
            case IN_GAME:
                Gdx.input.setInputProcessor(playScreen.inputMultiplexer);
                break;
            case MENU:
                Gdx.input.setInputProcessor(playScreen.getHud().getStage());
                if (logicTimer > 1f) {
                    logicTimer=0f;
                    playScreen.getHud().update();
                }
                break;
            case CREDIT:
                Gdx.input.setInputProcessor(playScreen.getHud().getStage());
                break;
            default:
                break;
        }

        // Autosave
        if(autoSaveTimer >= Constants.DELAY_AUTOSAVE){
            Gdx.app.debug("PlayScreen","Saving");
            GameInformation.INSTANCE.saveInformation();
            autoSaveTimer=0f;
        }

        // Increase Gold
        if(increaseGoldTimer >= Constants.DELAY_GENGOLD_PASSIV) {
            increaseGoldPassive();
            Gdx.app.debug("PlayScreen","Increasing Gold by "+GameInformation.INSTANCE.getGenGoldPassive()+" val "+GameInformation.INSTANCE.getGenCurrencyPassive());
            increaseGoldPassive();
            playScreen.getHud().updateGoldLabel();
            increaseGoldTimer=0f;
        }

        if (GameInformation.INSTANCE.isOptionWeather()) {
            if (weatherTimer >= Constants.DELAY_WEATHER_CHANGE) {
                Gdx.app.debug("PlayScreen", "Changing weather");
                weatherManager.addRandomWeather();
                weatherTimer = 0f;
            }
        }
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


}
