package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import level.LevelInformation;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private final Counter score;
    private final AnimationRunner ar;
    private final KeyboardSensor ks;
    private final GUI gui;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.score = new Counter();
    }

    /**
     * Run levels.
     *
     * @param levels the levels list
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWinner = true;

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, ar, ks, score, gui);

            level.initialize();
            level.run();
            if (level.getBallsCounter() == 0) {
                isWinner = false;
                break;
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                new EndScreen(score, isWinner)));

    }
}