package cz.cvut.fel.pjv.jirsakry.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.logging.Logger;

public class Timer {
    private static final Logger LOGGER = Logger.getLogger(Timer.class.getName());
    private final Timeline timeline;
    private long elapsedMillis;
    private boolean isRunning;

    public Timer() {
        this.elapsedMillis = 0;
        this.isRunning = false;

        this.timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            if (isRunning) {
                elapsedMillis += 10;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts the timer and begins measuring elapsed time.
     */
    public void start() {
        isRunning = true;
        timeline.play();
        LOGGER.info("Timer started");
    }

    /**
     * Stops the timer and pauses time measurement.
     */
    public void stop() {
        isRunning = false;
        timeline.stop();
        LOGGER.info("Timer stopped");
    }

    /**
     * Stops the timer and resets the elapsed time to zero.
     */
    public void reset() {
        stop();
        elapsedMillis = 0;
        LOGGER.info("Timer reset");
    }

    /**
     * Returns the current elapsed time formatted as MM:SS:ms.
     *
     * @return formatted time as a string
     */
    public String getFormattedTime() {
        long totalSeconds = elapsedMillis / 1000;
        long milliseconds = (elapsedMillis % 1000) / 10;
        long seconds = totalSeconds % 60;
        long minutes = totalSeconds / 60;
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds);
    }
}
