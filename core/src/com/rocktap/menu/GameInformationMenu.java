package com.rocktap.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rocktap.manager.GameManager;

/**
 * Created by Skronak on 21/08/2017.
* TODO a recharger en live
 */
public class GameInformationMenu extends AbstractMenu {

    private Label stationLvlLabel;
    private Label goldLabel;
    private Label activGoldGenLabel;
    private Label passivGoldGenLabel;
    private Label criticalHitLabel;
    private Label gameTimeLabel;
    private Label tapNumberLabel;

    public GameInformationMenu(GameManager gameManager) {
        super(gameManager);
        stationLvlLabel = new Label(String.valueOf(gameManager.getGameInformation().getStationId()), skin);
        goldLabel = new Label(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getCurrentGold(), gameManager.getGameInformation().getCurrency()), skin);
        activGoldGenLabel = new Label(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getGenGoldActive(), gameManager.getGameInformation().getGenCurrencyActive()), skin);
        passivGoldGenLabel = new Label(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getGenGoldPassive(), gameManager.getGameInformation().getGenCurrencyPassive()), skin);
        criticalHitLabel = new Label(String.valueOf("x "+gameManager.getGameInformation().getCriticalRate()), skin);
        gameTimeLabel = new Label(String.valueOf((gameManager.getGameInformation().getTotalGameTime()/ (1000*60*60)) + " hours"), skin);
        tapNumberLabel = new Label(String.valueOf(gameManager.getGameInformation().getTotalTapNumber()), skin);
        menutable.add(new Label("GAME INFORMATION ", skin)).left().top();
        menutable.row();
        menutable.row();
        menutable.add(new Label("Station lvl: ", skin)).left();
        menutable.add(stationLvlLabel).left();
        menutable.row();
        menutable.add(new Label("Current gold: ", skin)).left();
        menutable.add(goldLabel).left();
        menutable.row();
        menutable.add(new Label("Active Gold generation: ", skin)).left();
        menutable.add(activGoldGenLabel).left();
        menutable.row();
        menutable.add(new Label("Passive gold generation: ", skin)).left();
        menutable.add(passivGoldGenLabel).left();
        menutable.row();
        menutable.add(new Label("Critical rate: ", skin)).left();
        menutable.add(criticalHitLabel).left();
        menutable.row();
        menutable.add(new Label("Total Game time: ", skin)).left();
        menutable.add(gameTimeLabel).left();
        menutable.row();
        menutable.add(new Label("Total tap number: ", skin)).left();
        menutable.add(tapNumberLabel).left();
        menutable.row();

        menutable.setVisible(false);
    }

    public void update() {
        stationLvlLabel.setText(String.valueOf(gameManager.getGameInformation().getStationId()));
        goldLabel.setText(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getCurrentGold(), gameManager.getGameInformation().getCurrency()));
        activGoldGenLabel.setText(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getGenGoldActive(), gameManager.getGameInformation().getGenCurrencyActive()));
        passivGoldGenLabel.setText(gameManager.getLargeMath().getDisplayValue(gameManager.getGameInformation().getGenGoldPassive(), gameManager.getGameInformation().getGenCurrencyPassive()));
        criticalHitLabel.setText("x "+gameManager.getGameInformation().getCriticalRate());
        gameTimeLabel.setText(String.valueOf((gameManager.getGameInformation().getTotalGameTime()/ (1000*60*60)) + " hours"));
        tapNumberLabel.setText(String.valueOf(gameManager.getGameInformation().getTotalTapNumber()));
    }

//*****************************************************
//                  GETTER & SETTER
// ****************************************************
}
