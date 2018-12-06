package com.rocktap.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rocktap.entity.GameInformation;
import com.rocktap.manager.GameManager;

public class OptionMenu extends AbstractMenu{

    private TextButton weatherButton;
    private TextButton soundButton;

    public OptionMenu(GameManager gameManager) {
        super(gameManager);

        weatherButton = new TextButton("",gameManager.getAssetManager().getSkin());
        weatherButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                switchWeatherMode();
                return true;
            }
        });

        soundButton = new TextButton("disable sound",gameManager.getAssetManager().getSkin());
        soundButton.setDisabled(true);
        soundButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                switchSoundMode();
                return true;
            }
        });
        customizeMenuTable();
    }


    public void switchSoundMode(){
        GameInformation.INSTANCE.setOptionWeather(!GameInformation.INSTANCE.isOptionSound());
    }

    public void switchWeatherMode(){
        GameInformation.INSTANCE.setOptionWeather(!GameInformation.INSTANCE.isOptionWeather());
        gameManager.getPlayScreen().getWeatherManager().stopAll();
    }

    public void customizeMenuTable() {
        parentTable.add(new Label("OPTION", skin)).top();
        parentTable.row();
        parentTable.add(weatherButton).expandX().left().pad(20);
        parentTable.row();
        parentTable.add(soundButton).left().pad(20);
    }

    @Override
    public void update() {
        if (GameInformation.INSTANCE.isOptionWeather()){
            weatherButton.setText("Disable Weather");
        } else {
            weatherButton.setText("Enable Weather");
        }
        if (GameInformation.INSTANCE.isOptionSound()){
            soundButton.setText("Disable Sound");
        } else {
            soundButton.setText("Enable Sound");
        }
    }
}
