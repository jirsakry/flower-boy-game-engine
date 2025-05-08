package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.controller.Controller;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import cz.cvut.fel.pjv.jirsakry.view.GameRenderer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class JumpingGame extends Application {
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
        lastCheck = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) { // TODO: Very temporary, i hope...
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
                    gameWorld.update();
                    controller.update();
                    upsCount++;
                    lastUpdate = now;
                }

                // Vykreslení (co nejčastěji)
                gameRenderer.render(canvas);
                fpsCount++;
            }

        };timer.start();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, gameRenderer.getBackgroundWidth(), gameRenderer.getBackgroundHeight());

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {controller.handleKeyPressed(event);});
        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {controller.handleKeyReleased(event);});


        stage.setTitle("Jumping Game");
        stage.setResizable(false);
        stage.requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}