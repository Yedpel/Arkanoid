package sprites;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import collision.Collidable;
import game.Game;


import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
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
        this.hitListeners = new java.util.ArrayList<>();
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify hit.
     * @param hitter the ball that hit the block
     * @param collisionPoint the point of the collision
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
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

    /**
     * remove from game.Game.
     *
     * @param game the game will play
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notify hit.
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
