package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;

/**
 * These methods are primarily for player movement
 */
public class HelpMethods { // from a tutorial
    /**
     * Checks character movement based on input values.
     * Mostly combined with player velocity.
     *
     * @param x         is character hitbox position.
     * @param y         is character hitbox position.
     * @param object    for getting the hitbox.
     * @param levelData decides based on the level data combined with constant tile size.
     * @return
     */
    public static boolean CanMoveHere(double x, double y, GameObject object, int[][] levelData) {
        return !isSolid(x, y, levelData) &&
                (!isSolid(x + object.getHitBox().getWidth(), y + object.getHitBox().getHeight(), levelData)) &&
                (!isSolid(x + object.getHitBox().getWidth(), y, levelData)) &&
                (!isSolid(x, y + object.getHitBox().getHeight(), levelData));
    }

    private static boolean isSolid(double x, double y, int[][] levelData) {
        if (x < 0 || x >= GameWorld.SCREEN_WIDTH) {
            return true;
        }
        if (y < 0 || y >= GameWorld.SCREEN_HEIGHT) {
            return true;
        }
        double xIndex = x / GameWorld.TILE_SIZE;
        double yIndex = y / GameWorld.TILE_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];
        // is platform
        return value == 1;
    }

    /**
     * Returns position next to the wall, uses that value to create new hitbox.
     *
     * @param hitBox    all deciding is based on the hitbox at this point.
     * @param velocityX for checking horizontal movement.
     * @return value for new hitbox x position.
     */
    public static double GetXPosNextToWall(BoundingBox hitBox, double velocityX) {
        int currentTile = (int) (hitBox.getMinX() / GameWorld.TILE_SIZE);
        if (velocityX > 0) { // going right
            double tileXPos = currentTile * GameWorld.TILE_SIZE;
            double xOffset = (GameWorld.TILE_SIZE - hitBox.getWidth());
            return tileXPos + xOffset - 2;
        } else { // going left
            return currentTile * GameWorld.TILE_SIZE;
        }
    }

    /**
     * Same principal as getXPos but for vertical movement.
     *
     * @param hitBox    all deciding is based on the hitbox at this point.
     * @param velocityY for checking vertical movement.
     * @return value for new hitbox position.
     */
    public static double GetYPosAboveUnder(BoundingBox hitBox, double velocityY) {
        int currentTile = (int) (hitBox.getMinY() / GameWorld.TILE_SIZE);
        if (velocityY > 0) { //falling
            int tileYPos = currentTile * GameWorld.TILE_SIZE;
            double yOffset = (GameWorld.TILE_SIZE - hitBox.getHeight());
            return tileYPos + yOffset + 1;
        } else { // jumping
            return currentTile * GameWorld.TILE_SIZE;
        }
    }

    /**
     * Checks if space under character is empty or not.
     * Added little bit of headroom for smoother jumping.
     *
     * @param hitBox    all deciding is based on the hitbox at this point.
     * @param levelData decides based on position in level data
     * @return true if is on floor. False otherwise.
     */
    public static boolean IsEntityOnFloor(BoundingBox hitBox, int[][] levelData) {
        // bottom right corner
        return isSolid(hitBox.getMinX() - 7, hitBox.getMaxY() + 1, levelData) || // bottom left corner
                isSolid(hitBox.getMaxX() + 7, hitBox.getMaxY() + 1, levelData);
    }
}
