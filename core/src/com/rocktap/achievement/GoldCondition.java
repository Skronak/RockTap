package com.rocktap.achievement;

import com.rocktap.entity.GameInformation;

public class GoldCondition extends Condition {
    private int conditionValue;
    private int conditionCurrency;

    public GoldCondition(){}

    @Override
    public boolean isAchieved() {
        boolean val = false;
        if (GameInformation.INSTANCE.getCurrency()>=conditionCurrency) {
            val=true;
        } else if (GameInformation.INSTANCE.getCurrency()==conditionCurrency
                &&GameInformation.INSTANCE.getCurrentGold()>conditionValue){
            val=true;
        } else {
            val=false;
        }
        return val;
    }
    @Override
    public int getConditionProgression(){
        float progression = (GameInformation.INSTANCE.getCurrency()/conditionCurrency)*100;
        return (int) progression;
    }

}
