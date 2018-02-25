package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.menu.OLD_UpgradeModuleMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listener du bouton UPGRADE d'un module
 */
public class InputUpgradeSkillButtonListener extends ClickListener {

    private OLD_UpgradeModuleMenu moduleMenu;

    public InputUpgradeSkillButtonListener(OLD_UpgradeModuleMenu moduleMenu) {
        this.moduleMenu = moduleMenu;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        this.moduleMenu.getModuleManager().increaseUpgradeLevel(moduleMenu.getCurrentSelection());
        this.moduleMenu.getModuleManager().updateModuleInformation(moduleMenu.getCurrentSelection());
        this.moduleMenu.getGameManager().getGameInformation().saveInformation();
    return false;

    }


}
