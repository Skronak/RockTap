package com.rocktap.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocktap.game.AccountInformation;
import com.rocktap.game.Constants;
import com.rocktap.menu.UpgradeMenu;
import com.rocktap.utils.GameState;

/**
 * Created by Skronak on 11/12/2016.
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private AccountInformation accountInformation;
    UpgradeMenu upgradeMenu;
    Label versionLabel;
    Label scoreLabel;
    BitmapFont font;
    com.rocktap.utils.BitmapFontGenerator generator;
    Table table;

    private Texture upgradeButtonTexture;
    private Texture skillButtonTexture;
    private Texture achievButtonTexture;
    private Texture mapButtonTexture;
    private Button skillButton;
    private Button upgradeButton;
    private Button mapButton;
    private Button achievButton;


    public Hud(SpriteBatch sb, AccountInformation accountInformation) {
        initButton();
//        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        upgradeMenu = new UpgradeMenu();
        this.accountInformation = accountInformation;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport, sb);
        generator = new com.rocktap.utils.BitmapFontGenerator();
        font = generator.getFont();
        generator.dispose();
        font.setColor(Color.WHITE);
        initHud();
    }


    /**
     * Initialisation des bouton du playscreen
     * TODO gerer la taille des images des boutons
     */
    private void initButton() {
        upgradeButtonTexture = new Texture(Gdx.files.internal("icons/upgrade-icon.png"));
        skillButtonTexture = new Texture(Gdx.files.internal("icons/skill-icon.png"));
        achievButtonTexture = new Texture(Gdx.files.internal("icons/achievement-icon.png"));
        mapButtonTexture = new Texture(Gdx.files.internal("icons/map-icon.png"));
        Drawable upgradeDrawable = new TextureRegionDrawable(new TextureRegion(upgradeButtonTexture));
        Drawable skillDrawable = new TextureRegionDrawable(new TextureRegion(skillButtonTexture));
        Drawable mapDrawable = new TextureRegionDrawable(new TextureRegion(achievButtonTexture));
        Drawable achievDrawable = new TextureRegionDrawable(new TextureRegion(mapButtonTexture));
        upgradeButton = new ImageButton(upgradeDrawable);
        skillButton = new ImageButton(skillDrawable);
        mapButton = new ImageButton(mapDrawable);
        achievButton = new ImageButton(achievDrawable);
        upgradeButton = new Button(upgradeDrawable);
        skillButton = new Button(skillDrawable);
        mapButton = new Button(mapDrawable);
        achievButton = new Button(achievDrawable);
        // Declaration des listener
        InputListener buttonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleUpgradeMenu();
                return false;
            }
        };
        upgradeButton.addListener(buttonListener);
    }

    private void initHud(){
        versionLabel = new Label(Constants.CURRENT_VERSION, new Label.LabelStyle(font, Color.WHITE));
        versionLabel.setFontScale(0.5f);
        versionLabel.setWrap(true);
        scoreLabel = new Label(String.format("%d", accountInformation.getCurrentGold()), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel.setFontScale(2);
        table = new Table();
        table.top();
        table.setFillParent(true);
        table.row();
        table.add(versionLabel).expandX().align(Align.left).top();
        table.add(scoreLabel).expandX().align(Align.right).colspan(3);
        table.row();
        table.add(upgradeButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        table.add(skillButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        table.add(mapButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        table.add(achievButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        stage.addActor(table);

        // TODO: faire le upgradeMenu
        stage.addActor(upgradeMenu.getTable());
    }

    /**
     * Methode draw specifique
     */
    public void draw () {
        stage.draw();
        stage.act();
    }

    public void animateCritical() {
        scoreLabel.addAction(Actions.sequence(
                Actions.color(Constants.CRITICAL_LABEL_COLOR),
                Actions.color(Color.WHITE,0.5f)
        ));

    }

    private void toggleUpgradeMenu() {
            if (upgradeMenu.getTable().isVisible()) {
                upgradeMenu.getTable().setVisible(false);
                accountInformation.setCurrentState(GameState.IN_GAME);
            } else {
                upgradeMenu.getTable().setVisible(true);
                accountInformation.setCurrentState(GameState.UPGRADE);
            }
    }

    public void setGold(int gold){
        scoreLabel.setText(String.format("%d", gold));
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
