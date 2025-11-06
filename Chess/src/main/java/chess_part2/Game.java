package chess_part2;

import chess_part2.board.Board;
import chess_part2.file.FileMenu;

import javax.swing.*;
import java.awt.*;

public class Game {
    private final Board board = new Board();

    public void start() {
        SwingUtilities.invokeLater(() -> {
            board.resetBoard();
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(board);

            FileMenu fileMenu = new FileMenu(board);
            frame.setJMenuBar(fileMenu.createMenuBar());

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setSize(new Dimension(1100, 800));
        });
    }
}

