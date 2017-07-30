package com.rocktap.station;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Skronak on 17/07/2017.
 */

public class StationUpgrade extends Actor {

    private int stationId;
    private int lvl;
    private float posX;
    private float posY;
    private int[] cost;
    private TextureRegion texture;


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
    public StationUpgrade(int stationId, int lvl, int posX, int posY, int width, int height, int[] cost, String sprite) {
        this.stationId = stationId;
        this.lvl = lvl;
        this.posX = posX;
        this.posY = posY;
        this.cost = cost;
        this.setSize(width, height);
        this.texture = new TextureRegion(new Texture(sprite));
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
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

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int[] getCost() {
        return cost;
    }

    public void setCost(int[] cost) {
        this.cost = cost;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

}
