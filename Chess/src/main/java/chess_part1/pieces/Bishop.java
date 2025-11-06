package chess_part1.pieces;
import chess_part1.board.*;

public class Bishop extends Queen{
    public Bishop(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }
    @Override
    public boolean canMove(Board board, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }
        boolean math = Math.abs(end.getCol()- getCurCol()) == Math.abs(end.getRow()- getCurRow());
        return math && isValidSquare(end) && !pieceOnDiagonal(end, board);
    }
    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wB " : "bB ";
    }

}

