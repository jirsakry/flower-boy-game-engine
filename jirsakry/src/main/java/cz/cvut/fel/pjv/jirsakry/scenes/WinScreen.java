package cz.cvut.fel.pjv.jirsakry.scenes;

import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Represents the win screen displayed after completing a level.
 */
public class WinScreen {
    private final Stage stage;
    private final Scene winScreen;
    private final GameWorld gameWorld;
    private final Text timeMessage;
    private final MainMenu mainMenu;

    private final Button nextLevelButton;

    /**
     * Creates the WinScreen scene.
     *
     * @param stage     The primary stage of the application.
     * @param gameWorld The game world instance to manage game state.
     * @param mainMenu  The main menu scene handler.
     */
    public WinScreen(Stage stage, GameWorld gameWorld, MainMenu mainMenu) {
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

        nextLevelButton = new Button("NEXT LEVEL");
        nextLevelButton.setStyle("-fx-background-color: #ccc803;");
        nextLevelButton.setPrefSize(180, 40);
        nextLevelButton.setAlignment(Pos.CENTER);
        nextLevelButton.setOnAction(e -> {
            gameWorld.newGame();
        });

        Button backButton = new Button("BACK TO MENU");
        backButton.setStyle("-fx-background-color: #ccc803;-fx-padding: 15");
        backButton.setPrefSize(150, 40);
        backButton.setAlignment(Pos.CENTER);
        backButton.setOnMouseClicked(e -> {
            gameWorld.setGameState(GameState.MAIN_MENU);
            gameWorld.newGame();
            gameWorld.setCurrentLevelIndex(0);
            stage.setScene(mainMenu.getMainMenuScene());
        });

        Button goodByeButton = new Button("GOOD BYE!");
        goodByeButton.setStyle("-fx-background-color: #ccc803;");
        goodByeButton.setPrefSize(120, 40);
        goodByeButton.setOnAction(e -> {
            stage.close();
        });

        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(winMessage, timeMessage, nextLevelButton,backButton, goodByeButton);

        winScreen = new Scene(root, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);
    }

    /**
     * Displays the win screen, updates the game state.
     */
    public void showWinScreen() {
        stage.setScene(winScreen);
        gameWorld.setGameState(GameState.WIN);
        timeMessage.setText("YOU DID IT IN:" + gameWorld.getTimer().getFormattedTime());
    }

    /**
     * Updates the "Next Level" button state and text depending on whether
     * there are more levels available.
     */
    public void updateNextLevelButton(){
        if(gameWorld.getCurrentLevelIndex() > gameWorld.getLevels().size() - 1){
            nextLevelButton.setDisable(true);
            nextLevelButton.setText("NO MORE LEVELS :(");
        }
    }

    public Scene getWinScreen() {
        return winScreen;
    }

}
