package chess;
import chess.board.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    Board board = new Board();

    @Test
    public void testPieceName(){
        assertEquals("  A  B  C  D  E  F  G  H\n" +
                "8 bR bN bB bQ bK bB bN bR \n" +
                "7 bp bp bp bp bp bp bp bp \n" +
                "6    ##    ##    ##    ## \n" +
                "5 ##    ##    ##    ##    \n" +
                "4    ##    ##    ##    ## \n" +
                "3 ##    ##    ##    ##    \n" +
                "2 wp wp wp wp wp wp wp wp \n" +
                "1 wR wN wB wQ wK wB wN wR \n", board.stringBoard());


    }
}


