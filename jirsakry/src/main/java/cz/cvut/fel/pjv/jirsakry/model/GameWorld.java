package cz.cvut.fel.pjv.jirsakry.model;

import java.util.List;

public class GameWorld {
    private List<Enemy>  enemies;
    private double playerAcceleration = 2.5;
    private double playerMaxSpeed = 5;
    private int playerMaxHealth = 2;
    private int playerCurrentHealth = 1;

    private Player player = new Player(20, 500,
            playerAcceleration, playerMaxSpeed, playerMaxHealth, playerCurrentHealth);

    private void checkCollisions(){
        // TODO
    }

    public void update(){
        player.update();
//        enemies.forEach(Enemy::update);
//        checkCollisions();
    }

    public void goRight() {
        player.moveRight();
    }
    public void goLeft() {
        player.moveLeft();
    }
    public void jump() {
        player.jump();
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayerSpeed(double speed) {
        player.setSpeed(speed);
    }
}
