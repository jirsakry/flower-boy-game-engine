package cz.cvut.fel.pjv.jirsakry.model;

public class HelpMethods { // from a tutorial

    public static boolean canMoveHere(double x , double y, GameObject object, int[][] levelData){
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
}
