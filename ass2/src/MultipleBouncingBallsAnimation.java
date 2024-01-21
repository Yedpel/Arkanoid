import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Multiple bouncing balls animation.
 * This class is responsible for creating the balls and the enclosures
 * and drawing them on the screen.
 * It also moves the balls and draws them again.
 * It does this in a loop until the user closes the window.
 */
public class MultipleBouncingBallsAnimation {
    private final int width;
    private final int height;


    /**
     * Instantiates a new Multiple bouncing balls animation.
     *
     * @param width  the width
     * @param height the height
     */
    public MultipleBouncingBallsAnimation(int width, int height) {
        this.height = height;
        this.width = width;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation draw =
                new MultipleBouncingBallsAnimation(200, 200);

        Enclosure enclosure = new Enclosure(0, 0, draw.width,
                draw.height);

        Ball[] balls = new Ball[args.length];

        for (int i = 0; i < args.length; i++) {
            balls[i] = initBall(args[i], enclosure);
        }

        draw.drawAnimation(balls);
    }


    /**
     * Init ball .
     * This method creates a ball in the enclosure.
     *
     * @param radius    the radius
     * @param enclosure the enclosure
     * @return the ball
     */
    public static Ball initBall(String radius, Enclosure enclosure) {
        final int bigRadius = 50;
        int radiusInt = 0;
        //check if the input is legal
        try {
            radiusInt = Integer.parseUnsignedInt(radius);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
            return null;
        }
        //if the ball is bigger than enclosure, set radius 1/4 enclosure size
        if (radiusInt > enclosure.getXBR() / 2 || radiusInt > enclosure.getYBR()
                / 2) {
            radiusInt = Math.min(enclosure.getXBR() / 4, enclosure.getYBR()
                    / 4);
        }
        Point center = enclosure.getRandomPoint();
        //if part of the ball is outside the enclosure, move it to the enclosure
        if (center.getX() + radiusInt > enclosure.getXBR()) {
            center = new Point(enclosure.getXBR() - radiusInt,
                    center.getY());
        }
        if (center.getX() - radiusInt < enclosure.getXTL()) {
            center = new Point(enclosure.getXTL() + radiusInt,
                    center.getY());
        }
        if (center.getY() + radiusInt > enclosure.getYBR()) {
            center = new Point(center.getX(), enclosure.getYBR()
                    - radiusInt);
        }
        if (center.getY() - radiusInt < enclosure.getYTL()) {
            center = new Point(center.getX(), enclosure.getYTL()
                    + radiusInt);
        }
        Random random = new Random();
        Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN,
                Color.CYAN, Color.MAGENTA, Color.PINK};
        Ball ball = new Ball(center, radiusInt,
                colors[random.nextInt(colors.length)]);
        ball.setEnclosure(enclosure);
        double dXY;

        //if the ball is bigger than 50, set the speed to 1
        if (radiusInt >= bigRadius) {
            dXY = 1;
        } else {
            //the smaller the ball, the faster it moves
            dXY = 1 + ((double) (bigRadius - ball.getSize()) / bigRadius);
        }
        ball.setVelocity(dXY, dXY);
        return ball;
    }

    /**
     * Draw animation.
     *
     * @param balls the balls
     */
    public void drawAnimation(Ball[] balls) {
        GUI gui = new GUI("MultipleBouncingBallsAnimation", width, height);
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            balls[0].getEnclosure().drawOn(d);

            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(15);
        }
    }

}