package game;

import biuoop.DrawSurface;
import sprites.SpriteCollection;


/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {

    private final SpriteCollection gameScreen;
    private final int numOfAnimations;
    private int countFrom;
    private boolean pause;
    private int count;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.numOfAnimations = (int) (numOfSeconds * 60) / countFrom;
        this.countFrom = countFrom;
        this.pause = false;
        this.count = 0;
    }

    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 25,
                String.valueOf(this.countFrom), 100);
        this.count++;
        if (this.count == this.numOfAnimations) {
            this.countFrom--;
            this.count = 0;
        }
        if (this.countFrom == 0) {
            this.pause = true;
        }
    }

    /**
     * Should stop boolean.
     *
     * @return true if the animation should stop
     */
    public boolean shouldStop() {
        return this.pause;
    }
}