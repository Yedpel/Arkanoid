package collision;

import geometry.Point;

/**
 * CollisionInfo class.
 * a collection of objects a Ball can collide with
 */
public class CollisionInfo {

    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * constructor the CollisionInfo.
     *
     * @param collisionPoint  as the collision Point
     * @param collisionObject as the collision Object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * collisionPoint.
     *
     * @return the point at which the collision occurs.
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * collisionObject.
     *
     * @return the collidable object involved in the collision.
     */
    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return collisionObject;
    }
}