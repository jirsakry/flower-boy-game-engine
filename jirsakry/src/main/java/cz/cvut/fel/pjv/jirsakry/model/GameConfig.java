package cz.cvut.fel.pjv.jirsakry.model;

import java.util.List;

public class GameConfig {

    private double playerSpeed;
    private double playerJumpStrength;
    private double gravity;
    private int playerMaxHealth;
    private int[][] levelData;

    public GameConfig() {
    }

    public static GameConfig getDefaultConfig(){
        GameConfig defaultConfig = new GameConfig();
        defaultConfig.playerSpeed = 1.7;
        defaultConfig.playerJumpStrength = -11.7;
        defaultConfig.gravity = 0.6;
        defaultConfig.playerMaxHealth = 2;
        defaultConfig.levelData = new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
                {1,0,3,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,0,1,0,0,0,0,1,1,1,0,0,0,0,3,0,0,0},
                {0,0,0,0,1,0,0,4,0,0,0,0,0,1,1,0,0,0,0,0},
                {0,0,0,0,0,0,1,1,0,0,3,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,4,0,0,0},
                {0,0,4,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0},
                {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,3,0},
                {1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,1,1,1}
        };
        return defaultConfig;
    }

    public double getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(double playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    public double getPlayerJumpStrength() {
        return playerJumpStrength;
    }

    public void setPlayerJumpStrength(double playerJumpStrength) {
        this.playerJumpStrength = playerJumpStrength;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public int getPlayerMaxHealth() {
        return playerMaxHealth;
    }

    public void setPlayerMaxHealth(int playerMaxHealth) {
        this.playerMaxHealth = playerMaxHealth;
    }

    public int[][] getLevelData() {
        return levelData;
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;
    }
}
