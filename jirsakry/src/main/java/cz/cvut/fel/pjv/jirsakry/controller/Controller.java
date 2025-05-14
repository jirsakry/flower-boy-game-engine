package cz.cvut.fel.pjv.jirsakry.controller;

import cz.cvut.fel.pjv.jirsakry.view.DebugOverlay;
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

    /**
     * Handles key press events and updates the set of active keys or triggers actions.
     *
     * @param event the key event to handle
     */
    public void handleKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.LEFT){
            activeKeys.add(KeyCode.LEFT);
        }
        if(event.getCode() == KeyCode.RIGHT){
            activeKeys.add(KeyCode.RIGHT);
        }
        if(event.getCode() == KeyCode.DOWN){
            activeKeys.add(KeyCode.DOWN);
        }
        if(event.getCode() == KeyCode.UP){
            activeKeys.add(KeyCode.UP);
        }
        if(event.getCode() == KeyCode.SPACE){
            gameWorld.jump();
        }
        if(event.getCode() == KeyCode.F3){
            DebugOverlay.toggleDebug();
        }
    }

    /**
     * Handles key release events and updates the set of active keys.
     *
     * @param event the key event to handle
     */
    public void handleKeyReleased(KeyEvent event) {
        if(event.getCode() == KeyCode.LEFT){
            activeKeys.remove(KeyCode.LEFT);
        }
        if(event.getCode() == KeyCode.RIGHT){
            activeKeys.remove(KeyCode.RIGHT);
        }
        if(event.getCode() == KeyCode.DOWN){
            activeKeys.remove(KeyCode.DOWN);
        }
        if(event.getCode() == KeyCode.UP){
            activeKeys.remove(KeyCode.UP);
        }
    }

    /**
     * Updates the active keys buffer.
     */
    public void update() {
        if (activeKeys.contains(KeyCode.LEFT)) {
            gameWorld.moveLeft();
        }
        if (activeKeys.contains(KeyCode.RIGHT)) {
            gameWorld.moveRight();
        }
        if (activeKeys.contains(KeyCode.DOWN)
                && DebugOverlay.isShowDebug()) {
            gameWorld.moveDown();
        }
        if (activeKeys.contains(KeyCode.UP)
                && DebugOverlay.isShowDebug()) {
            gameWorld.moveUp();
        }
        if (activeKeys.contains(KeyCode.SPACE)) {
            gameWorld.jump();
        }
    }

}
