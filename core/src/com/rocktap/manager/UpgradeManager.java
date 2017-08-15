package com.rocktap.manager;

import com.rocktap.entity.UpgradeActor;
import com.rocktap.menu.UpgradeMenu;

import java.util.List;

/**
 * Created by Skronak on 14/08/2017.
 * Classe gerant les upgrades et l'ecran d'upgrade
 */
public class UpgradeManager {

    private GameManager gameManager;
    private UpgradeMenu upgradeMenu;
    private List<UpgradeActor> upgradeActorList;

    public UpgradeManager (UpgradeMenu upgradeMenu, GameManager gameManager) {
        this.upgradeMenu = upgradeMenu;
        this.gameManager = gameManager;
        this.upgradeActorList = gameManager.getAssetManager().getUpgradeFile();

        this.calculateGoldGen();

    }

    /**
     * Recalcule le goldGen en fonction
     * du niveau des updates
     */
    public void calculateGoldGen(){
        int goldGen = 0;
        for (int i=0;i<gameManager.getGameInformation().getUpgradeLevelList().size();i++) {
            if (gameManager.getGameInformation().getUpgradeLevelList().get(i) > 0) {
                goldGen += upgradeActorList.get(i).getGoldGen()[getGameManager().getGameInformation().getUpgradeLevelList().get(i)];
            }
        }
        // Valeur par defaut si aucun module
        if (goldGen==0) {
            goldGen=2;
        }
        gameManager.getGameInformation().setGenGold(goldGen);
    }

    /**
     * Valide si un upgrade est debloqué
     * @param
     * @return
     */
    public boolean isAvailable (int idSelect) {
        if (gameManager.getGameInformation().getCurrentGold() >= upgradeActorList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)] && gameManager.getGameInformation().getUpgradeLevelList().get(idSelect) < upgradeActorList.get(idSelect).getCost().length -1 ) {
            return true;
        }
        return false;
    }

    /**
     * Augmente le niveau d'un upgrade
     * @param idSelect
     */
    public void increaseUpgradeLevel(int idSelect) {
        if (isAvailable(idSelect)) {
            gameManager.getGameInformation().setCurrentGold(gameManager.getGameInformation().getCurrentGold() - upgradeActorList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)]);
            gameManager.getGameInformation().getUpgradeLevelList().set(idSelect, gameManager.getGameInformation().getUpgradeLevelList().get(idSelect) + 1);
            this.calculateGoldGen();
            this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
        }

        // modifie l'icon si premiere fois que upgrade debloquee
        if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevelList().get(idSelect) == 1) {
            switch (idSelect) {
                case 0:
                    this.upgradeMenu.getUpgradeButton1().getStyle().imageUp = (this.upgradeMenu.getUpgradeDrawable1());
                    break;
                case 1:
                    this.upgradeMenu.getUpgradeButton2().getStyle().imageUp = (this.upgradeMenu.getUpgradeDrawable2());
                    break;
                case 2:
                    this.upgradeMenu.getUpgradeButton3().getStyle().imageUp = (this.upgradeMenu.getUpgradeDrawable3());
                    break;
                case 3:
                    this.upgradeMenu.getUpgradeButton4().getStyle().imageUp = (this.upgradeMenu.getUpgradeDrawable4());
                    break;
                case 4:
                    this.upgradeMenu.getUpgradeButton5().getStyle().imageUp = (this.upgradeMenu.getUpgradeDrawable5());
                    break;
                case 5:
                    this.upgradeMenu.getUpgradeButton6().getStyle().imageUp = (this.upgradeMenu.getUpgradeDrawable6());
                    break;
                case 6:
                    this.upgradeMenu.getUpgradeButton7().getStyle().imageUp = (this.upgradeMenu.getUpgradeDrawable7());
                    break;
                case 7:
                    this.upgradeMenu.getUpgradeButton8().getStyle().imageUp = (this.upgradeMenu.getUpgradeDrawable8());
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Met a jour les informations d'un detail d'upgrade
     * @param idSelect
     */
    public void updateUpgradeInformation(int idSelect) {
        this.upgradeMenu.getDetailTitre().setText(upgradeActorList.get(idSelect).getTitle());
        this.upgradeMenu.getDetailLevel().setText(String.valueOf(upgradeMenu.getGameManager().getGameInformation().getUpgradeLevelList().get(idSelect)));
        this.upgradeMenu.getDetailGold().setText(String.valueOf(upgradeActorList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)]));
        this.upgradeMenu.getDetailDescription().setText(upgradeActorList.get(idSelect).getDescription());
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public UpgradeMenu getUpgradeMenu() {
        return upgradeMenu;
    }

    public void setUpgradeMenu(UpgradeMenu upgradeMenu) {
        this.upgradeMenu = upgradeMenu;
    }
}
