package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;

public class HelpMethods { // from a tutorial

    public static boolean CanMoveHere(double x , double y, GameObject object, int[][] levelData){
        if      (!isSolid(x, y,levelData) &&
                (!isSolid(x + object.getHitBox().getWidth(), y + object.getHitBox().getHeight(), levelData)) &&
                (!isSolid(x + object.getHitBox().getWidth(), y, levelData)) &&
                (!isSolid(x, y + object.getHitBox().getHeight(), levelData))) {
                    return true;
        }
        return false;
    }

    private static boolean isSolid(double x, double y, int[][] levelData){
        if(x < 0 || x >= GameWorld.SCREEN_WIDTH){
            return true;
        }
        if(y < 0 || y >= GameWorld.SCREEN_HEIGHT){
            return true;
        }
        double xIndex = x / GameWorld.TILE_SIZE;
        double yIndex = y / GameWorld.TILE_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];
        if(value == 1){ // is platform
            return true;
        }
        return false;
    }

    public static double GetXPosNextToWall(BoundingBox hitBox, double velocityX){
        int currentTile = (int)(hitBox.getMinX() / GameWorld.TILE_SIZE);
        if(velocityX > 0){ // going right
            double tileXPos = currentTile * GameWorld.TILE_SIZE;
            double xOffset = (GameWorld.TILE_SIZE - hitBox.getWidth());
            return tileXPos + xOffset - 1;
        }
        else{ // going left
            return currentTile * GameWorld.TILE_SIZE;
        }
    }

    public static double GetYPosAboveUnder(BoundingBox hitBox, double velocityY){
        int currentTile = (int)(hitBox.getMinY() / GameWorld.TILE_SIZE);
        if(velocityY > 0){ //falling
            int tileYPos = currentTile * GameWorld.TILE_SIZE;
            double yOffset = (GameWorld.TILE_SIZE - hitBox.getHeight());
            return tileYPos + yOffset + 1;
        }
        else{ // jumping
            return currentTile * GameWorld.TILE_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(BoundingBox hitBox, int[][] levelData){
        if(!(isSolid(hitBox.getMinX(), hitBox.getMaxY() + 1, levelData)) && // bottom left corner
           !(isSolid(hitBox.getMaxX(), hitBox.getMaxY() + 1, levelData))){ // bottom right corner
            return false;
        }
        return true;
    }
}
