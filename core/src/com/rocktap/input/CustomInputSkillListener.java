package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.menu.UpgradeMenu;
import com.rocktap.utils.Constants;

/**
 * Created by Skronak on 29/01/2017.
 * Listener de l'upgrade des skill
 * // TODO: modifie le fichier de pref l'etat du compte courant
 */
public class CustomInputSkillListener extends ClickListener {

    private UpgradeMenu upgradeMenu;

    public CustomInputSkillListener(UpgradeMenu upgradeMenu) {
        this.upgradeMenu = upgradeMenu;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        // Met a jour le skill
        switch (upgradeMenu.getCurrentSelection()) {
            case 1:
                this.upgradeMenu.getGameManager().getAccountInformation().setUpgradeLevel1(this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel1() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel1());
                if (this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel1()==1) {
                    this.upgradeMenu.getUpgradeButton1().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable9());
                    this.upgradeMenu.playUnlockSkillAnimation();
                }
                break;
            case 2:
                this.upgradeMenu.getGameManager().getAccountInformation().setUpgradeLevel2(this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel2() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel2());
                if (this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel2()==1) {
                    this.upgradeMenu.getUpgradeButton2().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable10());
                }
                break;
            case 3:
                this.upgradeMenu.getGameManager().getAccountInformation().setUpgradeLevel3(this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel3() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel3());
                if (this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel3()==1) {
                    this.upgradeMenu.getUpgradeButton3().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable11());
                }
                break;
            case 4:
                this.upgradeMenu.getGameManager().getAccountInformation().setUpgradeLevel7(this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel4() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel4());
                if (this.upgradeMenu.getGameManager().getAccountInformation().getUpgradeLevel4()==1) {
                    this.upgradeMenu.getUpgradeButton4().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable12());
                }
                break;
            default:
                break;
        }

        this.upgradeMenu.getGameManager().getAccountInformation().saveInformation();
        return false;
    }


}
