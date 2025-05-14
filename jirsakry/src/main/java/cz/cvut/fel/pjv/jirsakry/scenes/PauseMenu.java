package cz.cvut.fel.pjv.jirsakry.scenes;

import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Represents the PauseMenu overlay for pausing and resuming the game.
 */
public class PauseMenu {
    private final VBox pauseRoot;
    private final StackPane gameRoot;
    private final GameWorld gameWorld;

    /**
     * Creates the PauseMenu overlay for pausing and resuming the game.
     *
     * @param gameRoot The root StackPane of the game scene.
     * @param gameWorld The game world instance to manage game state.
     */
    public PauseMenu(StackPane gameRoot, GameWorld gameWorld) {
        this.gameRoot = gameRoot;
        this.gameWorld = gameWorld;

        pauseRoot = new VBox();
        pauseRoot.setSpacing(10);
        pauseRoot.setAlignment(Pos.CENTER);
        pauseRoot.setVisible(false);

        Button resumeButton = new Button("Resume Game");
        resumeButton.setOnAction(e -> {
            toggle();
        });

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(e -> {
            goToMainMenu();
        });

        pauseRoot.getChildren().addAll(resumeButton, mainMenuButton);
        gameRoot.getChildren().add(pauseRoot);
    }

    private void resumeGame() {
        gameWorld.setGameState(GameState.PLAYING);
        pauseRoot.setVisible(false);
        gameRoot.setOpacity(1.0);
    }

    private void goToMainMenu() {
        pauseRoot.setVisible(false);
        gameRoot.setOpacity(1.0);
        gameWorld.setGameState(GameState.MAIN_MENU);
    }

    /**
     * Toggles the pause menu visibility and game state.
     */
    public void toggle() {
        if (gameWorld.getGameState() == GameState.PAUSED) {
            resumeGame();
            gameWorld.getTimer().start();
        } else if (gameWorld.getGameState() == GameState.PLAYING) {
            gameWorld.setGameState(GameState.PAUSED);
            pauseRoot.setVisible(true);
            gameRoot.setOpacity(0.7);
            gameWorld.getTimer().stop();
        }
    }
}
