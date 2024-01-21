package sprites;

import collision.Collidable;
import collision.Enclosure;
import geometry.Point;
import geometry.Rectangle;
import collision.GameEnvironment;


import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * Game class.
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

    /**
     * get WIDTH.
     *
     * @return WIDTH
     */
    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * get HEIGHT.
     *
     * @return HEIGHT
     */
    public int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * get BOUNDS_SIZE.
     *
     * @return BOUNDS_SIZE
     */
    public int getBoundsSize() {
        return BOUNDS_SIZE;
    }

    /**
     * get GUI.
     *
     * @return gui
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
    }

    /**
     * init enclosure.
     */
    public void initEnclosure() {
        enclosure.addToGame(this);
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
        //lower bound
        block = new Block(new Rectangle(new Point(0,
                HEIGHT - BOUNDS_SIZE), WIDTH, BOUNDS_SIZE), Color.black);
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
            }
            x = WIDTH - BOUNDS_SIZE - brickWidth;
            y = y + brickHeight;
            numOfBricks--;
        }
    }

    /**
     * init the 2 balls.
     */
    public void initBalls() {
        Ball ball1 = new Ball(50, 50, BOUNDS_SIZE / 2, Color.RED);
        ball1.setVelocity(5, 5);
        ball1.setGameEnvironment(environment);
        ball1.setEnclosure(new Enclosure(0, 0, WIDTH, HEIGHT));
        ball1.addToGame(this);
        Ball ball2 = new Ball(150, 400, BOUNDS_SIZE / 2, Color.RED);
        ball2.setVelocity(5, 5);
        ball2.setGameEnvironment(environment);
        ball2.setEnclosure(new Enclosure(0, 0, WIDTH, HEIGHT));
        ball2.addToGame(this);
    }

    /**
     * run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
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

    }

}