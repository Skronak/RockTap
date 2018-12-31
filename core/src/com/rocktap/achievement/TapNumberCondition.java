package com.rocktap.achievement;

import com.rocktap.entity.GameInformation;

public class TapNumberCondition implements Condition {
    private int conditionValue;

    public TapNumberCondition(){}

    public boolean isAchieved() {
        return GameInformation.INSTANCE.getTotalTapNumber()>=conditionValue;
    }

    public int getConditionProgression(){
        float progression = (GameInformation.INSTANCE.getTotalTapNumber()/conditionValue)*100;
        return (int) progression;
    }

}
