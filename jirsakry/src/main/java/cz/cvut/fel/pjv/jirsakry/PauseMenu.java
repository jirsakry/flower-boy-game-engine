package cz.cvut.fel.pjv.jirsakry;

import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class PauseMenu {
    private final VBox pauseRoot;
    private final StackPane gameRoot;
    private final GameWorld gameWorld;
    private final MainMenu mainMenu;

    public PauseMenu(StackPane gameRoot, GameWorld gameWorld, MainMenu mainMenu) {
        this.gameRoot = gameRoot;
        this.gameWorld = gameWorld;
        this.mainMenu = mainMenu;

        pauseRoot = new VBox();
        pauseRoot.setSpacing(10);
        pauseRoot.setAlignment(Pos.CENTER);
        pauseRoot.setVisible(false);

        Button resumeButton = new Button("Resume Game");
        resumeButton.setOnAction(e -> resumeGame());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(e -> {
            goToMainMenu();
            gameWorld.getTimer().reset();
            System.out.println("timer reset");
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
        mainMenu.showMainMenu();
    }

    public void toggle() {
        if (gameWorld.getGameState() == GameState.PAUSED) {
            resumeGame();
            gameWorld.getTimer().start();
            System.out.println("timer resumed");
        } else if (gameWorld.getGameState() == GameState.PLAYING) {
            gameWorld.setGameState(GameState.PAUSED);
            pauseRoot.setVisible(true);
            gameRoot.setOpacity(0.7);
            gameWorld.getTimer().stop();
            System.out.println("timer paused");
        }
    }
}
