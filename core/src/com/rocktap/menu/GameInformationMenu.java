package com.rocktap.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rocktap.manager.GameManager;

/**
 * Created by Skronak on 21/08/2017.
* TODO a recharger en live
 */
public class GameInformationMenu extends AbstractMenu {

    public GameInformationMenu(GameManager gameManager) {
        super(gameManager);
        menutable.add(new Label("GAME INFORMATION ", skin)).left().top();
        menutable.row();
        menutable.row();
        menutable.add(new Label("Station lvl: ", skin)).left();
        menutable.add(new Label(String.valueOf(gameManager.getGameInformation().getStationId()), skin)).left();
        menutable.row();
        menutable.add(new Label("Current gold: ", skin)).left();
        menutable.add(new Label(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getCurrentGold(), gameManager.getGameInformation().getCurrency()), skin)).left();
        menutable.row();
        menutable.add(new Label("Active Gold generation: ", skin)).left();
        menutable.add(new Label(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getGenGoldActive(), gameManager.getGameInformation().getGenCurrencyActive()), skin)).left();
        menutable.row();
        menutable.add(new Label("Passive gold generation: ", skin)).left();
        menutable.add(new Label(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getGenGoldPassive(), gameManager.getGameInformation().getGenCurrencyPassive()), skin)).left();
        menutable.row();
        menutable.add(new Label("Critical rate: ", skin)).left();
        menutable.add(new Label(String.valueOf("x "+gameManager.getGameInformation().getCriticalRate()), skin)).left();
        menutable.row();
        menutable.add(new Label("Total Game time: ", skin)).left();
        menutable.add(new Label(String.valueOf((gameManager.getGameInformation().getTotalGameTime()/ (1000*60*60)) + " hours"), skin)).left();
        menutable.row();
        menutable.add(new Label("Total tap number: ", skin)).left();
        menutable.add(new Label(String.valueOf(gameManager.getGameInformation().getTotalTapNumber()), skin)).left();
        menutable.row();

        menutable.setVisible(false);
    }

    public void toggleOn(){

    }
//*****************************************************
//                  GETTER & SETTER
// ****************************************************
}
