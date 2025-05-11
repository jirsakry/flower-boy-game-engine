package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.Insets;
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
    private final Scene mainMenu;

    public WinScreen(Stage stage, GameWorld gameWorld, Scene mainMenu) {
        this.stage = stage;
        this.gameWorld = gameWorld;
        this.mainMenu = mainMenu;
        VBox root = new VBox();


        Text winMessage = new Text("YOU COLLECTED ALL FLOWERS, GOOD JOB!");
        winMessage.setFont(new Font("Constantia", 40));
        winMessage.setFill(Color.DARKGREEN);

        timeMessage = new Text();
        timeMessage.setFont(new Font("Constantia",35));
        timeMessage.setFill(Color.DARKGREEN);

        Button backButton = new Button("BACK TO MENU");
        backButton.setStyle("-fx-background-color: #ccc803;");
        backButton.setPrefSize(150, 40);
        backButton.setAlignment(Pos.CENTER);
        backButton.setPadding(new Insets(15));
        backButton.setOnAction(e -> {
            gameWorld.setGameState(GameState.MAIN_MENU);
            gameWorld.newGame();
            stage.setScene(mainMenu);
        });

        Button goodByeButton = new Button("GOOD BYE!");
        goodByeButton.setStyle("-fx-background-color: #ccc803;");
        goodByeButton.setPrefSize(100, 40);
        goodByeButton.setOnAction(e -> {
            stage.close();
        });

        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(winMessage, timeMessage, backButton, goodByeButton);

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
