import biuoop.GUI;
import game.AnimationRunner;
import game.GameFlow;
import level.Amateur;
import level.Beginner;
import level.LevelInformation;
import level.Pro;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass6 game.
 */


public class Ass6Game {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * run the game.
     *
     * @param args as a command line
     */
    public static void main(String[] args) {
        List<LevelInformation> orderLevels = new ArrayList<>();

        for (String level : args) {
            int levelAsInt;
            try {
                levelAsInt = Integer.parseInt(level);
            } catch (NumberFormatException e) {
                continue;
            }
            if (levelAsInt < 1 || levelAsInt > 4) {
                continue;
            }
            switch (levelAsInt) {
                case 1 -> orderLevels.add(new Beginner());
                case 2 -> orderLevels.add(new Amateur());
                case 3 -> orderLevels.add(new Pro());
                default -> {
                }
            }
        }

        // if there is no level from the command line run by the default order
        if (orderLevels.size() == 0) {
            orderLevels.add(new Beginner());
            orderLevels.add(new Amateur());
            orderLevels.add(new Pro());
        }

        GUI gui = new GUI("Ass6Game", WIDTH, HEIGHT);
        GameFlow game = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor(), gui);
        game.runLevels(orderLevels);
        gui.close();
    }

}