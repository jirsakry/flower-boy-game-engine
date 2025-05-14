package cz.cvut.fel.pjv.jirsakry.model;

import javafx.geometry.BoundingBox;

public class Shield extends Item {

    public Shield(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public BoundingBox getHitBox() {
        hitBox = new BoundingBox(x + 6, y + 6, 19, 21);
        return hitBox;
    }
}
