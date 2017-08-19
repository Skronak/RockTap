package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Classe gerant les boutons du playscreen
 * USELESS
 */
public class MainMenuBar extends Actor {

    private Table menuBar;
    private ImageButton skillButton;
    private Texture upgradeButtonTexture;
    private Texture skillButtonTexture;

    public MainMenuBar(){
        menuBar = new Table();
        upgradeButtonTexture = new Texture(Gdx.files.internal("icons/upgrade-icon.png"));
        skillButtonTexture = new Texture(Gdx.files.internal("icons/skill-icon.png"));
        initMenu();
    }

    public void initMenu() {
        Drawable upgradeDrawable = new TextureRegionDrawable(new TextureRegion(upgradeButtonTexture));
        skillButton = new ImageButton(upgradeDrawable);
        Drawable skillDrawable = new TextureRegionDrawable(new TextureRegion(skillButtonTexture));
        skillButton = new ImageButton(skillDrawable);

        menuBar.add(skillButton);
        menuBar.add(skillButton);
    }

}
