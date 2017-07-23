package com.rocktap.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.menu.UpgradeMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Click listner des boutons d'upgrade
 * // TODO: modifie le fichier de pref l'etat du compte courant
 */
public class CustomInputUpgradeListener extends ClickListener {

    private UpgradeMenu upgradeMenu;
    private String detail, cost, power, processor, titre;

    public CustomInputUpgradeListener(UpgradeMenu upgradeMenu, String titre, String detail, String cost, String power, String processor) {
        this.upgradeMenu = upgradeMenu;
        this.detail = detail;
        this.cost = cost;
        this.power = power;
        this.processor = processor;
        this.titre = titre;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log("LISTENER","LISTENER");
        this.upgradeMenu.getDetailGold().setText(cost);
        this.upgradeMenu.getDetailDetail().setText(detail);
        this.upgradeMenu.getDetailPower().setText(power);
        this.upgradeMenu.getDetailProcessor().setText(processor);
        this.upgradeMenu.getDetailTitre().setText(titre);
        return false;
    }

}
