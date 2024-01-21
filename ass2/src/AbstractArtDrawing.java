import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Abstract art drawing.
 * This class draws 10 random lines on the screen.
 * Each line has a random start and end points.
 * The lines are drawn in black and the middle point of each line in blue.
 */
public class AbstractArtDrawing {
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 300;
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 400;

    /**
     * Main function.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        AbstractArtDrawing drawing = new AbstractArtDrawing();
        drawing.drawRandomLines();
    }

    /**
     * Draw 10 random lines on new GUI.
     */
    public void drawRandomLines() {
        GUI gui = new GUI("Abstract_Art_Drawing", WIDTH, HEIGHT);
        DrawSurface surface = gui.getDrawSurface();
        surface.setColor(Color.WHITE);
        surface.fillRectangle(0, 0, WIDTH, HEIGHT);
        // "10" - the mission is to draw 10 random lines on the screen
        Line[] randLines = new Line[10];
        Enclosure enclosure = new Enclosure(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < randLines.length; i++) {
            Line lineI = enclosure.getRandomLine();
            // draw the line in black
            surface.setColor(Color.BLACK);
            surface.drawLine(((int) lineI.start().getX()),
                    ((int) lineI.start().getY()), ((int) lineI.end().getX()),
                    ((int) lineI.end().getY()));
            // draw the middle point of the line in blue
            Point mid = lineI.middle();
            surface.setColor(Color.BLUE);
            surface.fillCircle((int) mid.getX(), ((int) mid.getY()), 2);
            // check if the line intersects with any of the previous lines
            for (int j = 0; j < i; j++) {
                Point intersection = lineI.intersectionWith(randLines[j]);
                if (intersection == null) {
                    continue;
                }
                surface.setColor(Color.RED);
                surface.fillCircle((int) intersection.getX(),
                        ((int) intersection.getY()), 2);
            }
            //enter the line to the array
            randLines[i] = lineI;
        }
        //show the drawing
        gui.show(surface);
    }
}