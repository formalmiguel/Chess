package chess.pieces;
import chess.board.*;

public class Rook extends Queen{
    public Rook(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }

        boolean math = end.getCol() == getCurCol() || end.getRow() == getCurRow();
        return math && isValidSquare(end) && !pieceOnCross(end, board);
    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wR " : "bR ";
    }

}