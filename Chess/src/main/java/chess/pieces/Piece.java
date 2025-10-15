package chess.pieces;
import chess.board.*;

public abstract class Piece {
    protected boolean white;
    protected String name;
    public boolean hasMoved;
    public Piece hittingPiece;
    private int curRow;
    private int curCol;

    public Piece(boolean white, int row, int col){
        this.setWhite(white);
        this.hasMoved = false;
        this.setCurRow(row);
        this.setCurCol(col);
    }

    public boolean isWhite(){
        return this.white;
    }

    public void setWhite(boolean white){
        this.white = white;
    }

    public String getName() {
        return this.name;
    }

    public boolean sameSquare(Spot start, Spot end){ // handle outside of loop?
        return start.getCol() == end.getCol() && start.getRow() == end.getRow();
    }

    public boolean isValidSquare(Spot target){
        hittingPiece = target.getPiece();
        if(hittingPiece == null){
            return true;
        }else{
            if(hittingPiece.isWhite() != this.isWhite()){
                return true;
            }else{
                hittingPiece = null;
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
                if( piece.getCurCol() == end.getCol() && piece.getCurRow() == row){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        //moving down
        for(int row = this.getCurRow() + 1; row < end.getRow(); row++){
            for(Piece piece : board.pieces){
                if( piece.getCurCol() == end.getCol() && piece.getCurRow() == row){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        // no piece on cross
        return false;
    }

    protected abstract void setName();
    public abstract boolean canMove(Board board, Spot start, Spot end);

    public int getCurRow() {
        return curRow;
    }

    public void setCurRow(int curRow) {
        this.curRow = curRow;
    }

    public int getCurCol() {
        return curCol;
    }

    public void setCurCol(int curCol) {
        this.curCol = curCol;
    }
}

