package cz.cvut.fel.pjv.jirsakry.model;

abstract class Enemy extends GameObject {
    private double speed;

    public Enemy(double x, double y, double width, double height, double speed) {
        super(x, y, width, height);
        this.speed = speed;
    }
    @Override
    public void update(){

    }
}
