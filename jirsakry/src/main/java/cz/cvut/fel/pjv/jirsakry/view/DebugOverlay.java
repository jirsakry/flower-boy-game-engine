package cz.cvut.fel.pjv.jirsakry.view;

import cz.cvut.fel.pjv.jirsakry.model.Cactus;
import cz.cvut.fel.pjv.jirsakry.model.Flower;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import cz.cvut.fel.pjv.jirsakry.model.Shield;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Utility class for rendering debug overlay and hitboxes in the game.
 */
// WHILE DEBUG PLAYER CAN USE UP AND DOWN KEYS FOR LEVITATION
public class DebugOverlay { // generated and edited
    private static final Font DEBUG_FONT = Font.font("Arial", 16);
    private static boolean showDebug = false;

    public static void toggleDebug() {
        showDebug = !showDebug;
    }

    /**
     * Draws the debug overlay on the given GraphicsContext if debug mode is enabled.
     *
     * @param gc        the GraphicsContext to draw on
     * @param gameWorld game world instance
     */
    public static void draw(GraphicsContext gc, GameWorld gameWorld) {
        if (!showDebug) return;

        Font originalFont = gc.getFont();
        Color originalFill = (Color) gc.getFill();

        gc.setFont(DEBUG_FONT);
        gc.setFill(Color.LIMEGREEN);

        gc.fillText("DEBUG_OVERLAY", GameWorld.SCREEN_WIDTH / 2 - 100, 20);

        // player hitbox
        renderHitBoxes(gc, gameWorld);

        // player position
        String position = String.format("X: %.2f, Y: %.2f", gameWorld.getPlayer().getHitBox().getMinX(), gameWorld.getPlayer().getHitBox().getMinY());
        gc.fillText(position, 10, 20);

        String playerSpeed = String.format("velocityX:  %f\nvelocityY:  %f", gameWorld.getPlayer().getVelocityX(), gameWorld.getPlayer().getVelocityY());
        gc.fillText(playerSpeed, 10, 40);


        // doubleJumpReady + jump
        gc.fillText("doubleJumpReady: " + gameWorld.getPlayer().isDoubleJumpReady(), 10, 80);

        // inAir
        gc.fillText("inAir: " + gameWorld.getPlayer().isInAir(), 10, 100);

        // moving
        gc.fillText("moving: " + gameWorld.getPlayer().isMoving(), 10, 120);

        // playerState
        gc.fillText("playerState: " + gameWorld.getPlayer().getPlayerState(), 10, 140);

        // player Health
        gc.fillText("player health: " + gameWorld.getPlayer().getCurrentHealth() + "/" +
                gameWorld.getPlayer().getMaxHealth() + " shield: " + gameWorld.getPlayer().isHoldingShield(), 10, 160);

        gc.setFont(originalFont);
        gc.setFill(originalFill);

    }

    private static void renderHitBoxes(GraphicsContext gc, GameWorld gameWorld) {
        BoundingBox playerHitBox = gameWorld.getPlayer().getHitBox();

        gc.setStroke(Color.RED);
        gc.strokeRect(playerHitBox.getMinX(), playerHitBox.getMinY(),
                playerHitBox.getWidth(), playerHitBox.getHeight());

        for (Flower flower : gameWorld.getCurrentLevel().getFlowers()) {
            gc.strokeRect(flower.getHitBox().getMinX(), flower.getHitBox().getMinY(),
                    flower.getHitBox().getWidth(), flower.getHitBox().getHeight());
        }

        for (Cactus cactus : gameWorld.getCurrentLevel().getCacti()) {
            gc.strokeRect(cactus.getHitBox().getMinX(), cactus.getHitBox().getMinY(),
                    cactus.getHitBox().getWidth(), cactus.getHitBox().getHeight());
        }
        for (Shield shield : gameWorld.getCurrentLevel().getShields()) {
            gc.strokeRect(shield.getHitBox().getMinX(), shield.getHitBox().getMinY(),
                    shield.getHitBox().getWidth(), shield.getHitBox().getHeight());
        }
    }

    public static boolean isShowDebug() {
        return showDebug;
    }
}
