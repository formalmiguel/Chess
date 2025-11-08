package chess_part2;

/**
 * The main class that starts the Chess application.
 */

public class Chess {
    /**
     * Entry point of the Chess program.
     * Initializes and starts a new chess game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
