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
 * The type Pro.
 */
public class Pro implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelocity = new ArrayList<>(numberOfBalls());
        listVelocity.add(Velocity.fromAngleAndSpeed(320, 6));
        listVelocity.add(Velocity.fromAngleAndSpeed(40, 6));
        return listVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Pro";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(3, 136, 131));
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                d.setColor(new Color(105, 5, 145));
//                d.setColor(Color.BLACK);
                d.fillRectangle(115, 205, 15, 205);

                d.setColor(new Color(30, 87, 229));
//                d.setColor(Color.RED);
                d.fillCircle(120, 200, 14);

                d.setColor(new Color(225, 140, 56));
//                d.setColor(Color.ORANGE);
                d.fillCircle(120, 200, 9);

                d.setColor(Color.WHITE);
                d.fillCircle(120, 200, 4);

                d.setColor(new Color(105, 5, 145));
//                d.setColor(Color.BLACK);
                d.fillRectangle(105, 405, 35, 205);

                d.setColor(new Color(105, 5, 145));
//                d.setColor(Color.BLACK);
                d.fillRectangle(70, 455, 105, 205);

                d.setColor(Color.WHITE);

                for (int x = 0; x < 5; ++x) {
                    for (int y = 0; y < 5; ++y) {
                        d.fillRectangle(80 + x * 18, 465 + y * 32, 15, 25);
                    }
                }
            }

            public void timePassed() {
            }

        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block;
        int blocksInRow = 9;
        double width = 55, height = 35;
        double x = 800 - 20 - width, y = 100;
        List<Color> listColor = Arrays.asList(Color.gray, Color.red, Color.yellow, Color.blue,
                Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < blocksInRow; j++) {
                block = new Block(new Rectangle(new Point(x, y), width, height),
                        listColor.get(i));
                blocks.add(block);
                x = x - width;
            }
            x = 800 - 20 - width;
            y = y + height;
            blocksInRow--;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 35;
    }
}