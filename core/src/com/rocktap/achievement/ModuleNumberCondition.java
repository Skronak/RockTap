package com.rocktap.achievement;

import com.rocktap.entity.GameInformation;

public class ModuleNumberCondition implements Condition {
    private int conditionValue;
    private int currentValue;

    public ModuleNumberCondition(){
    }

    public boolean isAchieved() {
        currentValue=0;
        for (int i=0;i<GameInformation.INSTANCE.getUpgradeLevelList().size();i++) {
            if (GameInformation.INSTANCE.getUpgradeLevelList().get(i)>0){
                currentValue=+1;
            }
        }
        return currentValue>=conditionValue;
    }

    public int getConditionProgression() {
        float progression = (currentValue/conditionValue)*100;
        return (int) progression;
    }

}
