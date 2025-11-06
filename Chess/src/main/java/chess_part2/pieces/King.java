package chess_part2.pieces;

import chess_part2.board.Board;
import chess_part2.board.Spot;

public class King extends Piece {
    public King(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
        this.UNICODE_PIECE = this.isWhite() ? "♕" : "♛";
    }

    @Override
    public boolean canMove(Board board, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }
        int colDif = Math.abs(end.getCol()-this.getCurCol());
        int rowDif = Math.abs(end.getRow()- this.getCurRow());
        if(colDif + rowDif == 1 || colDif * rowDif == 1){
            return isValidSquare(end);
        }
        return false;
    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wK " : "bK ";
    }
}