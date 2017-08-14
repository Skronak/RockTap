package com.rocktap.manager;

import com.rocktap.entity.GameInformation;

/**
 * Created by Skronak on 14/08/2017.
 * Classe gerant les upgrades et l'ecran d'upgrade
 */
public class UpgradeManager {

    private GameInformation gameInformation;

    public UpgradeManager (GameInformation gameInformation) {
        this.gameInformation = gameInformation;
    }

    public boolean isAvailable () {
        return false;
    }
}
