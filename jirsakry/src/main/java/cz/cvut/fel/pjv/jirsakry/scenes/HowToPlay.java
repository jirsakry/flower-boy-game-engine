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

public class HowToPlay { // should have used scene builder
    private final Scene howToPlayScene;
    private final GameWorld gameWorld;
    private final Stage stage;
    public HowToPlay(Stage stage, GameWorld gameWorld) {
        this.stage = stage;
        this.gameWorld = gameWorld;

        HBox mainRoot = new HBox();
        mainRoot.setAlignment(Pos.CENTER);

        VBox rootLeft = createRootLeft();
        VBox rootRight = createRootRight();
        mainRoot.getChildren().addAll(rootLeft, rootRight);

        VBox rootFoo = new VBox();
        rootFoo.setAlignment(Pos.CENTER);
        rootFoo.setSpacing(20);
        Text doubleJumpText = new Text();
        doubleJumpText.setText("Also you can double jump");
        doubleJumpText.setFont(new Font("Constantia", 20));

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ccc803;");
        backButton.setPrefSize(150, 40);
        backButton.setOnAction(e -> {
            gameWorld.setGameState(GameState.MAIN_MENU);
        });
        rootFoo.getChildren().addAll(doubleJumpText, backButton);

        VBox absoluteRoot = new VBox();
        absoluteRoot.setAlignment(Pos.CENTER);
        absoluteRoot.getChildren().addAll(mainRoot, rootFoo);

        howToPlayScene = new Scene(absoluteRoot, GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT);
    }

    private VBox createRootLeft() {
        VBox rootLeft = new VBox();
        rootLeft.setSpacing(20);
        rootLeft.setAlignment(Pos.CENTER_LEFT);
        rootLeft.setPadding(new Insets(100));

        Image flowerImage = new Image("/flower_pot.png");
        ImageView flowerImageView = new ImageView(flowerImage);
        flowerImageView.setFitWidth(43 * 2);
        flowerImageView.setFitHeight(48 * 2);

        Text descriptionText = new Text("COLLECT ALL FLOWERS!");
        descriptionText.setFont(new Font("Constantia", 20));
        descriptionText.setTextAlignment(TextAlignment.CENTER);


        rootLeft.getChildren().addAll(flowerImageView, descriptionText);
        return rootLeft;
    }

    private VBox createRootRight() {
        VBox rootRight = new VBox();
        rootRight.setSpacing(20);
        rootRight.setAlignment(Pos.CENTER_RIGHT);
        rootRight.setPadding(new Insets(100));

        Image cactusImage = new Image("/cactus.png");
        ImageView flowerImageView = new ImageView(cactusImage);
//        flowerImageView.
        flowerImageView.setFitWidth(32 * 2);
        flowerImageView.setFitHeight(32 * 2);

        Text descriptionText = new Text("AVOID THE DEADLY CACTUS!");
        descriptionText.setFont(new Font("Constantia", 20));
        descriptionText.setTextAlignment(TextAlignment.CENTER);

        rootRight.getChildren().addAll(flowerImageView, descriptionText);
        return rootRight;
    }

    public Scene getHowToPlayScene() {
        return howToPlayScene;
    }

    public void showHowToPlay(){
        stage.setScene(howToPlayScene);
        gameWorld.setGameState(GameState.HOW_TO_PLAY);
    }
}
