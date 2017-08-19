package com.rocktap.manager;

import com.rocktap.entity.ModuleEntity;
import com.rocktap.menu.ModuleMenu;
import com.rocktap.utils.ValueDTO;

import java.util.List;

/**
 * Created by Skronak on 14/08/2017.
 * Classe gerant les upgrades et l'ecran d'upgrade
 */
public class ModuleManager {

    private GameManager gameManager;
    private ModuleMenu moduleMenu;
    private List<ModuleEntity> moduleEntityList;

    public ModuleManager(ModuleMenu moduleMenu, GameManager gameManager) {
        this.moduleMenu = moduleMenu;
        this.gameManager = gameManager;
        this.moduleEntityList = gameManager.getAssetManager().getUpgradeFile();

        this.calculateGoldGen();

    }

    /**
     * Recalcule le goldGen global en fonction
     * du niveau des updates
     */
    public void calculateGoldGen(){
        float goldGen = 0;
        int currGen = 0;
        for (int i=0;i<gameManager.getGameInformation().getUpgradeLevelList().size();i++) {
            if (gameManager.getGameInformation().getUpgradeLevelList().get(i) > 0) {
                goldGen += moduleEntityList.get(i).getGoldGen()[getGameManager().getGameInformation().getUpgradeLevelList().get(i)];
                currGen += moduleEntityList.get(i).getCurrency()[getGameManager().getGameInformation().getUpgradeLevelList().get(i)];
            }
        }
        gameManager.getGameInformation().setGenGoldPassive(goldGen);
        gameManager.getGameInformation().setGenCurrencyActive(currGen);
    }

    /**
     * Valide si un upgrade est debloqué
     * @param
     * @return
     */
    public boolean isAvailable (int idSelect) {
        if (((gameManager.getGameInformation().getCurrency() > moduleEntityList.get(idSelect).getCurrency()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)])
                || (gameManager.getGameInformation().getCurrency() == moduleEntityList.get(idSelect).getCurrency()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)]
                && gameManager.getGameInformation().getCurrentGold() >= moduleEntityList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)]))
           && (gameManager.getGameInformation().getUpgradeLevelList().get(idSelect) < moduleEntityList.get(idSelect).getCurrency().length - 1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Augmente le niveau d'un module
     * @param idSelect
     */
    public void increaseUpgradeLevel(int idSelect) {
        if (isAvailable(idSelect)) {
            float costValue = moduleEntityList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)];
            int currencyValue = moduleEntityList.get(idSelect).getCurrency()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)];
            this.gameManager.getPlayScreen().getHud().animateDecreaseGold(costValue);

            ValueDTO newValue = gameManager.getLargeMath().decreaseValue(gameManager.getGameInformation().getCurrentGold(),gameManager.getGameInformation().getCurrency(),costValue, currencyValue);
            this.gameManager.getGameInformation().setCurrentGold(newValue.getValue());
            this.gameManager.getGameInformation().setCurrency(newValue.getCurrency());

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
        this.moduleMenu.getDetailTitre().setText(moduleEntityList.get(idSelect).getTitle());
        this.moduleMenu.getDetailLevel().setText(String.valueOf(moduleMenu.getGameManager().getGameInformation().getUpgradeLevelList().get(idSelect)));
        this.moduleMenu.getDetailGold().setText(String.valueOf(moduleEntityList.get(idSelect).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)]));
        this.moduleMenu.getDetailDescription().setText(moduleEntityList.get(idSelect).getDescription());

        // TODO mettre valeur directement ds JSON
        float goldGen = moduleEntityList.get(idSelect).getGoldGen()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)];
        int currGen = moduleEntityList.get(idSelect).getCurrency()[gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)];
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
