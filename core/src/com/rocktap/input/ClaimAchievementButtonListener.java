package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.entity.AchivementElement;
import com.rocktap.entity.GameInformation;
import com.rocktap.menu.AchievementMenu;

/**
 *
 */
public class ClaimAchievementButtonListener extends ClickListener {

    // Identifiant du module rattaché au listener
    private AchievementMenu achievementMenu;
    public AchivementElement achivementElement;

    public ClaimAchievementButtonListener(AchievementMenu achievementMenu) {
        this.achievementMenu = achievementMenu;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        GameInformation.INSTANCE.setSkillPoint(GameInformation.INSTANCE.getSkillPoint()+achivementElement.skillPoint);
        achievementMenu.animateClaim();
        achievementMenu.descriptionLabel.setText(achivementElement.description);
        return false;
    }
}
