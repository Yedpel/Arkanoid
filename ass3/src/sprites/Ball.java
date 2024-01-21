package sprites;

import collision.Enclosure;
import geometry.Point;
import geometry.Line;
import geometry.Velocity;
import collision.GameEnvironment;
import collision.CollisionInfo;

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
public class Ball implements Sprite {
    private final int size;
    private final Color color;
    private Point point;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;

    private Enclosure enclosure;

//    private int maxPointX = 800;
//
//    private int maxPointY = 600;
//
//    private int minPointXY = 0;


    /**
     * constructor.
     *
     * @param center the center
     * @param r      the radius
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.size = r;
        this.color = color;
    }

    /**
     * constructor.
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
     * Sets the game environment.
     *
     * @param environment GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
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
     * Draw on.
     * draw the ball on the given DrawSurface
     *
     * @param surface the draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.size);
    }

    @Override
    public void timePassed() {
        moveOneStep();
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
     * Move one step.
     * move the ball one step according to its velocity
     */
    public void moveOneStep() {
        Line trajectory = new Line(point, new Point(point.getX() + velocity.getDx(),
                point.getY() + velocity.getDy()));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        Velocity prev = new Velocity(velocity.getDx(), velocity.getDy());
        if (collisionInfo == null) {
            moveRegularStep();
        } else {
            double epsilon = Math.pow(10, -7);
            Point upperLeft = collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft();
            Point collisionPoint = collisionInfo.collisionPoint();
            setVelocity(collisionInfo.collisionObject().hit(collisionPoint, getVelocity()));
            if (Math.abs((upperLeft.getX() - collisionPoint.getX())) < epsilon) {
                point = this.getVelocity().applyToPoint(new Point(collisionPoint.getX() - getSize(),
                                collisionPoint.getY()));
            } else if (Math.abs(upperLeft.getY() - collisionPoint.getY()) < epsilon) {
                point = this.getVelocity().applyToPoint(new Point(collisionPoint.getX(),
                                collisionPoint.getY() - getSize()));
            } else if (Math.abs((upperLeft.getX()
                    + collisionInfo.collisionObject().getCollisionRectangle().getWidth())
                    - collisionPoint.getX()) < epsilon) {
                point = this.getVelocity().applyToPoint(new Point(collisionPoint.getX() + getSize(),
                                collisionPoint.getY()));
            } else if (Math.abs((upperLeft.getY()
                    + collisionInfo.collisionObject().getCollisionRectangle().getHeight())
                    - collisionPoint.getY()) < epsilon) {
                point = this.getVelocity().applyToPoint(new Point(collisionPoint.getX(),
                                collisionPoint.getY() + getSize()));
            }
            // in case came to the corner
            if (gameEnvironment.isInsideCollidable(point)) {
                setVelocity(-prev.getDx(), -prev.getDy());
                this.point = this.getVelocity().applyToPoint(collisionPoint);
            }
        }

    }


    /**
     * Move regular step.
     * move the ball one step according to its velocity
     */
    public void moveRegularStep() {
        Velocity temp = new Velocity(velocity.getDx(), velocity.getDy());
        //Checks if the ball passes the boundaries of the screen
        if (point.getX() + size + velocity.getDx() >= enclosure.getXBR()) {
            //Passes the ball over the boundary
            double dx1 = point.getX() + size + velocity.getDx() - enclosure.getXBR();
            setVelocity(dx1, this.velocity.getDy());
            this.point = this.getVelocity().applyToPoint(this.point);
            //Changes the direction of the ball
            setVelocity(-temp.getDx(), temp.getDy());
        }
        if (point.getX() - size + velocity.getDx() <= enclosure.getXTL()) {
            //Passes the ball over the boundary
            double dx1 = point.getX() - size - enclosure.getXTL();
            setVelocity(dx1, this.velocity.getDy());
            this.point = this.getVelocity().applyToPoint(this.point);
            //Changes the direction of the ball
            setVelocity(-temp.getDx(), temp.getDy());
        }
        if (point.getY() + size + velocity.getDy() >= enclosure.getYBR()) {
            //Passes the ball over the boundary
            double dy1 = point.getY() + size + velocity.getDy() - enclosure.getYBR();
            setVelocity(this.velocity.getDx(), dy1);
            this.point = this.getVelocity().applyToPoint(this.point);
            //Changes the direction of the ball
            setVelocity(temp.getDx(), -temp.getDy());
        }
        if (point.getY() - size + velocity.getDy() <= enclosure.getYTL()) {
            //Passes the ball over the boundary
            double dy1 = point.getY() - size - enclosure.getYTL();
            setVelocity(this.velocity.getDx(), dy1);
            this.point = this.getVelocity().applyToPoint(this.point);
            //Changes the direction of the ball
            setVelocity(temp.getDx(), -temp.getDy());
        }
        //Moves the ball one step
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}