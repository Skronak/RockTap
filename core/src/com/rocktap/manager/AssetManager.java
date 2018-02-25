package com.rocktap.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.rocktap.entity.ModuleEntity;
import com.rocktap.utils.BitmapFontGenerator;

import java.util.ArrayList;

/**
 * Created by Skronak on 02/08/2017.
 *
 * Classe de chargement des assets du jeu
 * TODO Utiliser atlas
 */
public class AssetManager {
    private Json json;

    private ArrayList<ModuleEntity> upgradeFile;

    private TextButton.TextButtonStyle moduleMenuBuyTxtBtnStyle;
    private BitmapFont font;

    private int loadValue;

    public AssetManager() {
        this.json = new Json();

        loadValue=0;
        loadUpgradeFile();
        initAsset();
    }

    private void initAsset() {

        BitmapFontGenerator generator = new BitmapFontGenerator();
        font = generator.getFont();
        generator.dispose();
        loadValue+=1;

        TextureRegionDrawable buyDrawableUp = new TextureRegionDrawable( new TextureRegion(new Texture(Gdx.files.internal("ui/button/goldButtonUp.png"))) );
        TextureRegionDrawable buyDrawableDown = new TextureRegionDrawable( new TextureRegion(new Texture(Gdx.files.internal("ui/button/goldButtonDown.png"))) );
        TextureRegionDrawable buyDrawableChecked = new TextureRegionDrawable( new TextureRegion(new Texture(Gdx.files.internal("ui/button/goldButtonUp.png"))) );
        moduleMenuBuyTxtBtnStyle = new TextButton.TextButtonStyle(buyDrawableUp, buyDrawableDown,buyDrawableChecked, font);
        loadValue+=1;
    }
    public void loadUpgradeFile(){
        upgradeFile = new ArrayList<ModuleEntity>();
        upgradeFile = json.fromJson(ArrayList.class, ModuleEntity.class, Gdx.files.internal("json/upgradeModule.json"));
        loadValue+=1;
        Gdx.app.log("AssetManager","Chargement asset termine");
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public ArrayList<ModuleEntity> getUpgradeFile() {
        return upgradeFile;
    }

    public TextButton.TextButtonStyle getModuleMenuBuyTxtBtnStyle() {
        return moduleMenuBuyTxtBtnStyle;
    }

    public int getLoadValue() {
        return loadValue;
    }

    public BitmapFont getFont() {
        return font;
    }
}
