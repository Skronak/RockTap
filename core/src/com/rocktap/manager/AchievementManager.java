package com.rocktap.manager;

import com.rocktap.entity.AchivementElement;
import com.rocktap.entity.GameInformation;

import java.util.List;

/**
 * Todo menu is calculting achivement status, to do here
 */
public class AchievementManager {
    private GameManager gameManager;
    public List<AchivementElement> achivementElementList;

    public AchievementManager(GameManager gameManager) {
        this.gameManager = gameManager;

        // Retrieve Achievement from AssetManager
        this.achivementElementList = AssetManager.INSTANCE.getAchievementElementList();

        // Init all achievement status with information from current GameInformation
        updateAchivementElementStatus();
    }

    /**
     * Init achievementElement based on saved GameInformation
     * if achievement wasn't unlock, check if is now unlocked
     */
    public void updateAchivementElementStatus() {
        for (int i = 0; i < achivementElementList.size(); i++) {
            switch (GameInformation.INSTANCE.getAchievList().get(i)) {
                case 0:
                    achivementElementList.get(i).isAchieved = achivementElementList.get(i).condition.isAchieved();
                    achivementElementList.get(i).isClaimed = false;
                    if (achivementElementList.get(i).isAchieved){
                        GameInformation.INSTANCE.getAchievList().set(i,0);
                    } else {
                        GameInformation.INSTANCE.getAchievList().set(i, 0);
                    }
                    break;
                case 1:
                    achivementElementList.get(i).isAchieved = true;
                    achivementElementList.get(i).isClaimed = false;
                    break;
                case 2:
                    achivementElementList.get(i).isAchieved = true;
                    achivementElementList.get(i).isClaimed = true;
                    break;
                default:
                    break;
            }
        }
    }

}
