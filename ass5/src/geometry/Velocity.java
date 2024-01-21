package geometry;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Velocity.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * constructor the Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply to point.
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy)
     *
     * @param p the p
     * @return the point after the change
     */

    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }


    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return dx;
    }


    /**
     * From angle and speed velocity.
     * Returns a velocity given the angle and the speed
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle - 90);
        double dy = Math.sin(radian) * speed;
        double dx = Math.cos(radian) * speed;
        return new Velocity(dx, dy);
    }


}