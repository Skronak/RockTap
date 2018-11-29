package com.rocktap.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.rocktap.Animation.AnimatedActor;
import com.rocktap.Animation.AnimatedBaseActor;
import com.rocktap.entity.GameInformation;
import com.rocktap.entity.ModuleElementDTO;
import com.rocktap.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Gdx.files;

public class StationActor extends AnimatedBaseActor {

    private float stationAnimationTimer;
    private boolean stationAnimationUp; // indique si animation de montée ou descente

    private Image beamCriticalImage;
    private Image beamMaxSpeedImage;
    private AnimatedActor beamActor;
    private List<ModuleElementDTO> moduleToDraw;


    public StationActor(){
        stationAnimationTimer = 0f;
        stationAnimationUp = false;

        beamCriticalImage = new Image(new Texture(files.internal("sprites/beamCritical.png")));
        beamCriticalImage.setBounds(155,50, 32,getY()-30); // position de l'image
        beamCriticalImage.setVisible(false);
        beamMaxSpeedImage = new Image(new Texture(files.internal("sprites/bMaxSpeed.png")));
        beamMaxSpeedImage.setBounds(155,50, 32,getY()-30); // position de l'image
        beamMaxSpeedImage.setVisible(false);

        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b1.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b2.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b3.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b4.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b5.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b6.png"))));
        frames.add(new TextureRegion(new Texture(Gdx.files.internal("sprites/b7.png"))));
        beamActor = new AnimatedActor(157,50, 28,(int)this.getY()-30, 0.2f, frames, Animation.PlayMode.LOOP);
        beamActor.setVisible(false);
    }

    /**
     * Liste des upgrades a afficher
     * @return
     */
    public List<ModuleElementDTO> loadUpgrade() {
        this.moduleToDraw = new ArrayList<ModuleElementDTO>();
        for (int i = 0; i< GameInformation.INSTANCE.getUpgradeLevelList().size(); i++) {
            // Pour les upgrade 1-8, si lvl i > 0 alors ajoute dans liste a afficher
            if (GameInformation.INSTANCE.getUpgradeLevelList().get(i) > 0) {
                ModuleElementDTO moduleElementDTO = gameManager.getAssetManager().getModuleElementList().get(i);
                moduleElementDTO.setTextureRegion(new TextureRegion(new Texture(Gdx.files.internal(("sprites/upgrade/"+moduleElementDTO.getLevel().get(gameManager.getGameInformation().getUpgradeLevelList().get(i)).getSprite())))));
                moduleToDraw.add(moduleElementDTO);
            }
        }
        return moduleToDraw;
    }

    public void updatePosition(){
    // station animation
        if(stationAnimationTimer >= 0.2f) {
        if (this.getY() >= Constants.STATION_ANIMATION_MAX_ALTITUDE && stationAnimationUp) {
            stationAnimationUp = false;
        }
        if (this.getY() <= Constants.STATION_ANIMATION_MIN_ALTITUDE && !stationAnimationUp) {
            stationAnimationUp = true;
        }

        if (stationAnimationUp) {
            this.moveBy(0,1);
        } else {
            this.moveBy(0,-1);
        }
        stationAnimationTimer = 0f;
    }
  }

    @Override
    public void act(float deltaTime)
    {
        super.act(deltaTime);
        stationAnimationTimer += deltaTime;
        updatePosition();
    }




}
