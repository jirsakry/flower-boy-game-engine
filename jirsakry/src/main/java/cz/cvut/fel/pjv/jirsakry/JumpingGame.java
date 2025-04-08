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

import java.io.FileNotFoundException;

public class JumpingGame extends Application {
    private GameWorld  gameWorld;
    private Controller controller;
    private GameRenderer gameRenderer;

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        gameWorld = new GameWorld();
        Controller controller = new Controller(gameWorld);
        gameRenderer = new GameRenderer(gameWorld);
        gameRenderer.loadImages();
        Canvas canvas = gameRenderer.createCanvas();
        gameRenderer.drawBackground(canvas);


        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                gameRenderer.clearCanvas(canvas); //TODO: Very temporary, i hope
                if (lastTime++ > 10){
                    gameWorld.update();
                    lastTime = 0;
                }
                gameRenderer.render(canvas);
            }
        };
        timer.start();

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