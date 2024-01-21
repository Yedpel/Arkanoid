package sprites;

import biuoop.DrawSurface;
import game.Counter;
import game.Game;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * constructor.
     *
     * @param score - the score counter
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(375, 15, "Score: " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add the score indicator to the game.
     *
     * @param game - the game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
