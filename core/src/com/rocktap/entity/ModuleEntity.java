package com.rocktap.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Skronak on 17/07/2017.
 *
 */
public class ModuleEntity {

    private int id;
    private String title;
    private String description;
    private float[] cost;
    private int[] currency;
    private float[] goldGen;
    private int[] currGen;
    private int lvl;
    private float posX;
    private float posY;
    private TextureRegion textureRegion;
    private String sprite;
    private String icon;
    private String iconDisabled;
    private float width;
    private float height;

    /**
     * TODO menage dans param
     * @param stationId
     * @param lvl
     * @param posX
     * @param posY
     * @param width
     * @param height
     * @param cost
     * @param sprite
     */
    public ModuleEntity(int stationId, int lvl, int posX, int posY, int width, int height, float[] cost, String sprite) {
        this.id = stationId;
        this.lvl = lvl;
        this.posX = posX;
        this.posY = posY;
        this.cost = cost;
        this.height = height;
        this.width = width;
        this.textureRegion = new TextureRegion(new Texture(sprite));
    }

    public ModuleEntity(){

    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float[] getCost() {
        return cost;
    }

    public void setCost(float[] cost) {
        this.cost = cost;
    }

    public int[] getCurrency() {
        return currency;
    }

    public void setCurrency(int[] currency) {
        this.currency = currency;
    }

    public float[] getGoldGen() {
        return goldGen;
    }

    public void setGoldGen(float[] goldGen) {
        this.goldGen = goldGen;
    }

    public int[] getCurrGen() {
        return currGen;
    }

    public void setCurrGen(int[] currGen) {
        this.currGen = currGen;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconDisabled() {
        return iconDisabled;
    }

    public void setIconDisabled(String iconDisabled) {
        this.iconDisabled = iconDisabled;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
