package cz.cvut.fel.pjv.jirsakry.model;

public class Player extends GameObject {
    private double speed;
    private int maxHealth;
    private int currentHealth = 1;

    public Player(double x, double y, double speed,  int maxHealth) {
        super(x, y);
        this.speed = speed;
        this.maxHealth = maxHealth;
    }

    public void update(){

    }

    public void moveRight(){x += speed;}
    public void moveLeft(){x -= speed;}
    public void jump(){
        //TODO
    }
}
