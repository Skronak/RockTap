package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rocktap.entity.GameInformation;
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
    private Table achievementTable;

    public AchievementMenu(GameManager gameManager) {
        super(gameManager);
        customizeMenuTable();
    }

    public void customizeMenuTable() {
        titleLabel = new Label(gameManager.achievementManager.achivementElementList.get(0).title,skin);
        titleLabel.setColor(Color.TEAL);
        descriptionLabel = new Label(gameManager.achievementManager.achivementElementList.get(0).description,skin);
        descriptionLabel.setWrap(true);
        descriptionLabel.setScale(0.8f);

        VerticalGroup scrollContainerVG = new VerticalGroup();
        scrollContainerVG.space(5f);
        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
        paneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(AssetManager.INSTANCE.getScrollTexture(), 10, 50));
        ScrollPane pane = new ScrollPane(scrollContainerVG, paneStyle);
        pane.setScrollingDisabled(true, false);
        achievementTable = new Table();
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

        scrollContainerVG.addActor(achievementTable);
        Table descriptionTable = new Table();
        descriptionTable.add(titleLabel).expand().fill();
        descriptionTable.row();
        descriptionTable.add(descriptionLabel).fillX();
        descriptionTable.row();
        descriptionTable.add(new TextButton("claim",AssetManager.INSTANCE.getSkin())).width(100);
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("grey.9.png")),
                6,6, 6, 6);
        NinePatchDrawable background = new NinePatchDrawable(patch);
        descriptionTable.setBackground(background);

        parentTable.add(new Label("ACHIEVEMENT", skin)).center().padTop(20);
        parentTable.row();
        parentTable.add(pane).grow().top();
        parentTable.row();
        parentTable.add(descriptionTable).fillX();
    }

    @Override
    public void update() {
    }

    @Override
    public void updateOnShow(){
        Gdx.app.log("r","rr");
        for (int i=0;i<gameManager.achievementManager.achivementElementList.size();i++) {
            if (gameManager.achievementManager.achivementElementList.get(i).condition.isAchieved()) {
                ((Image) achievementTable.getCells().get(i).getActor()).setDrawable(new TextureRegionDrawable(new Texture(Gdx.files.internal("icons/achievment2.png"))));
                achievementTable.getCells().get(i).getActor().setOrigin(achievementTable.getCells().get(i).getActor().getWidth()/2,achievementTable.getCells().get(i).getActor().getHeight()/2);

                // Animate elements to claim
                if (!gameManager.achievementManager.achivementElementList.get(i).isClaimed) {
                    GameInformation.INSTANCE.getAchievList().set(i,1);
                    achievementTable.getCells().get(i).getActor().addAction(
                            Actions.sequence(Actions.parallel(
                                    Actions.scaleBy(0.2f,0.2f,0.5f),
                                        Actions.repeat(2,
                                            Actions.sequence(Actions.rotateBy(-20, 0.2f),
                                                Actions.rotateBy(20, 0.2f))
                                            )),
                            Actions.sequence(Actions.scaleBy(-0.2f,-0.2f,0.2f))));
                } else {
                    GameInformation.INSTANCE.getAchievList().set(i,2);
                }
            }
        }
    }
}
