package chess.pieces;
import chess.board.*;


public class Queen extends Piece{
    public Queen(boolean white, int row, int col){
        super(white, row, col);
        this.setName();
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        boolean crossMath = Math.abs(end.getCol()- getCurCol()) == Math.abs(end.getRow()- getCurRow());
        boolean diagMath = end.getCol() == getCurCol() || end.getRow() == getCurRow();

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

    public boolean pieceOnCross(Spot end, Board board){
        //moving left?
        for(int col = this.getCurCol() - 1; col > end.getCol(); col--){
            for(Piece piece : board.pieces){
                if( piece.getCurCol() == col && piece.getCurRow() == end.getCol()){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        //moving right
        for(int col = this.getCurCol() + 1; col < end.getCol(); col++){
            for(Piece piece : board.pieces){
                if( piece.getCurCol() == col && piece.getCurRow() == end.getCol()){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        //moving up
        for(int row = this.getCurRow() - 1; row > end.getRow(); row--){
            for(Piece piece : board.pieces){
                if( piece.getCurRow() == row && piece.getCurRow() == end.getRow()){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        //moving down
        for(int row = this.getCurRow() + 1; row < end.getRow(); row++){
            for(Piece piece : board.pieces){
                if( piece.getCurRow() == row && piece.getCurRow() == end.getRow()){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        // no piece on cross
        return false;
    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wQ " : "bQ ";
    }
}
