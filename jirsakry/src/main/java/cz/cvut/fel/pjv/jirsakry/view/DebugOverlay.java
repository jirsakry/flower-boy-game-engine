package cz.cvut.fel.pjv.jirsakry.view;

import cz.cvut.fel.pjv.jirsakry.model.Cactus;
import cz.cvut.fel.pjv.jirsakry.model.Flower;
import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DebugOverlay { // generated
    private static boolean showDebug = true;
    private static final Font DEBUG_FONT = Font.font("Arial", 16);

    public static void draw(GraphicsContext gc, GameWorld gameWorld) {
        if (!showDebug) return;

        Font originalFont = gc.getFont();
        Color originalFill = (Color) gc.getFill();

        gc.setFont(DEBUG_FONT);
        gc.setFill(Color.LIMEGREEN);

        gc.fillText("DEBUG_OVERLAY", GameWorld.SCREEN_WIDTH/2 - 100, 20);

        // player hitbox
        renderHitBoxes(gc, gameWorld);

        // Pozice objektu
        String position = String.format("X: %.2f, Y: %.2f", gameWorld.getPlayer().getHitBox().getMinX(), gameWorld.getPlayer().getHitBox().getMinY());
        gc.fillText(position, 10, 20);

        String playerSpeed = String.format("velocityX:  %f\nvelocityY:  %f", gameWorld.getPlayer().getVelocityX(), gameWorld.getPlayer().getVelocityY());
        gc.fillText(playerSpeed, 10, 40);

        // Index dlaždice
        double xIndex = gameWorld.getPlayer().getHitBox().getMinX() / GameWorld.TILE_SIZE;
        double yIndex = gameWorld.getPlayer().getHitBox().getMinY() / GameWorld.TILE_SIZE;
        String tileIndex = String.format("Tile: [%d, %d]", (int)xIndex, (int)yIndex);
        gc.fillText(tileIndex, 10, 80);

        // doubleJumpReady + jump
        gc.fillText("doubleJumpReady: " + gameWorld.getPlayer().isDoubleJumpReady() +
                " jump: " + gameWorld.getPlayer().isJump(),  10, 100);

        // inAir
        gc.fillText("inAir: " + gameWorld.getPlayer().isInAir(), 10, 120);

        // moving
        gc.fillText("moving: " + gameWorld.getPlayer().isMoving(), 10, 140);

        // gameState
        gc.fillText("gameState: " + gameWorld.getGameState(), 10, 160);

        // playerState
        gc.fillText("playerState: " + gameWorld.getPlayer().getPlayerState(), 10, 180);

        gc.setFont(originalFont);
        gc.setFill(originalFill);

    }

    public static void toggleDebug() {
        showDebug = !showDebug;
    }

    private static void renderHitBoxes(GraphicsContext gc, GameWorld gameWorld) {
        BoundingBox playerHitBox = gameWorld.getPlayer().getHitBox();

        gc.setStroke(Color.RED);
        gc.strokeRect(playerHitBox.getMinX(), playerHitBox.getMinY(),
                playerHitBox.getWidth(), playerHitBox.getHeight());

        for(Flower flower : gameWorld.getLevel0().getFlowers()){
            gc.strokeRect(flower.getHitBox().getMinX(), flower.getHitBox().getMinY(),
                    flower.getHitBox().getWidth(), flower.getHitBox().getHeight());
        }

        for(Cactus cactus : gameWorld.getLevel0().getCacti()){
            gc.strokeRect(cactus.getHitBox().getMinX(), cactus.getHitBox().getMinY(),
                    cactus.getHitBox().getWidth(), cactus.getHitBox().getHeight());
        }
    }

    public static boolean isShowDebug() {
        return showDebug;
    }
}
