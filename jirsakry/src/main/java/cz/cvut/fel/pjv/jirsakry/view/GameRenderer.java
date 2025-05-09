package cz.cvut.fel.pjv.jirsakry.view;

import cz.cvut.fel.pjv.jirsakry.model.DebugOverlay;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import cz.cvut.fel.pjv.jirsakry.model.Platform;
import cz.cvut.fel.pjv.jirsakry.model.PlayerState;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
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

    private int invertedImageOffset = 0;


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
        clearCanvas(canvas);

        boolean facingRight = gameWorld.getPlayer().getPlayerState() == PlayerState.FACING_RIGHT ? true : false;
        if (facingRight) {
            invertedImageOffset = 0;
        }else{
            invertedImageOffset = 4;
        }

        if(gameWorld.getPlayer().isInAir()) { // jumping
            animManager.updateAnimationTick(animManager.getJumpLength());
            if (gameWorld.getPlayer().getVelocityY() < 0) { // jumping up
                if (animManager.getAnimFrame() < animManager.getJumpUpAnim().length) {
                    gc.drawImage(
                            facingRight ? animManager.getJumpUpAnim()[animManager.getAnimFrame()]
                                        : animManager.getJumpUpAnimMirrored()[animManager.getAnimFrame()],
                            gameWorld.getPlayer().getX() - invertedImageOffset, gameWorld.getPlayer().getY()
                    );
                }
            } else { // falling
                if (animManager.getAnimFrame() < animManager.getJumpDownAnim().length) {
                    gc.drawImage(
                            facingRight ? animManager.getJumpDownAnim()[animManager.getAnimFrame()]
                                        : animManager.getJumpDownAnimMirrored()[animManager.getAnimFrame()],
                            gameWorld.getPlayer().getX() - invertedImageOffset, gameWorld.getPlayer().getY()
                    );
                }
            }
        } else {
            if(gameWorld.getPlayer().isMoving()) { // running
                animManager.updateAnimationTick(animManager.getRunLength());
                if (animManager.getAnimFrame() < animManager.getRunAnim().length) {
                    gc.drawImage(
                            facingRight ? animManager.getRunAnim()[animManager.getAnimFrame()]
                                        : animManager.getRunAnimMirrored()[animManager.getAnimFrame()],
                            gameWorld.getPlayer().getX() - invertedImageOffset, gameWorld.getPlayer().getY()
                    );
                }
            } else { // idle
                animManager.updateAnimationTick(animManager.getIdleLength());
                if (animManager.getAnimFrame() < animManager.getIdleAnim().length) {
                    gc.drawImage(
                            facingRight ? animManager.getIdleAnim()[animManager.getAnimFrame()]
                                        : animManager.getIdleAnimMirrored()[animManager.getAnimFrame()],
                            gameWorld.getPlayer().getX() - invertedImageOffset, gameWorld.getPlayer().getY()
                    );
                }
            }
        }

        renderLevel();
        DebugOverlay.draw(gc, gameWorld, gameWorld.getLevel0().getLevelData());
    }

    private void renderLevel(){
        for (Platform platform : gameWorld.getLevel0().getPlatforms()){
            gc.setFill(Color.DARKGREEN);
            gc.fillRect(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
        }
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
