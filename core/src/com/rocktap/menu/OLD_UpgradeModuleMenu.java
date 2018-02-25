package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
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
 * NEW MODULEMENU
 */
public class OLD_UpgradeModuleMenu extends AbstractMenu {

    private Label detailGold;
    private Label detailDescription;
    private Label detailLevel;
    private Label detailTitre;
    private Button buyButton;
    private int currentSelection;
    private List<ImageButton> moduleButtonList;
    private List<Drawable> moduleDrawableUpList;
    private List<Drawable> moduleDrawableDownList;
    private ModuleManager moduleManager;


    public OLD_UpgradeModuleMenu(GameManager gameManager) {
        super(gameManager);
        this.moduleManager = new ModuleManager(this, gameManager);
        customizeMenu();

        currentSelection = 1;
        moduleManager.updateModuleInformation(currentSelection);
    }

    private void customizeMenu(){

        //TODO classe a part pour charger les asset. a chaque asset charge on fait avancer bar de chargement
        buyButton = new TextButton("500A",gameManager.getAssetManager().getModuleMenuBuyTxtBtnStyle());

        // Contenu du menu
        parentTable.top();
        parentTable.add(new Label("UPGRADE", skin)).expandX().top().colspan(2).height(50);
        parentTable.row();

        Image image = new Image(new TextureRegion(new Texture("sprites/station/tf1.png")));
        image.setSize(280,280);

        // Prepare la liste des upgrade disponibles
        moduleDrawableUpList = new ArrayList<Drawable>();
        moduleDrawableDownList = new ArrayList<Drawable>();
        moduleButtonList = new ArrayList<ImageButton>();
        for (int i = 0; i < this.gameManager.getAssetManager().getUpgradeFile().size(); i++) {
            //Un fichier upgrade different par level de station
            moduleDrawableUpList.add(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/" + this.gameManager.getAssetManager().getUpgradeFile().get(i).getIcon())))));
            moduleDrawableDownList.add(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/" + this.gameManager.getAssetManager().getUpgradeFile().get(i).getIconDisabled())))));
            if (gameManager.getGameInformation().getUpgradeLevelList().get(i) == 0) {
                moduleButtonList.add(new ImageButton(moduleDrawableDownList.get(i)));
            } else {
                moduleButtonList.add(new ImageButton(moduleDrawableUpList.get(i)));
            }
        }

        // Image de fond
        WidgetGroup group = new WidgetGroup();
        group.addActor(image);

        // Ajoute les boutons dans au menu
        for (int i=0; i< moduleButtonList.size();i++) {
            moduleButtonList.get(i).addListener(new InputUpgradeMenuButtonListener(this, i));
            moduleButtonList.get(i).setPosition(gameManager.getAssetManager().getUpgradeFile().get(i).getPosX(), gameManager.getAssetManager().getUpgradeFile().get(i).getPosY());
            moduleButtonList.get(i).setColor(moduleButtonList.get(i).getColor().r, moduleButtonList.get(i).getColor().g, moduleButtonList.get(i).getColor().b, 0.7f);
            moduleButtonList.get(i).setPosition(this.gameManager.getAssetManager().getUpgradeFile().get(i).getIconPosX(), this.gameManager.getAssetManager().getUpgradeFile().get(i).getIconPosY());
            moduleButtonList.get(i).setScale(50,50);
            group.addActor(moduleButtonList.get(i));
        }
        parentTable.add(group).width(280).height(280);
        parentTable.row();

        InputUpgradeSkillButtonListener customInputSkillListener = new InputUpgradeSkillButtonListener(this);
        buyButton.addListener(customInputSkillListener);
        // Partie details
        detailTitre = new Label("Canon Garrick", skin);
        detailDescription = new Label("GoldGeneration 50A >>", skin);
        detailGold = new Label("", skin);
        detailLevel = new Label("", skin);
        parentTable.add(detailTitre).colspan(3);
        parentTable.row();
        parentTable.add(detailLevel);
        parentTable.add(new Label("", skin));
        parentTable.row();
        parentTable.add(detailDescription);
        parentTable.add(new Label("",skin));
        parentTable.row();
        parentTable.add(detailGold);
        parentTable.row();
        parentTable.add(buyButton).colspan(2).width(150).height(60);

        parentTable.setVisible(true);
        parentTable.debug();
    }

    // Met a jour les upgrade du joueur
    public void updateMenuState() {
        //gameManager.getGameInformation().getUpgradeLevelList()
    }

    public void updateDetailLevelLabel(String oldValue, String newValue){
        detailLevel.setText("Level "+oldValue+" >> "+newValue);
    }

    public void updateDetailGoldLabel(String oldValue, String newValue){
        detailGold.setText("Gold "+oldValue+" >> "+newValue);
    }
//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public int getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(int currentSelection) {
        this.currentSelection = currentSelection;
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

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }
}
