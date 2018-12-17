package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.entity.GameInformation;
import com.rocktap.manager.ModuleManager;

/**
 * Created by Skronak on 29/01/2017.
 * Listener du bouton UPGRADE d'un module
 */
public class BuyUpgradeButtonListener extends ClickListener {

    // Identifiant du module rattaché au listener
    private ModuleManager moduleManager;
    private int idModule;


    public BuyUpgradeButtonListener(ModuleManager moduleManager, int id) {
        this.moduleManager = moduleManager;
        this.idModule = id;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        if (moduleManager.isAvailableUpgrade(idModule)) {
            moduleManager.increaseModuleLevel(idModule);
            moduleManager.updateModuleMenuInformation(idModule);
            GameInformation.INSTANCE.saveInformation();
        }
        return false;
    }
}
