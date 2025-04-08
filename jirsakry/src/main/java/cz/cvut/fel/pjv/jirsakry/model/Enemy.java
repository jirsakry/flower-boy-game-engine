package cz.cvut.fel.pjv.jirsakry.model;

public class Enemy extends GameObject {
    private double speed;

    public Enemy(double x, double y, double speed) {
        super(x, y);
        this.speed = speed;
    }

    public void update(){

    }
}
