package cz.cvut.fel.pjv.jirsakry.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {
    private long elapsedMillis;
    private final Timeline timeline;
    private boolean isRunning;

    public Timer() {
        this.elapsedMillis = 0;
        this.isRunning = false;

        this.timeline = new Timeline(new KeyFrame(Duration.millis(1), e ->{
            if(isRunning){
                elapsedMillis += 1;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        isRunning = true;
        timeline.play();
    }

    public void stop() {
        isRunning = false;
        timeline.stop();
    }

    public void reset(){
        stop();
        elapsedMillis = 0;
    }

    public String getFormattedTime() {
        long totalSeconds = elapsedMillis / 1000;
        long milliseconds = elapsedMillis % 1000;
        long seconds = totalSeconds % 60;
        long minutes = totalSeconds / 60;
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }

    public boolean isRunning() {
        return isRunning;
    }
}
