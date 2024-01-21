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
    private final Game game;
    private final Counter ballCounter;

    /**
     * constructor.
     * @param game as the game
     * @param ballCounter as counter of balls in the game
     */
    public BallRemover(Game game, Counter ballCounter) {
        this.game = game;
        this.ballCounter = ballCounter;
    }


   @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.ballCounter.decrease(1);
    }
}
