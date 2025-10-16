package chess;
import chess.board.Board;
import chess.board.Spot;
import chess.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void Kingtest(){
        board.printBoard();
        try{
            Spot king = board.getSpot(5,4);
            Spot move = board.getSpot(4,4);
            System.out.println(king.getName());
            System.out.println(king.getPiece().moves.contains(move));
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    @Test void testMove(){
//        board.printBoard();
        try{
            Spot start = board.getSpot(6, 1);
            Spot end = board.getSpot(5, 1);
//            System.out.println("spot name start: " + start.getName());

            board.MovePiece(start,end);
//            board.printBoard();

//            System.out.println("spot name: " + start.getName());
//            System.out.println("spot name: " + start.getPiece());

            assertTrue(true);

        } catch (Exception e) {

        }

    }
    @Test
    public void testPawnMove1up() {
        try {
            Spot start = board.getSpot(6, 1);

            Spot end = board.getSpot(5, 1);


            boolean can = start.getPiece().canMove(board, end);
            assertTrue(can);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Test
    public void testPawnMove2up() {
        try {
            Spot start = board.getSpot(6, 2);
            Spot end = board.getSpot(4, 2);
            boolean can = start.getPiece().canMove(board end);
            assertTrue(can);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    @Test
    public void testPawnMove2upAfter() {
        try {
            Spot start1 = board.getSpot(6, 3);

            Spot end1 = board.getSpot(5, 3);
            start1.getPiece().canMove(board, end1);

            Spot start = board.getSpot(6, 3);
            Spot end = board.getSpot(4, 3);

            boolean can = start.getPiece().canMove(board, end);
            assertFalse(can);

        } catch (Exception e) {
            System.out.println(e);
        }

    }


}


