package chess.pieces;
import chess.board.*;

public class Rook extends  Queen{
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

        int startRow = start.getRow();
        int startCol = start.getCol();
        int endRow = end.getRow();
        int endCol = end.getCol();

        return endRow == startRow || endCol == startCol;
    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wR " : "bR ";
    }

    public void generateMoves(int row, int col, Board board){
        try {
            Spot cur = board.getSpot(row, col);
            // moves up and

        } catch(Exception e) {
            System.out.println("Rook issue");
        }

    }
}