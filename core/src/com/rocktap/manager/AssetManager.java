package com.rocktap.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.rocktap.entity.ModuleActor;

import java.util.ArrayList;

/**
 * Created by Skronak on 02/08/2017.
 *
 * Classe de chargement des assets du jeu
 */

public class AssetManager {
    private Json json;

    private ArrayList<ModuleActor> upgradeFile;

    public AssetManager() {
        this.json = new Json();
        loadUpgradeFile();
    }

    public void loadUpgradeFile(){
        upgradeFile = new ArrayList<ModuleActor>();
        upgradeFile = json.fromJson(ArrayList.class, ModuleActor.class, Gdx.files.internal("json/upgradeModule.json"));
        Gdx.app.log("AssetManager","Chargement asset termine");
    }

    public ArrayList<ModuleActor> getUpgradeFile() {
        return upgradeFile;
    }

    public void setUpgradeFile(ArrayList<ModuleActor> upgradeFile) {
        this.upgradeFile = upgradeFile;
    }
}
