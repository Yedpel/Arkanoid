package game;

import sprites.Ball;
import sprites.Block;
import sprites.HitListener;

/**
 * Ball remover class.
 * a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter ballCounter;

    /**
     * constructor.
     * @param gameLevel as the game
     * @param ballCounter as counter of balls in the game
     */
    public BallRemover(GameLevel gameLevel, Counter ballCounter) {
        this.gameLevel = gameLevel;
        this.ballCounter = ballCounter;
    }


   @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.ballCounter.decrease(1);
    }
}
