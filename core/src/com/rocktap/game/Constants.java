package com.rocktap.game;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Skronak on 29/01/2017.
 */
public class Constants {

    public static final String CURRENT_VERSION = "build v0.0.4";
    // Taille virtuelle verticale de l'application
    public static final int V_WIDTH = 320; //320

    // Taille virtuelle horizontale de l'application
    public static final int V_HEIGHT = 570; //570

    // Taux generation gold offline
    public static float RATE_GENGOLD_OFFLINE = 0.5f;

    // Delay pour generation coffre rattrapage gold offline
    public static int DELAY_GENGOLD_LAST_CO = 300;

    // Delay pour generation coffre rattrapage gold offline
    public static int DELAY_AUTOSAVE = 300;

    // Taille du texte du HUD
    public static int HUD_TEXT_SIZE = 22;

    // Style du font utilise
    public static String FONT_STYLE = "stocky";

    // chance d'avoir un crit (1/10)
    public static int CRITICAL_CHANCE = 20;

    public static Color CRITICAL_LABEL_COLOR = Color.ROYAL;

    public static Color NORMAL_LABEL_COLOR = Color.WHITE;

    public static float UPDATE_MENU_PAD = V_WIDTH/20;

    public static int PLAYSCREEN_MENU_BUTTON_HEIGHT = 50;

}
