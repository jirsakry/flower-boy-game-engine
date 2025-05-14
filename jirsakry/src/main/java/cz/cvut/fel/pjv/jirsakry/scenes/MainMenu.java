package cz.cvut.fel.pjv.jirsakry.scenes;

import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Represents the main menu scene of the game.
 */
public class MainMenu {
    private final Scene mainMenuScene;
    private final Scene gameScene;
    private final Stage stage;
    private final GameWorld gameWorld;

    /**
     * Constructs the MainMenu scene with all the text and buttons.
     *
     * @param gameScene The scene for the game itself.
     * @param stage     The primary stage of the application.
     * @param gameWorld The game world instance to manage game state.
     */
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
        playButton.setOnMouseClicked(e -> {
            startGame();
        });

        Button howToPlayButton = new Button("How to play");
        howToPlayButton.setStyle("-fx-background-color: #ccc803;");
        howToPlayButton.setPrefSize(150, 40);
        howToPlayButton.setAlignment(Pos.CENTER);
        howToPlayButton.setOnMouseClicked(e -> {
            gameWorld.setGameState(GameState.HOW_TO_PLAY);
        });

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #ccc803;");
        exitButton.setPrefSize(150, 40);
        exitButton.setAlignment(Pos.CENTER);
        exitButton.setOnMouseClicked(e -> stage.close());

        mainMenuRoot.getChildren().addAll(titleText, playButton, howToPlayButton, exitButton);
        mainMenuScene = new Scene(mainMenuRoot, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);
    }

    /**
     * Starts the game by switching to the game scene, updating the game state
     * Starts the game timer.
     */
    private void startGame() {
        stage.setScene(gameScene);
        gameWorld.setGameState(GameState.PLAYING);
        gameWorld.newGame();
        gameWorld.getTimer().start();
    }

    /**
     * Displays the main menu scene and updates the game state.
     */
    public void showMainMenu() {
        stage.setScene(mainMenuScene);
        gameWorld.setGameState(GameState.MAIN_MENU);
    }

    /**
     * Returns the main menu scene.
     *
     * @return The scene displaying the main menu.
     */
    public Scene getMainMenuScene() {
        return mainMenuScene;
    }
}
