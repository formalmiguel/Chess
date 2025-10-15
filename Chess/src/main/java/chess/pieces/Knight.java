package chess.pieces;
import chess.board.*;

public class Knight extends Piece {
    int[][] moveset = {
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };
    public Knight(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        } // maybe we check before

        int row = Math.abs(start.getRow() - end.getRow());
        int col = Math.abs(start.getCol() - end.getCol());
        return row * col == 2;
    }



    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wN " : "bN ";
    }


}