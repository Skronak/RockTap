package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.menu.UpgradeMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listener du bouton UPGRADE d'un module
 */
public class InputUpgradeSkillButtonListener extends ClickListener {

    private UpgradeMenu upgradeMenu;

    public InputUpgradeSkillButtonListener(UpgradeMenu upgradeMenu) {
        this.upgradeMenu = upgradeMenu;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        this.upgradeMenu.getUpgradeManager().increaseUpgradeLevel(upgradeMenu.getCurrentSelection());
        this.upgradeMenu.getUpgradeManager().updateUpgradeInformation(upgradeMenu.getCurrentSelection());
        this.upgradeMenu.getGameManager().getGameInformation().saveInformation();
    return false;

    }


}
