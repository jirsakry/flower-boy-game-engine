package cz.cvut.fel.pjv.jirsakry.model;

public class Player extends GameObject {
    PlayerState playerState;
    private double speed = 0;
    private double acceleration;
    private double maxSpeed;
    private int maxHealth;
    private int currentHealth;

    public Player(double x, double y, double acceleration, double maxSpeed, int maxHealth, int  currentHealth) {
        super(x, y);
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
    }

    public void update(){

    }

    public void moveRight(){
        if(speed >= Math.abs(maxSpeed)){
            speed = maxSpeed;
        }
        speed += acceleration;
        x += speed;
    }
    public void moveLeft(){
        if(Math.abs(speed) >= Math.abs(maxSpeed)){
            speed = -maxSpeed; // we are going left
        }
        speed -= acceleration;
        x += speed;
    }
    public void jump(){
        //TODO
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
