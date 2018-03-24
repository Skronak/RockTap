package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.manager.GameManager;
import com.rocktap.manager.ModuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skronak on 01/02/2017.
 * Menu d'update
 * // TODO: super menu desactivant l'input listener, gerer un state?
 */
public class UpgradeModuleMenu extends AbstractMenu {
    private Label detailGold;
    private Label detailDescription;
    private Label detailLevel;
    private Label detailTitre;
    private ModuleManager moduleManager;
    private Stack stack;
    private Label moduleLevelLabel;
    private TextButton buyButton;
    private Table moduleDetails;
    // indique le skill actuellement selectionne
    private int currentSelection;
    private List<ImageButton> moduleButtonList;
    private VerticalGroup scrollContainerVG;

    public UpgradeModuleMenu(GameManager gameManager) {
        super(gameManager);
        this.moduleManager = new ModuleManager(this, gameManager);
        customizeMenuTable();

        currentSelection = 1;         // selection 1 module par defaut
    }

    public void customizeMenuTable() {
        // titre
        parentTable.add(new Label("UPGRADE", skin)).colspan(2).height(50);
        parentTable.row();

        // Contenu
        stack = new Stack();
       // moduleDetails = new Table();
       // moduleDetails.add(new Label("ELELELE",skin));
       // moduleDetails.setWidth(100);
       // moduleDetails.setHeight(100);
       // moduleDetails.setPosition(Constants.UPDATE_MENU_PAD_EXTERNAL_WIDTH/2,Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT);
       // moduleDetails.setVisible(true);
       // moduleDetails.setBackground(gameManager.getAssetManager().getMenuBackgroundTexture());
       // moduleDetails.top();
       // moduleDetails.debug();



        stack.addActor(initScrollingModuleSelection());
        //stack.addActor(moduleDetails);
        parentTable.add(stack);
        parentTable.debug();

    }

    /**
     * Methode d'initialisation des modules disponibles a
     * l'upgrade
     *
     * @return
     */
    public ScrollPane initScrollingModuleSelection() {
        scrollContainerVG = new VerticalGroup();

        scrollContainerVG.space(5f);
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        paneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getScrollTexture(), 10, 50));

        ScrollPane pane = new ScrollPane(scrollContainerVG, paneStyle);
        pane.setScrollingDisabled(true, false);

        // Definition drawables possibles pour les boutons
        moduleButtonList = new ArrayList<ImageButton>();

        for (int i = 0; i < gameManager.getAssetManager().getModuleElementList().size(); i++) {
            ModuleElementMenu moduleElementMenu = new ModuleElementMenu(gameManager, this);
            moduleElementMenu.initModuleElementMenu(i);
            scrollContainerVG.addActor(moduleElementMenu);
        }
        Gdx.app.log("ModuleMenu", "Generation des boutons de Module terminee");

        return pane;
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

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

    public VerticalGroup getScrollContainerVG() {
        return scrollContainerVG;
    }
}
