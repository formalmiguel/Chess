package chess;

import chess.board.Board;
import javax.swing.*;
import java.awt.*;

public class Chess {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Board boardPanel = new Board();
            frame.add(boardPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setSize(new Dimension(1100, 800));
        });
        Game game = new Game();
        game.play();

    }
}
