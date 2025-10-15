package chess.pieces;
import chess.board.*;


public class Queen extends Piece{
    public Queen(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }

        int row = Math.abs(start.getRow() - end.getRow());
        int col = Math.abs(start.getCol() - end.getCol());
        return row * col == 2;
    }

    public boolean checkDiagonal(Spot start, Spot end){
        return Math.abs(end.getCol() - start.getCol()) ==
                Math.abs(end.getRow() - start.getRow());
    }

//    public boolean checkCross(Spot start, Spot end){
//
//    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wQ " : "bQ ";
    }
}
