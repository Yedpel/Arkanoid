package level;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Amateur.
 */
public class Amateur implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>(numberOfBalls());
        int angel = 300;
        for (int i = 0; i < numberOfBalls(); i++) {
            listVelocity.add(Velocity.fromAngleAndSpeed(angel, 7));
            angel += 11;
            if (i == 5) {
                angel += 11;
            }
        }
        return listVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 450;
    }

    @Override
    public String levelName() {
        return "Amateur";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(106, 130, 215));
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                d.setColor(new Color(101, 208, 72));
//                d.setColor(Color.cyan);
                d.drawCircle(405, 200, 55);
                d.drawCircle(405, 200, 95);
                d.drawCircle(405, 200, 135);
                d.drawLine(205, 200, 355, 200);
                d.drawLine(455, 200, 605, 200);
                d.drawLine(405, 40, 405, 150);
                d.drawLine(405, 250, 405, 400);
            }

            public void timePassed() {
            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> listBlock = new ArrayList<>(numberOfBlocksToRemove());
        int x = 23, width = 54, numColor = 0;
        List<Color> listColor = Arrays.asList(Color.red, Color.orange,
                Color.yellow, Color.cyan,
                Color.blue, Color.pink, Color.black);
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Block block = new Block(new Rectangle(new Point(x, 220), 54, 30),
                    listColor.get(numColor));
            listBlock.add(block);
            if (i % 2 == 1 && i <= 6) {
                numColor += 1;
            }
            if (i % 2 == 0 && i >= 8) {
                numColor += 1;
            }
            x = x + width;
        }
        return listBlock;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }
}