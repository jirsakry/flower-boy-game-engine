package cz.cvut.fel.pjv.jirsakry.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameWorldTest {
    private GameWorld gameWorld;
    private Level testLevel;

    @BeforeEach
    void setUp() {
        gameWorld = new GameWorld();
        gameWorld.setTimer(new DummyTimer());
        gameWorld.init();

        testLevel = new Level(new int[][] {
                {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        });
        gameWorld.setCurrentLevel(testLevel);
        gameWorld.getPlayer().setCurrentLevel(testLevel);
        List<Level> levels = new ArrayList<>();
        levels.add(testLevel);
        levels.add(testLevel);
        gameWorld.setLevels(levels);
    }

    @Test
    void testPlayerStartsAtInitialPosition() {
        Player player = gameWorld.getPlayer();
        assertNotNull(player);
        assertEquals(40.0, player.getX());
        assertEquals(600.0, player.getY());
    }

    @Test
    void testPlayerMoveRight() {
        Player player = gameWorld.getPlayer();
        double startX = player.getX();
        gameWorld.moveRight();
        gameWorld.update();
        assertTrue(player.getX() > startX, "Player should move right");
    }

    @Test
    void testPlayerMoveLeft() {
        Player player = gameWorld.getPlayer();
        double startX = player.getX();
        gameWorld.moveLeft();
        gameWorld.update();
        assertTrue(player.getX() < startX, "Player should move left");
    }

    @Test
    void testPlayerCannotPickUpShieldTwice() {
        Shield shield = testLevel.getShields().getFirst();
        Player player = gameWorld.getPlayer();

        // had to do this because of hotbox update
        player.setX(-20);
        player.setY(-9);
        shield.setX(0);
        shield.setY(0);
        gameWorld.update();

        assertTrue(gameWorld.getPlayer().isHoldingShield());
        assertTrue(shield.isCollected());
        gameWorld.update();
        assertTrue(gameWorld.getPlayer().isHoldingShield(), "Should hold only one shield");
        assertTrue(shield.isCollected());
    }


    @Test
    void testCollectFlowerIncreaseCount() {
        int initialFlowerCount = gameWorld.getPlayerFlowerCount();
        Flower flower = testLevel.getFlowers().getFirst();
        Player player = gameWorld.getPlayer();

        player.setX(flower.getX());
        player.setY(flower.getY());
        gameWorld.update();

        assertTrue(flower.isCollected());
        assertEquals(initialFlowerCount + 1, gameWorld.getPlayerFlowerCount());
    }

    @Test
    void testPlayerDestroysCactusWithShield() {
        Cactus cactus = testLevel.getCacti().getFirst();
        Player player = gameWorld.getPlayer();
        player.setHoldingShield(true);

        player.setX(cactus.getX());
        player.setY(cactus.getY());
        gameWorld.update();

        assertTrue(cactus.isDestroyed(), "Cactus should have been destroyed");
        assertFalse(player.isHoldingShield(), "Player should not be holding the shield anymore");
    }

    @Test
    void testPlayerTakesDamageFromCactusWithoutShield() {
        Cactus cactus = testLevel.getCacti().getFirst();
        Player player = gameWorld.getPlayer();
        player.setHoldingShield(false);

        int healthBefore = player.getCurrentHealth();

        player.setX(cactus.getX());
        player.setY(cactus.getY());
        gameWorld.update();

        assertTrue(player.getCurrentHealth() < healthBefore, "Player should lose lives without the shield");
    }

}
