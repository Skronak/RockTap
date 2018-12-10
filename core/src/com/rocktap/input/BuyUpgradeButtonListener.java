package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.entity.GameInformation;
import com.rocktap.menu.moduleMenu.ModuleMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listener du bouton UPGRADE d'un module
 */
public class BuyUpgradeButtonListener extends ClickListener {

    private ModuleMenu moduleMenu;
    // Identifiant du module rattaché au listener
    private int idModule;


    public BuyUpgradeButtonListener(ModuleMenu moduleMenu, int id) {
        this.moduleMenu = moduleMenu;
        this.idModule = id;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        if (moduleMenu.getGameManager().moduleManager.isAvailableUpgrade(idModule)) {
            this.moduleMenu.getGameManager().moduleManager.increaseModuleLevel(idModule);
            this.moduleMenu.getGameManager().moduleManager.updateModuleMenuInformation(idModule);
            GameInformation.INSTANCE.saveInformation();
        }
        return false;
    }


}
