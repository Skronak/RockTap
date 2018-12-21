package com.rocktap.menu.moduleMenu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.entity.GameInformation;
import com.rocktap.entity.ModuleElement;
import com.rocktap.entity.ModuleElementLevel;
import com.rocktap.input.BuyUpgradeButtonListener;
import com.rocktap.manager.AssetManager;
import com.rocktap.manager.GameManager;

import static com.rocktap.manager.AssetManager.INSTANCE;

/**
 * Represente un module dans le menu deroulant des modules
 * Created by Skronak on 21/03/2018.
 *
 */

public class ModuleMenuElement extends Table {
    private GameManager gameManager;
    private Label moduleLevelLabel;
    private TextButton buyButton;
    private Image moduleLevelImage;
    private Label elementTitle;
    private Label goldBonusLabel;
    private Label timeBonusLabel;
    private Image skillIcon;
    private Image goldIcon;
    private Image timeIcon;
    private ModuleElement moduleElementSource;


    public ModuleMenuElement(GameManager gameManager){
        this.gameManager = gameManager;
    }

    /**
     * Methode d'initialise du module a partir de sa position
     * dans la liste de tous les modules.
     * @param i
     */
    public void initModuleMenuElement(int i) {
        moduleElementSource = AssetManager.INSTANCE.getModuleElementList().get(i);
        int currentLevel = GameInformation.INSTANCE.getUpgradeLevelList().get(i);
        ModuleElementLevel moduleLevel = moduleElementSource.getLevel().get(GameInformation.INSTANCE.getUpgradeLevelList().get(i));

        moduleLevelLabel = new Label("Level "+currentLevel, AssetManager.INSTANCE.getSkin());
        moduleLevelImage = new Image(gameManager.moduleManager.getLevelTextureByLevel(i));
        elementTitle = new Label(moduleElementSource.getTitle(), AssetManager.INSTANCE.getSkin());
        goldBonusLabel = new Label("+"+gameManager.largeMath.getDisplayValue(moduleLevel.getGeneration().getValue(), moduleLevel.getGeneration().getCurrency()), AssetManager.INSTANCE.getSkin());
        timeBonusLabel = new Label("- 1 min", AssetManager.INSTANCE.getSkin());
        buyButton = new TextButton(gameManager.largeMath.getDisplayValue(moduleElementSource.getLevel().get(currentLevel).getCost().getValue(), moduleElementSource.getLevel().get(currentLevel).getCost().getCurrency()),AssetManager.INSTANCE.getModuleMenuBuyTxtBtnStyle());
        buyButton.addListener(new BuyUpgradeButtonListener(gameManager.moduleManager, i));
        if (currentLevel==0) {
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
        this.add(moduleLevelGroup).width(140);
        this.add(buyButton).height(90).width(70).padRight(10);

        this.setDebug(true,true);
    }

    public void update() {
        ModuleElementLevel moduleLevel = INSTANCE.getModuleElementList().get(moduleElementSource.getId()).getLevel().get(GameInformation.INSTANCE.getUpgradeLevelList().get(moduleElementSource.getId()));
        moduleLevelLabel.setText("Level "+GameInformation.INSTANCE.getUpgradeLevelList().get(moduleElementSource.getId()));
        moduleLevelImage.setDrawable(new TextureRegionDrawable(new TextureRegion(INSTANCE.getUpgradeLvlImageList().get(GameInformation.INSTANCE.getUpgradeLevelList().get(moduleElementSource.getId())))));
        skillIcon.setDrawable(new TextureRegionDrawable(new TextureRegion(INSTANCE.getModuleDrawableUpList().get(moduleElementSource.getId()))));
        goldBonusLabel.setText("+"+gameManager.largeMath.getDisplayValue(moduleLevel.getGeneration().getValue(), moduleLevel.getGeneration().getCurrency()));
        buyButton.setText(gameManager.largeMath.getDisplayValue(moduleLevel.getCost().getValue(), moduleLevel.getCost().getCurrency()));
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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
