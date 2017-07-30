package com.rocktap.game;

/**
 * Created by Skronak on 19/02/2017.
 */
public enum GameState {
    //Objets directement construits
    UPGRADE ("Upagrade"),
    IN_GAME ("Ingame"),
    SETTINGS ("Settings"),
    ACHIEVEMENT ("Achivement");

    private String state = "";

    //Constructeur
    GameState(String state){
        this.state = state;
    }

    public String toString(){
        return state;
    }
}
