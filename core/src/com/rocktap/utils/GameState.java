package com.rocktap.utils;

/**
 * Created by Skronak on 19/02/2017.
 */
public enum GameState {
    //Objets directement construits
    UPGRADE ("Upgrade"),
    IN_GAME ("Ingame"),
    CREDIT ("Credit"),
    SETTINGS ("Settings"),
    ACHIEVEMENT ("Achievement");

    private String state = "";

    //Constructeur
    GameState(String state){
        this.state = state;
    }

    public String toString(){
        return state;
    }
}
