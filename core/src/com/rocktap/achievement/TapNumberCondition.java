package com.rocktap.achievement;

import com.rocktap.entity.GameInformation;

public class TapNumberCondition extends Condition {
    private int conditionValue;

    public TapNumberCondition(){}

    @Override
    public boolean isAchieved() {
        return GameInformation.INSTANCE.getTotalTapNumber()>=conditionValue;
    }
    @Override
    public int getConditionProgression(){
        float progression = (GameInformation.INSTANCE.getTotalTapNumber()/conditionValue)*100;
        return (int) progression;
    }

}
