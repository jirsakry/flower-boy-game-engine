package cz.cvut.fel.pjv.jirsakry.model;

import java.util.ArrayList;
import java.util.List;

public class GameWorld{
    public static double SCREEN_WIDTH = 1280;
    public static double SCREEN_HEIGHT = 768;

    public static int TILE_SIZE = 64;
    public static int COLS = 20;
    public static int ROWS = 12;

    GameState gameState;

    private Level level0;
    private int levelFlowerCount;

    private double playerSpeed = 2;
    private int playerMaxHealth = 2;
    private int playerCurrentHealth = 1;
    private int playerFlowerCount = 0;

    //jump and gravity
    private double playerJumpStrength = -15;
    private double gravity = 0.6;

    private List<Enemy>  enemies;
    private List<Platform> platforms;
    private List<Flower> flowers;
    private Player player;

    public static int[][] levelData = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
            {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public void init(){
        enemies = new ArrayList<>();
        level0 = new Level(levelData);
        flowers = new ArrayList<>();
        level0.load(levelData);
        platforms = level0.getPlatforms();
        gameState = GameState.PLAYING;


        player = new Player(80, 600, 64, 64,
                playerSpeed, playerMaxHealth, playerCurrentHealth, playerJumpStrength, gravity);
        flowers.add(new Flower(TILE_SIZE*2, TILE_SIZE*8, 43, 48));
        levelFlowerCount++;
        flowers.add(new Flower(TILE_SIZE*11, TILE_SIZE*3, 43, 48));
        levelFlowerCount++;
        flowers.add(new Flower(TILE_SIZE*16, TILE_SIZE*4, 43, 48));
        levelFlowerCount++;
    }

    public void update(){
        if(playerFlowerCount == levelFlowerCount){
            gameState = GameState.WIN;
        }
        player.update();
        checkCollisions();
    }

    private void checkCollisions(){
        // TODO
        checkFlowerPickUp();
    }

    private void checkFlowerPickUp() {
        for (Flower flower : flowers) {
            if(!(flower.isCollected())) {
                if (flower.getHitBox().intersects(player.getHitBox())) {
                    flower.setCollected(true);
                    playerFlowerCount++;
                }
            }
        }
    }

    public void moveRight() {
            player.moveRight();

    }
    public void moveLeft() {
            player.moveLeft();
    }
    public void moveDown() {
            player.moveDown();
    }
    public void moveUp() {
            player.moveUp();
    }
    public void jump() {
        player.jump();
    }


    public Player getPlayer() {
        return player;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public int getPlayerFlowerCount() {
        return playerFlowerCount;
    }

    public Level getLevel0() {
        return level0;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
