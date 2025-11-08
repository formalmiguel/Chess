package chess_part2.file;

import chess_part2.board.Board;
import chess_part2.pieces.Piece;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
/**
 * Utility class responsible for saving the current chess game state
 * to a text file for later restoration by {@link LoadGame}.
 * The game is saved to "chess_save.txt" and includes all
 * pieces' positions, their movement states, and whose turn it is.
 * Example of a saved file:
 * wK 7 4 false
 * bQ 0 3 true
 * turn=white
 */
public class SaveGame {
    /**
     * Saves the current game state to "chess_save.txt".
     * If a save file already exists, the user is prompted to confirm
     * whether they want to overwrite it. Each piece's name, position,
     * and movement status are written line by line, followed by the
     * current player's turn.
     *
     * @param board the {@link Board} containing the current game state
     */
    public static void save(Board board) {
        File file = new File("chess_save.txt");

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
