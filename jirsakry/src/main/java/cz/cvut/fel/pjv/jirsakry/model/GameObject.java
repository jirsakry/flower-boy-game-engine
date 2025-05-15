package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;

abstract class GameObject {
    protected double x;
    protected double y;

    protected double width;
    protected double height;

    protected BoundingBox hitBox;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BoundingBox getHitBox() {
        hitBox = new BoundingBox(x, y, width, height);
        return hitBox;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void update() {
    }

}
