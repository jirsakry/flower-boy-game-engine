package cz.cvut.fel.pjv.jirsakry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JumpingGame extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Jumping Game");
        stage.setResizable(false);

        Canvas canvas = new Canvas(800, 500); // background size eventually



        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root,800, 500); // background size eventually
        stage.setTitle("Jumping Game");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}