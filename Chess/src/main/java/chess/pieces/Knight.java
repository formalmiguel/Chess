package chess.pieces;
import chess.board.*;

public class Knight extends Piece {
    public Knight(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }

    @Override
    public boolean canMove(Board board,Spot end) {
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