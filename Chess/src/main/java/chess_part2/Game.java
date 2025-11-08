package chess_part2;

import chess_part2.board.Board;
import chess_part2.file.FileMenu;

import javax.swing.*;
import java.awt.*;
/**
 * Represents a chess game instance.
 * Responsible for initializing the game window and board.
 */
public class Game {
    private final Board board = new Board();
    /**
     * Starts the chess game by initializing the board and GUI on the Event Dispatch Thread.
     */
    public void start() {
        SwingUtilities.invokeLater(() -> {
            board.resetBoard();
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(board);
            frame.setJMenuBar(new FileMenu(board, frame).createMenuBar());

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setSize(new Dimension(800, 800));
        });
    }
}

