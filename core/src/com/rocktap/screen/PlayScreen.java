package com.rocktap.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocktap.Animation.AnimatedActor;
import com.rocktap.game.AccountInformation;
import com.rocktap.game.Constants;
import com.rocktap.game.Station;
import com.rocktap.input.CustomInputProcessor;
import com.rocktap.menu.MainMenuBar;

import java.util.Random;

/**
 * Created by Skronak on 29/01/2017.
 */
public class PlayScreen implements Screen {

    private SpriteBatch spriteBatch;
    private Random random;
    private Stage stage;
    private BitmapFont font;
    private float autoSaveTimer;
    private float increaseGoldTimer;
    private float stationAnimationTimer;
    private float otherbeamTimer;
    private float lastTouch;
    private int consecutivTouch; // touche consecutives
    private OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;
    private int textAnimMinX;
    private AccountInformation accountInformation;
    private com.rocktap.utils.BitmapFontGenerator generator;
    private Image beamImage;
    private Image beamCriticalImage;
    private Image beamMaxSpeedImage;
    private Image stationBorderImage;
    private Image backgroundImage;
    private Image backgroundImageOverlay;
    private boolean stationAnimationUp; // indique si animation de montée ou descente
    private boolean backgroundImageOverlayIncrease; // indique si alpha augmente ou descend
    private Group layer0GraphicObject = new Group(); // Background
    private Group layer1GraphicObject = new Group(); // Objects
    private Group layer2GraphicObject = new Group(); // Foreground
    private MainMenuBar mainMenuBar;
    private Label goldLabel;
    private Station station;
    private AnimatedActor beamActor;
    private AnimatedActor tapActor;
    private Array<TextureRegion> frames;
    private InputMultiplexer inputMultiplexer;
    private Image amelio1;
    private Image amelio2;
    private Image amelio3;
    Table stationTable = new Table();

    @Override
    public void show() {
        autoSaveTimer = 0f;
        increaseGoldTimer = 0f;
        stationAnimationTimer = 0f;
        otherbeamTimer = 0f;
        lastTouch = 0f;
        stationAnimationUp = false;
        textAnimMinX =100;
        consecutivTouch=0;

        generator = new com.rocktap.utils.BitmapFontGenerator();
        font = generator.getFont();

        accountInformation = new AccountInformation();
        spriteBatch = new SpriteBatch();
        random = new Random();

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        CustomInputProcessor inputProcessor = new CustomInputProcessor(stage, this);
        hud = new Hud(spriteBatch, accountInformation);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputProcessor);
        inputMultiplexer.addProcessor(hud.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);

        //BeamActor
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b3.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b4.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b5.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b6.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b7.png"))));
        beamActor = new AnimatedActor(155,50, 32,270, 0.2f, frames);
        beamActor.setVisible(false);

        //touchActor
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c3.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c4.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c5.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c6.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c7.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c8.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/c9.png"))));
        tapActor = new AnimatedActor(0,0,20,20,0.1f,frames);
        tapActor.setVisible(false);

        // FOR TESTING: amelioration
        amelio1 = new Image(new Texture(Gdx.files.internal("sprites/beamup1.png")));
        amelio2 = new Image(new Texture(Gdx.files.internal("sprites/beamup2.png")));
        amelio3 = new Image(new Texture(Gdx.files.internal("sprites/antenna.png")));
        amelio1.setBounds(70,300,20,10);
        amelio2.setBounds(70,300,20,10);
        amelio3.setBounds(70,300,20,10);

        // Station //TODO a mettre dans une classe specifique pour gerer les amelio
        station = new Station(70,300,200,100,2f);
        stationTable.setBounds(70,300,200,100);
        stationTable.add(station);
        //TODO => j'ajoute dans une table pour add facilement des amelioration => creer object station direct avec partie amovibles

        // TODO: Mettre asset dans classe de chargement => splash screen
        beamImage = new Image(new Texture(Gdx.files.internal("sprites/beam.png")));
        beamImage.setBounds(155,50, 32,270); // position de l'image
        beamCriticalImage = new Image(new Texture(Gdx.files.internal("sprites/beamCritical.png")));
        beamCriticalImage.setBounds(155,50, 32,270); // position de l'image
        beamCriticalImage.setVisible(false);
        beamMaxSpeedImage = new Image(new Texture(Gdx.files.internal("sprites/bMaxSpeed.png")));
        beamMaxSpeedImage.setBounds(155,50, 32,270); // position de l'image
        beamMaxSpeedImage.setVisible(false);
        beamMaxSpeedImage.setVisible(false);
        stationBorderImage = new Image(new Texture(Gdx.files.internal("sprites/ship.png")));
        stationBorderImage.setBounds(70,300,200,100);
        stationBorderImage.debug();
        backgroundImage = new Image(new Texture(Gdx.files.internal("sprites/rock.png")));
        backgroundImageOverlay = new Image(new Texture(Gdx.files.internal("sprites/rock_overlay.png")));

//        backgroundImage.setScale(0.8f);
        mainMenuBar = new MainMenuBar();

        // Gestion des calques
        stage.addActor(layer0GraphicObject);
        stage.addActor(layer1GraphicObject);
        stage.addActor(layer2GraphicObject);

        // Ajout des objets dans les calques
        layer0GraphicObject.addActor(backgroundImage);
        layer0GraphicObject.addActor(backgroundImageOverlay);
        layer1GraphicObject.addActor(beamActor);
        layer1GraphicObject.addActor(beamMaxSpeedImage);
        layer1GraphicObject.addActor(beamCriticalImage);
        layer1GraphicObject.addActor(tapActor);
        layer2GraphicObject.addActor(stationBorderImage);
        layer2GraphicObject.addActor(stationTable);
        layer2GraphicObject.addActor(amelio1);
        layer2GraphicObject.addActor(amelio2);
        layer2GraphicObject.addActor(amelio3);
        layer2GraphicObject.addActor(mainMenuBar);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        this.spriteBatch.setProjectionMatrix(camera.combined);
        updateLogic();
//        spriteBatch.begin();
        hud.setGold(accountInformation.getCurrentGold());
        stage.act();
//        station.act(delta);
        stage.draw();
//        spriteBatch.end();
        spriteBatch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.draw();
    }

    /**
     * Affiche image la ou lecran est touche
     * @param positionX
     * @param positionY
     */
    public void processPointerHitAnimation(int positionX, int positionY) {
        tapActor.clearActions();
        Vector3 position2World = camera.unproject(new Vector3(positionX, positionY,0));
        tapActor.setColor(Color.WHITE);
        tapActor.setPosition(position2World.x- ((int)tapActor.getWidth()/2),( (int) position2World.y-tapActor.getHeight()/2));//TODO a calculer autrepart
        tapActor.addAction(Actions.sequence(
                Actions.show(),
                Actions.fadeIn(0.5f),
                Actions.fadeOut(0.2f),
                Actions.hide()
        ));
    }

    /**
     * Animation du jeu au touche
     */
    public void processHit() {
        goldLabel = new Label("hit "+String.valueOf(accountInformation.getGenGold()),new Label.LabelStyle(font, Constants.NORMAL_LABEL_COLOR));
        goldLabel.setPosition(150,0);
        layer2GraphicObject.addActor(goldLabel);
        goldLabel.addAction(Actions.sequence(
                Actions.fadeIn(1f),
                Actions.fadeOut(2f),
                Actions.removeActor(goldLabel)
        ));
        goldLabel.addAction(Actions.moveTo(150+random.nextInt(100+textAnimMinX)-textAnimMinX,250,3f));
        lastTouch += Gdx.graphics.getDeltaTime();
        if (lastTouch >= 0.5f) {
            beamActor.decreaseSpeed(0.05f);
            lastTouch=0f;
            consecutivTouch=0;
        } else {
            consecutivTouch++;
        }
        if (consecutivTouch >= 10) {
            beamActor.increaseSpeed(0.05f);
        }
    }

    /**
     * Animation du jeu au touche critique
     */
    public void processCriticalHit() {
        beamCriticalImage.clearActions();
        beamCriticalImage.addAction(Actions.sequence(
                Actions.show(),
                Actions.fadeIn(0.5f),
                Actions.fadeOut(0.5f),
                Actions.hide()));
        accountInformation.increaseGoldCritical();
        hud.animateCritical();

        goldLabel.setText("CRITICAL "+String.valueOf(accountInformation.getGenGold()));
        goldLabel.setColor(Constants.CRITICAL_LABEL_COLOR);
    }

    /**
     * Animation du jeu au touche normal
     */
    public void processNormalHit() {
        //TODO Isoler systeme gestion beam
        // TODO mep state
        if (beamActor.getIdleAnimation().getFrameDuration() <= 0.04f) {
            beamMaxSpeedImage.clearActions();
            beamMaxSpeedImage.addAction(Actions.sequence(
                    Actions.show(),
                    Actions.fadeIn(0.5f),
                    Actions.fadeOut(0.5f),
                    Actions.hide()));
        } else {
        beamActor.clearActions();
        beamActor.addAction(Actions.sequence(
                Actions.show(),
                Actions.fadeIn(0.5f),
                Actions.fadeOut(0.5f),
                Actions.hide()));
    }}

    /**
     * Modification de letat du jeu en fonction
     * du temps passe
     */
    public void updateLogic() {
        autoSaveTimer += Gdx.graphics.getDeltaTime();
        increaseGoldTimer += Gdx.graphics.getDeltaTime();
        stationAnimationTimer += Gdx.graphics.getDeltaTime();
        otherbeamTimer += Gdx.graphics.getDeltaTime();
        switch (accountInformation.getCurrentState()) {
            case IN_GAME: Gdx.input.setInputProcessor(inputMultiplexer);
                break;
            case UPGRADE:  Gdx.input.setInputProcessor(hud.getStage());
                break;
            default:
                break;
        }

        // Autosave
        if(autoSaveTimer >= Constants.DELAY_AUTOSAVE){
            Gdx.app.debug("PlayScreen","Saving");
            accountInformation.saveInformation();
            autoSaveTimer=0f;
        }
        // Increase Gold
        if(increaseGoldTimer >= 1) {
            Gdx.app.debug("PlayScreen","Increasing Gold");
            accountInformation.increaseGold();
            hud.setGold(accountInformation.getCurrentGold());
            increaseGoldTimer=0f;
        }

        // station animation
        if(stationAnimationTimer >= 0.5f) {
            if (backgroundImageOverlay.getColor().a >= 1f && backgroundImageOverlayIncrease) {
                backgroundImageOverlayIncrease = false;
            }
            if (backgroundImageOverlay.getColor().a <= 0.1f && !backgroundImageOverlayIncrease) {
                backgroundImageOverlayIncrease = true;
            }

            if (backgroundImageOverlayIncrease) {
                backgroundImageOverlay.getColor().a = backgroundImageOverlay.getColor().a + 0.05f;
            } else {
                backgroundImageOverlay.getColor().a = backgroundImageOverlay.getColor().a - 0.05f;
            }
            if (station.getY() >= 308 && stationAnimationUp) {
                stationAnimationUp = false;
            }
            if (station.getY() <= 302 && !stationAnimationUp) {
                stationAnimationUp = true;
            }

            if (stationAnimationUp) {
                station.moveBy(0,1);
                stationBorderImage.moveBy(0,1);
            } else {
                station.moveBy(0,-1);
                stationBorderImage.moveBy(0,-1);
            }
            stationAnimationTimer = 0f;
        }

        if(otherbeamTimer >= 5f) {
            otherbeamTimer = 0;
        }
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("PlayScreen", "Resize occured w"+width+" h"+height);
        viewport.update(width, height);
        hud.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        Gdx.app.log("PlayScreen","dispose");
        spriteBatch.dispose();
        accountInformation.saveInformation();
    }

    public AccountInformation getAccountInformation(){
        return accountInformation;
    }
}
