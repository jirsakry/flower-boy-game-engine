package cz.cvut.fel.pjv.jirsakry.controller;

import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.util.HashSet;
import java.util.Set;

public class Controller {
    private final GameWorld gameWorld;
    private final Set<KeyCode> activeKeys = new HashSet<>();

    public Controller(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void handleKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.LEFT){
            activeKeys.add(KeyCode.LEFT);
        }
        else if(event.getCode() == KeyCode.RIGHT){
            activeKeys.add(KeyCode.RIGHT);
        }
    }
    public void handleKeyReleased(KeyEvent event) {
        if(event.getCode() == KeyCode.LEFT){
            activeKeys.remove(KeyCode.LEFT);
            gameWorld.setPlayerSpeed(0);
        }
        else if(event.getCode() == KeyCode.RIGHT){
            activeKeys.remove(KeyCode.RIGHT);
            gameWorld.setPlayerSpeed(0);
        }
        else if(event.getCode() == KeyCode.SPACE){
            activeKeys.remove(KeyCode.SPACE);
        }
    }

    public void update() {
        if (activeKeys.contains(KeyCode.LEFT)) {
            gameWorld.goLeft();
        }
        else if (activeKeys.contains(KeyCode.RIGHT)) {
            gameWorld.goRight();
        }
        else if (activeKeys.contains(KeyCode.SPACE)) {
            gameWorld.jump();
        }
    }
}
