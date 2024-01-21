import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Bouncing ball animation.
 * This class is responsible for drawing the animation of a ball.
 * It also moves the ball and draws it again.
 * It does this in a loop until the user closes the window.
 */
public class BouncingBallAnimation {


    /**
     * Draw animation.
     *
     * @param start the start point
     * @param dx    the dx
     * @param dy    the dy
     */
    static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30,
                java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Point start = null;
        double dx = 0, dy = 0;
        //check if the input is legal
        try {
            double x = Double.parseDouble(args[0]),
                    y = Double.parseDouble(args[1]);
            start = new Point(x, y);
            dx = Double.parseDouble(args[2]);
            dy = Double.parseDouble(args[3]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
            return;
        }

        drawAnimation(start, dx, dy);
    }
}