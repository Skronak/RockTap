package com.rocktap.manager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.entity.GameInformation;
import com.rocktap.entity.ModuleElement;
import com.rocktap.entity.ModuleElementLevel;
import com.rocktap.menu.moduleMenu.ModuleMenu;
import com.rocktap.menu.moduleMenu.ModuleMenuElement;
import com.rocktap.utils.ValueDTO;

import java.util.List;

import static com.rocktap.manager.AssetManager.INSTANCE;

/**
 * Created by Skronak on 14/08/2017.
 * Classe gerant les upgrades et l'ecran d'upgrade
 */
public class ModuleManager {

    private GameManager gameManager;
    public ModuleMenu moduleMenu;
    private List<ModuleElement> moduleEntityList;

    public ModuleManager(GameManager gameManager) {
        this.moduleMenu = moduleMenu;
        this.gameManager = gameManager;
        this.moduleEntityList = AssetManager.INSTANCE.getModuleElementList();
    }

    public void initialize(ModuleMenu moduleMenu){
        this.moduleMenu=moduleMenu;
        evaluatePassivGen();
    }

    /**
     * Calculate passive incomes according to unlocked upgrade
     * du niveau des updates
     */
    public void evaluatePassivGen(){
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
                newVal = gameManager.largeMath.increaseValue(goldGen,currGen,goldGenToAdd,currGenToAdd);
            }
        }
        GameInformation.INSTANCE.setGenGoldPassive(newVal.getValue());
        GameInformation.INSTANCE.setGenCurrencyPassive(newVal.getCurrency());
    }

    /**
     * Compare current gold with a module cost
     *
     * @param
     * @return true if current gold > module cost
     * @return false if current gold < module cost
     */
    public boolean isAvailableUpgrade (int idSelect) {
        int currentlevel = GameInformation.INSTANCE.getUpgradeLevelList().get(idSelect);
        ModuleElementLevel moduleLevelCurrent = moduleEntityList.get(idSelect).getLevel().get(currentlevel);

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

    public void getModuleElementById(){
    }

    /**
     * Return level texture depending on module element level
      * @param level
     * @return
     */
    public Texture getLevelTextureByLevel(int level) {
        Texture levelTexture = AssetManager.INSTANCE.getUpgradeLvlImageList().get(GameInformation.INSTANCE.getUpgradeLevelList().get(level));
        if (null==levelTexture) {
            levelTexture = AssetManager.INSTANCE.getUpgradeLvlImageList().get(0);
        }
        return levelTexture;
    }

    public void getModuleElementByLevel(){

    }

    /**
     * Increase a module level
     * @param idSelect
     */
    public void increaseModuleLevel(int idSelect) {
        // Calcul et Affichage de la soustraction
        ValueDTO decreaseValue = moduleEntityList.get(idSelect).getLevel().get(GameInformation.INSTANCE.getUpgradeLevelList().get(idSelect)).getCost();
        gameManager.playScreen.getHud().animateDecreaseGold(decreaseValue);

        // Mise a jour du montant des golds du joueur
        ValueDTO gameInformationValue = gameManager.largeMath.decreaseValue(GameInformation.INSTANCE.getCurrentGold(),GameInformation.INSTANCE.getCurrency(),decreaseValue.getValue(), decreaseValue.getCurrency());
        GameInformation.INSTANCE.setCurrentGold(gameInformationValue .getValue());
        GameInformation.INSTANCE.setCurrency(gameInformationValue .getCurrency());

        // Met a jour les informations de l'upgrade
        GameInformation.INSTANCE.getUpgradeLevelList().set(idSelect, GameInformation.INSTANCE.getUpgradeLevelList().get(idSelect) + 1);
        this.evaluatePassivGen();

        // Regenere les upgrades a afficher en jeux
        this.gameManager.playScreen.getStationEntity().initModules();
     }

     public void animateNewModule() {

     }

    /**
     * Update upgrade information from the menu
     * @param id
     */
    public void updateModuleMenuInformation(int id) {
        ModuleMenuElement moduleMenuElement = (ModuleMenuElement) moduleMenu.getScrollContainerVG().getChildren().get(id);
        ModuleElementLevel moduleLevel = moduleEntityList.get(id).getLevel().get(GameInformation.INSTANCE.getUpgradeLevelList().get(id));

        moduleMenuElement.getModuleLevelLabel().setText("Level "+GameInformation.INSTANCE.getUpgradeLevelList().get(id));
        moduleMenuElement.getModuleLevelImage().setDrawable(new TextureRegionDrawable(new TextureRegion(INSTANCE.getUpgradeLvlImageList().get(GameInformation.INSTANCE.getUpgradeLevelList().get(id)))));
        moduleMenuElement.getSkillIcon().setDrawable(new TextureRegionDrawable(new TextureRegion(INSTANCE.getModuleDrawableUpList().get(id))));
        moduleMenuElement.getGoldBonusLabel().setText("+"+gameManager.largeMath.getDisplayValue(moduleLevel.getGeneration().getValue(), moduleLevel.getGeneration().getCurrency()));
        moduleMenuElement.getBuyButton().setText(gameManager.largeMath.getDisplayValue(moduleLevel.getCost().getValue(), moduleLevel.getCost().getCurrency()));
        animateLabel(moduleMenuElement.getGoldBonusLabel());
        animateLabel(moduleMenuElement.getTimeBonusLabel());
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
