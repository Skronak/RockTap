package com.rocktap.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Skronak on 17/07/2017.
 *
 */
public class UpgradeActor {

    private int id;
    private String title;
    private String description;
    private int[] cost;
    private int[] goldGen;
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
    public UpgradeActor(int stationId, int lvl, int posX, int posY, int width, int height, int[] cost, String sprite) {
        this.id = stationId;
        this.lvl = lvl;
        this.posX = posX;
        this.posY = posY;
        this.cost = cost;
        this.height = height;
        this.width = width;
        this.textureRegion = new TextureRegion(new Texture(sprite));
    }

    public UpgradeActor(){

    }

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

    public int[] getCost() {
        return cost;
    }

    public void setCost(int[] cost) {
        this.cost = cost;
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

    public int[] getGoldGen() {
        return goldGen;
    }

    public void setGoldGen(int[] goldGen) {
        this.goldGen = goldGen;
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

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }
}
