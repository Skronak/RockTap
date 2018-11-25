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
import com.rocktap.input.CameraDragListener;
import com.rocktap.manager.GameManager;
import com.rocktap.menu.AbstractMenu;
import com.rocktap.menu.AchievmentMenu;
import com.rocktap.menu.FactionMenu;
import com.rocktap.menu.GameInformationMenu;
import com.rocktap.menu.UpgradeModuleMenu;
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
    private UpgradeModuleMenu upgradeModuleMenu;
    private FactionMenu factionMenu;
    private GameInformationMenu gameInformationMenu;
    private Label versionLabel;
    private Label scoreLabel;
    private Label goldDecreaseLabel;
    private BitmapFont font;
    private com.rocktap.utils.BitmapFontGenerator generator;
    private Table table;
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
    private LargeMath largeMath;
    private AbstractMenu currentMenu;
    // Liste de tous les menus du jeu
    private ArrayList<AbstractMenu> activeMenuList;
    private PlayScreen playScreen;

    public Hud(SpriteBatch sb, GameManager gameManager, PlayScreen playscreen) {
        largeMath = gameManager.getLargeMath();
        this.gameManager = gameManager;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport, sb);
        generator = new com.rocktap.utils.BitmapFontGenerator();
        font = generator.getFont();
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
        upgradeModuleMenu = new UpgradeModuleMenu(gameManager);
        factionMenu = new FactionMenu(gameManager);
        gameInformationMenu = new GameInformationMenu(gameManager);

        activeMenuList = new ArrayList<AbstractMenu>();
        activeMenuList.add(upgradeModuleMenu);
        activeMenuList.add(new AchievmentMenu(gameManager));
        activeMenuList.add(factionMenu);
        activeMenuList.add(gameInformationMenu);
    }
    /**
     * Initialisation des bouton du hud
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

        // Declaration des listener
        InputListener buttonListenerCredit = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(activeMenuList.get(2));
                return false;
            }
        };
        mapButton.addListener(buttonListenerCredit);

        // Declaration des listener
        InputListener buttonListenerInformation = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleMenu(activeMenuList.get(3));
                return false;
            }
        };
        achievButton.addListener(buttonListenerInformation);

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
    }

    /**
     * Initialise les informations du HUD et
     * ajoute les elemnts dans le stage
     */
    private void initHud(){
        versionLabel = new Label(Constants.CURRENT_VERSION, new Label.LabelStyle(font, Color.WHITE));
        versionLabel.setFontScale(0.5f);
        versionLabel.setWrap(true);
        scoreLabel = new Label(largeMath.getDisplayValue(gameManager.getGameInformation().getCurrentGold(), gameManager.getGameInformation().getCurrency()), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel.setAlignment(Align.right);
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

        // Ajout des menu a l'interface
        stage.addActor(activeMenuList.get(0).getParentTable());
        stage.addActor(activeMenuList.get(1).getParentTable());
        stage.addActor(activeMenuList.get(2).getParentTable());
        stage.addActor(activeMenuList.get(3).getParentTable());
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
        String text = this.gameManager.getLargeMath().getDisplayValue(valueDto.getValue(), valueDto.getCurrency());
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
            gameManager.setCurrentState(GameState.IN_GAME);
            currentMenu = null;
        } else {
            menu.getParentTable().setVisible(true);
            gameManager.setCurrentState(GameState.UPGRADE);
            currentMenu = menu;
        }
    }

    // Met a jour l'affichage de l'or
    public void updateGoldLabel(){
        String scoreAffichage = largeMath.getDisplayValue(gameManager.getGameInformation().getCurrentGold(), gameManager.getGameInformation().getCurrency());
        scoreLabel.setText(scoreAffichage);
    }

    // Met a jour l'affichage du menu actif
    public void updateMenu(){
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

    public UpgradeModuleMenu getUpgradeModuleMenu() {
        return upgradeModuleMenu;
    }

}
