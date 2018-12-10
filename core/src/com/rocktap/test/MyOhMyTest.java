package com.rocktap.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.rocktap.utils.Constants;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Sine;

public class MyOhMyTest extends Game {

    private TweenManager tweenManager = new TweenManager();
    private Sprite grassSprite1, grassSprite2,grassSprite3;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public void create() {
        float screenW = Gdx.graphics.getWidth();
        float screenH = Gdx.graphics.getHeight();
        float w = 50;
        float h = w * screenH / screenW;

        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        batch = new SpriteBatch();

        grassSprite1= new Sprite(new TextureRegion(new Texture(Gdx.files.internal("test/grass1.png"))));
        grassSprite2= new Sprite(new TextureRegion(new Texture(Gdx.files.internal("test/grass2.png"))));
        grassSprite3= new Sprite(new TextureRegion(new Texture(Gdx.files.internal("test/grass3.png"))));
        grassSprite1.setPosition(0, 50);
        grassSprite2.setPosition(0, 50);
        grassSprite3.setPosition(0, 50);

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        Tween.call(windCallback).start(tweenManager);
    }

    private final TweenCallback windCallback = new TweenCallback() {
        @Override
        public void onEvent(int type, BaseTween<?> source) {
            float d = MathUtils.random() * 0.5f + 0.5f;    // duration
            float t = -0.5f * grassSprite1.getHeight();    // amplitude

            Timeline.createParallel()
                    .push(Tween.to(grassSprite1, SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT).repeatYoyo(1, 0).setCallback(windCallback))
                    .push(Tween.to(grassSprite2, SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT).delay(d/3).repeatYoyo(1, 0))
                    .push(Tween.to(grassSprite3, SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT).delay(d/3*2).repeatYoyo(1, 0))
                    .start(tweenManager);
        }
    };

    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // batch.setProjectionMatrix(camera.combined);
        batch.begin();
        grassSprite1.draw(batch);
        grassSprite2.draw(batch);
        grassSprite3.draw(batch);
        batch.end();
        tweenManager.update(Gdx.graphics.getDeltaTime());
    }

}
