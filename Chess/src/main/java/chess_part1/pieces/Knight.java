package chess_part1.pieces;
import chess_part1.board.*;

public class Knight extends Piece {
    public Knight(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }

    @Override
    public boolean canMove(Board board,Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }
        int colDif = Math.abs(end.getCol()-this.getCurCol());
        int rowDif = Math.abs(end.getRow()- this.getCurRow());

        if(colDif * rowDif == 2){
            return isValidSquare(end);
        }

        return false;
    }



    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wN " : "bN ";
    }


}