package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.controller.Controller;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import cz.cvut.fel.pjv.jirsakry.view.GameRenderer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JumpingGame extends Application {
    private GameWorld  gameWorld;
    private Controller controller;
    private GameRenderer gameRenderer;

    @Override
    public void start(Stage stage){
        gameWorld = new GameWorld();
        controller = new Controller(gameWorld);
        gameRenderer = new GameRenderer(gameWorld);
        gameRenderer.loadImages();
        Canvas canvas = gameRenderer.createCanvas();
        gameRenderer.loadAnimations();

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            private int frameCount = 0;
            private long lastFrameTime = 0;
            private double currentFps = 0;
            private int counter = 0;
            @Override
            public void handle(long now) {
//                gameRenderer.clearCanvas(canvas); //TODO: Very temporary, i hope...
                gameRenderer.render(canvas);
                if(counter++ > 10){
                    gameWorld.update();
                    counter = 0;
                }

//                if(lastUpdate == 0){ // generated gameloop
//                    lastUpdate = now;
//                    return;
//                }
//
//                double deltaTime = (now - lastUpdate) * 0.000_000_000_1;
//                lastUpdate = now;
//
//                frameCount++;
//                if(now - lastFrameTime >= 1_000_000_000){ // every second
//                    currentFps = frameCount;
//                    frameCount = 0;
//                    lastFrameTime = now;
//                    System.out.println("DeltaTime: " + deltaTime);
//                }
//
//                gameRenderer.render(canvas);
//                if(deltaTime > 0.016){ // should corespond to 60 times per second
//                    gameWorld.update();
//                }
            }
        };timer.start();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, gameRenderer.getBackgroundWidth(), gameRenderer.getBackgroundHeight());

        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            controller.handleInput(keyEvent);
        });

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