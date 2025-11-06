package chess_part2;

import chess_part2.board.Board;
import javax.swing.*;
import java.awt.*;

public class Game {
    private final Board board = new Board();

    public void start() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(board);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setSize(new Dimension(820, 820));
        });
    }
}

