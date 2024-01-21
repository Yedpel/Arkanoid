package collision;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * Collidable interface.
 * An interface of objects that can be collided with.
 */
public interface Collidable {

    // Return the "collision shape" of the object.
    /**
     * Gets collision rectangle.
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * Hit velocity.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}