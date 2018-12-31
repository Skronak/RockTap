package com.rocktap.achievement;

import com.rocktap.entity.GameInformation;

public class ModuleNumberCondition extends Condition {
    private int conditionValue;
    private int currentValue;

    public ModuleNumberCondition(){
    }
    @Override
    public boolean isAchieved() {
        currentValue=0;
        for (int i=0;i<GameInformation.INSTANCE.getUpgradeLevelList().size();i++) {
            if (GameInformation.INSTANCE.getUpgradeLevelList().get(i)>0){
                currentValue+=1;
            }
        }
        return currentValue>=conditionValue;
    }
    @Override
    public int getConditionProgression() {
        float progression = (currentValue/conditionValue)*100;
        return (int) progression;
    }

}
