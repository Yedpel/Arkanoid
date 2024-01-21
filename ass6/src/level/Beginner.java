package level;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import sprites.Sprite;

/**
 * The type Beginner.
 */
public class Beginner implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>(1);
        listVelocity.add(Velocity.fromAngleAndSpeed(0, 5));
        return listVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 4;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Beginner";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(106, 9, 110));
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                d.setColor(Color.yellow.darker().darker());
                d.fillCircle(150, 150, 60);
                for (int x = 10; x <= 780; x += 10) {
                    d.drawLine(150, 150, x, 250);
                }
                d.setColor(Color.yellow.darker());
                d.fillCircle(150, 150, 50);
                d.setColor(Color.yellow.brighter());
                d.fillCircle(150, 150, 40);
            }

            public void timePassed() {
            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(375, 170), 50, 50),
                Color.BLUE);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }
}
