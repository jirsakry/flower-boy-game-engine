package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;
import javafx.scene.shape.Rectangle;

abstract class GameObject {

    protected double x;
    protected double y;

    protected double width;
    protected double height;

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public BoundingBox getHitBox() {
        return new BoundingBox(x, y, width, height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public BoundingBox getTopEdge() {
        return new BoundingBox(x, y, width, 1);
    }

    public BoundingBox getBottomEdge() {
        return new BoundingBox(x, y + height - 1, width, 1);
    }

    public BoundingBox getLeftEdge() {
        return new BoundingBox(x, y, 1, height);
    }

    public BoundingBox getRightEdge() {
        return new BoundingBox(x + width - 1, y, 1, height);
    }

    public void update(){
    }

}
