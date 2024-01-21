package sprites;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import collision.Collidable;


import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Block.
 */
public class Block implements Collidable, Sprite {
    private final Rectangle rectangle;
    private final java.awt.Color color;

    /**
     * constructor.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Gets color.
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // epsilon for double comparison
        double epsilon = Math.pow(10, -7);
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // if the collision point is on the upper or lower edge of the block
        if (Math.abs(collisionPoint.getY()
                - this.rectangle.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY()
                - (this.rectangle.getUpperLeft().getY()
                + this.rectangle.getHeight())) < epsilon) {
            dy = -dy;
        }
        // if the collision point is on the left or right edge of the block
        if (Math.abs(collisionPoint.getX()
                - this.rectangle.getUpperLeft().getX()) < epsilon
                || Math.abs(collisionPoint.getX()
                - (this.rectangle.getUpperLeft().getX()
                + this.rectangle.getWidth())) < epsilon) {
            dx = -dx;
        }
        // return the new velocity
        return new Velocity(dx, dy);
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.lightGray);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
