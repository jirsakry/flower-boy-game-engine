package cz.cvut.fel.pjv.jirsakry.scenes;

import cz.cvut.fel.pjv.jirsakry.model.GameState;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * Creates how to play scene.
 */
public class HowToPlay { // should have used scene builder
    private final Scene howToPlayScene;
    private final GameWorld gameWorld;
    private final Stage stage;

    /**
     * Constructs the HowToPlay scene with all the text and buttons.
     *
     * @param stage     The primary stage of the application.
     * @param gameWorld The game world instance to manage game state.
     */
    public HowToPlay(Stage stage, GameWorld gameWorld) {
        this.stage = stage;
        this.gameWorld = gameWorld;

        HBox mainRoot = new HBox();
        mainRoot.setAlignment(Pos.CENTER);

        VBox rootLeft = createRootLeft();
        VBox rootCenter = createRootCenter();
        VBox rootRight = createRootRight();
        mainRoot.setSpacing(10);
        mainRoot.getChildren().addAll(rootLeft, rootCenter, rootRight);

        VBox rootLower = createLowerSection(gameWorld);

        VBox absoluteRoot = new VBox();
        absoluteRoot.setAlignment(Pos.CENTER);
        absoluteRoot.getChildren().addAll(mainRoot, rootLower);

        howToPlayScene = new Scene(absoluteRoot, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);
    }

    private static VBox createLowerSection(GameWorld gameWorld) {
        VBox rootLower = new VBox();
        rootLower.setAlignment(Pos.CENTER);
        rootLower.setSpacing(20);

        Text controlsText = new Text();
        controlsText.setText("LEFT and RIGHT for movement and SPACEBAR for jump");
        controlsText.setFont(new Font("Constantia", 20));

        Text doubleJumpText = new Text();
        doubleJumpText.setText("Also you can double jump");
        doubleJumpText.setFont(new Font("Constantia", 20));

        Text debuggingText = new Text("Press F3 in game for debugging");
        debuggingText.setFont(new Font("Constantia", 20));

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ccc803;");
        backButton.setPrefSize(150, 40);
        backButton.setOnMouseClicked(e -> {
            gameWorld.setGameState(GameState.MAIN_MENU);
        });

        rootLower.getChildren().addAll(controlsText, doubleJumpText, debuggingText, backButton);
        return rootLower;
    }

    private VBox createRootCenter() {
        VBox rootCenter = new VBox();
        rootCenter.setSpacing(10);
        rootCenter.setAlignment(Pos.CENTER);
        rootCenter.setPadding(new Insets(100));

        Image shieldImage = new Image("/shield.png");
        ImageView shieldImageView = new ImageView(shieldImage);
        shieldImageView.setFitWidth(32 * 2);
        shieldImageView.setFitHeight(32 * 2);

        Text descriptionText = new Text("COLLECT PROTECTIVE SHIELDS TO AVOID CACTUS DAMAGE");
        descriptionText.setFont(new Font("Constantia", 20));
        descriptionText.setTextAlignment(TextAlignment.CENTER);
        descriptionText.setWrappingWidth(250);


        rootCenter.getChildren().addAll(shieldImageView, descriptionText);
        return rootCenter;
    }

    private VBox createRootLeft() {
        VBox rootLeft = new VBox();
        rootLeft.setSpacing(10);
        rootLeft.setAlignment(Pos.CENTER_LEFT);
        rootLeft.setPadding(new Insets(100));

        Image flowerImage = new Image("/flower_pot.png");
        ImageView flowerImageView = new ImageView(flowerImage);
        flowerImageView.setFitWidth(43 * 2);
        flowerImageView.setFitHeight(48 * 2);

        Text descriptionText = new Text("COLLECT ALL FLOWERS!");
        descriptionText.setFont(new Font("Constantia", 20));
        descriptionText.setTextAlignment(TextAlignment.LEFT);
        descriptionText.setWrappingWidth(200);


        rootLeft.getChildren().addAll(flowerImageView, descriptionText);
        return rootLeft;
    }

    private VBox createRootRight() {
        VBox rootRight = new VBox();
        rootRight.setSpacing(10);
        rootRight.setAlignment(Pos.CENTER_RIGHT);
        rootRight.setPadding(new Insets(100));

        Image cactusImage = new Image("/cactus.png");
        ImageView flowerImageView = new ImageView(cactusImage);
        flowerImageView.setFitWidth(32 * 2);
        flowerImageView.setFitHeight(32 * 2);

        Text descriptionText = new Text("AVOID THE DEADLY CACTUS!");
        descriptionText.setFont(new Font("Constantia", 20));
        descriptionText.setTextAlignment(TextAlignment.CENTER);
        descriptionText.setWrappingWidth(200);

        rootRight.getChildren().addAll(flowerImageView, descriptionText);
        return rootRight;
    }

    /**
     * Displays the HowToPlay scene and updates the game state.
     */
    public void showHowToPlay() {
        stage.setScene(howToPlayScene);
        gameWorld.setGameState(GameState.HOW_TO_PLAY);
    }
}

