package cz.cvut.fel.pjv.jirsakry.view;

import cz.cvut.fel.pjv.jirsakry.model.DebugOverlay;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import cz.cvut.fel.pjv.jirsakry.model.Platform;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EnumMap;
import java.util.Map;

public class GameRenderer {
    private final AnimManager animManager;
    private final GameWorld gameWorld;
    private GraphicsContext gc;
    private final Map<ImageID, Image> images;
    private double backgroundWidth;
    private double backgroundHeight;


    public GameRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        images = new EnumMap<>(ImageID.class);
        animManager = new AnimManager(images);
    }

    public Canvas createCanvas(){
        backgroundWidth = images.get(ImageID.BACKGROUND).getWidth();
        backgroundHeight = images.get(ImageID.BACKGROUND).getHeight();
        Canvas canvas = new Canvas(backgroundWidth, backgroundHeight);
        return canvas;
    }

    public void drawBackground(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(images.get(ImageID.BACKGROUND), 0, 0);
    }

    public void render(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
//        gc.drawImage(images.get(ImageID.BACKGROUND), 0, 0);
        clearCanvas(canvas);
        animManager.updateAnimationTick();
        gc.drawImage(
                animManager.getIdleAnim()[animManager.getAnimFrame()],
                gameWorld.getPlayer().getX(), gameWorld.getPlayer().getY()
        );
        renderLevel(gc);
        renderHitBox(gc);

        DebugOverlay.draw(gc, gameWorld.getPlayer(), gameWorld.getLevel0().getLevelData());
    }

    private void renderLevel(GraphicsContext gc){
        for (Platform platform : gameWorld.getLevel0().getPlatforms()){
            gc.setStroke(Color.BLACK);
            gc.strokeRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
        }
    }

    private void renderHitBox(GraphicsContext gc){
        BoundingBox playerHitBox = gameWorld.getPlayer().getHitBox();

        gc.setStroke(Color.RED);
        gc.strokeRect(playerHitBox.getMinX(), playerHitBox.getMinY(), playerHitBox.getWidth(), playerHitBox.getHeight());
    }

    public void clearCanvas(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, backgroundWidth, backgroundHeight);
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

    public void loadAnimations() {
        animManager.loadAnimations();
    }

    public double getBackgroundWidth() {
        return backgroundWidth;
    }

    public double getBackgroundHeight() {
        return backgroundHeight;
    }

}
