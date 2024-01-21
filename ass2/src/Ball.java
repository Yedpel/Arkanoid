import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Ball.
 * This class is responsible for creating the balls
 * and drawing them on the screen.
 * It also moves the balls and draws them again.
 * It does this in a loop until the user closes the window.
 */
public class Ball {
    private final int size;
    private final Color color;
    private Point point;
    private Velocity velocity = new Velocity(0, 0);
    private Enclosure enclosure = null;


    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the radius
     * @param color  the color
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.size = r;
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x value of the center point
     * @param y     the y value of the center point
     * @param r     the radius
     * @param color the color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.size = r;
        this.color = color;
    }

    /**
     * Gets x.
     *
     * @return the x value of the center point
     */
// accessors
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Gets size.
     *
     * @return the size - the radius
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets color.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     * draw the ball on the given DrawSurface
     *
     * @param surface the draw surface
     */
    public void drawOn(DrawSurface surface) {
        //if part of the ball is out of the surface, move it into the enclosure
        if (this.getX() + this.size > surface.getWidth()) {
            this.point = new Point(surface.getWidth() - this.size,
                    this.getY());
        }
        if (this.getY() + this.size > surface.getHeight()) {
            this.point = new Point(this.getX(),
                    surface.getHeight() - this.size);
        }
        if (this.getX() - this.size < 0) {
            this.point = new Point(this.size, this.getY());
        }
        if (this.getY() - this.size < 0) {
            this.point = new Point(this.getX(), this.size);
        }

        if (this.enclosure == null) {
            this.enclosure = new Enclosure(0, 0, surface.getWidth(),
                    surface.getHeight());
        }
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    /**
     * Sets velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }


    /**
     * Sets point.
     *
     * @param p the point
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Sets enclosure.
     *
     * @param enclosure the enclosure
     */
    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }


    /**
     * Move one step.
     * move the ball one step according to its velocity
     */
    public void moveOneStep() {
        //move the ball one step
        this.point = velocity.applyToPoint(point);
        if (enclosure == null) {
            return;
        }
        //if the ball is out of the enclosure, it will move into the enclosure
        if (point.getX() + size >= enclosure.getXBR()) {
            setVelocity(-velocity.getDx(), velocity.getDy());
            setPoint(new Point(enclosure.getXBR() - size, point.getY()));
        }
        if (point.getX() - size <= enclosure.getXTL()) {
            setVelocity(-velocity.getDx(), velocity.getDy());
            setPoint(new Point(enclosure.getXTL() + size, point.getY()));
        }
        if (point.getY() + size >= enclosure.getYBR()) {
            setVelocity(velocity.getDx(), -velocity.getDy());
            setPoint(new Point(point.getX(), enclosure.getYBR() - size));
        }
        if (point.getY() - size <= enclosure.getYTL()) {
            setVelocity(velocity.getDx(), -velocity.getDy());
            setPoint(new Point(point.getX(), enclosure.getYTL() + size));
        }


    }


    /**
     * Gets enclosure.
     *
     * @return the enclosure
     */
    public Enclosure getEnclosure() {
        return enclosure;
    }
}