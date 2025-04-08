package cz.cvut.fel.pjv.jirsakry.controller;

import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    private final GameWorld gameWorld;

    public Controller(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void handleInput(KeyEvent event) {
        //TODO
        if  (event.getCode() == KeyCode.LEFT) {
            gameWorld.goLeft();
        }
        else if (event.getCode() == KeyCode.RIGHT) {
            gameWorld.goRight();
        }
    }

//    public void update(){
//        //TODO?
//        gameWorld.update();
//    }
}
