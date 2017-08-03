package com.rocktap.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.rocktap.entity.StationUpgrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skronak on 02/08/2017.
 *
 * Classe de chargement des assets du jeu
 */

public class AssetManager {
    private Json json;

    private List<StationUpgrade> stationUpgradeList;

    public AssetManager() {
        this.json = new Json();
        loadUpgradeFile();
    }

    public void loadUpgradeFile(){
        stationUpgradeList = new ArrayList<StationUpgrade>();
        stationUpgradeList = json.fromJson(ArrayList.class, Gdx.files.internal("json/upgradeModule.json"));
        Gdx.app.log("AssetManager","Chargement asset termine");
    }

    public List<StationUpgrade> getStationUpgradeList() {
        return stationUpgradeList;
    }

    public void setStationUpgradeList(List<StationUpgrade> stationUpgradeList) {
        this.stationUpgradeList = stationUpgradeList;
    }
}
