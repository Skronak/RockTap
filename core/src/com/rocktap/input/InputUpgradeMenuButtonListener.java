package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.menu.UpgradeMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listner des boutons d'upgrade
 * Classe de gestion des input du menu d'UPGRADE
 * // TODO: modifie le fichier de pref l'etat du compte courant
 */
public class InputUpgradeMenuButtonListener extends ClickListener {

    private UpgradeMenu upgradeMenu;
    private String detail, cost, power, titre;
    private int idSelect;

    public InputUpgradeMenuButtonListener(UpgradeMenu upgradeMenu, int idSelect, String titre, String detail, String cost, String power) {
        this.upgradeMenu = upgradeMenu;
        this.idSelect = idSelect;
        this.detail = detail;
        this.cost = cost;
        this.power = power;
        this.titre = titre;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        // Indique a upgradeMenu qu'on a selectionne skill <idSelect>
        upgradeMenu.setCurrentSelection(idSelect);

        // Set le level par default lorsqu'on selectionne le skill
        switch (idSelect) {
            case 1:
                this.upgradeMenu.getDetailTitre().setText(titre + this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel1());
                this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel1()));
                break;
            case 2:
                this.upgradeMenu.getDetailTitre().setText(titre + this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel2());
                this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel2()));
                break;
            case 3:
                this.upgradeMenu.getDetailTitre().setText(titre + this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel3());
                this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel3()));
                break;
            case 4:
                this.upgradeMenu.getDetailTitre().setText(titre + this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel4());
                this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel4()));
                break;
            case 5:
                this.upgradeMenu.getDetailTitre().setText(titre + this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel5());
                this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel5()));
                break;
            case 6:
                this.upgradeMenu.getDetailTitre().setText(titre + this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel6());
                this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel6()));
                break;
            case 7:
                this.upgradeMenu.getDetailTitre().setText(titre + this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel7());
                this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel7()));
                break;
            case 8:
                this.upgradeMenu.getDetailTitre().setText(titre + this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel8());
                this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel8()));
                break;
            default:
                break;
        }
        this.upgradeMenu.getDetailGold().setText(cost);
        this.upgradeMenu.getDetailDetail().setText(detail);
        this.upgradeMenu.getDetailPower().setText(power);

        return false;
    }

}
