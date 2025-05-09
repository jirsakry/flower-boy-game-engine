package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DebugOverlay { // generated
    private static boolean showDebug = true;
    private static final Font DEBUG_FONT = Font.font("Arial", 16);

    public static void draw(GraphicsContext gc, GameWorld gameWorld, int[][] levelData) {
        if (!showDebug) return;

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

        // playerFlowerCount
        gc.fillText("playerFlowerCount: " + gameWorld.getPlayerFlowerCount(),  10, 100);

        // inAir
        gc.fillText("inAir: " + gameWorld.getPlayer().isInAir(), 10, 120);

        // moving
        gc.fillText("moving: " + gameWorld.getPlayer().isMoving(), 10, 140);

        // gameState
        gc.fillText("gameState: " + gameWorld.getGameState(), 10, 160);
    }

    public static void toggleDebug() {
        showDebug = !showDebug;
    }

    private static void renderHitBoxes(GraphicsContext gc, GameWorld gameWorld) {
        BoundingBox playerHitBox = gameWorld.getPlayer().getHitBox();

        gc.setStroke(Color.RED);
        gc.strokeRect(playerHitBox.getMinX(), playerHitBox.getMinY(), playerHitBox.getWidth(), playerHitBox.getHeight());

        for(Flower flower : gameWorld.getFlowers()){
            gc.strokeRect(flower.getX(), flower.getY(), flower.getWidth(), flower.getHeight());
        }
    }
}
