package chess_part2.file;

import chess_part2.board.Board;
import chess_part2.pieces.*;

import java.io.File;
import java.util.Scanner;

/**
 * Utility class responsible for loading a saved chess game state
 * from a text file and restoring it to the provided {@link Board}.
 * The saved game file should be named "chess_save.txt" and
 * contain lines describing each piece and whose turn it is.
 * Example:
 * turn=white
 * wK 7 4
 * bQ 0 3
 */
public class LoadGame {
    /**
     * Loads a saved chess game from "chess_save.txt" and updates
     * the given board to reflect the saved state.
     * If the file does not exist, the method prints a message and exits.
     * The board is cleared before loading any new pieces.
     *
     * @param board the {@link Board} object to restore the game state into
     */
    public static void load(Board board) {
        File file = new File("chess_save.txt");
        if (!file.exists()) {
            System.out.println("No save file found.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            board.clearBoard(); // clear before loading
            boolean whiteTurn = true;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("turn=")) {
                    whiteTurn = line.split("=")[1].equals("white");
                    continue;
                }

                // Example line: "wK 7 4"
                String[] parts = line.split(" ");
                String name = parts[0];
                int row = Integer.parseInt(parts[1]);
                int col = Integer.parseInt(parts[2]);
                boolean isWhite = name.charAt(0) == 'w';

                Piece piece = switch (name.charAt(1)) {
                    case 'p' -> new Pawn(isWhite, row, col);
                    case 'R' -> new Rook(isWhite, row, col);
                    case 'N' -> new Knight(isWhite, row, col);
                    case 'B' -> new Bishop(isWhite, row, col);
                    case 'Q' -> new Queen(isWhite, row, col);
                    case 'K' -> new King(isWhite, row, col);
                    default -> null;
                };

                if (piece != null) board.addPiece(piece, row, col);
            }

            board.setWhiteTurn(whiteTurn);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
