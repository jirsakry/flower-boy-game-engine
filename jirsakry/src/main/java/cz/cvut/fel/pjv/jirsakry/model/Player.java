package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;

import static cz.cvut.fel.pjv.jirsakry.model.HelpMethods.*;

public class Player extends GameObject {
    private PlayerState playerState = PlayerState.FACING_RIGHT;
    private double speed;
    private final int maxHealth;
    private int currentHealth;
    private boolean moving = false;

    //jump and gravity
    private boolean jump = false;
    private boolean doubleJumpReady = true;
    private boolean inAir = false;
    private final double jumpStrength;
    private final double gravity;
    private double velocityX = 0;
    private double velocityY = 0;
    private double maxJumpHeight = 30;

    private final Level currentLevel;


    public Player(double x, double y, double width, double height, double speed, int maxHealth, int  currentHealth,
                  double jumpStrength, double gravity, Level currentLevel) {
        super(x, y, width, height);
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.jumpStrength = jumpStrength;
        this.gravity = gravity;
        this.currentLevel = currentLevel;
    }

    @Override
    public void update(){
        moving = velocityX != 0; // is moving?
        checkPlayerState();

        if(!inAir){
            if(!(IsEntityOnFloor(getHitBox(), currentLevel.getLevelData()))){
                inAir = true;
                jump = false;
            }
        }

        if(inAir){
            if(CanMoveHere(getHitBox().getMinX(), getHitBox().getMinY() + velocityY, this, currentLevel.getLevelData())){
                jump = false;
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
        if(playerState == PlayerState.DEATH){
            velocityX = 0;
            velocityY = 0;
        }
        else if (velocityX > 0){
            playerState = PlayerState.FACING_RIGHT;
        }
        else if (velocityX < 0){
            playerState = PlayerState.FACING_LEFT;
        }

    }

    private void resetInAir(){
        inAir = false;
        velocityY = 0;
        jump = false;
        doubleJumpReady = true;
    }

    private void updateHorizontalMove() {
        if(CanMoveHere(getHitBox().getMinX() + velocityX, getHitBox().getMinY(), this, currentLevel.getLevelData())){
            x += velocityX;
        }
        else {
            hitBox = new BoundingBox (GetXPosNextToWall(hitBox, velocityX), y, width, height);
            moving = false;
//            x = GetXPosNextToWall(hitBox, velocityX);
        }
    }

    public void reset(){
        playerState = PlayerState.FACING_RIGHT;
        x = 40;
        y = 600;
        velocityX = 0;
        velocityY = 0;
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
        if(!inAir) {
            inAir = true;
            jump = true;
            doubleJumpReady = true;
            velocityY = jumpStrength;
        }
        else if(doubleJumpReady && !jump) {
            velocityY = jumpStrength;
            doubleJumpReady = false;
        }
    }

    @Override
    public BoundingBox getHitBox(){
        hitBox = new BoundingBox(x + 26, y + 15, width - 54, height - 16);
        return hitBox;
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

    public boolean isDoubleJumpReady() {
        return doubleJumpReady;
    }

    public boolean isJump() {
        return jump;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
}
