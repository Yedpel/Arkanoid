import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Multiple frames bouncing balls animation.
 * This class is responsible for creating the balls and the enclosures
 * and drawing them on the screen.
 * It also moves the balls and draws them again.
 * It does this in a loop until the user closes the window.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Enclosure enclosure1 = new Enclosure(50, 50, 500,
                500, Color.GRAY);
        Enclosure enclosure2 = new Enclosure(450, 450, 600,
                600, Color.YELLOW);

        Ball[] balls = new Ball[args.length];

        //split the balls to two enclosures in the middle
        int split;
        if (args.length % 2 == 0) {
            split = args.length / 2;
        } else {
            split = args.length / 2 + 1;
        }
        for (int i = 0; i < args.length; i++) {
            if (i < split) {
                balls[i] = initBall(args[i],
                        enclosure1);
            } else {
                balls[i] = initBall(args[i],
                        enclosure2);
            }

        }

        MultipleFramesBouncingBallsAnimation draw =
                new MultipleFramesBouncingBallsAnimation();

        draw.drawAnimation(balls);

    }

    /**
     * Draw animation.
     *
     * @param balls the balls
     */
    public void drawAnimation(Ball[] balls) {
        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation",
                650, 650);
        Sleeper sleeper = new Sleeper();

        // calc the split point
        int split;
        if (balls.length % 2 == 0) {
            split = balls.length / 2;
        } else {
            split = balls.length / 2 + 1;
        }

        while (true) {
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
            }

            DrawSurface d = gui.getDrawSurface();
            //draw the balls and the enclosures of the first half
            for (int i = 0; i < split; i++) {
                balls[i].getEnclosure().drawOn(d);
            }
            for (int i = 0; i < split; i++) {
                balls[i].drawOn(d);
            }
            //draw the balls and the enclosures of the second half
            for (int i = split; i < balls.length; i++) {
                balls[i].getEnclosure().drawOn(d);
            }
            for (int i = split; i < balls.length; i++) {
                balls[i].drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(15);

        }
    }

    /**
     * Init ball.
     * This method creates a ball inside the enclosure.
     *
     * @param radius    the radius
     * @param enclosure the enclosure
     * @return the ball
     */
    public static Ball initBall(String radius, Enclosure enclosure) {
        final int bigRadius = 50;
        int radiusInt = 0;
        try {
            radiusInt = Integer.parseUnsignedInt(radius);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
            System.exit(-1);
        }
        //if the ball is bigger than enclosure, set radius 1/4 enclosure size
        if (radiusInt > enclosure.getWidth() / 2 || radiusInt
                > enclosure.getHeight() / 2) {
            radiusInt = Math.min(enclosure.getWidth() / 4, enclosure.getHeight()
                    / 4);
        }
        Point center = enclosure.getRandomPoint();
        //if the ball is outside the enclosure, move it to the enclosure
        if (center.getX() + radiusInt >= enclosure.getXBR()) {
            center = new Point(enclosure.getXM(), center.getY());
        }
        if (center.getX() - radiusInt <= enclosure.getXTL()) {
            center = new Point(enclosure.getXM(), center.getY());
        }
        if (center.getY() + radiusInt >= enclosure.getYBR()) {
            center = new Point(center.getX(), enclosure.getYM());
        }
        if (center.getY() - radiusInt <= enclosure.getYTL()) {
            center = new Point(center.getX(), enclosure.getYM());
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
            dXY = 1 + ((double) (bigRadius - radiusInt) / bigRadius);
        }
        ball.setVelocity(dXY, dXY);
        return ball;
    }


}


