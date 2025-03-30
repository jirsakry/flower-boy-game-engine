module cz.cvut.fel.pjv.jirsakry.semestral {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.cvut.fel.pjv.jirsakry.semestral to javafx.fxml;
    exports cz.cvut.fel.pjv.jirsakry.semestral;
}