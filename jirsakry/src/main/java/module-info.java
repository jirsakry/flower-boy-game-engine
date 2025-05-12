module cz.cvut.fel.pjv.jirsakry {
    requires javafx.controls;
    requires com.fasterxml.jackson.databind;
    requires java.logging;

    exports cz.cvut.fel.pjv.jirsakry;
    exports cz.cvut.fel.pjv.jirsakry.model;
    exports cz.cvut.fel.pjv.jirsakry.view;
    exports cz.cvut.fel.pjv.jirsakry.scenes;
}