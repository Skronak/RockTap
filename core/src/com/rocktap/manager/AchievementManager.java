package com.rocktap.manager;

import com.rocktap.entity.AchivementElement;

import java.util.List;

public class AchievementManager {
    private GameManager gameManager;
    public List<AchivementElement> achivementElementList;

    public AchievementManager(GameManager gameManager){
        this.gameManager=gameManager;
        this.achivementElementList = AssetManager.INSTANCE.getAchievementElementList();
    }
}
