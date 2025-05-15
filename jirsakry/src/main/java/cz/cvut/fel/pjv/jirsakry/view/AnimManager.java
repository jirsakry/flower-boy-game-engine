package cz.cvut.fel.pjv.jirsakry.view;


import cz.cvut.fel.pjv.jirsakry.model.GameWorld;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.Map;

public class AnimManager {
    private final Map<ImageID, Image> images;

    private final Image[] idleAnim;
    private final Image[] runAnim;
    private final Image[] jumpUpAnim;
    private final Image[] jumpDownAnim;
    private final Image[] deathAnim;

    private final Image[] idleAnimMirrored;
    private final Image[] runAnimMirrored;
    private final Image[] jumpUpAnimMirrored;
    private final Image[] jumpDownAnimMirrored;
    private final Image[] deathAnimMirrored;

    private final int animSpeed = 1;
    private int animTick = 0;
    private int animFrame = 0;

    private int idleLength = 6;
    private int runLength = 9;
    private final int jumpLength = 1;
    private int deathLength = 9;


    /**
     * Instantiates and prepares everything necessary.
     *
     * @param images map of images and their names
     */
    public AnimManager(Map<ImageID, Image> images) {
        idleAnim = new Image[idleLength];
        runAnim = new Image[runLength];
        jumpUpAnim = new Image[jumpLength];
        jumpDownAnim = new Image[jumpLength];
        deathAnim = new Image[deathLength];

        idleAnimMirrored = new Image[idleLength];
        runAnimMirrored = new Image[runLength];
        jumpUpAnimMirrored = new Image[jumpLength];
        jumpDownAnimMirrored = new Image[jumpLength];
        deathAnimMirrored = new Image[deathLength];

        this.images = images;
    }

    public void updateAnimationTick(int animationLength) { // based on a tutorial
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animFrame++;
            if (animFrame >= animationLength) {
                animFrame = 0;
            }
        }
    }

    public void loadAnimations() {
        loadIdleAnim();
        mirrorAnimation(idleAnim, idleAnimMirrored);

        loadRunAnim();
        mirrorAnimation(runAnim, runAnimMirrored);

        loadJumpAnim();
        mirrorAnimation(jumpUpAnim, jumpUpAnimMirrored);
        mirrorAnimation(jumpDownAnim, jumpDownAnimMirrored);

        loadDeathAnim();
        mirrorAnimation(deathAnim, deathAnimMirrored);
    }


    private void loadIdleAnim() {
        Image spriteSheet = images.get(ImageID.CHARACTER_IDLE);
        double frameSize = GameWorld.TILE_SIZE;
        int frameCount = (int) (spriteSheet.getWidth() / frameSize);
        idleLength = Math.min(frameCount, idleAnim.length);
        for (int i = 0; i < idleLength; i++) {
            idleAnim[i] = new WritableImage(spriteSheet.getPixelReader(),
                    (int) (i * frameSize), 0,
                    (int) frameSize, (int) frameSize);
        }
    }

    private void loadRunAnim() {
        Image spriteSheet = images.get(ImageID.CHARACTER_RUN);
        double frameSize = GameWorld.TILE_SIZE;
        int frameCount = (int) (spriteSheet.getWidth() / frameSize);
        runLength = Math.min(frameCount, runAnim.length);
        for (int i = 0; i < runLength; i++) {
            runAnim[i] = new WritableImage(spriteSheet.getPixelReader(),
                    (int) (i * frameSize), 0,
                    (int) frameSize, (int) frameSize);
        }
    }

    private void loadDeathAnim() {
        Image spriteSheet = images.get(ImageID.CHARACTER_DEATH);
        double frameSize = GameWorld.TILE_SIZE;
        int frameCount = (int) (spriteSheet.getWidth() / frameSize);
        deathLength = Math.min(frameCount, deathAnim.length);
        for (int i = 0; i < deathLength; i++) {
            deathAnim[i] = new WritableImage(spriteSheet.getPixelReader(),
                    (int) (i * frameSize), 0,
                    (int) frameSize, (int) frameSize);
        }
    }

    private void loadJumpAnim() {
        jumpDownAnim[0] = images.get(ImageID.CHARACTER_JUMP_DOWN);
        jumpUpAnim[0] = images.get(ImageID.CHARACTER_JUMP_UP);
    }

    private Image createMirroredImage(Image image) {
        WritableImage mirrored = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                mirrored.getPixelWriter().setColor((int) image.getWidth() - x - 1, y, image.getPixelReader().getColor(x, y));
            }
        }
        return mirrored;
    }

    private void mirrorAnimation(Image[] original, Image[] mirrored) { // generated
        for (int i = 0; i < original.length; i++) {
            if (original[i] != null) {
                mirrored[i] = createMirroredImage(original[i]);
            }
        }
    }

    public int getAnimFrame() {
        return animFrame;
    }

    public Image[] getIdleAnim() {
        return idleAnim;
    }

    public Image[] getRunAnim() {
        return runAnim;
    }

    public Image[] getJumpUpAnim() {
        return jumpUpAnim;
    }

    public Image[] getJumpDownAnim() {
        return jumpDownAnim;
    }

    public Image[] getDeathAnim() {
        return deathAnim;
    }

    public Image[] getIdleAnimMirrored() {
        return idleAnimMirrored;
    }

    public Image[] getRunAnimMirrored() {
        return runAnimMirrored;
    }

    public Image[] getJumpUpAnimMirrored() {
        return jumpUpAnimMirrored;
    }

    public Image[] getJumpDownAnimMirrored() {
        return jumpDownAnimMirrored;
    }

    public Image[] getDeathAnimMirrored() {
        return deathAnimMirrored;
    }

    public int getIdleLength() {
        return idleLength;
    }

    public int getRunLength() {
        return runLength;
    }

    public int getJumpLength() {
        return jumpLength;
    }

    public int getDeathLength() {
        return deathLength;
    }
}


