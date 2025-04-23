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
        else if(event.getCode() == KeyCode.DOWN){
            activeKeys.add(KeyCode.DOWN);
        }
        else if(event.getCode() == KeyCode.UP){
            activeKeys.add(KeyCode.UP);
        }
        else if(event.getCode() == KeyCode.SPACE){
            activeKeys.add(KeyCode.SPACE);
        }
    }
    public void handleKeyReleased(KeyEvent event) {
        if(event.getCode() == KeyCode.LEFT){
            activeKeys.remove(KeyCode.LEFT);
        }
        else if(event.getCode() == KeyCode.RIGHT){
            activeKeys.remove(KeyCode.RIGHT);
        }
        else if(event.getCode() == KeyCode.DOWN){
            activeKeys.remove(KeyCode.DOWN);
        }
        else if(event.getCode() == KeyCode.UP){
            activeKeys.remove(KeyCode.UP);
        }
        else if(event.getCode() == KeyCode.SPACE){
            activeKeys.remove(KeyCode.SPACE);
        }
    }

    public void update() {
//        System.out.println(activeKeys);
        if (activeKeys.contains(KeyCode.LEFT)) {
            gameWorld.moveLeft();
        }
        if (activeKeys.contains(KeyCode.RIGHT)) {
            gameWorld.moveRight();
        }
        if (activeKeys.contains(KeyCode.DOWN)) {
            gameWorld.moveDown();
        }
        if (activeKeys.contains(KeyCode.UP)){
            gameWorld.moveUp();
        }
        if (activeKeys.contains(KeyCode.SPACE)) {
            gameWorld.jump();
        }
    }
}
