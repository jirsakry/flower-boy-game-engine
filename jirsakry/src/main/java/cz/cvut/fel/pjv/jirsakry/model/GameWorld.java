package cz.cvut.fel.pjv.jirsakry.model;

import java.util.ArrayList;
import java.util.List;

public class GameWorld{
    public static double SCREEN_WIDTH = 1280;
    public static double SCREEN_HEIGHT = 768;

    public static int TILE_SIZE = 64;
    public static int COLS = 20;
    public static int ROWS = 12;

    private GameState gameState;

    private Level currentLevel;
    private Level level0;

    private double playerSpeed = 1.7;
    private int playerMaxHealth = 2;
    private int playerCurrentHealth = 1;
    private int playerFlowerCount = 0;

    //jump and gravity
    private double playerJumpStrength = -11.7;
    private double gravity = 0.6;

    private Player player;


    // timer
    Timer timer = new Timer();

    public static int[][] levelData = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
            {1,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,1,0,0,0,0,1,1,1,0,0,0,0,3,0,0,0},
            {0,0,0,0,1,0,0,4,0,0,0,0,0,1,1,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,0,0,3,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,4,0,0,0},
            {0,0,4,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0},
            {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,3,0},
            {1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,1,1,1}
    };

    public void init(){
        level0 = new Level(levelData);
        level0.load(levelData);

        gameState = GameState.MAIN_MENU;

        currentLevel = level0;

        player = new Player(40, 600, 64, 64,
                playerSpeed, playerMaxHealth, playerCurrentHealth, playerJumpStrength, gravity, currentLevel);
        newGame();
    }

    public void newGame() {
        player.reset();

        playerFlowerCount = 0;
        for(Flower flower: level0.getFlowers()){
            flower.setCollected(false);
        }

        timer.reset();
        if(gameState == GameState.MAIN_MENU){
            timer.stop();
        }
        else{
            gameState = GameState.PLAYING;
            timer.start();
        }
    }

    public void update(){
        if(playerFlowerCount == level0.getFlowerCount()
        && gameState != GameState.WIN) {
            gameState = GameState.WIN;
            timer.stop();
            System.out.println("timer stopped");
            System.out.println("Your time: " + timer.getFormattedTime());
        }
//        if(player.getPlayerState() == PlayerState.DEATH){
//            newGame();
//        }

        player.update();
        checkCollisions();
    }

    private void checkCollisions(){
        checkFlowerPickUp();
        checkCactusHit();
    }

    private void checkFlowerPickUp() {
        for (Flower flower : level0.getFlowers()) {
            if(!(flower.isCollected())) {
                if (flower.getHitBox().intersects(player.getHitBox())) {
                    flower.setCollected(true);
                    playerFlowerCount++;
                }
            }
        }
    }

    private void checkCactusHit() {
        for (Cactus cactus : level0.getCacti()){
            if(cactus.getHitBox().intersects(player.getHitBox())) {
                player.setPlayerState(PlayerState.DEATH);
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

    public int getPlayerFlowerCount() {
        return playerFlowerCount;
    }

    public Level getLevel0() {
        return level0;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
