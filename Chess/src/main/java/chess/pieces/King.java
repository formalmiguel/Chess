package chess.pieces;
import chess.board.*;


public class King extends Piece {
    public King(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }

    @Override
    public boolean canMove(Board board,  Spot end) {
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