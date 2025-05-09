package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.controller.Controller;
import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import cz.cvut.fel.pjv.jirsakry.view.GameRenderer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class FlowerBoy extends Application {
    private GameWorld gameWorld;
    private Controller controller;
    private GameRenderer gameRenderer;


    // game loop
    private static final long SECOND = 1_000_000_000L;
    private int fps = 0;
    private int ups = 0;
    private int fpsCount = 0;
    private int upsCount = 0;
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

        Canvas winCanvas = new Canvas(gameRenderer.getBackgroundWidth(), gameRenderer.getBackgroundHeight());
        StackPane winRoot = new StackPane(winCanvas);
        Scene winScreen = new Scene(winRoot, gameRenderer.getBackgroundWidth(), gameRenderer.getBackgroundHeight());

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, controller::handleKeyPressed);
        gameScene.addEventHandler(KeyEvent.KEY_RELEASED, controller::handleKeyReleased);

        MainMenu mainMenu = new MainMenu(gameScene, stage, gameWorld);

        lastCheck = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() { // generated game loop
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if(gameWorld.getGameState() == GameState.MAIN_MENU){
                    mainMenu.show();
                }
                if(gameWorld.getGameState() == GameState.WIN) {
                    if(stage.getScene() != winScreen) {
                        stage.setScene(winScreen);
                        GraphicsContext gc = winCanvas.getGraphicsContext2D();
                        gc.setFill(Color.DARKGREEN);
                        gc.setFont(new Font(35));
                        gc.fillText("YOU COLLECTED ALL THE FLOWERS, GOOD JOB!",
                                400,
                                GameWorld.SCREEN_HEIGHT / 2);
                    }
                }
                if (now - lastCheck >= SECOND) {
                    fps = fpsCount;
                    ups = upsCount;
                    fpsCount = 0;
                    upsCount = 0;
                    lastCheck = now;
                    System.out.println("FPS: " + fps + " | UPS: " + ups);
                }

                // Update logiky hry (fixní časový krok)
                if (now - lastUpdate >= SECOND / 120) { // 120 UPS
                    if(gameWorld.getGameState() != GameState.PAUSED) {
                        gameWorld.update();
                        controller.update();
                        upsCount++;
                        lastUpdate = now;
                    }
                }

                // Vykreslení (co nejčastěji)
                gameRenderer.render(canvas);
                fpsCount++;
            }

        };timer.start();


        stage.setTitle("Flower Boy");
        stage.getIcons().add(new Image("/Flower 9 - ORANGE.png"));
        stage.setResizable(false);
        stage.requestFocus();
        stage.setScene(gameScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}