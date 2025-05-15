package cz.cvut.fel.pjv.jirsakry.view;

import cz.cvut.fel.pjv.jirsakry.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EnumMap;
import java.util.Map;

/**
 * Handles rendering of game scene.
 */
public class GameRenderer {
    private final AnimManager animManager;
    private final GameWorld gameWorld;
    private final Map<ImageID, Image> images;
    private GraphicsContext gc;
    private double backgroundWidth;
    private double backgroundHeight;

    /**
     * Creates a GameRenderer for the game world.
     *
     * @param gameWorld The game world to render.
     */
    public GameRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        images = new EnumMap<>(ImageID.class);
        animManager = new AnimManager(images);
    }

    /**
     * Creates a new Canvas sized to the background image.
     *
     * @return The created Canvas.
     */
    public Canvas createCanvas() {
        backgroundWidth = images.get(ImageID.BACKGROUND).getWidth();
        backgroundHeight = images.get(ImageID.BACKGROUND).getHeight();
        return new Canvas(backgroundWidth, backgroundHeight);
    }

    /**
     * Renders the entire game scene onto the given canvas.
     *
     * @param canvas The canvas to render the game on.
     */
    public void render(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        clearCanvas(canvas);
        gc.setFont(new Font("Constantia", 30));
        gc.fillText(gameWorld.getTimer().getFormattedTime(), 1000, 30);

        renderCharacter();
        renderLevel();

        DebugOverlay.draw(gc, gameWorld);
    }

    private void renderLevel() {
        for (Platform platform : gameWorld.getCurrentLevel().getPlatforms()) { // platforms
            gc.drawImage(images.get(ImageID.GRASS), platform.getX(), platform.getY());
        }

        for (Flower flower : gameWorld.getCurrentLevel().getFlowers()) { // flowers
            if (!(flower.isCollected())) {
                gc.drawImage(images.get(ImageID.FLOWER), flower.getX(), flower.getY());
            }
        }

        for (Cactus cactus : gameWorld.getCurrentLevel().getCacti()) { // cacti
            if (!(cactus.isDestroyed())) {
                gc.drawImage(images.get(ImageID.CACTUS), cactus.getX(), cactus.getY());
            } else {
                gc.drawImage(images.get(ImageID.CACTUS_DESTROYED), cactus.getX(), cactus.getY());
            }
        }

        for (Shield shield : gameWorld.getCurrentLevel().getShields()) { // shields
            if (!(shield.isCollected())) {
                gc.drawImage(images.get(ImageID.SHIELD), shield.getX(), shield.getY());
            }
        }
    }

    private void renderCharacter() {
        boolean facingRight = gameWorld.getPlayer().getPlayerState() == PlayerState.FACING_RIGHT;

        if (gameWorld.getPlayer().isHoldingShield()) {
            gc.drawImage(images.get(ImageID.SHIELD), gameWorld.getPlayer().getX() - 3, gameWorld.getPlayer().getY() - 2);
        }

        int invertedImageOffset;
        if (facingRight) { // centering the inverted animations
            invertedImageOffset = 0;
        } else {
            invertedImageOffset = 4;
        }

        if (gameWorld.getPlayer().getPlayerState() == PlayerState.DEATH) { // death
            animManager.updateAnimationTick(animManager.getDeathLength());
            renderCharacterDeath(facingRight, invertedImageOffset);
        } else if (gameWorld.getPlayer().isInAir()) { // jumping
            animManager.updateAnimationTick(animManager.getJumpLength());
            renderCharacterJump(facingRight, invertedImageOffset);
        } else {
            if (gameWorld.getPlayer().isMoving()) { // running
                animManager.updateAnimationTick(animManager.getRunLength());
                renderCharacterRun(facingRight, invertedImageOffset);
            } else { // idle
                animManager.updateAnimationTick(animManager.getIdleLength());
                renderCharacterIdle(facingRight, invertedImageOffset);
            }
        }
    }

    private void renderCharacterIdle(boolean facingRight, int invertedImageOffset) {
        if (animManager.getAnimFrame() < animManager.getIdleAnim().length) {
            gc.drawImage(
                    facingRight ? animManager.getIdleAnim()[animManager.getAnimFrame()]
                            : animManager.getIdleAnimMirrored()[animManager.getAnimFrame()],
                    gameWorld.getPlayer().getX() - invertedImageOffset, gameWorld.getPlayer().getY()
            );
        }
    }

    private void renderCharacterRun(boolean facingRight, int invertedImageOffset) {
        if (animManager.getAnimFrame() < animManager.getRunAnim().length) {
            gc.drawImage(
                    facingRight ? animManager.getRunAnim()[animManager.getAnimFrame()]
                            : animManager.getRunAnimMirrored()[animManager.getAnimFrame()],
                    gameWorld.getPlayer().getX() - invertedImageOffset, gameWorld.getPlayer().getY()
            );
        }
    }

    private void renderCharacterJump(boolean facingRight, int invertedImageOffset) {
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
    }

    private void renderCharacterDeath(boolean facingRight, int invertedImageOffset) {
        gc.drawImage(
                facingRight ? animManager.getDeathAnim()[animManager.getAnimFrame()]
                        : animManager.getDeathAnimMirrored()[animManager.getAnimFrame()],
                gameWorld.getPlayer().getX() - invertedImageOffset, gameWorld.getPlayer().getY()
        );
        if (animManager.getAnimFrame() == animManager.getDeathAnim().length - 1) {
            gameWorld.newGame();
        }
    }

    /**
     * Clears the entire canvas.
     *
     * @param canvas The canvas to clear.
     */
    public void clearCanvas(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, backgroundWidth, backgroundHeight);
    }

    /**
     * Loads all images required for rendering.
     */
    public void loadImages() {
        for (ImageID imageID : ImageID.values()) {
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

    /**
     * Loads all character animations.
     */
    public void loadAnimations() {
        animManager.loadAnimations();
    }

    /**
     * Gets the width of the background image.
     *
     * @return The background width.
     */
    public double getBackgroundWidth() {
        return backgroundWidth;
    }

    /**
     * Gets the height of the background image.
     *
     * @return The background height.
     */
    public double getBackgroundHeight() {
        return backgroundHeight;
    }
}
