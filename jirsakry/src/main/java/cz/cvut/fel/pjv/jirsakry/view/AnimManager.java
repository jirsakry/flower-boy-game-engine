package cz.cvut.fel.pjv.jirsakry.view;


import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.Map;

public class AnimManager {
    private final Map<ImageID, Image> images;

    private Image[] idleAnim;
    private Image[] runAnim;

    private int animSpeed = 15;
    private int animTick;
    private int animFrame = 0;

    private int idleLength = 6;
    private int runLength = 9;

    public AnimManager(Map<ImageID, Image> images) {
        idleAnim = new Image[idleLength];
        runAnim = new Image[runLength];
        this.images = images;
    }

    public void updateAnimationTick(){ // based on a tutorial
        animTick++;
        if(animTick >= animSpeed){
            animTick = 0;
            animFrame++;
            if(animFrame >= idleLength){ // TODO: WORKS ONLY FOR ONE LENGTH OF ANIMATION
                animFrame = 0;
            }
        }
    }

    public void loadAnimations() {
     loadIdleAnim();
     loadRunAnim();
    }

    private void loadIdleAnim(){ // semi-generated
        Image spriteSheet = images.get(ImageID.CHARACTER_IDLE);
        double frameWidth = spriteSheet.getWidth() / idleLength;
        double frameHeight = spriteSheet.getHeight();
        for (int i = 0; i < idleLength; i++){
            idleAnim[i] = new WritableImage(spriteSheet.getPixelReader(),
                    (int) (i * frameWidth), 0,
                    (int) frameWidth, (int) frameHeight);
        }
    }

    private void loadRunAnim(){ // semi-generated
        Image spriteSheet = images.get(ImageID.CHARACTER_RUN);
        double frameWidth = spriteSheet.getWidth() / runLength;
        double frameHeight = spriteSheet.getHeight();
        for (int i = 0; i < runLength; i++){
            runAnim[i] = new WritableImage(spriteSheet.getPixelReader(),
                    (int) (i * frameWidth), 0,
                    (int) frameWidth, (int) frameHeight);
        }
    }

    public Image[] getIdleAnim() {
        return idleAnim;
    }

    public Image[] getRunAnim() {
        return runAnim;
    }

    public int getAnimFrame() {
        return animFrame;
    }
}
