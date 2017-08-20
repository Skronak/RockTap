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
        float goldGenToAdd=0;
        int currGenToAdd=0;
        ValueDTO newVal=new ValueDTO(0,0);
        for (int i=0;i<gameManager.getGameInformation().getUpgradeLevelList().size();i++) {
            if (gameManager.getGameInformation().getUpgradeLevelList().get(i) > 0) {
                goldGen=newVal.getValue();
                currGen=newVal.getCurrency();
                goldGenToAdd=moduleEntityList.get(i).getGeneration().get(gameManager.getGameInformation().getUpgradeLevelList().get(i)).getValue();
                currGenToAdd=moduleEntityList.get(i).getGeneration().get(gameManager.getGameInformation().getUpgradeLevelList().get(i)).getCurrency();
                newVal = gameManager.getLargeMath().increaseValue(goldGen,currGen,goldGenToAdd,currGenToAdd);
            }
        }
        gameManager.getGameInformation().setGenGoldPassive(newVal.getValue());
        gameManager.getGameInformation().setGenCurrencyPassive(newVal.getCurrency());
    }

    /**
     * Valide si un upgrade est debloqué
     * @param
     * @return
     */
    public boolean isAvailable (int idSelect) {
        if ( (gameManager.getGameInformation().getUpgradeLevelList().get(idSelect) < moduleEntityList.get(idSelect).getCost().size()-1) &&
            ((gameManager.getGameInformation().getCurrency() > moduleEntityList.get(idSelect).getCost().get(gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)).getCurrency()
                || (gameManager.getGameInformation().getCurrency() == moduleEntityList.get(idSelect).getCost().get(gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)).getCurrency()
                && gameManager.getGameInformation().getCurrentGold() >= moduleEntityList.get(idSelect).getCost().get(gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)).getValue())))) {
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

            ValueDTO decreaseValue = moduleEntityList.get(idSelect).getCost().get(gameManager.getGameInformation().getUpgradeLevelList().get(idSelect));
            this.gameManager.getPlayScreen().getHud().animateDecreaseGold(decreaseValue);

            ValueDTO gameInformationValue = gameManager.getLargeMath().decreaseValue(gameManager.getGameInformation().getCurrentGold(),gameManager.getGameInformation().getCurrency(),decreaseValue.getValue(), decreaseValue.getCurrency());
            this.gameManager.getGameInformation().setCurrentGold(gameInformationValue .getValue());
            this.gameManager.getGameInformation().setCurrency(gameInformationValue .getCurrency());

            gameManager.getGameInformation().getUpgradeLevelList().set(idSelect, gameManager.getGameInformation().getUpgradeLevelList().get(idSelect) + 1);
            this.calculateGoldGen();
            this.moduleMenu.getGameManager().getStationActor().loadUpgrade();
        }

        // modifie l'icon si premiere fois que upgrade debloquee
        if (this.moduleMenu.getGameManager().getGameInformation().getUpgradeLevelList().get(idSelect) == 1) {
                this.moduleMenu.getModuleButtonList().get(idSelect).getStyle().imageUp = (this.moduleMenu.getModuleDrawableUpList().get(idSelect));
            }
     }

    /**
     * Met a jour les informations d'un detail d'upgrade
     * en fonction de l'upgrade selectionne
     * @param idSelect
     */
    public void updateModuleInformation(int idSelect) {
        ModuleEntity moduleEntity = moduleEntityList.get(idSelect);
        ValueDTO costValue = moduleEntity.getCost().get(gameManager.getGameInformation().getUpgradeLevelList().get(idSelect));
        ValueDTO genValue = moduleEntity.getGeneration().get(gameManager.getGameInformation().getUpgradeLevelList().get(idSelect));

        this.moduleMenu.getDetailTitre().setText(moduleEntity.getTitle());
        this.moduleMenu.getDetailLevel().setText(String.valueOf(gameManager.getGameInformation().getUpgradeLevelList().get(idSelect)));
        this.moduleMenu.getDetailGold().setText(this.gameManager.getLargeMath().getDisplayValue(costValue.getValue(), costValue.getCurrency()));
        this.moduleMenu.getDetailDescription().setText(moduleEntity.getDescription());

        //TODO a switch selon si actif ou passif
        int nbSquare=0;
        int diffCurr = genValue.getCurrency() - gameManager.getGameInformation().getGenCurrencyActive();
        float diffValue = genValue.getValue() - gameManager.getGameInformation().getGenGoldActive();
        if (diffCurr > 2) {
            nbSquare=5;
        } else if (diffCurr > 1) {
            nbSquare=4;
        } else if (diffCurr ==0 && diffValue > gameManager.getGameInformation().getGenGoldActive()) {
            nbSquare=3;
        } else if (diffCurr == 0 && diffValue >0 ) {
            nbSquare=2;
        } else {
            nbSquare = 1;
        }

        for (int i = 0; i<this.moduleMenu.getUpgradeCostTable().getCells().size; i++) {
            if (i<nbSquare) {
                this.moduleMenu.getUpgradeCostTable().getCells().get(i).getActor().setVisible(true);
            } else {
                this.moduleMenu.getUpgradeCostTable().getCells().get(i).getActor().setVisible(false);
            }
        }
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************
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
