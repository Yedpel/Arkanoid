package sprites;

import geometry.Point;
import geometry.Rectangle;
import collision.Collidable;
import geometry.Velocity;
import game.Game;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Block block;
    private final int paddleMove = 5;
    private final double paddleWidth;
    private final double paddleHeight;
    private final int boundsLeft;
    private final int boundsRight;


    /**
     * constructor.
     *
     * @param game the game
     */
    public Paddle(Game game) {
        this.paddleWidth = (double) game.getWIDTH() / 3;
        this.paddleHeight = (double) game.getBoundsSize() / 2 - 3;
        this.block = new
                Block(new Rectangle(new Point((double) game.getWIDTH() / 2
                - paddleWidth / 2, game.getHEIGHT() - paddleHeight
                - game.getBoundsSize()), paddleWidth, paddleHeight),
                Color.CYAN);
        this.boundsLeft = game.getBoundsSize();
        this.boundsRight = game.getWIDTH() - game.getBoundsSize();
        this.keyboard = game.getGui().getKeyboardSensor();
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        double newX = this.block.getCollisionRectangle().getUpperLeft().getX()
                - paddleMove;
        if (newX < boundsLeft) {
            block = new Block(new Rectangle(new Point(boundsLeft,
                    block.getCollisionRectangle().getUpperLeft().getY()),
                    paddleWidth, paddleHeight), block.getColor());
        } else {
            block = new Block(new Rectangle(new Point(newX,
                    block.getCollisionRectangle().getUpperLeft().getY()),
                    paddleWidth, paddleHeight), block.getColor());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        double newX = this.block.getCollisionRectangle().getUpperLeft().getX()
                + paddleMove;
        if (newX + paddleWidth > boundsRight) {
            block = new Block(new Rectangle(new Point(boundsRight
                    - paddleWidth,
                    block.getCollisionRectangle().getUpperLeft().getY()),
                    paddleWidth, paddleHeight), block.getColor());
        } else {
            block = new Block(new Rectangle(new Point(newX,
                    block.getCollisionRectangle().getUpperLeft().getY()),
                    paddleWidth, paddleHeight), block.getColor());
        }
    }

    // Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));

        double x = collisionPoint.getX();

        if (x >= getCollisionRectangle().getUpperLeft().getX() + 3
                * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        if (x >= getCollisionRectangle().getUpperLeft().getX() + 4
                * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        if (x <= getCollisionRectangle().getUpperLeft().getX()
                + getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (x <= getCollisionRectangle().getUpperLeft().getX() + 2
                * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }

        return Velocity.fromAngleAndSpeed(0, speed);
    }

    /**
     * Add to game.
     * add this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}