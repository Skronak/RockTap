package com.rocktap.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.rocktap.entity.UpgradeActor;

import java.util.ArrayList;

/**
 * Created by Skronak on 02/08/2017.
 *
 * Classe de chargement des assets du jeu
 */

public class AssetManager {
    private Json json;

    private ArrayList<UpgradeActor> upgradeFile;

    public AssetManager() {
        this.json = new Json();
        loadUpgradeFile();
    }

    public void loadUpgradeFile(){
        upgradeFile = new ArrayList<UpgradeActor>();
        upgradeFile = json.fromJson(ArrayList.class, UpgradeActor.class, Gdx.files.internal("json/upgradeModule.json"));
        Gdx.app.log("AssetManager","Chargement asset termine");
    }

    public ArrayList<UpgradeActor> getUpgradeFile() {
        return upgradeFile;
    }

    public void setUpgradeFile(ArrayList<UpgradeActor> upgradeFile) {
        this.upgradeFile = upgradeFile;
    }
}
