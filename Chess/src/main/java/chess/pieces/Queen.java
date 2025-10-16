package chess.pieces;
import chess.board.*;


public class Queen extends Piece{
    public Queen(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }

    @Override
    public boolean canMove(Board board,Spot end) {

        boolean diagMath = Math.abs(end.getCol()- getCurCol()) == Math.abs(end.getRow()- getCurRow());
        boolean crossMath = end.getCol() == getCurCol() || end.getRow() == getCurRow();

        if(crossMath){
            return isValidSquare(end) && !pieceOnCross(end, board);
        } else if (diagMath) {
            return isValidSquare(end) && !pieceOnDiagonal(end, board);
        }

        return false;
    }

    public boolean pieceOnDiagonal(Spot end, Board board){
        if(end.getRow() < getCurRow()){
            //up and to the left
            for(int col = getCurCol() -1; col > end.getCol(); col--){
                int difference = Math.abs(col - getCurCol());
                for(Piece p : board.pieces){
                    if(p.getCurCol() == col && p.getCurRow() == getCurRow() - difference){
                        hittingPiece = p;
                        return true;
                    }
                }
            }

            for(int col = getCurCol() +1; col < end.getCol(); col++){
                int difference = Math.abs(col - getCurCol());
                for(Piece p : board.pieces){
                    if(p.getCurCol() == col && p.getCurRow() == getCurRow() - difference){
                        hittingPiece = p;
                        return true;
                    }
                }
            }
        } else if (end.getRow() > getCurRow()) {
            // dl
            for(int col = getCurCol() - 1; col > end.getCol(); col--){
                int difference = Math.abs(col - getCurCol());
                for(Piece p : board.pieces){
                    if(p.getCurCol() == col && p.getCurRow() == getCurRow() + difference){
                        hittingPiece = p;
                        return true;
                    }
                }
            }
            //dr
            for(int col = getCurCol() +1; col < end.getCol(); col++){
                int difference = Math.abs(col - getCurCol());
                for(Piece p : board.pieces){
                    if(p.getCurCol() == col && p.getCurRow() == getCurRow() + difference){
                        hittingPiece = p;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wQ " : "bQ ";
    }
}
