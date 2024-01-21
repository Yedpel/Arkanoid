package sprites;

import biuoop.DrawSurface;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     * draw the sprite to the screen
     * @param d the d
     */
    void drawOn(DrawSurface d);
    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    void timePassed();
}