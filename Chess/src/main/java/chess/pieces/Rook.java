package chess.pieces;
import chess.board.*;

public class Rook extends  Piece{
    public Rook(boolean white){
        super(white);
        this.setName();
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }

        int startRow = start.getRow();
        int startCol = start.getCol();
        int endRow = end.getRow();
        int endCol = end.getCol();

        return startRow != endRow && startCol != endCol;
    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wR " : "bR ";
    }
}