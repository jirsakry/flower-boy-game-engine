package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.controller.Controller;
import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import cz.cvut.fel.pjv.jirsakry.scenes.HowToPlay;
import cz.cvut.fel.pjv.jirsakry.scenes.MainMenu;
import cz.cvut.fel.pjv.jirsakry.scenes.PauseMenu;
import cz.cvut.fel.pjv.jirsakry.scenes.WinScreen;
import cz.cvut.fel.pjv.jirsakry.view.GameRenderer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.logging.Logger;


public class FlowerBoy extends Application {
    private GameWorld gameWorld;
    private Controller controller;
    private GameRenderer gameRenderer;

    private static final Logger LOGGER = Logger.getLogger(FlowerBoy.class.getName());


    // game loop
    private static final long SECOND = 1_000_000_000L;
    private long lastCheck = 0;

    @Override
    public void start(Stage stage){
        gameWorld = new GameWorld();
        controller = new Controller(gameWorld);
        gameRenderer = new GameRenderer(gameWorld);
        gameRenderer.loadImages();
        Canvas canvas = gameRenderer.createCanvas();
        gameRenderer.loadAnimations();

        gameWorld.init();

        StackPane gameRoot = new StackPane(canvas);
        Scene gameScene = new Scene(gameRoot, gameRenderer.getBackgroundWidth(), gameRenderer.getBackgroundHeight());

        MainMenu mainMenu = new MainMenu(gameScene, stage, gameWorld);
        PauseMenu pauseMenu = new PauseMenu(gameRoot, gameWorld);
        HowToPlay howToPlay = new HowToPlay(stage, gameWorld);
        WinScreen winScreen = new WinScreen(stage, gameWorld, mainMenu);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            controller.handleKeyPressed(event);
            if(event.getCode() == KeyCode.ESCAPE){
                pauseMenu.toggle();
            }
        });
        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> controller.handleKeyReleased(event));

        mainMenu.showMainMenu();

        stage.setTitle("Flower Boy");
        stage.getIcons().add(new Image("/icon_flower.png"));
        stage.setResizable(false);
        stage.requestFocus();
        stage.show();

        lastCheck = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() { // generated game loop
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                switch (gameWorld.getGameState()) {
                    case WIN -> {
                        winScreen.showWinScreen();
                        winScreen.updateNextLevelButton();
                    }
                    case MAIN_MENU -> {
                        mainMenu.showMainMenu();
                    }
                    case HOW_TO_PLAY -> howToPlay.showHowToPlay();
                    case PLAYING -> {
                        stage.setScene(gameScene);
                    }
                }
                if (now - lastUpdate >= SECOND / 60) { // 60 UPS and FPS
                    if(gameWorld.getGameState() != GameState.PAUSED) {
                        gameWorld.update();
                        controller.update();
                        lastUpdate = now;
                        gameRenderer.render(canvas);
                    }
                }
            }
        };timer.start();


    }

    public static void main(String[] args) {
        launch();
    }
}
