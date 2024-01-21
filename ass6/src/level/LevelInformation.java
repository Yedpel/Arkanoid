package level;

import sprites.Block;
import sprites.Sprite;

import java.util.List;

import geometry.Velocity;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int of the number of balls
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list of velocities
     */
// The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int of the speed of the paddle
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int of the width of the paddle
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the string of the level name
     */
// the level name will be displayed at the top of the screen.
    String levelName();

    /**
     * Gets background.
     *
     * @return the background sprite
     */
// Returns a sprite with the background of the level
    Sprite getBackground();

    /**
     * Blocks list.
     *
     * @return the list of the blocks
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return the int of the number of blocks to remove
     */
// Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}