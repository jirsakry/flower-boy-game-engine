package cz.cvut.fel.pjv.jirsakry.model;

import java.util.ArrayList;
import java.util.List;

public class GameWorld{
    public static double SCREEN_WIDTH = 1280;
    public static double SCREEN_HEIGHT = 768;

    public static int TILE_SIZE = 64;
    public static int COLS = 20;
    public static int ROWS = 12;

    private List<Enemy>  enemies;
    private List<Platform> platforms;

    private Level level0;

    private double playerSpeed = 2;
    private int playerMaxHealth = 2;
    private int playerCurrentHealth = 1;
    private boolean playerCollision = false;

    //jump and gravity
    private double playerJumpStrength = -15;
    private double gravity = 0.6;

    private GameObject background;
    private Player player;

    public static int[][] levelData = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
            {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
            {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public void init(){
        enemies = new ArrayList<>();
        level0 = new Level(levelData);
        level0.load(levelData);
        platforms = level0.getPlatforms();

        background = new GameObject(0, 0, 1280, 768) {
            @Override
            public double getX() {
                return super.getX();
            }

            @Override
            public double getY() {
                return super.getY();
            }

            @Override
            public double getWidth() {
                return super.getWidth();
            }

            @Override
            public double getHeight() {
                return super.getHeight();
            }
        };
        player = new Player(80, 600, 64, 64,
                playerSpeed, playerMaxHealth, playerCurrentHealth, playerJumpStrength, gravity);
    }

    public void update(){
        player.update();
        checkCollisions();
        playerCollision = false;
        for (Platform platform : platforms) {
            if(player.getHitBox().intersects(platform.getHitBox())){
                playerCollision = true;
            }
        }
    }

    private void checkCollisions(){
        // TODO
        boolean stageCollision = false;
        if (player.getHitBox().intersects(background.getTopEdge()) ||
                player.getHitBox().intersects(background.getBottomEdge()) ||
                player.getHitBox().intersects(background.getLeftEdge()) ||
                player.getHitBox().intersects(background.getRightEdge())) {
            stageCollision = true;
        }
//        System.out.println("stageCollision: " + stageCollision);
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

    public Level getLevel0() {
        return level0;
    }

    public boolean isPlayerCollision() {
        return playerCollision;
    }
}
