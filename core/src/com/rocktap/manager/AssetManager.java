package com.rocktap.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.rocktap.entity.ModuleElement;
import com.rocktap.utils.BitmapFontGenerator;

import java.util.ArrayList;

/**
 * Created by Skronak on 02/08/2017.
 *
 * Classe de chargement des assets du jeu
 * TODO Utiliser atlas
 */
public enum AssetManager {
    INSTANCE;
    private Json json;

    private ArrayList<ModuleElement> moduleElementList;
    private TextButton.TextButtonStyle moduleMenuBuyTxtBtnStyle;
    private TextureRegionDrawable menuBackgroundTexture;
    private BitmapFont font;

    private Texture goldIcon;
    private Texture timeIcon;
    private ArrayList<Texture> moduleDrawableUpList;
    private Texture disabledIcon;
    private Texture scrollTexture;
    private Skin skin;
    private ArrayList<Texture> upgradeLvlImageList;

    private int loadValue;

    AssetManager() {
        this.json = new Json();

        loadValue=0;
        loadUpgradeFile();
        loadIcons();
        loadImage();
        loadTexture();
        initAsset();
    }

    private void initAsset() {
        BitmapFontGenerator generator = new BitmapFontGenerator();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
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
        moduleElementList = new ArrayList<ModuleElement>();
        moduleElementList = json.fromJson(ArrayList.class, ModuleElement.class, Gdx.files.internal("json/moduleElement.json"));

        loadValue+=1;
        Gdx.app.log("AssetManager","Chargement asset termine");
    }

    public void loadIcons(){
        goldIcon = new Texture(Gdx.files.internal("icons/gold+.png"));
        timeIcon = new Texture(Gdx.files.internal("icons/speed+.png"));
        disabledIcon = new Texture(Gdx.files.internal("icons/disabled.png"));
    }

    public void loadTexture() {
        scrollTexture = new Texture(Gdx.files.internal("sprites/menu/bar.png"));
        menuBackgroundTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("credit.png"))));
    }
    public void loadImage() {
        moduleDrawableUpList = new ArrayList<Texture>();
        upgradeLvlImageList = new ArrayList<Texture>();

        for (int i = 0; i < getModuleElementList().size(); i++) {
            moduleDrawableUpList.add(new Texture(Gdx.files.internal("sprites/menu/" + getModuleElementList().get(i).getIcon())));
        }

        // Pour chaque niveau de rarete on met dans la liste
        for (int i=0; i<7;i++){
            upgradeLvlImageList.add(new Texture(Gdx.files.internal("icons/upgradeMenu/mLvlC"+i+".png")));
        }
        for (int i=0; i<7;i++){
            upgradeLvlImageList.add(new Texture(Gdx.files.internal("icons/upgradeMenu/mLvlB"+i+".png")));
        }
        for (int i=0; i<7;i++){
            upgradeLvlImageList.add(new Texture(Gdx.files.internal("icons/upgradeMenu/mLvlA"+i+".png")));
        }
        for (int i=0; i<7;i++){
            upgradeLvlImageList.add(new Texture(Gdx.files.internal("icons/upgradeMenu/mLvlS"+i+".png")));
        }
        for (int i=0; i<7;i++){
            upgradeLvlImageList.add(new Texture(Gdx.files.internal("icons/upgradeMenu/mLvlSS"+i+".png")));
        }

    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

//    public ArrayList<ModuleEntity> getUpgradeFile() {
//        return upgradeFile;
//    }

    public TextButton.TextButtonStyle getModuleMenuBuyTxtBtnStyle() {
        return moduleMenuBuyTxtBtnStyle;
    }

    public Texture getGoldIcon() {
        return goldIcon;
    }

    public Texture getTimeIcon() {
        return timeIcon;
    }

    public ArrayList<Texture> getModuleDrawableUpList() {
        return moduleDrawableUpList;
    }

    public Texture getScrollTexture() {
        return scrollTexture;
    }

    public Skin getSkin() {
        return skin;
    }

    public ArrayList<Texture> getUpgradeLvlImageList() {
        return upgradeLvlImageList;
    }

    public int getLoadValue() {
        return loadValue;
    }

    public ArrayList<ModuleElement> getModuleElementList() {
        return moduleElementList;
    }

    public TextureRegionDrawable getMenuBackgroundTexture() {
        return menuBackgroundTexture;
    }

    public Texture getDisabledIcon() {
        return disabledIcon;
    }

    public BitmapFont getFont() {
        return font;
    }
}
