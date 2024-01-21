package game;

import biuoop.DrawSurface;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private final Counter score;
    private final boolean isWinner;

    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param score    the score
     * @param isWinner the is winner boolean
     */
    public EndScreen(Counter score, boolean isWinner) {
        this.score = score;
        this.isWinner = isWinner;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (isWinner) {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2,
                    "You Win! Your score is " + score.getValue(), 32);
        } else {
            d.drawText(d.getWidth() / 4, d.getHeight() / 2,
                    "Game Over. Your score is " + score.getValue(), 32);
        }
//        if (this.ks.isPressed("space")) {
//            this.setStop(true);
//        }

    }

    //The "end screen" should persist until the space key is pressed.
    // After the space key is pressed, your program should terminate.
    @Override
    public boolean shouldStop() {
        return stop;
    }

    /**
     * Sets stop.
     *
     * @param bool the bool that indicate if the animation should stop
     */
    public void setStop(boolean bool) {
        this.stop = bool;
    }
}
