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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
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
import com.rocktap.Animation.TapActor;
import com.rocktap.entity.GameInformation;
import com.rocktap.entity.StationEntity;
import com.rocktap.input.CustomInputProcessor;
import com.rocktap.manager.AssetManager;
import com.rocktap.manager.GameManager;
import com.rocktap.utils.Constants;
import com.rocktap.utils.ScrollingBackground;

import java.util.Random;

import static com.badlogic.gdx.Gdx.files;

/**
 * Created by Skronak on 29/01/2017.
 */
public class PlayScreen implements Screen {

    // common batch for screen/hud
    private SpriteBatch spriteBatch;
    private GameManager gameManager;
    private Random random;
    private Stage stage;
    private long lastTouch;
    private int consecutivTouch; // touche consecutives
    public OrthographicCamera camera;
    private Viewport viewport;
    private Hud hud;
    private int textAnimMinX;
    private com.rocktap.utils.BitmapFontGenerator generator;
    private Image backgroundImage;
    private Image undergroundImage;
    private Image skyImage;
    private Image holeImage;
    private Group layer0GraphicObject = new Group(); // Background
    private Group layer1GraphicObject = new Group(); // Objects
    private Group layer2GraphicObject = new Group(); // Foreground
    private Label goldLabel;
    private ScrollingBackground scrollingBackground;
    private int[] goldLabelPosition = {100,80,120,70,130};
    int gLPPointer;
    private TapActor tapActor;
    private AnimatedActor rewardActor;
    private Array<TextureRegion> frames;
    public InputMultiplexer inputMultiplexer;
    public StationEntity stationEntity;

    @Override
    public void show() {
        lastTouch = 0l;
        textAnimMinX =100;
        consecutivTouch=0;

        spriteBatch = new SpriteBatch();
        random = new Random();
        gameManager = new GameManager(this);
        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport, spriteBatch);

        hud = new Hud(spriteBatch, gameManager, this);

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        CustomInputProcessor inputProcessor = new CustomInputProcessor(this);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputProcessor);
        inputMultiplexer.addProcessor(hud.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);

        //tapActor
        tapActor = new TapActor();

        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ GameInformation.INSTANCE.getStationId()+"_0.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ GameInformation.INSTANCE.getStationId()+"_1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ GameInformation.INSTANCE.getStationId()+"_2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/station/ship"+ GameInformation.INSTANCE.getStationId()+"_3.png"))));
        Animation idleAnimation = new Animation(2f, frames);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);

        stationEntity = new StationEntity(gameManager);

        holeImage = new Image(new Texture(files.internal("sprites/background/hole.png")));
        holeImage.setSize(80,30);
        holeImage.setPosition(Constants.V_WIDTH/2-holeImage.getWidth()/2, 70);

       // backgroundImage = new Image(new Texture(files.internal("sprites/background/rockValley2.png")));
       // backgroundImage.setSize(0.6f,0.6f);
       // backgroundImage.setPosition(-180,-(backgroundImage.getHeight()-550)*0.6f);
        Texture backgroundTexture = new Texture(files.internal("sprites/background/background_island.png"));
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backgroundImage = new Image(backgroundTexture);
        //backgroundImage.setSize(1980,1140);
        backgroundImage.setSize(1190,684);
        backgroundImage.setPosition(-backgroundImage.getWidth()/2,Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT);

        undergroundImage = new Image(new Texture(files.internal("sprites/background/underground.png")));
        undergroundImage.setSize(backgroundImage.getWidth(), 3000);
        undergroundImage.setPosition(-backgroundImage.getWidth()/2,-undergroundImage.getHeight()+Constants.PLAYSCREEN_MENU_BUTTON_HEIGHT);

        skyImage = new Image(new Texture(files.internal("sprites/background/sky.png")));
        skyImage.setPosition(-Constants.V_WIDTH/2,0);
        skyImage.scaleBy(0.6f);

        scrollingBackground = new ScrollingBackground("sprites/background/cl.png");

        // Gestion des calques
        stage.addActor(layer0GraphicObject);
        stage.addActor(layer1GraphicObject);
        stage.addActor(layer2GraphicObject);

        // Ajout des objets dans les calques
        layer0GraphicObject.addActor(skyImage);
        layer0GraphicObject.addActor(scrollingBackground);
        layer0GraphicObject.addActor(undergroundImage);
        layer0GraphicObject.addActor(backgroundImage);
//        layer0GraphicObject.addActor(holeImage);

        layer1GraphicObject.addActor(stationEntity.beamActor);
        layer1GraphicObject.addActor(tapActor);
        layer2GraphicObject.addActor(stationEntity.stationActor);

        if (GameInformation.INSTANCE.isFirstPlay()) {
            displayTutorial();
        }

        gameManager.initialiseGame();
    }

    // member variables:
    float timeToCameraZoomTarget, cameraZoomTarget, cameraZoomOrigin, cameraZoomDuration;

    private void zoomTo (float newZoom, float duration){
        cameraZoomOrigin = camera.zoom;
        cameraZoomTarget = newZoom;
        timeToCameraZoomTarget = cameraZoomDuration = duration;
    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameManager.updateLogic(delta);
        hud.updateGoldLabel();
        stage.act();
        stage.draw();
        spriteBatch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.draw();

        //DEBUG
        //todo bloquer rezoom si deja zoom max, pareil max dezoom
        if (timeToCameraZoomTarget > 0) {
            timeToCameraZoomTarget -= delta;
            float progress = timeToCameraZoomTarget < 0 ? 1 : 1f - timeToCameraZoomTarget / cameraZoomDuration;
            camera.zoom = Interpolation.pow3Out.apply(cameraZoomOrigin, cameraZoomTarget, progress);
            // reajust y position
            if (cameraZoomTarget == 2) {
                camera.position.y = Interpolation.pow3Out.apply(285, 0, progress);
            } else {
                camera.position.y = Interpolation.pow3Out.apply(0, 285, progress);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            zoomTo(1,3);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            zoomTo(2,3);        }

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
        goldLabel = new Label(gameManager.largeMath.getDisplayValue(GameInformation.INSTANCE.getGenGoldActive(), GameInformation.INSTANCE.getGenCurrencyActive()),new Label.LabelStyle(AssetManager.INSTANCE.getFont(), Constants.NORMAL_LABEL_COLOR));
        goldLabel.setPosition(150,goldLabelPosition[gLPPointer]);

        hud.screAnimatedActor.restartAnimation();

        if (gLPPointer<goldLabelPosition.length-1){
            gLPPointer++;
        } else {
            gLPPointer=0;
        }
        layer2GraphicObject.addActor(goldLabel);
        goldLabel.addAction(Actions.sequence(
                Actions.fadeIn(1f),
                Actions.fadeOut(2f),
                Actions.removeActor(goldLabel)
        ));
        goldLabel.addAction(Actions.moveTo(150+random.nextInt(100+textAnimMinX)-textAnimMinX,250,3f));

        // Augmente vitesse en fonction delai avec derniere touche
        if (System.currentTimeMillis()-lastTouch >= 500f) {
            //stationEntity.beamActor.decreaseSpeed(0.08f);
            consecutivTouch=0;
        } else {
            consecutivTouch++;
        }
        lastTouch=System.currentTimeMillis();

        if (consecutivTouch >= 10) {
            //stationEntity.beamActor.increaseSpeed(0.041f);
//            stationEntity.beamActor.setWidth(stationEntity.beamActor.getWidth()+10);
//            stationEntity.beamActor.setX(stationEntity.beamActor.getX()-5);
            //Flammes animation drill object
        }
    }

    /**
     * Animation du jeu au touche critique
     * @param value: valeur du critique
     */
    public void processCriticalHit(float value) {
        stationEntity.beamActor.clearActions();
        stationEntity.beamActor.addAction(Actions.sequence(
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        stationEntity.beamActor.setActiveAnimation("critical");
                    }
                }),
                Actions.show(),
                Actions.fadeIn(0.5f),
                Actions.fadeOut(0.5f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        stationEntity.beamActor.setActiveAnimation("idle");
                    }
                }),
                Actions.hide()));
        hud.animateCritical();

        goldLabel.setText("CRITICAL "+String.valueOf(value));
        goldLabel.setColor(Constants.CRITICAL_LABEL_COLOR);
    }

    /**
     * Animation du jeu au touche normal
     */
    public void processNormalHit() {
        if (stationEntity.beamActor.getCurrentAnimation().getFrameDuration() <= 0.04f) {
          //  beamMaxSpeedImage.clearActions();
          //  beamMaxSpeedImage.addAction(Actions.sequence(
          //          Actions.show(),
          //          Actions.fadeIn(0.5f),
          //          Actions.fadeOut(0.5f),
          //          Actions.hide()));
        } else {
            stationEntity.beamActor.clearActions();
            stationEntity.beamActor.addAction(Actions.sequence(
            Actions.show(),
            Actions.fadeIn(0.5f),
            Actions.fadeOut(0.5f),
            Actions.hide()));

    }}



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
        hud.dispose();
        Gdx.app.debug("PlayScreen","saveInformation");
        gameManager.largeMath.formatGameInformation();
        GameInformation.INSTANCE.saveInformation();
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************

    public GameManager getGameManager() {
        return gameManager;
    }

    public Hud getHud() {
        return hud;
    }

    public Group getLayer1GraphicObject() {
        return layer1GraphicObject;
    }

    public StationEntity getStationEntity() {
        return stationEntity;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }


    public Group getLayer2GraphicObject() {
        return layer2GraphicObject;
    }
}
