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
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocktap.entity.GameInformation;
import com.rocktap.manager.GameManager;
import com.rocktap.menu.AbstractMenu;
import com.rocktap.menu.CreditMenu;
import com.rocktap.menu.UpgradeMenu;
import com.rocktap.utils.Constants;
import com.rocktap.utils.GameState;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Skronak on 11/12/2016.
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private GameInformation gameInformation;
    private UpgradeMenu upgradeMenu;
    private CreditMenu creditMenu;
    private Label versionLabel;
    private Label scoreLabel;
    private Label goldDecreaseLabel;
    BitmapFont font;
    com.rocktap.utils.BitmapFontGenerator generator;
    Table table;
    private GameManager gameManager;
    private Texture upgradeButtonTextureUp;
    private Texture skillButtonTextureUp;
    private Texture achievButtonTextureUp;
    private Texture mapButtonTextureUp;
    private Texture upgradeButtonTextureDown;
    private Texture skillButtonTextureDown;
    private Texture achievButtonTextureDown;
    private Texture mapButtonTextureDown;
    private Button skillButton;
    private Button upgradeButton;
    private Button mapButton;
    private Button achievButton;
    private NavigableMap<Long, String> suffixes = new TreeMap<Long, String> ();

    public Hud(SpriteBatch sb, GameManager gameManager) {
        initButton();
        initGoldFormater();
        this.gameManager = gameManager;
        upgradeMenu = new UpgradeMenu(gameManager);
        creditMenu = new CreditMenu(gameManager);
        this.gameInformation = gameManager.getGameInformation();
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

        upgradeButtonTextureUp = new Texture(Gdx.files.internal("icons/hud_b2.png"));
        skillButtonTextureUp = new Texture(Gdx.files.internal("icons/hud_b1.png"));
        achievButtonTextureUp = new Texture(Gdx.files.internal("icons/hud_b3.png"));
        mapButtonTextureUp = new Texture(Gdx.files.internal("icons/hud_b4.png"));
        upgradeButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b2_r.png"));
        skillButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b1_r.png"));
        achievButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b3_r.png"));
        mapButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b4_r.png"));
        Drawable upgradeDrawableUp = new TextureRegionDrawable(new TextureRegion(upgradeButtonTextureUp));
        Drawable skillDrawableUp = new TextureRegionDrawable(new TextureRegion(skillButtonTextureUp));
        Drawable mapDrawableUp = new TextureRegionDrawable(new TextureRegion(achievButtonTextureUp));
        Drawable achievDrawableUp = new TextureRegionDrawable(new TextureRegion(mapButtonTextureUp));
        Drawable upgradeDrawableDown = new TextureRegionDrawable(new TextureRegion(upgradeButtonTextureDown));
        Drawable skillDrawableDown = new TextureRegionDrawable(new TextureRegion(skillButtonTextureDown));
        Drawable mapDrawableDown = new TextureRegionDrawable(new TextureRegion(achievButtonTextureDown));
        Drawable achievDrawableDown = new TextureRegionDrawable(new TextureRegion(mapButtonTextureDown));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skillDrawableUp;
        style.down = skillDrawableDown;
        upgradeButton = new ImageButton(style);
        ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle();
        style2.up = upgradeDrawableUp;
        style2.down = upgradeDrawableDown;
        skillButton = new ImageButton(style2);
        ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle();
        style3.up = mapDrawableUp;
        style3.down = mapDrawableDown;
        mapButton = new ImageButton(style3);
        ImageButton.ImageButtonStyle style4 = new ImageButton.ImageButtonStyle();
        style4.up = achievDrawableUp;
        style4.down = achievDrawableDown;
        achievButton = new ImageButton(style4);

        // Declaration des listener
        InputListener buttonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(upgradeMenu);
                return false;
            }
        };
        upgradeButton.addListener(buttonListener);

        // Declaration des listener
        InputListener buttonListenerCredit = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(creditMenu);
                return false;
            }
        };
        mapButton.addListener(buttonListenerCredit);

        InputListener buttonListenerDEV = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameManager.getGameInformation().getUpgradeLevelList().set(0, 0);
                gameManager.getGameInformation().getUpgradeLevelList().set(1, 0);
                gameManager.getGameInformation().getUpgradeLevelList().set(2, 0);
                gameManager.getGameInformation().getUpgradeLevelList().set(3, 0);
                gameManager.getGameInformation().getUpgradeLevelList().set(4, 0);
                gameManager.getGameInformation().getUpgradeLevelList().set(5, 0);
                gameManager.getGameInformation().getUpgradeLevelList().set(6, 0);
                gameManager.getGameInformation().getUpgradeLevelList().set(7, 0);
                gameManager.getGameInformation().setStationId(1);
                return false;
            }
        };
        achievButton.addListener(buttonListenerDEV);
    }

    private void initHud(){
        versionLabel = new Label(Constants.CURRENT_VERSION, new Label.LabelStyle(font, Color.WHITE));
        versionLabel.setFontScale(0.5f);
        versionLabel.setWrap(true);
        scoreLabel = new Label(String.format("%d", gameInformation.getCurrentGold()), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel.setFontScale(2);

        goldDecreaseLabel = new Label("", new Label.LabelStyle(font, Color.RED));
        goldDecreaseLabel.setVisible(false);
        goldDecreaseLabel.setFontScale(2);
        goldDecreaseLabel.setAlignment(1);
        Stack stack = new Stack();
        stack.add(scoreLabel);
        stack.add(goldDecreaseLabel);
        table = new Table();
        table.top();
        table.setFillParent(true);
        table.row();
        table.add(versionLabel).expandX().align(Align.left).top();
        table.add(stack).expandX().align(Align.right).colspan(3);
        table.row();
        table.add(upgradeButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        table.add(skillButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        table.add(mapButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        table.add(achievButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/4);
        stage.addActor(table);

        stage.addActor(upgradeMenu.getTable());
        stage.addActor(creditMenu.getTable());
    }

    /**
     * Formater pour l'affichage de l'or
     */
    private void initGoldFormater(){
        suffixes.put(1000L, "A");
        suffixes.put(1000000L, "B");
        suffixes.put(1000000000L, "C");
        suffixes.put(1000000000000L, "D");
        suffixes.put(1000000000000000L, "E");
        suffixes.put(1000000000000000000L, "F");
    }

    public String format(long value) {
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
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

    public void animateDecreaseGold(int value) {
        goldDecreaseLabel.setText("- " + value);
//        goldDecreaseLabel.setPosition(150,0);
        goldDecreaseLabel.clearActions();
        goldDecreaseLabel.addAction(Actions.sequence(
                Actions.show(),
                Actions.fadeIn(0.5f),
                Actions.fadeOut(1f),
                Actions.hide()
        ));
        goldDecreaseLabel.addAction(Actions.sequence(
                Actions.delay(0.5f),
                Actions.moveTo(goldDecreaseLabel.getX(),goldDecreaseLabel.getY()-100,3f)
        ));
    }

    /**
     * Modification du listener d'input en fonction de l'etat
     */
    private void toggleMenu(AbstractMenu menu) {
        // Affiche le menu s'il n'est pas visible
        if (!menu.getTable().isVisible()) {
            menu.getTable().setVisible(true);
            gameManager.setCurrentState(GameState.UPGRADE);
        } else {
            menu.getTable().setVisible(false);
            gameManager.setCurrentState(GameState.IN_GAME);
        }

        // masque les autres menus
        if (menu instanceof CreditMenu ){
            upgradeMenu.getTable().setVisible(false);
        }
        if (menu instanceof UpgradeMenu ){
            creditMenu.getTable().setVisible(false);
        }
    }

    public void setGold(int gold){
        scoreLabel.setText(format(gold));
//        scoreLabel.setText(String.format("%d", gold));
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
