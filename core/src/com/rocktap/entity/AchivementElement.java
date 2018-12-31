package com.rocktap.entity;

import com.rocktap.achievement.Condition;
import com.rocktap.utils.ValueDTO;

public class AchivementElement {

    public int id;

    public String title;

    public String description;

    public String icon;

    public Condition condition;

    public String rewardType;

    public ValueDTO rewardValue;

    public boolean isClaimed;

    public boolean isAchieved;
}
