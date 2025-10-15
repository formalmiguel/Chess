package chess;

import chess.pieces.Knight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    Knight whitePiece = new Knight(true);

    @Test
    public void testPieceColor() {
        assertTrue(whitePiece.isWhite());
    }

    @Test
    public void testPieceName(){
        assertEquals("wN ", whitePiece.getName());
    }

}
