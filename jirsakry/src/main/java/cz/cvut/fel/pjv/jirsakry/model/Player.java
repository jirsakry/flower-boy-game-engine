package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;

import static cz.cvut.fel.pjv.jirsakry.model.HelpMethods.*;

public class Player extends GameObject {
    private PlayerState playerState = PlayerState.FACING_RIGHT;
    private final double speed;
    private int maxHealth;
    private int currentHealth;
    private boolean moving = false;

    //jump and gravity
    private boolean jump = false;
    private boolean inAir = false;
    private double jumpStrength;
    private double gravity;
    private double velocityX = 0;
    private double velocityY = 0;
    private double maxJumpHeight = 30;
    private double maxFallSpeed = 1.5;


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
        moving = velocityX != 0; // is moving?
        checkPlayerState();

        if(!inAir){
            if(!(IsEntityOnFloor(getHitBox(), GameWorld.levelData))){
                inAir = true;
            }
        }

        if(inAir){
            if(CanMoveHere(getHitBox().getMinX(), getHitBox().getMinY() + velocityY, this, GameWorld.levelData)){
                y += velocityY;
                velocityY += gravity;
                updateHorizontalMove();
            }
            else{
                hitBox = new BoundingBox (x, GetYPosAboveUnder(getHitBox(), velocityY), width, height);
                if (velocityY > 0){
                    resetInAir();
                }
                else{
                    velocityY = 0;
                }
                updateHorizontalMove();
            }
        }
        else{
            updateHorizontalMove();
        }
        velocityX = 0;
    }

    private void checkPlayerState() {
        if (velocityX > 0){
            playerState = PlayerState.FACING_RIGHT;
        }
        if (velocityX < 0){
            playerState = PlayerState.FACING_LEFT;
        }
    }

    private void resetInAir(){
        inAir = false;
        velocityY = 0;
    }

    private void updateHorizontalMove() {
        if(CanMoveHere(getHitBox().getMinX() + velocityX, getHitBox().getMinY(), this, GameWorld.levelData)){
            x += velocityX;

        }
        else {
            hitBox = new BoundingBox (GetXPosNextToWall(hitBox, velocityX), y, width, height);
            moving = false;
//            x = GetXPosNextToWall(hitBox, velocityX);
        }
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
        if(!inAir){
            inAir = true;
            jump = true;
            velocityY += jumpStrength;
        }
    }

    @Override
    public BoundingBox getHitBox(){
        hitBox =  new BoundingBox(x + 22, y + 11, width - 48, height - 14);
//        hitBox = new BoundingBox(x, y, width, height);
        return hitBox;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public boolean isInAir() {
        return inAir;
    }

    public boolean isMoving() {
        return moving;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}
