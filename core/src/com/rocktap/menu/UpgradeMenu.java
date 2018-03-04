package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.input.InputUpgradeSkillButtonListener;
import com.rocktap.manager.GameManager;
import com.rocktap.manager.ModuleManager;

import java.util.ArrayList;
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
    private Label moduleLevelLabel;
    private Image moduleLevelImage;
    private TextButton buyButton;
    // indique le skill actuellement selectionne
    private int currentSelection;
    private List<ImageButton> moduleButtonList;
    private List<Drawable> moduleDrawableUpList;
    private List<Drawable> moduleDrawableDownList;

    public UpgradeMenu(GameManager gameManager) {
        super(gameManager);
        this.moduleManager = new ModuleManager(this, gameManager);
        customizeMenuTable();

        currentSelection = 1;         // selection 1 module par defaut
    }

    public void customizeMenuTable() {
        // Contenu du menu
        parentTable.add(new Label("UPGRADE", skin)).colspan(2).height(50);
        parentTable.row();
        // Partie gauche
        parentTable.add(initScrollingModuleSelection());
        parentTable.debug();
    }

    /**
     * Methode d'initialisation des modules disponibles a
     * l'upgrade
     * @return
     */
    private ScrollPane initScrollingModuleSelection() {
        VerticalGroup scrollContainerVG = new VerticalGroup();
        scrollContainerVG.space(5f);
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        ScrollPane pane = new ScrollPane(scrollContainerVG, paneStyle);
        pane.setScrollingDisabled(true, false);

        //Couleur de fond du menu
        Pixmap pm1 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pm1.setColor(Color.BLUE);
        pm1.fill();

        // Definition drawables possibles pour les boutons
        moduleDrawableUpList = new ArrayList<Drawable>();
        moduleDrawableDownList = new ArrayList<Drawable>();
        moduleButtonList = new ArrayList<ImageButton>();
        for (int i = 0; i < this.gameManager.getAssetManager().getUpgradeFile().size(); i++) {
            moduleDrawableUpList.add(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/" + this.gameManager.getAssetManager().getUpgradeFile().get(i).getIcon())))));
            moduleDrawableDownList.add(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/" + this.gameManager.getAssetManager().getUpgradeFile().get(i).getIconDisabled())))));
        }

        // Genere chaque upgrade disponible dans la liste
        for (int i = 0; i < gameManager.getAssetManager().getUpgradeFile().size(); i++) {
            goldIcon = new Image(new Texture(Gdx.files.internal("icons/gold+.png")));
            timeIcon = new Image(new Texture(Gdx.files.internal("icons/speed+.png")));
            Image moduleIconImage = new Image(moduleDrawableUpList.get(i));
            moduleLevelLabel = new Label("Level "+gameManager.getGameInformation().getUpgradeLevelList().get(i), skin);
            moduleLevelImage = new Image(new Texture(Gdx.files.internal("icons/upgradeMenu/mLvl"+gameManager.getGameInformation().getUpgradeLevelList().get(i)+".png")));
            buyButton = new TextButton(moduleManager.getCostLabelById(i),gameManager.getAssetManager().getModuleMenuBuyTxtBtnStyle());
            InputUpgradeSkillButtonListener customInputSkillListener = new InputUpgradeSkillButtonListener(this);
            buyButton.addListener(customInputSkillListener);
            // Premiere colonne
            VerticalGroup vgCol0 = new VerticalGroup();
            vgCol0.addActor((new Label(gameManager.getAssetManager().getUpgradeFile().get(i).getTitle(), skin)));
            vgCol0.addActor(moduleIconImage);

            // Liste level actuel du module
            Table moduleLevelGroup = new Table();
            moduleLevelGroup.add(moduleLevelLabel).left().colspan(4);
            moduleLevelGroup.row();
            moduleLevelGroup.add(moduleLevelImage).size(120,40).left().colspan(4);
            moduleLevelGroup.row();
            moduleLevelGroup.add(goldIcon).size(20,20).left();
            moduleLevelGroup.add(new Label("+ 50A", skin)).left();
            moduleLevelGroup.add(timeIcon).size(20,20).left();
            moduleLevelGroup.add(new Label("- 1 min", skin)).left();
            Table moduleElementTable = new Table();
            moduleElementTable.setHeight(30);
            moduleElementTable.add(vgCol0).width(80);
            moduleElementTable.add(moduleLevelGroup);
            moduleElementTable.add(buyButton).height(90).width(70);
            moduleElementTable.debug();
            scrollContainerVG.addActor(moduleElementTable);

            // Bouton englobe le moduleElement pour afficher un fond
            /* Button button = new Button(moduleElementTable,skin);
            button.setColor(Color.GRAY);
            button.setWidth(200);
            button.setHeight(50);
            scrollContainerVG.addActor(button);*/
        }

        Gdx.app.log("ModuleMenu", "Generation des boutons de Module terminee");

        return pane;
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
