package cz.cvut.fel.pjv.jirsakry.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DebugOverlay {
    private static boolean showDebug = true;
    private static final Font DEBUG_FONT = Font.font("Arial", 16);

    public static void draw(GraphicsContext gc, Player player, int[][] levelData) {
        if (!showDebug) return;

        gc.setFont(DEBUG_FONT);
        gc.setFill(Color.LIMEGREEN);

        // Pozice objektu
        String position = String.format("X: %.2f, Y: %.2f", player.getX(), player.getY());
        gc.fillText(position, 10, 20);

        String playerSpeed = String.format("velocityX:  %f\nvelocityY:  %f", player.getVelocityX(), player.getVelocityY());
        gc.fillText(playerSpeed, 10, 40);

        // Index dlaždice
        double xIndex = player.getX() / GameWorld.TILE_SIZE;
        double yIndex = player.getY() / GameWorld.TILE_SIZE;
        String tileIndex = String.format("Tile: [%d, %d]", (int)xIndex, (int)yIndex);
        gc.fillText(tileIndex, 10, 80);

        // Hodnota dlaždice
        int value = levelData[(int)yIndex][(int)xIndex];
        gc.fillText("Value: " + value, 10, 100);
    }

    public static void toggleDebug() {
        showDebug = !showDebug;
    }
}
