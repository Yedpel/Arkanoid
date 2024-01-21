package collision;

import geometry.Point;
import geometry.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Game environment.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * constructor the list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * addCollidable.
     * add the given Collidable to the environment.
     *
     * @param c as a Collidable we want to add to list
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.collidables.add(c);
        }
    }

    /**
     * getClosesCollision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory as a line (way of the ball).
     * @return the closest collision that is going to occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // epsilon for double comparison
        double epsilon = Math.pow(10, -7);
        double minDistance = Double.MAX_VALUE;
        double curDistance;
        Collidable closestCollObject = null;
        Point closestCollPoint = null;
        // flag for the first one to avoid compare to null
        int flag = 0;
        Point curPoint;
        for (Collidable collidable : collidables) {
            curPoint =
                    trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (flag == 0) {
                if (curPoint != null) {
                    closestCollObject = collidable;
                    minDistance = trajectory.start().distance(curPoint);
                    closestCollPoint = curPoint;
                    flag = 1;
                }
            } else if (curPoint != null) {
                curDistance = trajectory.start().distance(curPoint);
                if (Math.abs(curDistance - minDistance) < epsilon) {
                    continue;
                }
                if (curDistance < minDistance) {
                    closestCollObject = collidable;
                    minDistance = curDistance;
                    closestCollPoint = curPoint;
                }
            }
        }
        if (flag == 0) {
            return null;
        } else {
            return new CollisionInfo(closestCollPoint, closestCollObject);
        }
    }

    /**
     * isInsideCollidable.
     * check if the ball is inside collidable.
     *
     * @param p as the point center of the ball
     * @return true if it is inside collidable, false otherwise
     */
    public boolean isInsideCollidable(Point p) {
        for (Collidable c : collidables) {
            if (c.getCollisionRectangle().getUpperLeft().getX() < p.getX()
                    && c.getCollisionRectangle().getUpperLeft().getX()
                    + c.getCollisionRectangle().getWidth() > p.getX()) {
                if (c.getCollisionRectangle().getUpperLeft().getY() < p.getY()
                        && c.getCollisionRectangle().getUpperLeft().getY()
                        + c.getCollisionRectangle().getHeight() > p.getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * removeCollidable.
     * remove the given Collidable from the environment.
     *
     * @param c as a Collidable we want to remove from list
     */
    public void removeCollidable(Collidable c) {
        if (c != null) {
            this.collidables.remove(c);
        }
    }
}