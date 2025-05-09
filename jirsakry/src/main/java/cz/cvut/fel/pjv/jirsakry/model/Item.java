package cz.cvut.fel.pjv.jirsakry.model;

abstract class Item extends GameObject {

    protected boolean collected = false;

    public Item(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
