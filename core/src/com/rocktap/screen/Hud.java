package com.rocktap.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocktap.Animation.AnimatedBaseActor;
import com.rocktap.entity.GameInformation;
import com.rocktap.input.CameraDragListener;
import com.rocktap.manager.AssetManager;
import com.rocktap.manager.GameManager;
import com.rocktap.menu.AbstractMenu;
import com.rocktap.menu.AchievmentMenu;
import com.rocktap.menu.FactionMenu;
import com.rocktap.menu.GameInformationMenu;
import com.rocktap.menu.OptionMenu;
import com.rocktap.menu.moduleMenu.ModuleMenu;
import com.rocktap.utils.Constants;
import com.rocktap.utils.GameState;
import com.rocktap.utils.LargeMath;
import com.rocktap.utils.ValueDTO;

import java.util.ArrayList;

/**
 * Created by Skronak on 11/12/2016.
 *
 * Classe HUD contenant tous les boutons et menu
 * du jeu
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private ModuleMenu moduleMenu;
    private FactionMenu factionMenu;
    private OptionMenu optionMenu;
    private GameInformationMenu gameInformationMenu;
    private Label versionLabel;
    private Label scoreLabel;
    private Label goldDecreaseLabel;
    private BitmapFont font;
    private com.rocktap.utils.BitmapFontGenerator generator;
    private Table table;
    private GameManager gameManager;
    private Button skillButton;
    private Button upgradeButton;
    private Button mapButton;
    private Button achievButton;
    private Button optionButton;
    private Button passiveButton;
    private LargeMath largeMath;
    private AbstractMenu currentMenu;
    // Liste de tous les menus du jeu
    private ArrayList<AbstractMenu> activeMenuList;
    private PlayScreen playScreen;
    private Label depthLabel;
    public AnimatedBaseActor screAnimatedActor;
    private Animation idleAnimation;

    public Hud(SpriteBatch sb, GameManager gameManager, PlayScreen playscreen) {
        largeMath = gameManager.largeMath;
        this.gameManager = gameManager;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport, sb);
        generator = new com.rocktap.utils.BitmapFontGenerator();
        font = AssetManager.INSTANCE.getFont();
        generator.dispose();
        font.setColor(Color.WHITE);
        this.playScreen = playscreen;
        initMenu();
        initButton();
        initHud();
    }

    /**
     * Initialise les menu
     */
    private void initMenu() {
        moduleMenu = new ModuleMenu(gameManager);
        factionMenu = new FactionMenu(gameManager);
        gameInformationMenu = new GameInformationMenu(gameManager);
        optionMenu = new OptionMenu(gameManager);

        activeMenuList = new ArrayList<AbstractMenu>();
        activeMenuList.add(moduleMenu);
        activeMenuList.add(new AchievmentMenu(gameManager));
        activeMenuList.add(factionMenu);
        activeMenuList.add(gameInformationMenu);
        activeMenuList.add(new OptionMenu(gameManager));
    }

    /**
     * Initialisation des bouton du hud
     */
    private void initButton() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("icons/screw_0.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("icons/screw_1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("icons/screw_2.png"))));
        idleAnimation = new Animation(0.2f, frames);
        idleAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        screAnimatedActor = new AnimatedBaseActor();
        screAnimatedActor.storeAnimation("idle",idleAnimation);
        screAnimatedActor.pauseAnimation();

        Texture upgradeButtonTextureUp = new Texture(Gdx.files.internal("icons/hud_b2.png"));
        Texture skillButtonTextureUp = new Texture(Gdx.files.internal("icons/hud_b1.png"));
        Texture achievButtonTextureUp = new Texture(Gdx.files.internal("icons/hud_b3.png"));
        Texture mapButtonTextureUp = new Texture(Gdx.files.internal("icons/hud_b4.png"));
        Texture upgradeButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b2_r.png"));
        Texture skillButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b1_r.png"));
        Texture achievButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b3_r.png"));
        Texture mapButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b4_r.png"));
        Texture passivButtonTextureup = new Texture(Gdx.files.internal("icons/hud_b5_r.png"));
        Texture passivButtonTextureDown = new Texture(Gdx.files.internal("icons/hud_b5.png"));

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(skillButtonTextureUp));
        style.down = new TextureRegionDrawable(new TextureRegion(skillButtonTextureDown));
        upgradeButton = new ImageButton(style);
        ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle();
        style2.up = new TextureRegionDrawable(new TextureRegion(upgradeButtonTextureUp));
        style2.down =  new TextureRegionDrawable(new TextureRegion(upgradeButtonTextureDown));
        skillButton = new ImageButton(style2);
        ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle();
        style3.up = new TextureRegionDrawable(new TextureRegion(achievButtonTextureUp));
        style3.down = new TextureRegionDrawable(new TextureRegion(achievButtonTextureDown));
        mapButton = new ImageButton(style3);
        ImageButton.ImageButtonStyle style4 = new ImageButton.ImageButtonStyle();
        style4.up = new TextureRegionDrawable(new TextureRegion(mapButtonTextureUp));
        style4.down =  new TextureRegionDrawable(new TextureRegion(mapButtonTextureDown));
        achievButton = new ImageButton(style4);
        ImageButton.ImageButtonStyle style5 = new ImageButton.ImageButtonStyle();
        style5.up = new TextureRegionDrawable(new TextureRegion(mapButtonTextureUp));
        style5.down =  new TextureRegionDrawable(new TextureRegion(mapButtonTextureDown));
        optionButton = new ImageButton(style5);
        ImageButton.ImageButtonStyle style6 = new ImageButton.ImageButtonStyle();
        style6.up = new TextureRegionDrawable(new TextureRegion(passivButtonTextureDown));
        style6.down = new TextureRegionDrawable(new TextureRegion(passivButtonTextureup));
        passiveButton = new ImageButton(style6);

        stage.addListener(new CameraDragListener(playScreen));

        // Declaration des listener
        InputListener buttonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(activeMenuList.get(0));
                return false;
            }
        };
        upgradeButton.addListener(buttonListener);

        InputListener buttonListenerSkill = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(activeMenuList.get(1));
                return false;
            }
        };
        skillButton.addListener(buttonListenerSkill);

        InputListener buttonListenerCredit = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(activeMenuList.get(2));
                return false;
            }
        };
        mapButton.addListener(buttonListenerCredit);

        InputListener buttonListenerInformation = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(activeMenuList.get(3));
                return false;
            }
        };
        achievButton.addListener(buttonListenerInformation);

        InputListener buttonListenerOption = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(activeMenuList.get(4));
                return false;
            }
        };
        passiveButton.addListener(buttonListenerOption);

    }

    /**
     * Initialise les informations du HUD et
     * ajoute les elemnts dans le stage
     */
    private void initHud(){
        versionLabel = new Label(Constants.CURRENT_VERSION, new Label.LabelStyle(font, Color.WHITE));
        versionLabel.setFontScale(0.5f);
        versionLabel.setWrap(true);
        scoreLabel = new Label(largeMath.getDisplayValue(GameInformation.INSTANCE.getCurrentGold(), GameInformation.INSTANCE.getCurrency()), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setFontScale(2);
        depthLabel = new Label(String.valueOf(GameInformation.INSTANCE.getDepth()+" Meters"), new Label.LabelStyle(font, Color.WHITE));
        depthLabel.setFontScale(1);

        goldDecreaseLabel = new Label("", new Label.LabelStyle(font, Color.RED));
        goldDecreaseLabel.setVisible(false);
        goldDecreaseLabel.setFontScale(2);
        goldDecreaseLabel.setAlignment(1);
        Stack stack = new Stack();
        stack.add(scoreLabel);
        stack.add(goldDecreaseLabel);

        Table tableTopMiddle = new Table();
        tableTopMiddle.add(stack).expandX().align(Align.center);
        tableTopMiddle.row();
        tableTopMiddle.add(depthLabel).colspan(activeMenuList.size());
        tableTopMiddle.row();
        tableTopMiddle.add(screAnimatedActor).width(100).height(20);

        table = new Table();
        table.setFillParent(true);
        table.add(passiveButton).height(50).width(50);
        table.add(tableTopMiddle).colspan(activeMenuList.size()).align(Align.left).padLeft(Constants.V_WIDTH/2-45-60);
        table.row();
        table.add(versionLabel).expandX().align(Align.right).colspan(activeMenuList.size()-1).bottom();
        table.row();
        table.add(upgradeButton).expandY().bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/activeMenuList.size());
        table.add(skillButton).bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/activeMenuList.size());
        table.add(mapButton).bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/activeMenuList.size());
        table.add(achievButton).bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/activeMenuList.size());
        table.add(optionButton).bottom().height(Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT).width(Constants.V_WIDTH/activeMenuList.size());
      //  table.debug();

        // Ajout des menu a l'interface
        for(int i=0;i<activeMenuList.size();i++) {
            table.addActor(activeMenuList.get(i).getParentTable());
            stage.addActor(table);
        }
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

    public void animateDecreaseGold(ValueDTO valueDto) {
        String text = gameManager.largeMath.getDisplayValue(valueDto.getValue(), valueDto.getCurrency());
        goldDecreaseLabel.setText("- " + text);
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
        // Masque tous les menu
        for (int i = 1; i < activeMenuList.size(); i++) {
            activeMenuList.get(i).getParentTable().setVisible(false);
        }

        // Affiche le menu concerné si non visible
        if (menu.equals(currentMenu)) {
            menu.getParentTable().setVisible(false);
            gameManager.currentState=GameState.IN_GAME;
            currentMenu = null;
        } else {
            menu.getParentTable().setVisible(true);
            gameManager.currentState=GameState.MENU;
            currentMenu = menu;
        }
    }

    // Met a jour l'affichage de l'or
    public void updateGoldLabel(){
        String scoreAffichage = largeMath.getDisplayValue(GameInformation.INSTANCE.getCurrentGold(), GameInformation.INSTANCE.getCurrency());
        scoreLabel.setText(scoreAffichage);
    }

    public Animation getIdleAnimation() {
        return idleAnimation;
    }

    // Met a jour l'affichage du menu actif
    public void updateMenu() {
        if (null != currentMenu) {
            currentMenu.update();
        }
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

    public ModuleMenu getModuleMenu() {
        return moduleMenu;
    }

}
