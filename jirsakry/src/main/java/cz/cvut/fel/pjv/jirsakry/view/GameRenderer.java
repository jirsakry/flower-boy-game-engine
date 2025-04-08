package cz.cvut.fel.pjv.jirsakry.view;

import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import cz.cvut.fel.pjv.jirsakry.model.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EnumMap;
import java.util.Map;

public class GameRenderer {
    private final GameWorld gameWorld;
    private GraphicsContext gc;
    private final Map<ImageID, Image> images; // = new EnumMap<>(ImageID.class);
    private final Player player;

    private double backgroundWidth;
    private double backgroundHeight;

    public GameRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        player= gameWorld.getPlayer();
        images = new EnumMap<>(ImageID.class);
    }

    public void drawBackground(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(images.get(ImageID.BACKGROUND), 0, 0);
    }

    public void render(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(images.get(ImageID.CHARACTER), player.getX(), player.getY());

    }

    public Canvas createCanvas(){
        backgroundWidth = images.get(ImageID.BACKGROUND).getWidth();
        backgroundHeight = images.get(ImageID.BACKGROUND).getHeight();
        Canvas canvas = new Canvas(backgroundWidth, backgroundHeight);
        return canvas;
    }

    public void loadImages() {
        for  (ImageID imageID : ImageID.values()) {
            Image image = null;
            try {
                image = new Image(new FileInputStream("src/main/resources/" + imageID.getFileName()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imageID.setWidth(image.getWidth());
            imageID.setHeight(image.getHeight());
            images.put(imageID, image);
        }
    }

    public double getBackgroundWidth() {
        return backgroundWidth;
    }

    public double getBackgroundHeight() {
        return backgroundHeight;
    }

    public void clearCanvas(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, backgroundWidth, backgroundHeight);
    }
}
