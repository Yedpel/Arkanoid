package game;


import collision.Collidable;
import collision.Enclosure;
import geometry.Point;
import geometry.Rectangle;
import collision.GameEnvironment;
import level.LevelInformation;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BOUNDS_SIZE = 20;
    private final GUI gui;
    private final Enclosure enclosure = new Enclosure(0, 0, WIDTH, HEIGHT,
            Color.GRAY);
    private final Counter blockCounter = new Counter();
    private final BlockRemover blockRemover = new BlockRemover(this,
            blockCounter);
    private final Counter ballCounter = new Counter();
    private final BallRemover ballRemover = new BallRemover(this,
            ballCounter);
    private final Counter score;
    private final ScoreTrackingListener scoreTracking;
    private final ScoreIndicator scoreIndicator;

    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInfo;

    /**
     * constructor.
     *
     * @param levelInfo the level info
     * @param runner    the runner
     * @param keyboard  the keyboard
     * @param score     the score
     * @param gui       the gui
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner runner,
                     KeyboardSensor keyboard, Counter score, GUI gui) {
        this.gui = gui;
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInfo = levelInfo;
        this.score = score;
        this.scoreTracking = new ScoreTrackingListener(score);
        this.scoreIndicator = new ScoreIndicator(score);
    }


    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
            runner.run(new CountdownAnimation(2, 3, sprites));
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.drawText(575, 15, "Level Name: " + levelInfo.levelName(), 15);


        if (blockCounter.getValue() == 0) {
            this.running = false;
            score.increase(100);
        }
        if (ballCounter.getValue() == 0) {
            this.running = false;
        }

    }

    /**
     * run the game -- start the animation loop.
     */
    public void run() {
        this.running = true;
        runner.run(new CountdownAnimation(2, 3, sprites));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * getBallsCounter.
     *
     * @return ballCounter balls counter
     */
    public int getBallsCounter() {
        return ballCounter.getValue();
    }

    /**
     * get WIDTH.
     *
     * @return WIDTH the width
     */
    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * get HEIGHT.
     *
     * @return HEIGHT height
     */
    public int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * get BOUNDS_SIZE.
     *
     * @return BOUNDS_SIZE bounds size
     */
    public int getBoundsSize() {
        return BOUNDS_SIZE;
    }

    /**
     * get GUI.
     *
     * @return gui gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * addCollidable.
     *
     * @param c as a gaven collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite.
     *
     * @param s as a gaven sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }


    /**
     * initialize the game.
     */
    public void initialize() {
        initEnclosure();
        addSprite(levelInfo.getBackground());
        initBounds();
        initPaddle();
        initBricks();
        initBalls();
        initScoreIndicator();
    }

    /**
     * init enclosure.
     */
    public void initEnclosure() {
        enclosure.addToGame(this);
    }

    /**
     * Init score indicator.
     */
    public void initScoreIndicator() {
        scoreIndicator.addToGame(this);
    }

    /**
     * init the bounds.
     */
    public void initBounds() {
        //upper bound
        Block block = new Block(new Rectangle(new Point(0, 0), WIDTH,
                BOUNDS_SIZE), Color.black);
        block.addToGame(this);
        //left bound
        block = new Block(new Rectangle(new Point(0, 0), BOUNDS_SIZE,
                HEIGHT), Color.black);
        block.addToGame(this);
        //right bound
        block = new Block(new Rectangle(new Point(WIDTH - BOUNDS_SIZE, 0),
                BOUNDS_SIZE, HEIGHT), Color.black);
        block.addToGame(this);
        //lower bound - "death region" - the ball will disappear if it hits it
        block = new Block(new Rectangle(new Point(0,
                HEIGHT), WIDTH, 0), Color.GRAY);
        block.addHitListener(this.ballRemover);
        block.addToGame(this);
    }

    /**
     * init the paddle.
     */
    public void initPaddle() {
        Paddle paddle = new Paddle(this, levelInfo.paddleSpeed(), levelInfo.paddleWidth());
        paddle.addToGame(this);
    }

    /**
     * init the bricks.
     */
    public void initBricks() {
        for (Block block : levelInfo.blocks()) {
            block.addToGame(this);
            blockCounter.increase(1);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracking);
        }
    }


    /**
     * init the balls.
     */
    public void initBalls() {
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 550), 5, Color.red);
            ball.setEnclosure(new Enclosure(0, 0, WIDTH, HEIGHT));
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
            ballCounter.increase(1);
            ball.setGameEnvironment(environment);
        }


    }

    /**
     * removeCollidable.
     * remove the given collidable from the game.
     *
     * @param c as a given collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removeSprite.
     * remove the given sprite from the game.
     *
     * @param s as a given sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

}