package chess.pieces;
import chess.board.*;

public class Bishop extends Piece{
    public Bishop(boolean white){
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

        return Math.abs(startRow-endRow) != Math.abs(startCol - endCol); // is it a diagonal move
    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wB " : "bB ";
    }

    public void legaMoves(Board board, Spot start) {
        try{
            while(true){
                canMove(board, start, board.getSpot(start.getRow() +1, start.getCol()));
            }
        } catch (Exception e) {

        }


//        return moves;
    }

}

