package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.entity.AchivementElement;
import com.rocktap.menu.AchievementMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listener du bouton UPGRADE d'un module
 */
public class AchievementButtonListener extends ClickListener {

    // Identifiant du module rattaché au listener
    private AchievementMenu achievementMenu;
    private AchivementElement achivementElement;


    public AchievementButtonListener(AchievementMenu achievementMenu, AchivementElement achivementElement) {
        this.achievementMenu = achievementMenu;
        this.achivementElement = achivementElement;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        achievementMenu.titleLabel.setText(achivementElement.title);
        achievementMenu.descriptionLabel.setText(achivementElement.description);
        return false;
     }
}
