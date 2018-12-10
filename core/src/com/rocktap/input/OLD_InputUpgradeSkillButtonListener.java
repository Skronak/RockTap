package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.entity.GameInformation;
import com.rocktap.menu.moduleMenu.ModuleMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listener du bouton BUY de l'upgrade d'un module
 */
public class OLD_InputUpgradeSkillButtonListener extends ClickListener {

    private ModuleMenu moduleMenu;

    public OLD_InputUpgradeSkillButtonListener(ModuleMenu moduleMenu) {
        this.moduleMenu = moduleMenu;
    }

    /**
     * Pression sur la touche
     * @param event
     * @param x
     * @param y
     * @param pointer
     * @param button
     * @return
     */
    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        this.moduleMenu.getGameManager().moduleManager.increaseModuleLevel(moduleMenu.getCurrentSelection());
//        this.moduleMenu.getModuleManager().updateModuleMenuInformation(moduleMenu.getCurrentSelection());
        GameInformation.INSTANCE.saveInformation();
    return false;

    }


}
