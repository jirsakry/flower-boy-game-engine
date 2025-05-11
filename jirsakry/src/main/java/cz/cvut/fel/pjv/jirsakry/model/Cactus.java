package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;

public class Cactus extends  GameObject {

    public Cactus(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public BoundingBox getHitBox() {
        hitBox = new BoundingBox(x + 6, y + 4, 21, 27);
        return hitBox;
    }
}
