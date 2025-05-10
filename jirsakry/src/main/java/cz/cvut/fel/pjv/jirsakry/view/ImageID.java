package cz.cvut.fel.pjv.jirsakry.view;

public enum ImageID {
    BACKGROUND("background.png"),
    CHARACTER("character.png"),
    CHARACTER_IDLE("idle.png"),
    CHARACTER_RUN("run.png"),
    CHARACTER_JUMP("jump.png"),
    CHARACTER_JUMP_UP("jumpUp.png"),
    CHARACTER_JUMP_DOWN("jumpDown.png"),
    CHARACTER_DEATH("death.png"),
    FLOWER("Flower Pot 5 - COLORFUL.png"),
    CACTUS("cactus.png"),
    GRASS("grass_block.png"),;


    private final String fileName;
    private double width;
    private double height;


    ImageID(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
