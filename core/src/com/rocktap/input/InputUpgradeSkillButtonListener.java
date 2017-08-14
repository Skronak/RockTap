package com.rocktap.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocktap.menu.UpgradeMenu;

/**
 * Created by Skronak on 29/01/2017.
 * Listener du bouton UPGRADE d'un module
 */
public class InputUpgradeSkillButtonListener extends ClickListener {

    private UpgradeMenu upgradeMenu;

    public InputUpgradeSkillButtonListener(UpgradeMenu upgradeMenu) {
        this.upgradeMenu = upgradeMenu;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        // Met a jour le skill
        switch (upgradeMenu.getCurrentSelection()) {
            case 1:
                this.upgradeMenu.getGameManager().getGameInformation().setUpgradeLevel1(this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel1() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel1());
                if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel1()==1) {
                    this.upgradeMenu.getUpgradeButton1().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable1());
                    this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
                }
                break;
            case 2:
                this.upgradeMenu.getGameManager().getGameInformation().setUpgradeLevel2(this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel2() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel2());
                if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel2()==1) {
                    this.upgradeMenu.getUpgradeButton2().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable2());
                    this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
                }
                break;
            case 3:
                this.upgradeMenu.getGameManager().getGameInformation().setUpgradeLevel3(this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel3() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel3());
                if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel3()==1) {
                    this.upgradeMenu.getUpgradeButton3().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable3());
                    this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
                }
                break;
            case 4:
                this.upgradeMenu.getGameManager().getGameInformation().setUpgradeLevel4(this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel4() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel4());
                if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel4()==1) {
                    this.upgradeMenu.getUpgradeButton4().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable4());
                    this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
                }
                break;
            case 5:
                this.upgradeMenu.getGameManager().getGameInformation().setUpgradeLevel5(this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel5() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel5());
                if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel5()==1) {
                    this.upgradeMenu.getUpgradeButton5().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable5());
                    this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
                }
                break;
            case 6:
                this.upgradeMenu.getGameManager().getGameInformation().setUpgradeLevel6(this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel6() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel6());
                if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel6()==1) {
                    this.upgradeMenu.getUpgradeButton6().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable6());
                    this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
                }
                break;
            case 7:
                this.upgradeMenu.getGameManager().getGameInformation().setUpgradeLevel7(this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel7() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel7());
                if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel7()==1) {
                    this.upgradeMenu.getUpgradeButton7().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable7());
                    this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
                }
                break;
            case 8:
                this.upgradeMenu.getGameManager().getGameInformation().setUpgradeLevel8(this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel8() + 1);
                this.upgradeMenu.getDetailTitre().setText("Upgrade level: "+ this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel8());
                if (this.upgradeMenu.getGameManager().getGameInformation().getUpgradeLevel8()==1) {
                    this.upgradeMenu.getUpgradeButton8().getStyle().imageUp =(this.upgradeMenu.getUpgradeDrawable8());
                    this.upgradeMenu.getGameManager().getStationActor().loadUpgrade();
                }
                break;
            default:
                break;
        }

        this.upgradeMenu.getGameManager().getGameInformation().saveInformation();
        return false;
    }


}
