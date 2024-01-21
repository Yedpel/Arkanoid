import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The type Enclosure.
 *
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Enclosure.
 * Enclosure is a rectangle with a background color.
 * Its horizontal and vertical lines are parallel to the x and y axes.
 * It is used to represent frame or the game's borders.
 * It is used to limit the movement of the ball.
 */
public class Enclosure {
    private final int xTL, xBR, yTL, yBR;
    private Color color = Color.WHITE;

    /**
     * Constructor.
     *
     * @param xTL the top-left point's x coordinate.
     * @param yTL the top-left point's y coordinate.
     * @param xBR the bottom-right point's x coordinate.
     * @param yBR the bottom-right point's y coordinate.
     */
    public Enclosure(int xTL, int yTL, int xBR, int yBR) {
        this.xTL = xTL;
        this.yTL = yTL;
        this.xBR = xBR;
        this.yBR = yBR;
    }

    /**
     * Constructor.
     *
     * @param xTL   the top-left point's x coordinate.
     * @param yTL   the top-left point's y coordinate.
     * @param xBR   the bottom-right point's x coordinate.
     * @param yBR   the bottom-right point's y coordinate.
     * @param color the enclosure's background color.
     */
    public Enclosure(int xTL, int yTL, int xBR, int yBR, Color color) {
        this(xTL, yTL, xBR, yBR);

        this.color = color;
    }

    /**
     * Gets random point.
     *
     * @return random point within the enclosure.
     */
    public Point getRandomPoint() {
        Random rand = new Random();
        return new Point(rand.nextInt(xBR - xTL) + xTL,
                rand.nextInt(yBR - yTL) + yTL);
    }


    /**
     * Gets random line.
     *
     * @return random line within the enclosure.
     */
    public Line getRandomLine() {
        return new Line(getRandomPoint(), getRandomPoint());
    }

    /**
     * Gets XBR.
     *
     * @return the top-left point's x coordinate.
     */
    public int getXTL() {
        return xTL;
    }

    /**
     * Gets YTL.
     *
     * @return the top-left point's y coordinate.
     */
    public int getYTL() {
        return yTL;
    }

    /**
     * Gets XBR.
     *
     * @return the bottom-right point's x coordinate.
     */
    public int getXBR() {
        return xBR;
    }

    /**
     * Gets YBR.
     *
     * @return the bottom-right point's y coordinate.
     */
    public int getYBR() {
        return yBR;
    }

    /**
     * Gets xm.
     *
     * @return the middle point's x coordinate.
     */
    public int getXM() {
        return (xBR - xTL) / 2 + xTL;
    }

    /**
     * Gets ym.
     *
     * @return the middle point's y coordinate.
     */
    public int getYM() {
        return (yBR - yTL) / 2 + yTL;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return xBR - xTL;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return yBR - yTL;
    }

    /**
     * Draws the enclosure on the surface.
     *
     * @param surface a surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle(xTL, yTL, xBR - xTL, yBR - yTL);
    }

}