package cz.cvut.fel.pjv.jirsakry.model;

import java.util.List;
import java.util.logging.Logger;

public class GameWorld {
    private List<Enemy>  enemies;

    private double playerSpeed = 3;
    private int playerMaxHealth = 2;
    private int playerCurrentHealth = 1;

    //jump and gravity
    private double playerJumpStrength = 10;
    private double gravity = 0.8;

    private Player player = new Player(20, 500, 64, 64,
            playerSpeed, playerMaxHealth, playerCurrentHealth, playerJumpStrength, gravity);
    private GameObject background = new GameObject(0, 0, 1280, 720) {
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

    public void update(){
        player.update();
        checkCollisions();
//        enemies.forEach(Enemy::update);
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
        if(player.getHitBox().intersects(background.getBottomEdge())){
            player.setOnGround(true);
            player.setIsJumping(false);
        }
//        System.out.println("stageCollision: " + stageCollision);
    }

    private boolean checkOutOfScreen() { // TODO: needs better logics
        if(
                player.getHitBox().getMinX() - playerSpeed < 0 ||
                player.getHitBox().getMinY() - playerSpeed < 0 ||
                player.getHitBox().getMaxX() + playerSpeed > background.getWidth() ||
                player.getHitBox().getMaxY() + playerSpeed > background.getHeight()
        ){
            return false;
        }
        else return true;
    }

    public void moveRight() {
        if(player.getHitBox().getMaxX() + playerSpeed < background.getWidth()){
            player.moveRight();

        }
    }
    public void moveLeft() {
        if(player.getHitBox().getMinX() - playerSpeed > 0){
        player.moveLeft();

        }
    }
    public void moveDown() {
        if(player.getHitBox().getMaxY() + playerSpeed < background.getHeight()){
        player.moveDown();

        }
    }
    public void moveUp() {
        if(player.getHitBox().getMinY() - playerSpeed > 0){
        player.moveUp();

        }
    }
    public void jump() {
        player.jump();
    }

    public Player getPlayer() {
        return player;
    }
}
