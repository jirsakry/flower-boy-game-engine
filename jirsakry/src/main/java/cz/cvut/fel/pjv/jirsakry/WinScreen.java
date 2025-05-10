package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinScreen {
    private final Stage stage;
    private final Scene winScreen;
    private final GameWorld gameWorld;
    private final Text timeMessage;

    public WinScreen(Stage stage, GameWorld gameWorld) {
        this.stage = stage;
        this.gameWorld = gameWorld;
        VBox root = new VBox();


        Text winMessage = new Text("YOU COLLECTED ALL FLOWERS, GOOD JOB!");
        winMessage.setFont(new Font(40));
        winMessage.setFill(Color.DARKGREEN);

        timeMessage = new Text();
        timeMessage.setFont(new Font(35));
        timeMessage.setFill(Color.DARKGREEN);

        Button goodByeButton = new Button("GOOD BYE!");
        goodByeButton.setStyle("-fx-background-color: #ccc803;");
        goodByeButton.setOnAction(e -> {
            stage.close();
        });

        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(winMessage, timeMessage, goodByeButton);

        winScreen = new Scene(root, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);
    }

    public void showWinScreen() {
        stage.setScene(winScreen);
        timeMessage.setText("YOU DID IT IN:" + gameWorld.getTimer().getFormattedTime());
    }

    public Scene getWinScreen() {
        return winScreen;
    }
}
