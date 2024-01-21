package game;

import collision.Collidable;
import collision.Enclosure;
import geometry.Point;
import geometry.Rectangle;
import collision.GameEnvironment;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


import java.awt.Color;

/**
 * The type Game.
 *
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 */
public class Game {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BOUNDS_SIZE = 20;
    private final GUI gui = new GUI("Ass3Game", WIDTH, HEIGHT);
    private final Enclosure enclosure = new Enclosure(0, 0, WIDTH, HEIGHT,
            Color.GRAY);
    private final Counter blockCounter = new Counter();
    private final BlockRemover blockRemover = new BlockRemover(this,
            blockCounter);
    private final Counter ballCounter = new Counter();
    private final BallRemover ballRemover = new BallRemover(this,
            ballCounter);
    private final Counter score = new Counter();
    private final ScoreTrackingListener scoreTracking =
            new ScoreTrackingListener(score);
    private final ScoreIndicator scoreIndicator = new ScoreIndicator(score);


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
        Paddle paddle = new Paddle(this);
        paddle.addToGame(this);
    }

    /**
     * init the bricks.
     */
    public void initBricks() {
        int brickWidth = WIDTH / 15;
        int brickHeight = HEIGHT / 20;
        double x = WIDTH - BOUNDS_SIZE - brickWidth;
        double y = BOUNDS_SIZE * 3 + brickHeight;
        int numOfBricks = 12;
        Color[] colors = {Color.PINK, Color.ORANGE, Color.YELLOW, Color.GREEN,
                Color.BLUE};
        for (int i = 0; i < 5; i++) {
            Color color = colors[i];
            for (int j = 0; j < numOfBricks; j++) {
                Block block = new Block(new Rectangle(new Point(x, y),
                        brickWidth, brickHeight), color);
                block.addToGame(this);
                x = x - brickWidth;
                blockCounter.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracking);
            }
            x = WIDTH - BOUNDS_SIZE - brickWidth;
            y = y + brickHeight;
            numOfBricks--;
        }
    }

    /**
     * init the balls.
     */
    public void initBalls() {
        Ball[] balls = new Ball[3];
        for (int i = 0; i < 3; i++) {
            balls[i] = new Ball(200 + i * 50, 400, BOUNDS_SIZE / 2, Color.RED);
            balls[i].setVelocity(5, 5);
            balls[i].setGameEnvironment(environment);
            balls[i].setEnclosure(new Enclosure(0, 0, WIDTH, HEIGHT));
            balls[i].addToGame(this);
            ballCounter.increase(1);
        }
    }

    /**
     * run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (blockCounter.getValue() > 0 && ballCounter.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (blockCounter.getValue() == 0) {
            score.increase(100);
        }

        gui.close();
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