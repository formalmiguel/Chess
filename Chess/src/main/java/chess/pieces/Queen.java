package chess.pieces;
import chess.board.*;


public class Queen extends Piece{
    public Queen(boolean white){
        super(white);
        this.setName();
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int row = Math.abs(start.getRow() - end.getRow());
        int col = Math.abs(start.getCol() - end.getCol());
        return row * col == 2;
    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wQ " : "bQ ";
    }
}
