package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.input.AchievementButtonListener;
import com.rocktap.manager.AssetManager;
import com.rocktap.manager.GameManager;

/**
 * Created by Skronak on 11/02/2017.
 */
public class AchievementMenu extends AbstractMenu {
    public Label titleLabel;
    public Label descriptionLabel;
    private int max_element_row=3;

    public AchievementMenu(GameManager gameManager) {
        super(gameManager);
        customizeMenuTable();
    }

    public void customizeMenuTable() {
        titleLabel = new Label("Un petit coup de polish",skin);
        descriptionLabel = new Label("Reparer completement la station et la passer niveau 2",skin);
        descriptionLabel.setWrap(true);

        VerticalGroup scrollContainerVG = new VerticalGroup();
        scrollContainerVG.space(5f);
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        paneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(AssetManager.INSTANCE.getScrollTexture(), 10, 50));
        ScrollPane pane = new ScrollPane(scrollContainerVG, paneStyle);
        pane.setScrollingDisabled(true, false);
        Table achievementTable = new Table();
        int y=0; // coun nb element per line
        for (int i=0;i<gameManager.achievementManager.achivementElementList.size();i++){
            Image achievElement = new Image(new Texture(Gdx.files.internal("icons/achievment.png")));
            achievElement.addListener(new AchievementButtonListener(this,gameManager.achievementManager.achivementElementList.get(i)));
            achievementTable.add(achievElement).width(50).height(50).pad(10);

            if (y<max_element_row) {
                y++;
            } else {
                y=0;
                achievementTable.row();
            }
        }

//        for (int i=0;i<60;i++){
//            if (y<4) {
//                Image achievElement = new Image(new Texture(Gdx.files.internal("icons/achievment.png")));
////                achievElement.addListener(new AchievementButtonListener(this,i));
//                achievementTable.add(achievElement).width(50).height(50).pad(10);
//                y++;
//            } else {
//                y=0;
//                achievementTable.row();
//            }
//        }
        scrollContainerVG.addActor(achievementTable);
        Table descriptionTable = new Table();
        descriptionTable.add(titleLabel).expand().fill();
        descriptionTable.row();
        descriptionTable.add(descriptionLabel).fillX();
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("grey.9.png")),
                6,6, 6, 6);
        NinePatchDrawable background = new NinePatchDrawable(patch);
        descriptionTable.setBackground(background);

        parentTable.add(new Label("ACHIEVEMENT", skin)).padTop(20);
        parentTable.row();
        parentTable.add(pane).grow().top();
        parentTable.row();
        parentTable.add(descriptionTable).fillX();
    }

    public void update(){

    }
}
