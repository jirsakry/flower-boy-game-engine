package cz.cvut.fel.pjv.jirsakry.view;

import cz.cvut.fel.pjv.jirsakry.model.*;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

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
        return new Canvas(backgroundWidth, backgroundHeight);
    }

    public void drawBackground(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(images.get(ImageID.BACKGROUND), 0, 0);
    }

    public void render(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
        clearCanvas(canvas);
        gc.setFont(new Font("Impact", 30));
        gc.fillText(gameWorld.getTimer().getFormattedTime(), 1000, 30);
        renderFlowers();
        renderCharacter();
        renderLevel();

        DebugOverlay.draw(gc, gameWorld, gameWorld.getLevel0().getLevelData());
    }

    private void renderFlowers() {
        for(Flower flower : gameWorld.getFlowers()){
            if(!(flower.isCollected())) {
                gc.drawImage(images.get(ImageID.FLOWER), flower.getX(), flower.getY());
            }
        }
    }

    private void renderCharacter() {
        boolean facingRight = gameWorld.getPlayer().getPlayerState() == PlayerState.FACING_RIGHT;

        int invertedImageOffset;
        if (facingRight) { // centering the inverted animations
            invertedImageOffset = 0;
        }else{
            invertedImageOffset = 4;
        }

        if(gameWorld.getPlayer().isInAir()) { // jumping
            animManager.updateAnimationTick(animManager.getJumpLength());
            if (gameWorld.getPlayer().getVelocityY() < 1) { // jumping up
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
    }

    private void renderLevel(){
        for (Platform platform : gameWorld.getLevel0().getPlatforms()){
            gc.drawImage(images.get(ImageID.GRASS), platform.getX(), platform.getY());
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
