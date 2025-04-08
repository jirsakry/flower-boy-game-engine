package cz.cvut.fel.pjv.jirsakry.model;

abstract class GameObject {

    protected double x;
    protected double y;

    protected double width;
    protected double height;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
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

    public void update(){

    }

}
