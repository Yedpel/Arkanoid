package sprites;

import biuoop.DrawSurface;


import java.util.List;

/**
 * The type Sprite collection.
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
        List<Sprite> copySpriteList = new java.util.ArrayList<>(spriteList);
        for (Sprite sprite : copySpriteList) {
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

    /**
     * Remove sprite.
     *
     * @param s the sprite we remove from list.
     */
    public void removeSprite(Sprite s) {
        if (s != null) {
            spriteList.remove(s);
        }
    }
}