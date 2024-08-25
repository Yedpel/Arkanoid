package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;


    /**
     * constructor the Rectangle.
     *
     * @param upperLeft the location of the upper left point
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * IntersectionPoints.
     *
     * @param line the line
     * @return a (possibly empty) List of intersection points
     * with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] sides = getSides();
        List<Point> interPoints = new ArrayList<>();
        for (Line side : sides) {
            Point intersection = line.intersectionWith(side);
            if (intersection != null) {
                interPoints.add(intersection);
            }
        }
        return interPoints;
    }

    /**
     * Gets width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * Gets Vertices.
     *
     * @return the vertices of the rectangle
     */
    public Point[] getVertices() {
        Point[] vertices = new Point[4];
        //upper left
        vertices[0] = upperLeft;
        //upper right
        vertices[1] = new Point(upperLeft.getX() + getWidth(),
                upperLeft.getY());
        //down right
        vertices[2] = new Point(upperLeft.getX() + getWidth(),
                upperLeft.getY() + getHeight());
        //down left
        vertices[3] = new Point(upperLeft.getX(),
                upperLeft.getY() + getHeight());
        return vertices;
    }

    /**
     * Gets sides.
     *
     * @return the sides of the rectangle
     */
    public Line[] getSides() {
        Line[] sides = new Line[4];
        Point[] points = getVertices();
        //upper side
        sides[0] = new Line(points[0], points[1]);
        //right side
        sides[1] = new Line(points[1], points[2]);
        //down side
        sides[2] = new Line(points[2], points[3]);
        //left side
        sides[3] = new Line(points[3], points[0]);
        return sides;
    }

}
