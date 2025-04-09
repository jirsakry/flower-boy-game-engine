package cz.cvut.fel.pjv.jirsakry.model;

import java.util.List;

public class GameWorld {
    private List<Enemy>  enemies;
    private double playerSpeed = 5;
    private int maxHealth = 2;

    private Player player = new Player(20, 500, playerSpeed, 2);


    public void update(){
        player.update();
//        enemies.forEach(Enemy::update);
//        checkCollisions();
    }

    private void checkCollisions(){
        // TODO
    }

    public void goRight() {
        player.goRight();
    }
    public void goLeft() {
        player.goLeft();
    }

    public Player getPlayer() {
        return player;
    }
}
