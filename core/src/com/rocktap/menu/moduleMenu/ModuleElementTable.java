package com.rocktap.menu.moduleMenu;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.rocktap.entity.GameInformation;
import com.rocktap.entity.ModuleElementDTO;
import com.rocktap.entity.ModuleLevelDTO;
import com.rocktap.input.BuyUpgradeButtonListener;
import com.rocktap.manager.AssetManager;
import com.rocktap.manager.GameManager;

/**
 * Represente un module dans le menu deroulant des modules
 * Created by Skronak on 21/03/2018.
 *
 */

public class ModuleElementTable extends Table {
    private GameManager gameManager;
    private ModuleMenu moduleMenu;

    private Label moduleLevelLabel;
    private TextButton buyButton;
    private Image moduleLevelImage;
    private Label elementTitle;
    private Label goldBonusLabel;
    private Label timeBonusLabel;
    private Image skillIcon;
    private Image goldIcon;
    private Image timeIcon;

    public ModuleElementTable(GameManager gameManager, ModuleMenu moduleMenu){
        this.gameManager = gameManager;
        this.moduleMenu = moduleMenu;
    }

    /**
     * Methode d'initialise du module a partir de sa position
     * dans la liste de tous les modules.
     * @param i
     */
    public void initModuleElementMenu(int i) {
        ModuleElementDTO moduleElementDTOSource = AssetManager.INSTANCE.getModuleElementList().get(i);
        int currentLevel = GameInformation.INSTANCE.getUpgradeLevelList().get(i);
        ModuleLevelDTO moduleLevel = AssetManager.INSTANCE.getModuleElementList().get(i).getLevel().get(GameInformation.INSTANCE.getUpgradeLevelList().get(i));

        moduleLevelLabel = new Label("Level "+currentLevel, AssetManager.INSTANCE.getSkin());
        moduleLevelImage = new Image(AssetManager.INSTANCE.getUpgradeLvlImageList().get(GameInformation.INSTANCE.getUpgradeLevelList().get(i)));
        elementTitle = new Label(moduleElementDTOSource.getTitle(), AssetManager.INSTANCE.getSkin());
        goldBonusLabel = new Label("+"+gameManager.largeMath.getDisplayValue(moduleLevel.getGeneration().getValue(), moduleLevel.getGeneration().getCurrency()), AssetManager.INSTANCE.getSkin());
        timeBonusLabel = new Label("- 1 min", AssetManager.INSTANCE.getSkin());
        buyButton = new TextButton(gameManager.largeMath.getDisplayValue(moduleElementDTOSource.getLevel().get(currentLevel).getCost().getValue(), moduleElementDTOSource.getLevel().get(currentLevel).getCost().getCurrency()),AssetManager.INSTANCE.getModuleMenuBuyTxtBtnStyle());
        buyButton.addListener(new BuyUpgradeButtonListener(moduleMenu, i));
        if (GameInformation.INSTANCE.getUpgradeLevelList().get(i)==0) {
            skillIcon = new Image(AssetManager.INSTANCE.getDisabledIcon());
        } else {
            skillIcon = new Image(AssetManager.INSTANCE.getModuleDrawableUpList().get(i));
        }
        goldIcon = new Image(AssetManager.INSTANCE.getGoldIcon());
        timeIcon = new Image(AssetManager.INSTANCE.getTimeIcon());

        // Premiere colonne
        VerticalGroup vgCol0 = new VerticalGroup();
        vgCol0.addActor(elementTitle);
        vgCol0.addActor(skillIcon);

        // Liste level actuel du module
        Table moduleLevelGroup = new Table();
        moduleLevelGroup.debug();
        moduleLevelGroup.add(moduleLevelLabel).left().colspan(4);
        moduleLevelGroup.row();
        moduleLevelGroup.add(moduleLevelImage).size(120, 40).left().colspan(4);
        moduleLevelGroup.row();
        moduleLevelGroup.add(goldIcon).size(20,20).left();
        moduleLevelGroup.add(goldBonusLabel).left();
        moduleLevelGroup.add(timeIcon).size(20,20).left();
        moduleLevelGroup.add(timeBonusLabel).left();

        this.setHeight(30);
        this.add(vgCol0).width(80);
        this.add(moduleLevelGroup);
        this.add(buyButton).height(90).width(70).padLeft(2);
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************
    public Label getModuleLevelLabel() {
        return moduleLevelLabel;
    }

    public void setModuleLevelLabel(Label moduleLevelLabel) {
        this.moduleLevelLabel = moduleLevelLabel;
    }

    public TextButton getBuyButton() {
        return buyButton;
    }

    public void setBuyButton(TextButton buyButton) {
        this.buyButton = buyButton;
    }

    public Image getModuleLevelImage() {
        return moduleLevelImage;
    }

    public void setModuleLevelImage(Image moduleLevelImage) {
        this.moduleLevelImage = moduleLevelImage;
    }

    public Label getElementTitle() {
        return elementTitle;
    }

    public void setElementTitle(Label elementTitle) {
        this.elementTitle = elementTitle;
    }

    public Label getGoldBonusLabel() {
        return goldBonusLabel;
    }

    public void setGoldBonusLabel(Label goldBonusLabel) {
        this.goldBonusLabel = goldBonusLabel;
    }

    public Label getTimeBonusLabel() {
        return timeBonusLabel;
    }

    public void setTimeBonusLabel(Label timeBonusLabel) {
        this.timeBonusLabel = timeBonusLabel;
    }

    public Image getSkillIcon() {
        return skillIcon;
    }

    public Image getGoldIcon() {
        return goldIcon;
    }

    public Image getTimeIcon() {
        return timeIcon;
    }
}
