package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.menu.ModuleMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listner des boutons d'upgrade
 * Classe de gestion des input du menu d'UPGRADE
 */
public class InputUpgradeMenuButtonListener extends ClickListener {

    private ModuleMenu moduleMenu;
    private int idSelect;

    public InputUpgradeMenuButtonListener(ModuleMenu moduleMenu, int idSelect) {
        this.moduleMenu = moduleMenu;
        this.idSelect = idSelect;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        // Indique a moduleMenu qu'on a selectionne skill <idSelect>
        moduleMenu.setCurrentSelection(idSelect);
        moduleMenu.getModuleManager().updateModuleInformation(idSelect);
        return false;
    }

}
