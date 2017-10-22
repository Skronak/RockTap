package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.manager.GameManager;
import com.rocktap.utils.BitmapFontGenerator;
import com.rocktap.utils.Constants;

/**
 * Created by Skronak on 04/08/2017.
 */

public abstract class AbstractMenu {
    protected GameManager gameManager;
    protected Table menutable;
    protected Skin skin;
    protected BitmapFontGenerator generator;
    protected BitmapFont font;
    private float menu_width;
    private float menu_height;
    private TextureRegionDrawable menuBackground;

    public AbstractMenu(GameManager gameManager) {
        this.gameManager = gameManager;
        initMenu();
    }

    public void initMenu() {
        menu_width = Constants.V_WIDTH - Constants.UPDATE_MENU_PAD_EXTERNAL_WIDTH;
        menu_height = Constants.V_HEIGHT - Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT - (Constants.UPDATE_MENU_PAD_EXTERNAL_HEIGHT);
        menuBackground = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("credit.png"))));

        //Couleur de fond du menu
//        Pixmap pm1 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//        pm1.setColor(10,1,1,.2f);
//        pm1.fill();
//        menutable.setBackground((new TextureRegionDrawable(new TextureRegion(new Texture(pm1)))));

        // Text
        generator = new BitmapFontGenerator();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        font = generator.getFont();
        generator.dispose();

        // Definition du menu
        menutable = new Table();
        menutable.setBackground(menuBackground);
        menutable.setWidth(menu_width);
        menutable.setHeight(menu_height);
        menutable.setPosition(Constants.UPDATE_MENU_PAD_EXTERNAL_WIDTH/2,Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT);
    }

    public void update(){

    }

    /**
     * Definition du fond du menu
     * @param fname
     * @return
     */
    private NinePatch getNinePatch(String fname) {
        // Get the image
        final Texture t = new Texture(Gdx.files.internal(fname));
        return new NinePatch( new TextureRegion(t, 1, 1 , t.getWidth() - 2, t.getHeight() - 2), Constants.UPDATE_MENU_PAD_INTERNAL, Constants.UPDATE_MENU_PAD_INTERNAL, Constants.UPDATE_MENU_PAD_INTERNAL, Constants.UPDATE_MENU_PAD_INTERNAL);
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Table getTable() {
        return menutable;
    }

}
