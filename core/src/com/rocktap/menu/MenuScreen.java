package com.rocktap.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.rocktap.game.Constants;

import javax.swing.plaf.basic.BasicMenuUI;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.table;
import static javax.swing.SpringLayout.WIDTH;

/**
 * Created by Skronak on 16/07/2017.
 */

public class MenuScreen extends Actor {

    private Table container;

    public MenuScreen(){
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;

        container = new Table();
        Table table = new Table();
        ScrollPane pane = new ScrollPane(table,paneStyle);
        container.add(pane).width(Constants.V_WIDTH).height(Constants.V_HEIGHT);
        container.row();
        container.setBounds(0,0,Constants.V_WIDTH,Constants.V_HEIGHT);

        for(int i = 0; i < 5 || i < 100; i++){
            TextButton tmp = new TextButton("Hello",skin);
//            tmp.scale(2);
            tmp.setText(i+ "   asdf");
            table.add(tmp).width(Constants.V_WIDTH/4f);
            if(i%2 == 1 && i > 0)
                table.row();
        }
    }

    public Table getContainer() {
        return container;
    }
}
