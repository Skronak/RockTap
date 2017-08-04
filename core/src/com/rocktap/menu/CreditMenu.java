package com.rocktap.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rocktap.manager.GameManager;

/**
 * Created by Skronak on 01/02/2017.
 * Menu d'update
 * // TODO: super menu desactivant l'input listener, gerer un state?
 */
public class CreditMenu extends AbstractMenu {

    public CreditMenu(GameManager gameManager) {
        super(gameManager);
        menutable.add(new Label("ROCK TAP: 2017-2018", skin)).pad(60);
        menutable.row();
        menutable.add(new Label("PROGRAMMING", skin)).pad(10);
        menutable.row();
        menutable.add(new Label("MUSIC AND GRAPHICS", skin)).pad(10);
        menutable.row();
        menutable.add(new Label("COUTURIER GUILLAUME", skin)).pad(10);
        menutable.setVisible(false);
    }



//*****************************************************
//                  GETTER & SETTER
// ****************************************************
}
