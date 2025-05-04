package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;
import javafx.scene.shape.Rectangle;

import static cz.cvut.fel.pjv.jirsakry.model.HelpMethods.canMoveHere;

public class Player extends GameObject {
    private PlayerState playerState;
    private final double speed;
    private int maxHealth;
    private int currentHealth;
    private boolean canMove;


    //jump and gravity
    private boolean onGround = false;
    private boolean isJumping = false;
    private double jumpStrength;
    private double gravity;
    private double velocityX = 0;
    private double velocityY = 0;
    private double maxJumpHeight = 30;

    private final int[][] levelData = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
            {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
            {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

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
//        if(!onGround && canMoveHere(x + velocityX, y + velocityY, this, levelData)){
//            applyGravity();
//        }
        if(velocityY > 0.8){ // max fall speed cap
            velocityY = 0.8;
        }

        if(canMoveHere(getHitBox().getMinX() + velocityX, getHitBox().getMinY() + velocityY, this, levelData)){
            x += velocityX;
            y += velocityY;
        }
        velocityX = 0;
        velocityY = 0;
//        System.out.println("canMove: " + canMove);
//        System.out.println("isJumping:" + isJumping + " onGround:" +  onGround);
    }

    private void applyGravity(){
        if(!onGround){
            velocityY += gravity;
        }
        y += velocityY;
    }

    public void moveRight(){
            velocityX = speed;
    }
    public void moveLeft(){
        velocityX = -speed;
    }
    public void moveDown(){
        velocityY = speed;
    }
    public void moveUp(){
        velocityY = -speed;
    }

    public void jump(){
        //TODO: non-static jump height
        if(onGround){
            velocityY = -jumpStrength;
            isJumping = true;
            onGround = false;
        }
    }

    @Override
    public BoundingBox getHitBox(){
        return new BoundingBox(x + 20, y + 10, width - 45, height - 13);
//        return new BoundingBox(x, y, width, height);
    }

    public boolean getCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }
}
