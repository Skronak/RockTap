package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.rocktap.manager.UpgradeManager;

/**
 * Created by Skronak on 01/02/2017.
 * Menu d'update
 * // TODO: super menu desactivant l'input listener, gerer un state?
 */
public class UpgradeMenu extends AbstractMenu {
    private Table upgradeTable;
    private Label detailGold;
    private Label detailDescription;
    private Label detailLevel;
    private Label detailTitre;
    private UpgradeManager upgradeManager;

    // indique le skill actuellement selectionne
    private int currentSelection;
    private Drawable upgradeDrawable1, upgradeDrawable2, upgradeDrawable3, upgradeDrawable4, upgradeDrawable5, upgradeDrawable6, upgradeDrawable7, upgradeDrawable8,
            upgradeDrawable1_r, upgradeDrawable2_r, upgradeDrawable3_r, upgradeDrawable4_r, upgradeDrawable5_r, upgradeDrawable6_r, upgradeDrawable7_r, upgradeDrawable8_r;
    private ImageButton upgradeButton1, upgradeButton2, upgradeButton3, upgradeButton4, upgradeButton5, upgradeButton6, upgradeButton7, upgradeButton8;

    public UpgradeMenu(GameManager gameManager) {
        super(gameManager);
        this.upgradeManager = new UpgradeManager(this, gameManager);
        customizeMenuTable();
    }

    public void customizeMenuTable() {
        // Contenu du menu
        menutable.add(new Label("UPGRADE",skin)).expandX().top().colspan(2).height(50);
        menutable.row();
        menutable.add(initUpgradeButtonTable());
        menutable.add(initUpgradeDetailsTable()).top().expand();
//        menutable.debug();
        menutable.setVisible(false);
    }

    public Table initUpgradeDetailsTable() {
        detailTitre = new Label("", skin);
        detailDescription = new Label("DESCRIPTION",skin);
        detailDescription.setWrap(true);
        detailDescription.pack();
        detailGold = new Label("", skin);
        detailLevel = new Label("", skin);
        TextButton upgraderButton = new TextButton("UPGRADER", skin);
        InputUpgradeSkillButtonListener customInputSkillListener = new InputUpgradeSkillButtonListener(this);
        upgraderButton.addListener(customInputSkillListener);
        Table detailTable = new Table();
        detailTable.add(detailTitre).expand().top().height(50).width(100);
        detailTable.row();
        detailTable.add(detailDescription).left();
        detailTable.row();
        detailTable.add(new Label("LEVEL: ", skin)).left();
        detailTable.add(detailLevel).width(50);
        detailTable.row();
        detailTable.add(new Label("GOLD GENERATION: ", skin)).left();
        detailTable.add(detailGold).width(50);
        detailTable.row();
        detailTable.row();
        detailTable.add(upgraderButton).expandY().center();
        detailTable.debug();

        return detailTable;
    }
    private ScrollPane initUpgradeButtonTable() {
        upgradeTable = new Table();
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        ScrollPane pane = new ScrollPane(upgradeTable,paneStyle);
        pane.setScrollingDisabled(true, false);

        // Definition des boutons
        upgradeDrawable1 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(0).getIcon()))));
        upgradeDrawable2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(1).getIcon()))));
        upgradeDrawable3 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(2).getIcon()))));
        upgradeDrawable4 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(3).getIcon()))));
        upgradeDrawable5 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(4).getIcon()))));
        upgradeDrawable6 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(5).getIcon()))));
        upgradeDrawable7 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(6).getIcon()))));
        upgradeDrawable8 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(7).getIcon()))));
        upgradeDrawable1_r = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(0).getIconDisabled()))));
        upgradeDrawable2_r = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(1).getIconDisabled()))));
        upgradeDrawable3_r = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(2).getIconDisabled()))));
        upgradeDrawable4_r = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(3).getIconDisabled()))));
        upgradeDrawable5_r = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(4).getIconDisabled()))));
        upgradeDrawable6_r = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(5).getIconDisabled()))));
        upgradeDrawable7_r = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(6).getIconDisabled()))));
        upgradeDrawable8_r = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/"+gameManager.getAssetManager().getUpgradeFile().get(7).getIconDisabled()))));

        // Initialisation etat bouton au commencement du jeu
        if (gameManager.getGameInformation().getUpgradeLevelList().get(0)==0) {
            upgradeButton1 = new ImageButton(upgradeDrawable1_r);
        } else {
            upgradeButton1 = new ImageButton(upgradeDrawable1);
        }
        if (gameManager.getGameInformation().getUpgradeLevelList().get(1)==0) {
            upgradeButton2 = new ImageButton(upgradeDrawable2_r);
        } else {
            upgradeButton2 = new ImageButton(upgradeDrawable2);
        }
        if (gameManager.getGameInformation().getUpgradeLevelList().get(2)==0) {
            upgradeButton3 = new ImageButton(upgradeDrawable3_r);
        } else {
            upgradeButton3 = new ImageButton(upgradeDrawable3);
        }
        if (gameManager.getGameInformation().getUpgradeLevelList().get(3)==0) {
            upgradeButton4 = new ImageButton(upgradeDrawable4_r);
        } else {
            upgradeButton4 = new ImageButton(upgradeDrawable4);
        }
        if (gameManager.getGameInformation().getUpgradeLevelList().get(4)==0) {
            upgradeButton5 = new ImageButton(upgradeDrawable5_r);
        } else {
            upgradeButton5 = new ImageButton(upgradeDrawable5);
        }
        if (gameManager.getGameInformation().getUpgradeLevelList().get(5)==0) {
            upgradeButton6 = new ImageButton(upgradeDrawable6_r);
        } else {
            upgradeButton6 = new ImageButton(upgradeDrawable6);
        }
        if (gameManager.getGameInformation().getUpgradeLevelList().get(6)==0) {
            upgradeButton7 = new ImageButton(upgradeDrawable7_r);
        } else {
            upgradeButton7 = new ImageButton(upgradeDrawable7);
        }
        if (gameManager.getGameInformation().getUpgradeLevelList().get(7)==0) {
            upgradeButton8 = new ImageButton(upgradeDrawable8_r);
        } else {
            upgradeButton8 = new ImageButton(upgradeDrawable8);
        }

        // Ajout des listener sur les boutons du menu
        InputUpgradeMenuButtonListener customInputUpgradeProcessor1 = new InputUpgradeMenuButtonListener(this,0,
                gameManager.getAssetManager().getUpgradeFile().get(0).getTitle(),
                gameManager.getAssetManager().getUpgradeFile().get(0).getDescription(),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(0).getCost()[gameManager.getGameInformation().getUpgradeLevelList().get(0)]),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(0).getGoldGen()[0]));
        InputUpgradeMenuButtonListener customInputUpgradeProcessor2 = new InputUpgradeMenuButtonListener(this,1,
                gameManager.getAssetManager().getUpgradeFile().get(1).getTitle(),
                gameManager.getAssetManager().getUpgradeFile().get(1).getDescription(),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(1).getCost()[0]),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(1).getGoldGen()[0]));
        InputUpgradeMenuButtonListener customInputUpgradeProcessor3 = new InputUpgradeMenuButtonListener(this,2,
                gameManager.getAssetManager().getUpgradeFile().get(2).getTitle(),
                gameManager.getAssetManager().getUpgradeFile().get(2).getDescription(),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(2).getCost()[0]),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(2).getGoldGen()[0]));
        InputUpgradeMenuButtonListener customInputUpgradeProcessor4 = new InputUpgradeMenuButtonListener(this,3,
                gameManager.getAssetManager().getUpgradeFile().get(3).getTitle(),
                gameManager.getAssetManager().getUpgradeFile().get(3).getDescription(),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(3).getCost()[0]),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(3).getGoldGen()[0]));
        InputUpgradeMenuButtonListener customInputUpgradeProcessor5 = new InputUpgradeMenuButtonListener(this,4,
                gameManager.getAssetManager().getUpgradeFile().get(4).getTitle(),
                gameManager.getAssetManager().getUpgradeFile().get(4).getDescription(),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(4).getCost()[0]),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(4).getGoldGen()[0]));
        InputUpgradeMenuButtonListener customInputUpgradeProcessor6 = new InputUpgradeMenuButtonListener(this,5,
                gameManager.getAssetManager().getUpgradeFile().get(5).getTitle(),
                gameManager.getAssetManager().getUpgradeFile().get(5).getDescription(),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(5).getCost()[0]),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(5).getGoldGen()[0]));
        InputUpgradeMenuButtonListener customInputUpgradeProcessor7 = new InputUpgradeMenuButtonListener(this,6,
                gameManager.getAssetManager().getUpgradeFile().get(6).getTitle(),
                gameManager.getAssetManager().getUpgradeFile().get(6).getDescription(),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(6).getCost()[0]),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(6).getGoldGen()[0]));
        InputUpgradeMenuButtonListener customInputUpgradeProcessor8 = new InputUpgradeMenuButtonListener(this,7,
                gameManager.getAssetManager().getUpgradeFile().get(7).getTitle(),
                gameManager.getAssetManager().getUpgradeFile().get(7).getDescription(),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(7).getCost()[0]),
                String.valueOf(gameManager.getAssetManager().getUpgradeFile().get(7).getGoldGen()[0]));
        upgradeButton1.addListener(customInputUpgradeProcessor1);
        upgradeButton2.addListener(customInputUpgradeProcessor2);
        upgradeButton3.addListener(customInputUpgradeProcessor3);
        upgradeButton4.addListener(customInputUpgradeProcessor4);
        upgradeButton5.addListener(customInputUpgradeProcessor5);
        upgradeButton6.addListener(customInputUpgradeProcessor6);
        upgradeButton7.addListener(customInputUpgradeProcessor7);
        upgradeButton8.addListener(customInputUpgradeProcessor8);

        // Ajout des boutons
        upgradeTable.add(upgradeButton1).pad(2).row();
        upgradeTable.add(upgradeButton2).pad(2).row();
        upgradeTable.add(upgradeButton3).pad(2).row();
        upgradeTable.add(upgradeButton4).pad(2).row();
        upgradeTable.add(upgradeButton5).pad(2).row();
        upgradeTable.add(upgradeButton6).pad(2).row();
        upgradeTable.add(upgradeButton7).pad(2).row();
        upgradeTable.add(upgradeButton8).pad(2).row();

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

    public int getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(int currentSelection) {
        this.currentSelection = currentSelection;
    }

    public Drawable getUpgradeDrawable1() {
        return upgradeDrawable1;
    }

    public void setUpgradeDrawable1(Drawable upgradeDrawable1) {
        this.upgradeDrawable1 = upgradeDrawable1;
    }

    public Drawable getUpgradeDrawable2() {
        return upgradeDrawable2;
    }

    public void setUpgradeDrawable2(Drawable upgradeDrawable2) {
        this.upgradeDrawable2 = upgradeDrawable2;
    }

    public Drawable getUpgradeDrawable3() {
        return upgradeDrawable3;
    }

    public void setUpgradeDrawable3(Drawable upgradeDrawable3) {
        this.upgradeDrawable3 = upgradeDrawable3;
    }

    public Drawable getUpgradeDrawable4() {
        return upgradeDrawable4;
    }

    public void setUpgradeDrawable4(Drawable upgradeDrawable4) {
        this.upgradeDrawable4 = upgradeDrawable4;
    }

    public Drawable getUpgradeDrawable5() {
        return upgradeDrawable5;
    }

    public void setUpgradeDrawable5(Drawable upgradeDrawable5) {
        this.upgradeDrawable5 = upgradeDrawable5;
    }

    public Drawable getUpgradeDrawable6() {
        return upgradeDrawable6;
    }

    public void setUpgradeDrawable6(Drawable upgradeDrawable6) {
        this.upgradeDrawable6 = upgradeDrawable6;
    }

    public Drawable getUpgradeDrawable7() {
        return upgradeDrawable7;
    }

    public void setUpgradeDrawable7(Drawable upgradeDrawable7) {
        this.upgradeDrawable7 = upgradeDrawable7;
    }

    public Drawable getUpgradeDrawable8() {
        return upgradeDrawable8;
    }

    public void setUpgradeDrawable8(Drawable upgradeDrawable8) {
        this.upgradeDrawable8 = upgradeDrawable8;
    }

    public Drawable getUpgradeDrawable1_r() {
        return upgradeDrawable1_r;
    }

    public void setUpgradeDrawable1_r(Drawable upgradeDrawable1_r) {
        this.upgradeDrawable1_r = upgradeDrawable1_r;
    }

    public Drawable getUpgradeDrawable2_r() {
        return upgradeDrawable2_r;
    }

    public void setUpgradeDrawable2_r(Drawable upgradeDrawable2_r) {
        this.upgradeDrawable2_r = upgradeDrawable2_r;
    }

    public Drawable getUpgradeDrawable3_r() {
        return upgradeDrawable3_r;
    }

    public void setUpgradeDrawable3_r(Drawable upgradeDrawable3_r) {
        this.upgradeDrawable3_r = upgradeDrawable3_r;
    }

    public Drawable getUpgradeDrawable4_r() {
        return upgradeDrawable4_r;
    }

    public void setUpgradeDrawable4_r(Drawable upgradeDrawable4_r) {
        this.upgradeDrawable4_r = upgradeDrawable4_r;
    }

    public Drawable getUpgradeDrawable5_r() {
        return upgradeDrawable5_r;
    }

    public void setUpgradeDrawable5_r(Drawable upgradeDrawable5_r) {
        this.upgradeDrawable5_r = upgradeDrawable5_r;
    }

    public Drawable getUpgradeDrawable6_r() {
        return upgradeDrawable6_r;
    }

    public void setUpgradeDrawable6_r(Drawable upgradeDrawable6_r) {
        this.upgradeDrawable6_r = upgradeDrawable6_r;
    }

    public Drawable getUpgradeDrawable7_r() {
        return upgradeDrawable7_r;
    }

    public void setUpgradeDrawable7_r(Drawable upgradeDrawable7_r) {
        this.upgradeDrawable7_r = upgradeDrawable7_r;
    }

    public Drawable getUpgradeDrawable8_r() {
        return upgradeDrawable8_r;
    }

    public void setUpgradeDrawable8_r(Drawable upgradeDrawable8_r) {
        this.upgradeDrawable8_r = upgradeDrawable8_r;
    }

    public ImageButton getUpgradeButton1() {
        return upgradeButton1;
    }

    public void setUpgradeButton1(ImageButton upgradeButton1) {
        this.upgradeButton1 = upgradeButton1;
    }

    public ImageButton getUpgradeButton2() {
        return upgradeButton2;
    }

    public void setUpgradeButton2(ImageButton upgradeButton2) {
        this.upgradeButton2 = upgradeButton2;
    }

    public ImageButton getUpgradeButton3() {
        return upgradeButton3;
    }

    public void setUpgradeButton3(ImageButton upgradeButton3) {
        this.upgradeButton3 = upgradeButton3;
    }

    public ImageButton getUpgradeButton4() {
        return upgradeButton4;
    }

    public void setUpgradeButton4(ImageButton upgradeButton4) {
        this.upgradeButton4 = upgradeButton4;
    }

    public ImageButton getUpgradeButton5() {
        return upgradeButton5;
    }

    public void setUpgradeButton5(ImageButton upgradeButton5) {
        this.upgradeButton5 = upgradeButton5;
    }

    public ImageButton getUpgradeButton6() {
        return upgradeButton6;
    }

    public void setUpgradeButton6(ImageButton upgradeButton6) {
        this.upgradeButton6 = upgradeButton6;
    }

    public ImageButton getUpgradeButton7() {
        return upgradeButton7;
    }

    public void setUpgradeButton7(ImageButton upgradeButton7) {
        this.upgradeButton7 = upgradeButton7;
    }

    public ImageButton getUpgradeButton8() {
        return upgradeButton8;
    }

    public void setUpgradeButton8(ImageButton upgradeButton8) {
        this.upgradeButton8 = upgradeButton8;
    }

    public UpgradeManager getUpgradeManager() {
        return upgradeManager;
    }

    public void setUpgradeManager(UpgradeManager upgradeManager) {
        this.upgradeManager = upgradeManager;
    }
}
