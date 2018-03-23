package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.menu.UpgradeModuleMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listener du bouton UPGRADE d'un module
 */
public class BuyUpgradeButtonListener extends ClickListener {

    private UpgradeModuleMenu moduleMenu;
    // Identifiant du module rattaché au listener
    private int idModule;


    public BuyUpgradeButtonListener(UpgradeModuleMenu moduleMenu, int id) {
        this.moduleMenu = moduleMenu;
        this.idModule = id;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        if (moduleMenu.getModuleManager().isAvailableUpgrade(idModule)) {
            this.moduleMenu.getModuleManager().increaseUpgradeLevel(idModule);
            this.moduleMenu.getModuleManager().updateModuleInformation(idModule);
            this.moduleMenu.getGameManager().getGameInformation().saveInformation();
        }
        return false;
    }


}
