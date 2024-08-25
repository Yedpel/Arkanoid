package geometry;

import java.util.List;

/**
 * The type Line.
 */


public class Line {
    private final Point start;
    private final Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start point
     * @param end   the end point
     */
// constructors
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x of start point
     * @param y1 the y of start point
     * @param x2 the x of end point
     * @param y2 the y of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }


    /**
     * Length double.
     *
     * @return the length of the line
     */
// Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle point.
     *
     * @return the middle point of the line
     */
// Returns the middle point of the line
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }


    /**
     * Is on the line boolean.
     *
     * @param p1 a point.
     * @return true if the point is on the line, false otherwise.
     */
    public boolean isOnTheLine(Point p1) {
        //epsilon is the threshold of the difference between the lines
        double epsilon = Math.pow(10, -7);
        return Math.abs(p1.distance(end) + p1.distance(start)
                - length()) < epsilon;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean if the lines intersect, false otherwise.
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        //if the lines have infinite intersection or one start/end point is
        // on the other line
        if (isOnTheLine(other.start) || isOnTheLine(other.end)) {
            return true;
        }
        return intersectionWith(other) != null;
    }


    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the intersection point if the lines intersect, null otherwise.
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (equals(other)) {
            return null;
        }
        // Calc slopes and y-intercepts of the two lines
        double slope1 = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        double slope2 = (other.end.getY() - other.start.getY())
                / (other.end.getX() - other.start.getX());
        // b = y - mx from y = mx + b equation
        double b1 = this.start.getY() - slope1 * this.start.getX();
        double b2 = other.start.getY() - slope2 * other.start.getX();

        //epsilon is the threshold of the difference between the lines
        double epsilon = Math.pow(10, -7);

        //check if the lines are both vertical
        if (Math.abs(this.start.getX() - this.end.getX()) < epsilon
                && Math.abs(other.start.getX() - other.end.getX()) < epsilon) {
            if (Math.abs(this.start.getX() - other.start.getX()) < epsilon) {
                double minY1 = Math.min(this.start.getY(), this.end.getY());
                double minY2 = Math.min(other.start.getY(), other.end.getY());
                double maxY1 = Math.max(this.start.getY(), this.end.getY());
                double maxY2 = Math.max(other.start.getY(), other.end.getY());
                //if there are infinite intersection points return null
                if (minY1 <= minY2 && minY2 < maxY1 && ((maxY1 < maxY2)
                        || (maxY2 < maxY1))) {
                    return null;
                } else if (minY2 <= minY1 && minY1 < maxY2 && ((maxY2 < maxY1)
                        || (maxY1 < maxY2))) {
                    return null;
                }
            }
        }
        // Check if lines have same slope
        if (Math.abs(slope1 - slope2) < epsilon) {
            // Check if lines have same b
            if (b1 != b2) {
                return null;
            } else {
                // check if they have intersection point
                double minX1 = Math.min(this.start.getX(), this.end.getX());
                double minX2 = Math.min(other.start.getX(), other.end.getX());
                double maxX1 = Math.max(this.start.getX(), this.end.getX());
                double maxX2 = Math.max(other.start.getX(), other.end.getX());
                //if there are infinite intersection points return null
                if (minX1 <= minX2 && minX2 < maxX1 && ((maxX1 < maxX2)
                        || (maxX2 < maxX1))) {
                    return null;
                } else if (minX2 <= minX1 && minX1 < maxX2 && ((maxX2 < maxX1)
                        || (maxX1 < maxX2))) {
                    return null;
                }
            }
        }

        //if the intersection point is one of the start/end points
        if ((start.equals(other.start) || start.equals(other.end))
                && !(other.isOnTheLine(end))) {
            return start;
        }

        if ((end.equals(other.start) || end.equals(other.end))
                && !(other.isOnTheLine(start))) {
            return end;
        }

        // Calc the change in y,x coordinate, and constant term for each line
        double deltaY1 = this.end.getY() - this.start.getY();
        double deltaX1 = this.start.getX() - this.end.getX();
        double constant1 = deltaY1 * (this.start.getX()) + deltaX1
                * (this.start.getY());

        double deltaY2 = other.end.getY() - other.start.getY();
        double deltaX2 = other.start.getX() - other.end.getX();
        double constant2 = deltaY2 * (other.start.getX()) + deltaX2
                * (other.start.getY());
        // Calc determinant of coefficients
        double detCoeffs = deltaY1 * deltaX2 - deltaY2 * deltaX1;

        if (Math.abs(detCoeffs - 0) < epsilon) {
            return null;
        } else {
            // Calc intersection point
            double xIntersect = (deltaX2 * constant1 - deltaX1 * constant2)
                    / detCoeffs;
            double yIntersect = (deltaY1 * constant2 - deltaY2 * constant1)
                    / detCoeffs;

            Point intersect = new Point(xIntersect, yIntersect);
            //check if the intersection point is on both lines
            if (this.isOnTheLine(intersect) && other.isOnTheLine(intersect)) {
                return intersect;
            } else {
                return null;
            }
        }
    }


    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean if the lines are equal, false otherwise.
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        return ((start.equals(other.start)) && (end.equals(other.end)))
                || ((start.equals(other.end)) && (end.equals(other.start)));
    }

    /**
     * Point closestIntersectionToStartOfLine.
     *
     * @param rect the rectangle
     * @return if this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start
     * of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closest = intersectionPoints.get(0);
        for (int i = 1; i < intersectionPoints.size(); i++) {
            if (intersectionPoints.get(i).distance(start)
                    < closest.distance(start)) {
                closest = intersectionPoints.get(i);
            }
        }
        return closest;
    }


}