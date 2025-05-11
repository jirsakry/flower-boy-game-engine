package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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

        VBox mainMenuRoot = new VBox();
        mainMenuRoot.setSpacing(15);
        mainMenuRoot.setAlignment(Pos.CENTER);

        Text titleText = new Text("FLOWER BOY");
        titleText.setFont(new Font("Constantia", 40));

        Button playButton = new Button("Play");
        playButton.setStyle("-fx-background-color: #ccc803;");
        playButton.setPrefSize(150, 40);
        playButton.setAlignment(Pos.CENTER);
        playButton.setOnAction(e -> {
            startGame();
            gameWorld.getTimer().start();
            System.out.println("timer started");
        });

        Button howToPlayButton = new Button("How to play");
        howToPlayButton.setStyle("-fx-background-color: #ccc803;");
        howToPlayButton.setPrefSize(150, 40);
        howToPlayButton.setAlignment(Pos.CENTER);
        howToPlayButton.setOnAction(e -> {
            System.out.println("Já nic nedělám");
        });

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #ccc803;");
        exitButton.setPrefSize(150, 40);
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setOnAction(e -> stage.close());

        mainMenuRoot.getChildren().addAll(titleText, playButton,howToPlayButton, exitButton);
        mainMenuScene = new Scene(mainMenuRoot, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);
    }

    private void startGame() {
        stage.setScene(gameScene);
        gameWorld.setGameState(GameState.PLAYING);
        gameWorld.newGame();
    }

    public void showMainMenu() {
        gameWorld.setGameState(GameState.MAIN_MENU);
        stage.setScene(mainMenuScene);
    }

    public Scene getMainMenuScene() {
        return mainMenuScene;
    }
}
