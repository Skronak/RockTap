package com.rocktap.object;

/**
 * Created by Skronak on 01/02/2017.
 * Menu d'update
 * // TODO: super menu desactivant l'input listener, gerer un state?
 */
public class OLD_ModuleMenu {
}
//extends AbstractMenu {
//    private Table upgradeTable;
//    private Table upgradeCostTable;
//    private Label detailGold;
//    private Label detailDescription;
//    private Label detailLevel;
//    private Label detailTitre;
//    private ModuleManager moduleManager;
//    private Texture squareTexture;
//    private Image goldIcon;
//    private Image timeIcon;
//
//    private Image squareImage1;
//    private Image squareImage2;
//    private Image squareImage3;
//    private Image squareImage4;
//    private Image squareImage5;
//
//    // indique le skill actuellement selectionne
//    private int currentSelection;
//    private List<ImageButton> moduleButtonList;
//    private List<Drawable> moduleDrawableUpList;
//    private List<Drawable> moduleDrawableDownList;
//
//    public OLD_ModuleMenu(GameManager gameManager) {
//        super(gameManager);
////        this.moduleManager = new ModuleManager(new OLD_UpgradeModuleMenu(gameManager), gameManager);
//        customizeMenuTable();
//
//        currentSelection = 1;         // selection 1 module par defaut
////        moduleManager.updateModuleInformation(currentSelection);
//    }
//
//    public void customizeMenuTable() {
//        // Contenu du menu
//        parentTable.add(new Label("UPGRADE", skin)).expandX().top().colspan(2).height(50);
//        parentTable.row();
//        // Partie gauche
//        parentTable.add(initScrollingModuleSelection()).left().fill();
//        // Partie droite
//        initUpgradeDetailsTable();
////        parentTable.add(initUpgradeDetailsTable()).fillY().width(100);
//
////        parentTable.debug();
//    }
//
//    /**
//     * Methode d'initialisation des modules disponibles a
//     * l'upgrade
//     * @return
//     */
//    private ScrollPane initScrollingModuleSelection() {
//        VerticalGroup scrollContainerVG = new VerticalGroup();
//        scrollContainerVG.space(5f);
//        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
//        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
//        ScrollPane pane = new ScrollPane(scrollContainerVG, paneStyle);
//        pane.setScrollingDisabled(true, false);
//
//        //Couleur de fond du menu
//        Pixmap pm1 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//        pm1.setColor(Color.BLUE);
//        pm1.fill();
//
//        // Definition drawables possibles pour les boutons
//        moduleDrawableUpList = new ArrayList<Drawable>();
//        moduleDrawableDownList = new ArrayList<Drawable>();
//        moduleButtonList = new ArrayList<ImageButton>();
//        for (int i = 0; i < this.gameManager.getAssetManager().getUpgradeFile().size(); i++) {
//            moduleDrawableUpList.add(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/" + this.gameManager.getAssetManager().getUpgradeFile().get(i).getIcon())))));
//            moduleDrawableDownList.add(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("sprites/menu/" + this.gameManager.getAssetManager().getUpgradeFile().get(i).getIconDisabled())))));
//        }
//
//        // Generation des upgrades de modules disponible
//        for (int i = 0; i < gameManager.getAssetManager().getUpgradeFile().size(); i++) {
//            goldIcon = new Image(new Texture(Gdx.files.internal("icons/gold+.png")));
//            timeIcon = new Image(new Texture(Gdx.files.internal("icons/speed+.png")));
//
//            Table moduleElementTable = new Table();
//            moduleElementTable.setHeight(30);
//
//            Image moduleIconImage = new Image(moduleDrawableUpList.get(i));
//            moduleElementTable.add(moduleIconImage);
//            VerticalGroup vg = new VerticalGroup();
//            Table col2 = new Table();
//            moduleElementTable.add(vg);
//            vg.addActor(new Label(gameManager.getAssetManager().getUpgradeFile().get(i).getTitle(), skin));
//            vg.addActor(col2);
//            col2.add(goldIcon).size(20,20).left();
//            col2.add(new Label("+ 50A", skin)).left();
//            col2.add(timeIcon).size(20,20).left();
//            col2.add(new Label("- 1 min", skin)).left();
//
////            moduleElementTable.add(moduleElementVG);
//            moduleElementTable.add(new TextButton("500 A",skin)).fill();
//            Button button = new Button(moduleElementTable,skin);
//            button.setColor(Color.BLUE);
//            button.setWidth(200);
//            button.setHeight(50);
//            button.debug();
//            scrollContainerVG.addActor(button);
//        }
//
//
//        Gdx.app.debug("OLD_ModuleMenu", "Generation des boutons de Module terminee");
//
//        return pane;
//    }
//
//
//    public Table initUpgradeDetailsTable() {
//        // Initialisation des icon cost
//        squareTexture = new Texture(Gdx.files.internal("sprites/menu/square.png"));
//        squareImage1 = new Image(squareTexture);
//        squareImage1.setSize(10, 10);
//        squareImage1.setY(-(squareImage1.getHeight() / 2));
//        squareImage2 = new Image(squareTexture);
//        squareImage2.setSize(10, 10);
//        squareImage2.setY(-(squareImage2.getHeight() / 2));
//        squareImage3 = new Image(squareTexture);
//        squareImage3.setSize(10, 10);
//        squareImage3.setY(-(squareImage3.getHeight() / 2));
//        squareImage4 = new Image(squareTexture);
//        squareImage4.setSize(10, 10);
//        squareImage4.setY(-(squareImage4.getHeight() / 2));
//        squareImage5 = new Image(squareTexture);
//        squareImage5.setSize(10, 10);
//        squareImage5.setY(-(squareImage5.getHeight() / 2));
//
//        detailTitre = new Label("", skin);
//        detailDescription = new Label("EFFECT", skin);
//        detailDescription.setWrap(true);
//        detailDescription.pack();
//        detailGold = new Label("", skin);
//        detailLevel = new Label("", skin);
//        TextButton upgraderButton = new TextButton("UPGRADER", skin);
////        OLD_InputUpgradeSkillButtonListener customInputSkillListener = new OLD_InputUpgradeSkillButtonListener(this);
////        upgraderButton.addListener(customInputSkillListener);
//        Table detailTable = new Table();
//
//        // Detail d'un module
//        upgradeCostTable = new Table();
////        detailTable.debug();
//        detailTable.add(detailTitre).top().height(50);//.width(Value.percentWidth(.1F, parentTable));
////        detailTable.row();
////        detailTable.add(detailDescription).left();
//        detailTable.row();
//        Label lab = new Label("LEVEL: ", skin);
////        detailTable.add(new Label("LEVEL: ", skin)).left();
//        lab.setFontScale(0.75f, 0.75f);
//
//        detailTable.add(lab);
//        detailTable.add(detailLevel);//.width(50);
//        detailTable.row();
//        detailTable.add(new Label("GEN: ", skin)).left();
//        detailTable.row();
//        detailTable.add(new Label("COST: ", skin)).left();
//        detailTable.add(detailGold);//.width(50);
//        detailTable.row();
//        detailTable.row();
//        detailTable.add(upgraderButton).expandY().center();
//
//        return detailTable;
//    }
//
////            if (gameManager.getGameInformation().getUpgradeLevelList().get(i) == 0) {
////                moduleButtonList.add(new ImageButton(moduleDrawableDownList.get(i)));
////            } else {
////                moduleButtonList.add(new ImageButton(moduleDrawableUpList.get(i)));
////            }
////            moduleButtonList.get(i).addListener(new OLD_InputUpgradeMenuButtonListener(this, i));
//    //           upgradeTable.add(moduleButtonList.get(i)).pad(2);
//
////*****************************************************
////                  GETTER & SETTER
//// ****************************************************
//
//    public Table getUpgradeTable() {
//        return upgradeTable;
//    }
//
//    public void setUpgradeTable(Table upgradeTable) {
//        this.upgradeTable = upgradeTable;
//    }
//
//    public Table getUpgradeCostTable() {
//        return upgradeCostTable;
//    }
//
//    public void setUpgradeCostTable(Table upgradeCostTable) {
//        this.upgradeCostTable = upgradeCostTable;
//    }
//
//    public Label getDetailGold() {
//        return detailGold;
//    }
//
//    public void setDetailGold(Label detailGold) {
//        this.detailGold = detailGold;
//    }
//
//    public Label getDetailDescription() {
//        return detailDescription;
//    }
//
//    public void setDetailDescription(Label detailDescription) {
//        this.detailDescription = detailDescription;
//    }
//
//    public Label getDetailLevel() {
//        return detailLevel;
//    }
//
//    public void setDetailLevel(Label detailLevel) {
//        this.detailLevel = detailLevel;
//    }
//
//    public Label getDetailTitre() {
//        return detailTitre;
//    }
//
//    public void setDetailTitre(Label detailTitre) {
//        this.detailTitre = detailTitre;
//    }
//
//    public ModuleManager getModuleManager() {
//        return moduleManager;
//    }
//
//    public void setModuleManager(ModuleManager moduleManager) {
//        this.moduleManager = moduleManager;
//    }
//
//    public Texture getSquareTexture() {
//        return squareTexture;
//    }
//
//    public void setSquareTexture(Texture squareTexture) {
//        this.squareTexture = squareTexture;
//    }
//
//    public Image getSquareImage1() {
//        return squareImage1;
//    }
//
//    public void setSquareImage1(Image squareImage1) {
//        this.squareImage1 = squareImage1;
//    }
//
//    public Image getSquareImage2() {
//        return squareImage2;
//    }
//
//    public void setSquareImage2(Image squareImage2) {
//        this.squareImage2 = squareImage2;
//    }
//
//    public Image getSquareImage3() {
//        return squareImage3;
//    }
//
//    public void setSquareImage3(Image squareImage3) {
//        this.squareImage3 = squareImage3;
//    }
//
//    public Image getSquareImage4() {
//        return squareImage4;
//    }
//
//    public void setSquareImage4(Image squareImage4) {
//        this.squareImage4 = squareImage4;
//    }
//
//    public Image getSquareImage5() {
//        return squareImage5;
//    }
//
//    public void setSquareImage5(Image squareImage5) {
//        this.squareImage5 = squareImage5;
//    }
//
//    public int getCurrentSelection() {
//        return currentSelection;
//    }
//
//    public void setCurrentSelection(int currentSelection) {
//        this.currentSelection = currentSelection;
//    }
//
//    public List<ImageButton> getModuleButtonList() {
//        return moduleButtonList;
//    }
//
//    public void setModuleButtonList(List<ImageButton> moduleButtonList) {
//        this.moduleButtonList = moduleButtonList;
//    }
//
//    public List<Drawable> getModuleDrawableUpList() {
//        return moduleDrawableUpList;
//    }
//
//    public void setModuleDrawableUpList(List<Drawable> moduleDrawableUpList) {
//        this.moduleDrawableUpList = moduleDrawableUpList;
//    }
//
//    public List<Drawable> getModuleDrawableDownList() {
//        return moduleDrawableDownList;
//    }
//
//    public void setModuleDrawableDownList(List<Drawable> moduleDrawableDownList) {
//        this.moduleDrawableDownList = moduleDrawableDownList;
//    }
//}
