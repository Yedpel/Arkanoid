package sprites;

import biuoop.DrawSurface;

import java.util.List;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The SpriteCollection interface.
 */
public class SpriteCollection {
    private final List<Sprite> spriteList;

    /**
     * constructor the list.
     */
    public SpriteCollection() {
        spriteList = new java.util.ArrayList<>();
    }


    /**
     * Add sprite.
     *
     * @param s the sprite we add to list.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}