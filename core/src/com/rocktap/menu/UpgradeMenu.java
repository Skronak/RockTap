package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.game.Constants;
import com.rocktap.input.CustomInputUpgradeListener;
import com.rocktap.utils.BitmapFontGenerator;

/**
 * Created by Skronak on 01/02/2017.
 * Menu d'update
 * // TODO: super menu desactivant l'input listener, gerer un state?
 */
public class UpgradeMenu {
    private Table menutable;
    private Skin skin;
    BitmapFontGenerator generator;
    BitmapFont font;
    private Table upgradeTable;
    private float menu_width;
    private float menu_height;
    private Label detailGold;
    private Label detailDetail;
    private Label detailPower;
    private Label detailProcessor;
    private Label detailTitre;

    public UpgradeMenu() {
        initMenu();
    }

    public void initMenu() {
        menu_width = Constants.V_WIDTH - (Constants.UPDATE_MENU_PAD*2);
        menu_height = Constants.V_HEIGHT - (Constants.UPDATE_MENU_PAD*3);
        //Couleur de fond du menu
        Pixmap pm1 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pm1.setColor(10,1,1,.2f);
        pm1.fill();

        // Text
        generator = new BitmapFontGenerator();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        font = generator.getFont();
        generator.dispose();

        // Definition du menu
        menutable = new Table();
        menutable.setBackground((new TextureRegionDrawable(new TextureRegion(new Texture(pm1)))));
        menutable.setBackground(new NinePatchDrawable(getNinePatch(("menu.png"))));
        menutable.setWidth(menu_width);
        menutable.setHeight(menu_height);
        menutable.setPosition(Constants.UPDATE_MENU_PAD,Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT);

        // Contenu du menu
        menutable.add(new Label("UPGRADE",skin)).expandX().top().colspan(2).height(50);
        menutable.row();
        menutable.add(initUpgradeButtonTable());
        menutable.add(initUpgradeDetailsTable()).top().expand();
        menutable.debug();

     }

    public Table initUpgradeDetailsTable() {
        detailTitre = new Label("", skin);
        detailDetail = new Label("DESCRIPTIF",skin);
        detailDetail.setWrap(true);
        detailDetail.pack();
        detailGold = new Label("", skin);
        detailPower= new Label("", skin);
        detailProcessor= new Label("", skin);
        Table detailTable = new Table();
        detailTable.add(detailTitre).expand().top().height(50);
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
        detailTable.add(new TextButton("UPGRADER", skin)).expandY().center();

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

        // Definitoin des boutons
        // TODO: Donnes test a rempalcer par fichier
        Drawable drawable1 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o1.png"))));
        Drawable drawable2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o2.png"))));
        Drawable drawable3 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o3.png"))));
        Drawable drawable4 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o4.png"))));
        Drawable drawable5 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o5.png"))));
        Drawable drawable6 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o6.png"))));
        Drawable drawable7 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o7.png"))));
        Drawable drawable8 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/o8.png"))));
        Button upgradeButton1 = new ImageButton(drawable1);
        Button upgradeButton2 = new ImageButton(drawable2);
        Button upgradeButton3 = new ImageButton(drawable3);
        Button upgradeButton4 = new ImageButton(drawable4);
        Button upgradeButton5 = new ImageButton(drawable5);
        Button upgradeButton6 = new ImageButton(drawable6);
        Button upgradeButton7 = new ImageButton(drawable7);
        Button upgradeButton8 = new ImageButton(drawable8);
        CustomInputUpgradeListener customInputUpgradeProcessor1 = new CustomInputUpgradeListener(this,"GENERATEUR ION" ,"Amelioration du generateur a ION", "500","5","10");
        CustomInputUpgradeListener customInputUpgradeProcessor2 = new CustomInputUpgradeListener(this,"PROCESSUS" ,"Optimisation des\n processus", "1500","45","0");
        CustomInputUpgradeListener customInputUpgradeProcessor3 = new CustomInputUpgradeListener(this,"RAYON GAMMA" ,"Decouverte des\n rayons GAMMA", "1500","150","15");
        CustomInputUpgradeListener customInputUpgradeProcessor4 = new CustomInputUpgradeListener(this,"REACTEUR" ,"Amelioration du\n reacteur", "700","10","10");
        CustomInputUpgradeListener customInputUpgradeProcessor5 = new CustomInputUpgradeListener(this,"RAYON TRACTEUR" ,"Prototype de\n rayon tracteur", "4500","0","90");
        CustomInputUpgradeListener customInputUpgradeProcessor6 = new CustomInputUpgradeListener(this,"TOXINES GRAV" ,"Nettoyage des\ntoxines gravitationnelles", "1000","500","100");
        CustomInputUpgradeListener customInputUpgradeProcessor7 = new CustomInputUpgradeListener(this,"LASER" ,"Amelioration du\n Laser", "5500","5","10");
        CustomInputUpgradeListener customInputUpgradeProcessor8 = new CustomInputUpgradeListener(this,"CODE" ,"Optimisation du\n code", "800","8","2");
        upgradeButton1.addListener(customInputUpgradeProcessor1);
        upgradeButton1.addListener(customInputUpgradeProcessor2);
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



    /**
     * Definition du fond du menu
     * @param fname
     * @return
     */
    private NinePatch getNinePatch(String fname) {

        // Get the image
        final Texture t = new Texture(Gdx.files.internal(fname));
        return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getHeight() - 2), 10, 10, 10, 10);
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

    public Table getTable() {
        return menutable;
    }
}
