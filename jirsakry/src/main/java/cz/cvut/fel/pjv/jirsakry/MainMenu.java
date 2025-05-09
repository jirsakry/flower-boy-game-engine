package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu {
    private final Scene mainMenuScene;
    private final Scene gameScene;
    private final Stage stage;
    private final GameWorld gameWorld;

    public MainMenu(Scene gameScene, Stage stage, GameWorld gameWorld) {
        this.gameScene = gameScene;
        this.stage = stage;
        this.gameWorld = gameWorld;

        Button startGameButton = new Button("Play");
        Button exitGameButton = new Button("Exit");
        Text startGameText = new Text();
        startGameText.setText("FLOWER BOY");
//        startGameText.
        startGameButton.setOnAction(e -> {
            stage.setScene(gameScene);
            gameWorld.setGameState(GameState.PLAYING);
        });
        exitGameButton.setOnAction(e -> {
            stage.close();
        });
        VBox vBox = new VBox(startGameButton, exitGameButton);
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.CENTER);
        mainMenuScene = new Scene(vBox, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);
    }

    public void show() {
        stage.setScene(mainMenuScene);
    }
}
