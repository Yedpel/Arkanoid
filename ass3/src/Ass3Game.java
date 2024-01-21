import sprites.Game;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * Ass3 game.
 */
public class Ass3Game {

    /**
     * run the game.
     *
     * @param args as a command line
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
