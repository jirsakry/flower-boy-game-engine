package cz.cvut.fel.pjv.jirsakry.model;

import javafx.scene.shape.Rectangle;

public class Player extends GameObject {
    private PlayerState playerState;
    private final double speed;
    private int maxHealth;
    private int currentHealth;


    //jump and gravity
    private boolean onGround = false;
    private boolean isJumping = false;
    private double jumpStrength;
    private double gravity;
    private double velocityY;
    private double maxJumpHeight = 30;

    public Player(double x, double y, double width, double height, double speed, int maxHealth, int  currentHealth,
                  double jumpStrength,  double gravity) {
        super(x, y, width, height);
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.jumpStrength = jumpStrength;
        this.gravity = gravity;
    }

    @Override
    public void update(){
        if(!(getHitBox().getMaxY() + gravity > 720)){
            y += gravity;
        }
        if(!onGround){
            applyGravity();
        }
        if(velocityY > 8){ // max fall speed cap
            velocityY = 8;
        }
        System.out.println("isJumping: " + isJumping + " onGround: " +  onGround);
    }

    private void applyGravity(){
        if(!onGround){
            velocityY += gravity * 0.6;
        }
//        if(!(getHitBox().getMaxY() + velocityY >= 720)) {
            y += velocityY;
//        }
    }

    public Rectangle getBoundingBox() {
        Rectangle rectangle = new Rectangle(getHitBox().getMinX(), getHitBox().getMinY(),
                                            getHitBox().getWidth(), getHitBox().getHeight());
        return rectangle;
    }

    public void moveRight(){
            x += speed;
    }
    public void moveLeft(){
            x -= speed;
    }
    public void moveDown(){
            y += speed;
    }
    public void moveUp(){
            y -= speed;
    }

    public void jump(){
        //TODO
        if(onGround){
            velocityY = -jumpStrength;
            isJumping = true;
            onGround = false;
        }
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }
}
