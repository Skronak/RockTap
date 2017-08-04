package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.input.CustomInputSkillListener;
import com.rocktap.input.CustomInputUpgradeListener;
import com.rocktap.manager.GameManager;

/**
 * Created by Skronak on 01/02/2017.
 * Menu d'update
 * // TODO: super menu desactivant l'input listener, gerer un state?
 */
public class UpgradeMenu extends AbstractMenu {
    private Table upgradeTable;
    private Label detailGold;
    private Label detailDetail;
    private Label detailPower;
    private Label detailProcessor;
    private Label detailTitre;
    // indique le skill actuellement selectionne
    private int currentSelection;
    private Drawable upgradeDrawable1, upgradeDrawable2, upgradeDrawable3, upgradeDrawable4, upgradeDrawable5, upgradeDrawable6, upgradeDrawable7, upgradeDrawable8,
            upgradeDrawable9, upgradeDrawable10, upgradeDrawable11, upgradeDrawable12, upgradeDrawable13, upgradeDrawable14, upgradeDrawable15, upgradeDrawable16;
    private ImageButton upgradeButton1, upgradeButton2, upgradeButton3, upgradeButton4, upgradeButton5, upgradeButton6, upgradeButton7, upgradeButton8;

    public UpgradeMenu(GameManager gameManager) {
        super(gameManager);
        customizeMenuTable();
    }

    public void customizeMenuTable() {
        // Contenu du menu
        menutable.add(new Label("UPGRADE",skin)).expandX().top().colspan(2).height(50);
        menutable.row();
        menutable.add(initUpgradeButtonTable());
        menutable.add(initUpgradeDetailsTable()).top().expand();
        menutable.debug();
        menutable.setVisible(false);
    }

    public Table initUpgradeDetailsTable() {
        detailTitre = new Label("", skin);
        detailDetail = new Label("DESCRIPTIF",skin);
        detailDetail.setWrap(true);
        detailDetail.pack();
        detailGold = new Label("", skin);
        detailPower= new Label("", skin);
        detailProcessor= new Label("", skin);
        TextButton upgraderButton = new TextButton("UPGRADER", skin);
        CustomInputSkillListener customInputSkillListener = new CustomInputSkillListener(this);
        upgraderButton.addListener(customInputSkillListener);
        Table detailTable = new Table();
        detailTable.add(detailTitre).expand().top().height(50).width(100);
        detailTable.row();
        detailTable.add(detailDetail).left();
        detailTable.row();
        detailTable.add(new Label("GOLD GENERATION: ", skin)).left();
        detailTable.add(detailGold).width(50);
        detailTable.row();
        detailTable.add(new Label("POWER: ", skin)).left();
        detailTable.add(detailPower).width(50);
        detailTable.row();
        detailTable.add(new Label("PROCESSOR: ", skin)).left();
        detailTable.add(detailProcessor).width(50);
        detailTable.row();
        detailTable.add(upgraderButton).expandY().center();

        return detailTable;
    }
    private ScrollPane initUpgradeButtonTable() {
        upgradeTable = new Table();
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        ScrollPane pane = new ScrollPane(upgradeTable,paneStyle);
        pane.setScrollingDisabled(true, false);

//        for(int i = 0; i < 15 ; i++) {
//            TextButton tmp = new TextButton("", skin);
//            tmp.setText(i + " UPGRADE");
//            upgradeTable.add(tmp).expandX().fillX().pad(2).height(50);
//            upgradeTable.row();
//        }

        // Definition des boutons
        // TODO: Donnes test a rempalcer par fichier
        upgradeDrawable1 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o1_r.png"))));
        upgradeDrawable2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o2_r.png"))));
        upgradeDrawable3 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o3_r.png"))));
        upgradeDrawable4 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o4_r.png"))));
        upgradeDrawable5 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o5_r.png"))));
        upgradeDrawable6 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o6_r.png"))));
        upgradeDrawable7 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o7_r.png"))));
        upgradeDrawable8 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o8_r.png"))));
        upgradeDrawable9 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o1.png"))));
        upgradeDrawable10 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o2.png"))));
        upgradeDrawable11 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o3.png"))));
        upgradeDrawable12 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o4.png"))));
        upgradeDrawable13 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o5.png"))));
        upgradeDrawable14 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o6.png"))));
        upgradeDrawable15 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o7.png"))));
        upgradeDrawable16 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o8.png"))));
        if (gameManager.getAccountInformation().getUpgradeLevel1()==0) {
            upgradeButton1 = new ImageButton(upgradeDrawable1);
        } else {
            upgradeButton1 = new ImageButton(upgradeDrawable9);
        }
        if (gameManager.getAccountInformation().getUpgradeLevel2()==0) {
            upgradeButton2 = new ImageButton(upgradeDrawable2);
        } else {
            upgradeButton2 = new ImageButton(upgradeDrawable10);
        }
        if (gameManager.getAccountInformation().getUpgradeLevel3()==0) {
            upgradeButton3 = new ImageButton(upgradeDrawable3);
        } else {
            upgradeButton3 = new ImageButton(upgradeDrawable11);
        }
        if (gameManager.getAccountInformation().getUpgradeLevel4()==0) {
            upgradeButton4 = new ImageButton(upgradeDrawable4);
        } else {
            upgradeButton4 = new ImageButton(upgradeDrawable12);
        }
        if (gameManager.getAccountInformation().getUpgradeLevel5()==0) {
            upgradeButton5 = new ImageButton(upgradeDrawable5);
        } else {
            upgradeButton5 = new ImageButton(upgradeDrawable13);
        }
        if (gameManager.getAccountInformation().getUpgradeLevel6()==0) {
            upgradeButton6 = new ImageButton(upgradeDrawable6);
        } else {
            upgradeButton6 = new ImageButton(upgradeDrawable14);
        }
        if (gameManager.getAccountInformation().getUpgradeLevel7()==0) {
            upgradeButton7 = new ImageButton(upgradeDrawable7);
        } else {
            upgradeButton7 = new ImageButton(upgradeDrawable15);
        }
        if (gameManager.getAccountInformation().getUpgradeLevel8()==0) {
            upgradeButton8 = new ImageButton(upgradeDrawable8);
        } else {
            upgradeButton8 = new ImageButton(upgradeDrawable16);
        }

        CustomInputUpgradeListener customInputUpgradeProcessor1 = new CustomInputUpgradeListener(this,1,"GENERATEUR ION Lvl:","Amelioration du generateur a ION", "500","5","10");
        CustomInputUpgradeListener customInputUpgradeProcessor2 = new CustomInputUpgradeListener(this,2,"PROCESSUS Lvl:","Optimisation des\n processus", "1500","45","0");
        CustomInputUpgradeListener customInputUpgradeProcessor3 = new CustomInputUpgradeListener(this,3,"RAYON GAMMA Lvl:","Decouverte des\n rayons GAMMA", "1500","150","15");
        CustomInputUpgradeListener customInputUpgradeProcessor4 = new CustomInputUpgradeListener(this,4,"REACTEUR Lvl:","Amelioration du\n reacteur", "700","10","10");
        CustomInputUpgradeListener customInputUpgradeProcessor5 = new CustomInputUpgradeListener(this,5,"RAYON TRACTEUR Lvl:","Prototype de\n rayon tracteur", "4500","0","90");
        CustomInputUpgradeListener customInputUpgradeProcessor6 = new CustomInputUpgradeListener(this,6,"TOXINES GRAV Lvl:","Nettoyage des\ntoxines gravitationnelles", "1000","500","100");
        CustomInputUpgradeListener customInputUpgradeProcessor7 = new CustomInputUpgradeListener(this,7,"LASER Lvl:","Amelioration du\n Laser", "5500","5","10");
        CustomInputUpgradeListener customInputUpgradeProcessor8 = new CustomInputUpgradeListener(this,8,"CODE Lvl:","Optimisation du\n code", "800","8","2");
        upgradeButton1.addListener(customInputUpgradeProcessor1);
        upgradeButton2.addListener(customInputUpgradeProcessor2);
        upgradeButton3.addListener(customInputUpgradeProcessor3);
        upgradeButton4.addListener(customInputUpgradeProcessor4);
        upgradeButton5.addListener(customInputUpgradeProcessor5);
        upgradeButton6.addListener(customInputUpgradeProcessor6);
        upgradeButton7.addListener(customInputUpgradeProcessor7);
        upgradeButton8.addListener(customInputUpgradeProcessor8);
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


    public void playUnlockSkillAnimation() {
        Label goldLabel = new Label("hiddddt ", skin);
        this.getTable().add(goldLabel);
        goldLabel.addAction(Actions.sequence(
                Actions.fadeIn(1f),
                Actions.fadeOut(2f),
                Actions.removeActor(goldLabel)
        ));
    }

    public Label getDetailGold() {
        return detailGold;
    }

    public void setDetailGold(Label detailGold) {
        this.detailGold = detailGold;
    }

    public Label getDetailDetail() {
        return detailDetail;
    }

    public void setDetailDetail(Label detailDetail) {
        this.detailDetail = detailDetail;
    }

    public Label getDetailPower() {
        return detailPower;
    }

    public void setDetailPower(Label detailPower) {
        this.detailPower = detailPower;
    }

    public Label getDetailProcessor() {
        return detailProcessor;
    }

    public void setDetailProcessor(Label detailProcessor) {
        this.detailProcessor = detailProcessor;
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

    public Drawable getUpgradeDrawable9() {
        return upgradeDrawable9;
    }

    public void setUpgradeDrawable9(Drawable upgradeDrawable9) {
        this.upgradeDrawable9 = upgradeDrawable9;
    }

    public Drawable getUpgradeDrawable10() {
        return upgradeDrawable10;
    }

    public void setUpgradeDrawable10(Drawable upgradeDrawable10) {
        this.upgradeDrawable10 = upgradeDrawable10;
    }

    public Drawable getUpgradeDrawable11() {
        return upgradeDrawable11;
    }

    public void setUpgradeDrawable11(Drawable upgradeDrawable11) {
        this.upgradeDrawable11 = upgradeDrawable11;
    }

    public Drawable getUpgradeDrawable12() {
        return upgradeDrawable12;
    }

    public void setUpgradeDrawable12(Drawable upgradeDrawable12) {
        this.upgradeDrawable12 = upgradeDrawable12;
    }

    public Drawable getUpgradeDrawable13() {
        return upgradeDrawable13;
    }

    public void setUpgradeDrawable13(Drawable upgradeDrawable13) {
        this.upgradeDrawable13 = upgradeDrawable13;
    }

    public Drawable getUpgradeDrawable14() {
        return upgradeDrawable14;
    }

    public void setUpgradeDrawable14(Drawable upgradeDrawable14) {
        this.upgradeDrawable14 = upgradeDrawable14;
    }

    public Drawable getUpgradeDrawable15() {
        return upgradeDrawable15;
    }

    public void setUpgradeDrawable15(Drawable upgradeDrawable15) {
        this.upgradeDrawable15 = upgradeDrawable15;
    }

    public Drawable getUpgradeDrawable16() {
        return upgradeDrawable16;
    }

    public void setUpgradeDrawable16(Drawable upgradeDrawable16) {
        this.upgradeDrawable16 = upgradeDrawable16;
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
}
