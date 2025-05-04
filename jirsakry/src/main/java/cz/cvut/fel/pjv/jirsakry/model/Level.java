package cz.cvut.fel.pjv.jirsakry.model;

import java.util.ArrayList;

public class Level {
    private final ArrayList<Platform> platforms;
    private final int[][] levelData;
    private final int TILE_SIZE = GameWorld.TILE_SIZE;
    private final int COLS = GameWorld.COLS;
    private final int ROWS = GameWorld.ROWS;

    public Level(int[][] levelData) {
        this.platforms = new ArrayList<>();
        this.levelData = levelData;

    }

    public void load(int[][] levelData) {
        for (int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                if(levelData[row][col] == 1){
                    platforms.add(new Platform(
                            col * TILE_SIZE, row * TILE_SIZE,
                            TILE_SIZE, TILE_SIZE));
                }
            }
        }
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public int[][] getLevelData() {
        return levelData;
    }
}
