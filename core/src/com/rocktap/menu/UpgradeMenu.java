package com.rocktap.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.rocktap.manager.GameManager;
import com.rocktap.manager.ModuleManager;

import java.util.List;

/**
 * Created by Skronak on 01/02/2017.
 * Menu d'update
 * // TODO: super menu desactivant l'input listener, gerer un state?
 */
public class UpgradeMenu extends AbstractMenu {
    private Table upgradeTable;
    private Table upgradeCostTable;
    private Label detailGold;
    private Label detailDescription;
    private Label detailLevel;
    private Label detailTitre;
    private ModuleManager moduleManager;
    private Texture squareTexture;
    private Image goldIcon;
    private Image timeIcon;

    private Image squareImage1;
    private Image squareImage2;
    private Image squareImage3;
    private Image squareImage4;
    private Image squareImage5;

    // indique le skill actuellement selectionne
    private int currentSelection;
    private List<ImageButton> moduleButtonList;
    private List<Drawable> moduleDrawableUpList;
    private List<Drawable> moduleDrawableDownList;

    public UpgradeMenu(GameManager gameManager) {
        super(gameManager);
        customizeMenuTable();
        this.gameManager = gameManager;

        currentSelection = 1;         // selection 1 module par defaut
    }

    public void customizeMenuTable() {
        // Contenu du menu
        menutable.add(new Label("UPGRADE", skin)).expandX().top().colspan(2).height(50);
        menutable.row();
        Image image = new Image(new TextureRegion(new Texture("st1.png")));
        menutable.add(image);
        menutable.setVisible(true);

//        menutable.debug();
    }


//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public Table getUpgradeTable() {
        return upgradeTable;
    }

    public void setUpgradeTable(Table upgradeTable) {
        this.upgradeTable = upgradeTable;
    }

    public Table getUpgradeCostTable() {
        return upgradeCostTable;
    }

    public void setUpgradeCostTable(Table upgradeCostTable) {
        this.upgradeCostTable = upgradeCostTable;
    }

    public Label getDetailGold() {
        return detailGold;
    }

    public void setDetailGold(Label detailGold) {
        this.detailGold = detailGold;
    }

    public Label getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(Label detailDescription) {
        this.detailDescription = detailDescription;
    }

    public Label getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(Label detailLevel) {
        this.detailLevel = detailLevel;
    }

    public Label getDetailTitre() {
        return detailTitre;
    }

    public void setDetailTitre(Label detailTitre) {
        this.detailTitre = detailTitre;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public Texture getSquareTexture() {
        return squareTexture;
    }

    public void setSquareTexture(Texture squareTexture) {
        this.squareTexture = squareTexture;
    }

    public Image getSquareImage1() {
        return squareImage1;
    }

    public void setSquareImage1(Image squareImage1) {
        this.squareImage1 = squareImage1;
    }

    public Image getSquareImage2() {
        return squareImage2;
    }

    public void setSquareImage2(Image squareImage2) {
        this.squareImage2 = squareImage2;
    }

    public Image getSquareImage3() {
        return squareImage3;
    }

    public void setSquareImage3(Image squareImage3) {
        this.squareImage3 = squareImage3;
    }

    public Image getSquareImage4() {
        return squareImage4;
    }

    public void setSquareImage4(Image squareImage4) {
        this.squareImage4 = squareImage4;
    }

    public Image getSquareImage5() {
        return squareImage5;
    }

    public void setSquareImage5(Image squareImage5) {
        this.squareImage5 = squareImage5;
    }

    public int getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(int currentSelection) {
        this.currentSelection = currentSelection;
    }

    public List<ImageButton> getModuleButtonList() {
        return moduleButtonList;
    }

    public void setModuleButtonList(List<ImageButton> moduleButtonList) {
        this.moduleButtonList = moduleButtonList;
    }

    public List<Drawable> getModuleDrawableUpList() {
        return moduleDrawableUpList;
    }

    public void setModuleDrawableUpList(List<Drawable> moduleDrawableUpList) {
        this.moduleDrawableUpList = moduleDrawableUpList;
    }

    public List<Drawable> getModuleDrawableDownList() {
        return moduleDrawableDownList;
    }

    public void setModuleDrawableDownList(List<Drawable> moduleDrawableDownList) {
        this.moduleDrawableDownList = moduleDrawableDownList;
    }
}
