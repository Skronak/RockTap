package com.rocktap.manager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.entity.GameInformation;
import com.rocktap.entity.ModuleElementDTO;
import com.rocktap.entity.ModuleLevelDTO;
import com.rocktap.menu.ModuleElementTable;
import com.rocktap.menu.UpgradeModuleMenu;
import com.rocktap.utils.ValueDTO;

import java.util.List;

/**
 * Created by Skronak on 14/08/2017.
 * Classe gerant les upgrades et l'ecran d'upgrade
 */
public class ModuleManager {

    private GameManager gameManager;
    private UpgradeModuleMenu moduleMenu;
    private List<ModuleElementDTO> moduleEntityList;

    public ModuleManager(UpgradeModuleMenu moduleMenu, GameManager gameManager) {
        this.moduleMenu = moduleMenu;
        this.gameManager = gameManager;
        this.moduleEntityList = gameManager.getAssetManager().getModuleElementList();

        this.calculateGoldGen();
    }

    /**
     * Recalcule le goldGen global en fonction
     * du niveau des updates
     */
    public void calculateGoldGen(){
        float goldGen = 0;
        int currGen = 0;
        float goldGenToAdd = 0;
        int currGenToAdd = 0;
        // Total a additionner
        ValueDTO newVal = new ValueDTO(0,0);

        // Parcours la liste upgrades du joueur
        for (int i=0;i<GameInformation.INSTANCE.getUpgradeLevelList().size();i++) {
            if (GameInformation.INSTANCE.getUpgradeLevelList().get(i) > 0) {
                int upgradeLevel = GameInformation.INSTANCE.getUpgradeLevelList().get(i);
                goldGen = newVal.getValue();
                currGen = newVal.getCurrency();
                // Dans les generations possible de l'upgrade, cherche celui du lvl puis cherche valeur de l'upgrade
                goldGenToAdd = moduleEntityList.get(i).getLevel().get(upgradeLevel).getGeneration().getValue();
                currGenToAdd = moduleEntityList.get(i).getLevel().get(upgradeLevel).getGeneration().getCurrency();
                newVal = gameManager.getLargeMath().increaseValue(goldGen,currGen,goldGenToAdd,currGenToAdd);
            }
        }
        GameInformation.INSTANCE.setGenGoldPassive(newVal.getValue());
        GameInformation.INSTANCE.setGenCurrencyPassive(newVal.getCurrency());
    }

    /**
     * Verifie qu'on peut cliquer sur l'upgrade
     * @param
     * @return
     */
    public boolean isAvailableUpgrade (int idSelect) {
        int currentlevel = GameInformation.INSTANCE.getUpgradeLevelList().get(idSelect);
        ModuleLevelDTO moduleLevelCurrent = moduleEntityList.get(idSelect).getLevel().get(currentlevel);

        if ( (moduleEntityList.get(idSelect).getLevel().size() > currentlevel + 1)
           && (GameInformation.INSTANCE.getCurrency() > moduleLevelCurrent.getCost().getCurrency())
           || ( (GameInformation.INSTANCE.getCurrency() == moduleLevelCurrent.getCost().getCurrency())
              && (GameInformation.INSTANCE.getCurrentGold() >= moduleLevelCurrent.getCost().getValue())
              )){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if button is disabled or not
     */
    public void updateUpgradeButton () {
        for (int i=0;i<gameManager.getAssetManager().getModuleElementList().size();i++) {
            if (isAvailableUpgrade(i)){
                ((ModuleElementTable) moduleMenu.getScrollContainerVG().getChildren().get(i)).getBuyButton().setTouchable(Touchable.enabled);
                ((ModuleElementTable) moduleMenu.getScrollContainerVG().getChildren().get(i)).getBuyButton().setColor(Color.YELLOW);
            } else {
                ((ModuleElementTable) moduleMenu.getScrollContainerVG().getChildren().get(i)).getBuyButton().setTouchable(Touchable.disabled);
                ((ModuleElementTable) moduleMenu.getScrollContainerVG().getChildren().get(i)).getBuyButton().setColor(Color.GRAY);
            }
        }
    }

    /**
     * Augmente le niveau d'un module
     * @param idSelect
     */
    public void increaseUpgradeLevel(int idSelect) {
        // Calcul et Affichage de la soustraction
        ValueDTO decreaseValue = moduleEntityList.get(idSelect).getLevel().get(GameInformation.INSTANCE.getUpgradeLevelList().get(idSelect)).getCost();
        this.gameManager.getPlayScreen().getHud().animateDecreaseGold(decreaseValue);

        // Mise a jour du montant des golds du joueur
        ValueDTO gameInformationValue = gameManager.getLargeMath().decreaseValue(GameInformation.INSTANCE.getCurrentGold(),GameInformation.INSTANCE.getCurrency(),decreaseValue.getValue(), decreaseValue.getCurrency());
        GameInformation.INSTANCE.setCurrentGold(gameInformationValue .getValue());
        GameInformation.INSTANCE.setCurrency(gameInformationValue .getCurrency());

        // Met a jour les informations de l'upgrade
        GameInformation.INSTANCE.getUpgradeLevelList().set(idSelect, GameInformation.INSTANCE.getUpgradeLevelList().get(idSelect) + 1);
        this.calculateGoldGen();

        // Regenere les upgrades a afficher en jeux
        this.gameManager.getPlayScreen().getStationEntity().initModule();
     }

    /**
     * Met a jour les informations d'un detail d'upgrade
     * en fonction de l'upgrade selectionne
     * @param id
     */
    public void updateModuleInformation(int id) {
        ModuleElementTable moduleElementTable = (ModuleElementTable) moduleMenu.getScrollContainerVG().getChildren().get(id);
        ModuleLevelDTO moduleLevel = gameManager.getAssetManager().getModuleElementList().get(id).getLevel().get(GameInformation.INSTANCE.getUpgradeLevelList().get(id));

        moduleElementTable.getModuleLevelLabel().setText("Level "+GameInformation.INSTANCE.getUpgradeLevelList().get(id));
        moduleElementTable.getModuleLevelImage().setDrawable(new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getUpgradeLvlImageList().get(GameInformation.INSTANCE.getUpgradeLevelList().get(id)))));
        moduleElementTable.getSkillIcon().setDrawable(new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getModuleDrawableUpList().get(id))));
        moduleElementTable.getGoldBonusLabel().setText("+"+gameManager.getLargeMath().getDisplayValue(moduleLevel.getGeneration().getValue(), moduleLevel.getGeneration().getCurrency()));
        moduleElementTable.getBuyButton().setText(gameManager.getLargeMath().getDisplayValue(moduleLevel.getCost().getValue(), moduleLevel.getCost().getCurrency()));
        animateLabel(moduleElementTable.getGoldBonusLabel());
        animateLabel(moduleElementTable.getTimeBonusLabel());
    }

    public void animateLabel(Label label) {
        label.clearActions();
        label.addAction(Actions.sequence(Actions.color(Color.BLUE,0.5f), Actions.color(Color.WHITE)));
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
}
