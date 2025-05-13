package cz.cvut.fel.pjv.jirsakry.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GameWorld{
    public static double SCREEN_WIDTH = 1280;
    public static double SCREEN_HEIGHT = 768;

    public static int TILE_SIZE = 64;
    public static int COLS = 20;
    public static int ROWS = 12;

    private static final Logger LOGGER = Logger.getLogger(GameWorld.class.getName());

    private GameState gameState;

    private GameConfig config;

    private List <int[][]> levelMaps;
    private Level currentLevel;
    private int currentLevelIndex = 0;

    private List<Level> levels;
    private Level level0;
    private Level level1;

    private Player player;

    private int playerFlowerCount;


    // timer
    Timer timer = new Timer();

    public void init(){
        config = ConfigManager.loadConfig();
        levels = new ArrayList<>();
        levelMaps = config.getLevels();

        level0 = new Level(levelMaps.get(0));
        levels.add(level0);
        level1 = new Level(levelMaps.get(1));
        levels.add(level1);
        currentLevel = level0;
        currentLevelIndex = 0;

        player = new Player(40, 600, 64, 64,
                config.getPlayerSpeed(), config.getPlayerMaxHealth(), config.getPlayerMaxHealth(),
                config.getPlayerJumpStrength(), config.getGravity(), currentLevel);

        newGame();
    }

    public void newGame() {
        LOGGER.info(player.getPlayerInfo());
        LOGGER.info("currentLevelIndex: " + currentLevelIndex);

        player = new Player(40, 600, 64, 64, // TODO: Maybe more elegant way?
                config.getPlayerSpeed(), config.getPlayerMaxHealth(), config.getPlayerMaxHealth(),
                config.getPlayerJumpStrength(), config.getGravity(), currentLevel);
        player.reset();
        LOGGER.info(player.getPlayerInfo());

        playerFlowerCount = 0;
        for(Flower flower: currentLevel.getFlowers()){
            flower.setCollected(false);
        }

        timer.reset();

        if(gameState == GameState.MAIN_MENU){
            timer.stop();
        }
        else{
            gameState = GameState.PLAYING;
            timer.start();
        }
    }

    public void update(){
        if(playerFlowerCount == currentLevel.getFlowerCount()
        && gameState != GameState.WIN) {
            LOGGER.info(player.getPlayerInfo());
            gameState = GameState.WIN;
            timer.stop();
            currentLevelIndex++;
            if (currentLevelIndex < (levelMaps.size())) {
                currentLevel = levels.get(currentLevelIndex);
            }
        }
        if(currentLevelIndex < (levelMaps.size())) {
            currentLevel = levels.get(currentLevelIndex);
        }

        player.update();
        checkCollisions();
    }

    private void checkCollisions(){
        checkFlowerPickUp();
        checkCactusHit();
    }

    private void checkFlowerPickUp() {
        for (Flower flower : currentLevel.getFlowers()) {
            if(!(flower.isCollected())) {
                if (flower.getHitBox().intersects(player.getHitBox())) {
                    flower.setCollected(true);
                    playerFlowerCount++;
                }
            }
        }
    }

    private void checkCactusHit() {
        for (Cactus cactus : currentLevel.getCacti()){
            if(cactus.getHitBox().intersects(player.getHitBox())) {
                player.setPlayerState(PlayerState.DEATH);

            }
        }
    }

    public void moveRight() {
        player.moveRight();
    }
    public void moveLeft() {
        player.moveLeft();
    }
    public void moveDown() {
        player.moveDown();
    }
    public void moveUp() {
        player.moveUp();
    }
    public void jump() {
        player.jump();
    }


    public Player getPlayer() {
        return player;
    }

    public int getPlayerFlowerCount() {
        return playerFlowerCount;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public void setCurrentLevelIndex(int currentLevelIndex) {
        this.currentLevelIndex = currentLevelIndex;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
