package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.input.InputUpgradeMenuButtonListener;
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
public class ModuleMenu extends AbstractMenu {
    private Table upgradeTable;
    private Table upgradeCostTable;
    private Label detailGold;
    private Label detailDescription;
    private Label detailLevel;
    private Label detailTitre;
    private ModuleManager moduleManager;
    private Texture squareTexture;
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

    public ModuleMenu(GameManager gameManager) {
        super(gameManager);
        this.moduleManager = new ModuleManager(this, gameManager);
        customizeMenuTable();
        currentSelection = 1;         // selection 1 module par defaut
        moduleManager.updateModuleInformation(currentSelection);
    }

    public void customizeMenuTable() {
        // Contenu du menu
        menutable.add(new Label("UPGRADE", skin)).expandX().top().colspan(2).height(50);
        menutable.row();
        menutable.add(initModuleButtonTable());
        menutable.add(initUpgradeDetailsTable()).top().expand();
//        menutable.debug();
        menutable.setVisible(false);
    }

    public Table initUpgradeDetailsTable() {
        // Initialisation des icon cost
        squareTexture = new Texture(Gdx.files.internal("sprites/menu/square.png"));
        squareImage1 = new Image(squareTexture);
        squareImage1.setSize(10, 10);
        squareImage1.setY(-(squareImage1.getHeight() / 2));
        squareImage2 = new Image(squareTexture);
        squareImage2.setSize(10, 10);
        squareImage2.setY(-(squareImage2.getHeight() / 2));
        squareImage3 = new Image(squareTexture);
        squareImage3.setSize(10, 10);
        squareImage3.setY(-(squareImage3.getHeight() / 2));
        squareImage4 = new Image(squareTexture);
        squareImage4.setSize(10, 10);
        squareImage4.setY(-(squareImage4.getHeight() / 2));
        squareImage5 = new Image(squareTexture);
        squareImage5.setSize(10, 10);
        squareImage5.setY(-(squareImage5.getHeight() / 2));

        detailTitre = new Label("", skin);
        detailDescription = new Label("DESCRIPTION", skin);
        detailDescription.setWrap(true);
        detailDescription.pack();
        detailGold = new Label("", skin);
        detailLevel = new Label("", skin);
        TextButton upgraderButton = new TextButton("UPGRADER", skin);
        InputUpgradeSkillButtonListener customInputSkillListener = new InputUpgradeSkillButtonListener(this);
        upgraderButton.addListener(customInputSkillListener);
        Table detailTable = new Table();

        // Detail d'un module
        upgradeCostTable = new Table();
        detailTable.add(detailTitre).expand().top().height(50).width(100);
        detailTable.row();
        detailTable.add(detailDescription).left();
        detailTable.row();
        detailTable.add(new Label("LEVEL: ", skin)).left();
        detailTable.add(detailLevel).width(50);
        detailTable.row();
        detailTable.add(new Label("GEN GOLD: ", skin)).left();
        detailTable.add(upgradeCostTable).width(50);
        upgradeCostTable.add(squareImage1).width(10).height(10).padLeft(10);
        upgradeCostTable.add(squareImage2).width(10).height(10).padLeft(5);
        upgradeCostTable.add(squareImage3).width(10).height(10).padLeft(5);
        upgradeCostTable.add(squareImage4).width(10).height(10).padLeft(5);
        upgradeCostTable.add(squareImage5).width(10).height(10).padLeft(5);
        detailTable.row();
        detailTable.add(new Label("COST: ", skin)).left();
        detailTable.add(detailGold).width(50);
        detailTable.row();
        detailTable.row();
        detailTable.add(upgraderButton).expandY().center();

        return detailTable;
    }

    /**
     * Methode d'initialisation des modules disponibles a
     * l'upgrade
     * @return
     */
    private ScrollPane initModuleButtonTable() {
        upgradeTable = new Table();
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        ScrollPane pane = new ScrollPane(upgradeTable, paneStyle);
        pane.setScrollingDisabled(true, false);

        // Definition drawables pour les boutons
        moduleDrawableUpList = new ArrayList<Drawable>();
        moduleDrawableDownList = new ArrayList<Drawable>();
        moduleButtonList = new ArrayList<ImageButton>();
        for (int i = 0; i < this.gameManager.getAssetManager().getUpgradeFile().size(); i++) {
            moduleDrawableUpList.add(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/" + this.gameManager.getAssetManager().getUpgradeFile().get(i).getIcon())))));
            moduleDrawableDownList.add(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/" + this.gameManager.getAssetManager().getUpgradeFile().get(i).getIconDisabled())))));
        }

        // Initialisation etat bouton au commencement du jeu avec un listener
        for (int i = 0; i < gameManager.getAssetManager().getUpgradeFile().size(); i++) {
            if (gameManager.getGameInformation().getUpgradeLevelList().get(i) == 0) {
                moduleButtonList.add(new ImageButton(moduleDrawableDownList.get(i)));
            } else {
                moduleButtonList.add(new ImageButton(moduleDrawableUpList.get(i)));
            }
            moduleButtonList.get(i).addListener(new InputUpgradeMenuButtonListener(this, i));
            upgradeTable.add(moduleButtonList.get(i)).pad(2).row();
        }

        Gdx.app.debug("ModuleMenu", "Generation des boutons de Module terminee");

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
