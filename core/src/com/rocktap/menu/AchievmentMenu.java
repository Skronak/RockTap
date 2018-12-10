package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.manager.AssetManager;
import com.rocktap.manager.GameManager;

/**
 * Created by Skronak on 11/02/2017.
 */
public class AchievmentMenu extends AbstractMenu {
    private Label titleLabel;
    private Label descriptionLabel;

    public AchievmentMenu(GameManager gameManager) {
        super(gameManager);
        customizeMenuTable();
    }

    public void customizeMenuTable() {
        titleLabel = new Label("Un petit coup de polish",skin);
        descriptionLabel = new Label("Reparer completement la station et la passer niveau 2",skin);
        descriptionLabel.setWrap(true);
        parentTable.debug();
        parentTable.add(new Label("ACHIEVEMENT", skin)).left().top();
        parentTable.row();

        VerticalGroup scrollContainerVG = new VerticalGroup();
        scrollContainerVG.space(5f);
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        paneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(AssetManager.INSTANCE.getScrollTexture(), 10, 50));
        ScrollPane pane = new ScrollPane(scrollContainerVG, paneStyle);
        pane.setScrollingDisabled(true, false);
        Table achievementTable = new Table();
        int y=0;
        for (int i=0;i<60;i++){
            if (y<4) {
                achievementTable.add(new Image(new Texture(Gdx.files.internal("icons/achievment.png")))).width(50).height(50).pad(10);
                y++;
            } else {
                y=0;
                achievementTable.row();
            }
        }
        scrollContainerVG.addActor(achievementTable);
        parentTable.add(pane);
        parentTable.row();
        parentTable.add(titleLabel).left();
        parentTable.row();
        parentTable.add(descriptionLabel).left().fillX();

    }
}
