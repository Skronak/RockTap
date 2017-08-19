package com.rocktap.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocktap.Animation.AnimatedActor;
import com.rocktap.entity.GameInformation;
import com.rocktap.entity.StationActor;
import com.rocktap.input.CustomInputProcessor;
import com.rocktap.manager.GameManager;
import com.rocktap.menu.MainMenuBar;
import com.rocktap.utils.Constants;

import java.util.Random;

/**
 * Created by Skronak on 29/01/2017.
 */
public class PlayScreen implements Screen {

    private SpriteBatch spriteBatch;
    private GameManager gameManager;
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
    private GameInformation gameInformation;
    private com.rocktap.utils.BitmapFontGenerator generator;
    private Image beamCriticalImage;
    private Image beamMaxSpeedImage;
    private Image stationBorderImage;
    private Image backgroundImage;
    private Image skyImage;
    private Image backgroundImageOverlay;
    private boolean stationAnimationUp; // indique si animation de montée ou descente
    private boolean backgroundImageOverlayIncrease; // indique si alpha augmente ou descend
    private Group layer0GraphicObject = new Group(); // Background
    private Group layer1GraphicObject = new Group(); // Objects
    private Group layer2GraphicObject = new Group(); // Foreground
    private MainMenuBar mainMenuBar;
    private Label goldLabel;
    private StationActor station;

    private AnimatedActor tapActor;
    private AnimatedActor rewardActor;
    private Array<TextureRegion> frames;
    private Array<TextureRegion> frames2;
    private InputMultiplexer inputMultiplexer;

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

        gameInformation = new GameInformation();
        spriteBatch = new SpriteBatch();
        random = new Random();
        gameManager = new GameManager(gameInformation, this);
        gameManager.calculateRestReward();
        hud = new Hud(spriteBatch, gameManager);

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);


        CustomInputProcessor inputProcessor = new CustomInputProcessor(this);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputProcessor);
        inputMultiplexer.addProcessor(hud.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);

        //BeamActor

        //tapActor
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/tap/tap1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/tap/tap2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/tap/tap3.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/tap/tap4.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/tap/tap5.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/tap/tap6.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/tap/tap7.png"))));
        tapActor = new AnimatedActor(0,0,20,20,0.09f,frames, Animation.PlayMode.REVERSED);
        tapActor.setVisible(false);
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/reward/chest1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/reward/chest1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/reward/chest1.png"))));
        rewardActor =new AnimatedActor(0,0,20,20,0.1f,frames, Animation.PlayMode.NORMAL);
        rewardActor.setVisible(false);

        // Station //TODO a mettre dans une classe specifique pour gerer les amelio
        station = gameManager.initStationActor(70,400,200,100,2f);
        //TODO => j'ajoute dans une table pour add facilement des amelioration => creer object station direct avec partie amovibles

        // TODO: Mettre asset dans classe de chargement => splash screen
        beamCriticalImage = new Image(new Texture(Gdx.files.internal("sprites/beamCritical.png")));
        beamCriticalImage.setBounds(155,50, 32,station.getY()-30); // position de l'image
        beamCriticalImage.setVisible(false);
        beamMaxSpeedImage = new Image(new Texture(Gdx.files.internal("sprites/bMaxSpeed.png")));
        beamMaxSpeedImage.setBounds(155,50, 32,station.getY()-30); // position de l'image
        beamMaxSpeedImage.setVisible(false);
        stationBorderImage = new Image(new Texture(Gdx.files.internal("sprites/station/ship"+ gameManager.getGameInformation().getStationId()+"_0.png")));
        stationBorderImage.setBounds(70,400,200,100);
//        backgroundImage = new Image(new Texture(Gdx.files.internal("sprites/rock.png")));
        backgroundImage = new Image(new Texture(Gdx.files.internal("sprites/background/rockValley.png")));
        backgroundImage.setScale(0.6f,0.6f);
        backgroundImage.setPosition(-130, backgroundImage.getY());
        skyImage = new Image(new Texture(Gdx.files.internal("sprites/background/sky.png")));
        skyImage.scaleBy(0.4f);

//        backgroundImageOverlay = new Image(new Texture(Gdx.files.internal("sprites/rock_overlay.png")));

        mainMenuBar = new MainMenuBar();

        // Gestion des calques
        stage.addActor(layer0GraphicObject);
        stage.addActor(layer1GraphicObject);
        stage.addActor(layer2GraphicObject);

        // Ajout des objets dans les calques
        layer0GraphicObject.addActor(skyImage);
        layer0GraphicObject.addActor(backgroundImage);
//        layer0GraphicObject.addActor(backgroundImageOverlay);
        layer1GraphicObject.addActor(station.getBeamActor());
        layer1GraphicObject.addActor(beamMaxSpeedImage);
        layer1GraphicObject.addActor(beamCriticalImage);
        layer1GraphicObject.addActor(tapActor);
        layer2GraphicObject.addActor(rewardActor);
        layer2GraphicObject.addActor(stationBorderImage);
        layer2GraphicObject.addActor(station);

        layer2GraphicObject.addActor(mainMenuBar);

        if (gameInformation.isFirstPlay()) {
            displayTutorial();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        this.spriteBatch.setProjectionMatrix(camera.combined);
        updateLogic();
//        spriteBatch.begin();
        hud.updateGoldLabel();
        stage.act();
//        station.act(delta);
        stage.draw();
//        spriteBatch.end();
        spriteBatch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.draw();

        //DEBUG
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.zoom = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.zoom = 2;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-1f,0f);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(1f,0f);
        }
    }

    /**
     * Affiche image la ou lecran est touche
     * @param positionX
     * @param positionY
     */
    public void processPointerHitAnimation(int positionX, int positionY) {
        tapActor.clearActions();
        tapActor.setDeltatime(0);
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
        goldLabel = new Label(gameManager.getLargeMath().getDisplayValue(gameInformation.getGenGoldActive(), gameInformation.getGenCurrencyActive()),new Label.LabelStyle(font, Constants.NORMAL_LABEL_COLOR));
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
            station.getBeamActor().decreaseSpeed(0.05f);
            lastTouch=0f;
            consecutivTouch=0;
        } else {
            consecutivTouch++;
        }
        if (consecutivTouch >= 10) {
            station.getBeamActor().increaseSpeed(0.05f);
//            beamActor.setWidth(beamActor.getWidth()+1);
//            beamActor.setX(beamActor.getX()-1);
        }
    }

    /**
     * Animation du jeu au touche critique
     * @param value: valeur du critique
     */
    public void processCriticalHit(float value) {
        beamCriticalImage.clearActions();
        beamCriticalImage.addAction(Actions.sequence(
                Actions.show(),
                Actions.fadeIn(0.5f),
                Actions.fadeOut(0.5f),
                Actions.hide()));
        hud.animateCritical();

        goldLabel.setText("CRITICAL "+String.valueOf(value));
        goldLabel.setColor(Constants.CRITICAL_LABEL_COLOR);
    }

    /**
     * Animation du jeu au touche normal
     */
    public void processNormalHit() {
        //TODO Isoler systeme gestion beam
        // TODO mep state
        if (station.getBeamActor().getIdleAnimation().getFrameDuration() <= 0.04f) {
            beamMaxSpeedImage.clearActions();
            beamMaxSpeedImage.addAction(Actions.sequence(
                    Actions.show(),
                    Actions.fadeIn(0.5f),
                    Actions.fadeOut(0.5f),
                    Actions.hide()));
        } else {
            station.getBeamActor().clearActions();
            station.getBeamActor().addAction(Actions.sequence(
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
        switch (gameManager.getCurrentState()) {
            case IN_GAME: Gdx.input.setInputProcessor(inputMultiplexer);
                break;
            case UPGRADE:  Gdx.input.setInputProcessor(hud.getStage());
                break;
            case CREDIT:  Gdx.input.setInputProcessor(hud.getStage());
                break;
            default:
                break;
        }

        // Autosave
        if(autoSaveTimer >= Constants.DELAY_AUTOSAVE){
            Gdx.app.debug("PlayScreen","Saving");
            gameInformation.saveInformation();
            autoSaveTimer=0f;
        }
        // Increase Gold
        if(increaseGoldTimer >= 1) {
            gameManager.increaseGoldPassive();
            Gdx.app.debug("PlayScreen","Increasing Gold by "+gameInformation.getGenCurrencyPassive()+" val "+gameInformation.getGenGoldPassive());
            gameManager.increaseGoldPassive();
            hud.updateGoldLabel();
            increaseGoldTimer=0f;
        }

        // station animation
        if(stationAnimationTimer >= 0.5f) {
/*
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

 */

        if (station.getY() >= Constants.STATION_ANIMATION_MAX_ALTITUDE && stationAnimationUp) {
                stationAnimationUp = false;
            }
            if (station.getY() <= Constants.STATION_ANIMATION_MIN_ALTITUDE && !stationAnimationUp) {
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
//            otherBeamImage.addAction(Actions.sequence(
//                    Actions.show(),
//                    Actions.fadeIn(0.5f),
//                    Actions.fadeOut(0.3f),
//                    Actions.hide()
//            ));
            otherbeamTimer = 0;
        }

    }

    //TODO: a terminer
    //afficher tuto en surimpression
    private void displayTutorial(){
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug("PlayScreen", "Resize occured w"+width+" h"+height);
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
        Gdx.app.debug("PlayScreen","dispose");
        spriteBatch.dispose();
        Gdx.app.debug("PlayScreen","saveInformation");
        gameManager.getLargeMath().formatGameInformation();
        gameInformation.saveInformation();

    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public GameManager getGameManager() {
        return gameManager;
    }

    public StationActor getStation() {
        return station;
    }

    public void setStation(StationActor station) {
        this.station = station;
    }

    public Hud getHud() {
        return hud;
    }
}
