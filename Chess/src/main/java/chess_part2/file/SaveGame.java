package chess_part2.file;

import chess_part2.board.Board;
import chess_part2.pieces.Piece;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SaveGame {

    public static void save(Board board) {
        File file = new File("chess_save.txt");

        // Ask before overwriting
        if (file.exists()) {
            int option = javax.swing.JOptionPane.showConfirmDialog(null,
                    "Save file already exists. Overwrite?",
                    "Confirm Save",
                    javax.swing.JOptionPane.YES_NO_OPTION);
            if (option != javax.swing.JOptionPane.YES_OPTION) {
                return; // cancel the save
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Piece piece : board.pieces) {
                String cleanName = piece.getName().trim();
                writer.write(cleanName + " " +
                        piece.getCurRow() + " " +
                        piece.getCurCol() + " " +
                        piece.hasMoved + "\n");
            }
            writer.write("turn=" + (board.isWhiteTurn() ? "white" : "black"));
            System.out.println("Game saved.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
