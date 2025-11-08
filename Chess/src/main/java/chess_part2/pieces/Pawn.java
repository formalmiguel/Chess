package chess_part2.pieces;

import chess_part2.board.Board;
import chess_part2.board.Spot;

public class Pawn extends Piece{
    public Pawn(boolean white, int row, int col){
        super(white, row, col);
        this.UNICODE_PIECE = this.isWhite() ? "♙": "♟";
    }

    @Override
    public boolean canMove(Board board, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }
        int direction = this.isWhite() ? -1 : 1;
        hittingPiece = end.getPiece();
        //1 square up
        if(end.getCol() == getCurCol()
                && end.getRow() == getCurRow() + direction
                && hittingPiece == null){
            return true;
        }
        //move 2 squares in a direction
        if(end.getCol() == getCurCol()
                && end.getRow() == getCurRow() + direction*2
                && hittingPiece == null
                && !hasMoved
                && !pieceOnCross(end, board)){
            return true;
        }
        //capture piece
        return Math.abs(end.getCol() - getCurCol()) == 1
                && end.getRow() == getCurRow() + direction
                && hittingPiece != null
                && hittingPiece.isWhite() != this.isWhite();

    }


    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wp " : "bp ";
    }

}