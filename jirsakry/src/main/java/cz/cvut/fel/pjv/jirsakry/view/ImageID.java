package cz.cvut.fel.pjv.jirsakry.view;

public enum ImageID {
    CHARACTER_IDLE("idle.png"),
    BACKGROUND("background.png"),;

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
