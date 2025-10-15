package chess.pieces;
import chess.board.*;


public class Pawn extends Piece{
    public Pawn(boolean white){
        super(white);
        this.setName();
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }
        int endCol = end.getCol();
        int endRow = end.getRow();
        int startCol = start.getCol();

        int nextRow = this.isWhite() ? start.getRow() - 1 : start.getRow() + 1; //white moves down 1 row black up 1
        boolean canCapture = end.getPiece() != null && // can't be empty
                end.getPiece().isWhite() != this.isWhite() && //must be opp
                (endCol == startCol + 1 || endCol == startCol - 1 ) && // must be l or r by 1
                endRow == nextRow; // must be in next row

        boolean canMove = endRow == nextRow && end.getPiece() == null &&
                endCol == startCol;

        if(!this.hasMoved && (endRow != nextRow)) {
            try{
                Spot nextSpot = board.getSpot(nextRow,startCol);
                nextRow = this.isWhite() ? start.getRow() - 2 : start.getRow() + 2;
                canMove = endRow == nextRow && // can be 2 up/down
                        end.getPiece() == null && // but must be empty
                        endCol == startCol && // must be in same col
                        nextSpot.getPiece() == null; // and not have a piece in-between
            } catch (Exception e) { // shouldn't happen
            }

        }

        return canMove || canCapture;

    }

//    @Override
//    public Set<Spot> legaMoves(Board board, int row, int col) {
//        if(!hasMoved){  // if piece hasn't moved can move up to spaces
//            row ++;
//           while(board.getSpot(row,col).getPiece() == null || row == 2){
//               moves.add(board.getSpot(row, col));
//               row ++;
//           }
//        }else{
//            if(board.getSpot(row ++, col).getPiece() == null){
//                moves.add(board.getSpot(row,col));
//            }
//        }
//        //captures
//
//
//        return moves;
//    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wp " : "bp ";
    }

}
