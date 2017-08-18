package com.rocktap.manager;

import com.rocktap.entity.ModuleActor;
import com.rocktap.menu.ModuleMenu;

import java.util.List;

/**
 * Created by Skronak on 14/08/2017.
 * Classe gerant les upgrades et l'ecran d'upgrade
 */
public class ModuleManager {

    private GameManager gameManager;
    private ModuleMenu moduleMenu;
    private List<ModuleActor> moduleActorList;

    public ModuleManager(ModuleMenu moduleMenu, GameManager gameManager) {
        this.moduleMenu = moduleMenu;
        this.gameManager = gameManager;
        this.moduleActorList = gameManager.getAssetManager().getUpgradeFile();

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
                goldGen += moduleActorList.get(i).getGoldGen()[getGameManager().getGameInformation().getUpgradeLevelList().get(i)];
            }
        }
        gameManager.getGameInformation().setGenGoldPassive(goldGen);
    }

    /**
     * Valide si un upgrade est debloqué
     * @param
     * @return
     */
    public boolean isAvailable (int idSelect) {
        if (gameManager.getGameInformation().getCurrentGold() >= moduleActorList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)] && gameManager.getGameInformation().getUpgradeLevelList().get(idSelect) < moduleActorList.get(idSelect).getCost().length -1 ) {
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
            float costValue = moduleActorList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)];
            this.gameManager.getPlayScreen().getHud().animateDecreaseGold(costValue);

            gameManager.getGameInformation().setCurrentGold(gameManager.getGameInformation().getCurrentGold() - costValue);
            gameManager.getGameInformation().getUpgradeLevelList().set(idSelect, gameManager.getGameInformation().getUpgradeLevelList().get(idSelect) + 1);
            this.calculateGoldGen();
            this.moduleMenu.getGameManager().getStationActor().loadUpgrade();
        }

        // modifie l'icon si premiere fois que upgrade debloquee
        if (this.moduleMenu.getGameManager().getGameInformation().getUpgradeLevelList().get(idSelect) == 1) {
            switch (idSelect) {
                case 0:
                    this.moduleMenu.getUpgradeButton1().getStyle().imageUp = (this.moduleMenu.getUpgradeDrawable1());
                    break;
                case 1:
                    this.moduleMenu.getUpgradeButton2().getStyle().imageUp = (this.moduleMenu.getUpgradeDrawable2());
                    break;
                case 2:
                    this.moduleMenu.getUpgradeButton3().getStyle().imageUp = (this.moduleMenu.getUpgradeDrawable3());
                    break;
                case 3:
                    this.moduleMenu.getUpgradeButton4().getStyle().imageUp = (this.moduleMenu.getUpgradeDrawable4());
                    break;
                case 4:
                    this.moduleMenu.getUpgradeButton5().getStyle().imageUp = (this.moduleMenu.getUpgradeDrawable5());
                    break;
                case 5:
                    this.moduleMenu.getUpgradeButton6().getStyle().imageUp = (this.moduleMenu.getUpgradeDrawable6());
                    break;
                case 6:
                    this.moduleMenu.getUpgradeButton7().getStyle().imageUp = (this.moduleMenu.getUpgradeDrawable7());
                    break;
                case 7:
                    this.moduleMenu.getUpgradeButton8().getStyle().imageUp = (this.moduleMenu.getUpgradeDrawable8());
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
        this.moduleMenu.getDetailTitre().setText(moduleActorList.get(idSelect).getTitle());
        this.moduleMenu.getDetailLevel().setText(String.valueOf(moduleMenu.getGameManager().getGameInformation().getUpgradeLevelList().get(idSelect)));
        this.moduleMenu.getDetailGold().setText(String.valueOf(moduleActorList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)]));
        this.moduleMenu.getDetailDescription().setText(moduleActorList.get(idSelect).getDescription());

        // TODO mettre valeur directement ds JSON
        int goldGen = moduleActorList.get(idSelect).getGoldGen()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)];
        int nbSquare=0;
        if (goldGen < 5) {
            nbSquare=1;
        }else if (goldGen < 12) {
            nbSquare=2;
        }else if (goldGen < 20) {
            nbSquare=3;
        }else if (goldGen < 30) {
            nbSquare = 4;
        } else {
            nbSquare = 5;
        }
        for (int i = 0; i<this.moduleMenu.getUpgradeCostTable().getCells().size; i++) {
            if (i<nbSquare) {
                this.moduleMenu.getUpgradeCostTable().getCells().get(i).getActor().setVisible(true);
            } else {
                this.moduleMenu.getUpgradeCostTable().getCells().get(i).getActor().setVisible(false);
            }
        }
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public ModuleMenu getModuleMenu() {
        return moduleMenu;
    }

    public void setModuleMenu(ModuleMenu moduleMenu) {
        this.moduleMenu = moduleMenu;
    }
}
