package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.game.Constants;
import com.rocktap.utils.BitmapFontGenerator;
import com.sun.org.apache.bcel.internal.generic.TargetLostException;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.table;

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
    private Texture shipTexture;//TODO recuperer model du ship en cours
    Image stationImage;
    Table shipUpgradeTable;
    private Group layer0;
    private Group layer1;
    private Button button;
    private Table skillTable;
    private Table detailTable;
    Button upgradeButton1;
    Button upgradeButton2;
    Button upgradeButton3;
    Button upgradeButton4;
    private MenuScreen menuScreen;
    private Table upgradeTable;
    private float menu_width;
    private float menu_height;

    public UpgradeMenu() {
        initMenu();
    }

    public void initMenu() {
        menuScreen = new MenuScreen();
        menu_width = Constants.V_WIDTH - (Constants.UPDATE_MENU_PAD*2);
        menu_height = Constants.V_HEIGHT - (Constants.UPDATE_MENU_PAD*2);
        //Couleur de fond du menu
        Pixmap pm1 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pm1.setColor(10,1,1,.6f);
        pm1.fill();

        generator = new BitmapFontGenerator();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        font = generator.getFont();
        generator.dispose();

        // Definitoin des boutons
        button = new TextButton("test",skin);
        upgradeButton1 = new TextButton("Beam", skin);
        upgradeButton2 = new TextButton("Processor", skin);
        upgradeButton3 = new TextButton("Spector", skin);
        upgradeButton4 = new TextButton("Fuel Tank", skin);

        menutable = new Table();
//        menutable.debug();
//        menutable.setBackground(skin.getDrawable("default-scroll"));
        menutable.setBackground((new TextureRegionDrawable(new TextureRegion(new Texture(pm1)))));
        menutable.setBackground(new NinePatchDrawable(getNinePatch(("menu.png"))));
        menutable.setWidth(menu_width);
        menutable.setHeight(menu_height);
        menutable.setPosition(Constants.UPDATE_MENU_PAD,Constants.UPDATE_MENU_PAD);

        // Titre de la fenetre
        menutable.add(new Label("UPGRADE",skin)).expand().top();
        menutable.row();

        // Menu d'upgrade
        VerticalGroup splitPaneDetail = initUpgradeDetailsTable();
        HorizontalGroup hg = new HorizontalGroup();
        hg.addActor(initUpgradeButtonTable());
        hg.addActor(splitPaneDetail);
//        SplitPane splitPaneButton = new SplitPane(initUpgradeButtonTable(),splitPaneDetail,false,skin );
        menutable.add(hg);
    }

    private VerticalGroup initUpgradeDetailsTable() {
        VerticalGroup vg = new VerticalGroup();
        Image imageDetail  = new Image();
        Label labelDetail = new Label("ghjghj", skin);
        vg.addActor(imageDetail);
        vg.addActor(imageDetail);
        vg.debug();

        SplitPane splitPane = new SplitPane(imageDetail, labelDetail, true, skin );
        return vg;
    }
    private Table initUpgradeButtonTable() {
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;

        upgradeTable = new Table();
        Table table = new Table();
        ScrollPane pane = new ScrollPane(table,paneStyle);
        upgradeTable.add(pane).width(menu_width).height(menu_height);
        upgradeTable.row();
        upgradeTable.setBounds(0,0,menu_width,menu_height);
        upgradeTable.debug();

        for(int i = 0; i < 100 ; i++){
            TextButton tmp = new TextButton("",skin);
            tmp.setText(i+ " UPGRADE");
            table.add(tmp).expandX().fillX().pad(2).height(50);
            table.row();
            /*
            if(i%2 == 1 && i > 0)
                table.row();
*/
        }
        return upgradeTable;
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

    public Table getTable() {
        return menutable;
    }
}
