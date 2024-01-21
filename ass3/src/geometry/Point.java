package geometry;
/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Point.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * constructor the Point.
     *
     * @param x the x value
     * @param y the y value
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other Point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2)
                + Math.pow(this.y - other.y, 2));
    }

    /**
     * Equals boolean.
     *
     * @param other the other Point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        //epsilon is the threshold for the difference between the two points
        double epsilon = Math.pow(10, -7);
        return (Math.abs(other.x - x) < epsilon)
                && (Math.abs(other.y - y) < epsilon);
    }

    /**
     * Gets x.
     *
     * @return the x value of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y value of this point
     */
    public double getY() {
        return y;
    }
}