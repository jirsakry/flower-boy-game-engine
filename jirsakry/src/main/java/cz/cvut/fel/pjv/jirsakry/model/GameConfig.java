package cz.cvut.fel.pjv.jirsakry.model;

import java.util.List;

public class GameConfig {
    private double playerSpeed;
    private double playerJumpStrength;
    private double gravity;
    private int playerMaxHealth;
    private List<int[][]> levels;

    public GameConfig() {
    }

    /**
     * Creates and returns the default game configuration including predefined levels.
     *
     * @return instance of the default game config.
     */
    public static GameConfig getDefaultConfig() {
        GameConfig defaultConfig = new GameConfig();
        defaultConfig.playerSpeed = 1.7;
        defaultConfig.playerJumpStrength = -11.7;
        defaultConfig.gravity = 0.55;
        defaultConfig.playerMaxHealth = 1;
        defaultConfig.levels = List.of(
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                        {0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                        {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0},
                        {6, 0, 0, 0, 0, 0, 0, 0, 3, 0, 1, 1, 1, 1, 0, 5, 3, 0, 0, 0},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                },
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {4, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 0, 3, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 3, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 4, 0, 0, 0},
                        {0, 0, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                        {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 3, 0, 0},
                        {1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                }
        );

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

    public List<int[][]> getLevels() {
        return levels;
    }

    public void setLevels(List<int[][]> levels) {
        this.levels = levels;
    }
}
