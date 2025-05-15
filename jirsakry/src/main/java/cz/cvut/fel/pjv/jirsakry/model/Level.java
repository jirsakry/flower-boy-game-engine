package cz.cvut.fel.pjv.jirsakry.model;

import java.util.ArrayList;

public class Level {
    private final int[][] levelData;

    private final ArrayList<Platform> platforms;
    private final ArrayList<Flower> flowers;
    private final ArrayList<Cactus> cacti;
    private final ArrayList<Shield> shields;

    private final int TILE_SIZE = GameWorld.TILE_SIZE;
    private final int COLS = GameWorld.COLS;
    private final int ROWS = GameWorld.ROWS;

    private int flowerCount;

    /**
     * Converts values from input matrix and creates GameObjects accordingly.
     *
     * @param levelData matrix of tile values
     */
    public Level(int[][] levelData) {
        this.levelData = levelData;

        this.platforms = new ArrayList<>();
        this.flowers = new ArrayList<>();
        this.cacti = new ArrayList<>();
        this.shields = new ArrayList<>();
        load(levelData);
    }

    private void load(int[][] levelData) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int tileValue = levelData[row][col];
                switch (tileValue) {
                    case TileValues.GRASS -> {
                        platforms.add(new Platform(
                                col * TILE_SIZE, row * TILE_SIZE,
                                TILE_SIZE, TILE_SIZE));
                    }
                    case TileValues.GROUND -> {
                        continue;
                    }
                    case TileValues.FLOWER -> {
                        double flowerOffset = TILE_SIZE - 48;
                        flowers.add(new Flower(
                                col * TILE_SIZE, row * TILE_SIZE + flowerOffset,
                                43, 48
                        ));
                        flowerCount++;
                    }
                    case TileValues.CACTUS -> {
                        cacti.add(new Cactus(
                                col * TILE_SIZE, (row * TILE_SIZE) + TILE_SIZE / 2,
                                (double) TILE_SIZE / 2, (double) TILE_SIZE / 2
                        ));
                    }
                    case TileValues.DOUBLE_CACTUS -> {
                        cacti.add(new Cactus(
                                col * TILE_SIZE, (row * TILE_SIZE) + TILE_SIZE / 2,
                                (double) TILE_SIZE / 2, (double) TILE_SIZE / 2));
                        cacti.add(new Cactus(
                                col * TILE_SIZE + TILE_SIZE / 2, (row * TILE_SIZE) + TILE_SIZE / 2,
                                (double) TILE_SIZE / 2, (double) TILE_SIZE / 2));
                    }
                    case TileValues.SHIELD -> {
                        shields.add(new Shield(
                                col * TILE_SIZE, row * TILE_SIZE,
                                TILE_SIZE / 2, TILE_SIZE / 2
                        ));
                    }
                }

            }
        }
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public ArrayList<Flower> getFlowers() {
        return flowers;
    }

    public ArrayList<Cactus> getCacti() {
        return cacti;
    }

    public ArrayList<Shield> getShields() {
        return shields;
    }

    public int[][] getLevelData() {
        return levelData;
    }

    public int getFlowerCount() {
        return flowerCount;
    }
}
