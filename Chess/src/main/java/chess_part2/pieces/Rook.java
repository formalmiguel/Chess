package chess_part2.pieces;


import chess_part2.board.Board;
import chess_part2.board.Spot;

public class Rook extends Piece {
    public Rook(boolean white, int row, int col){
        super(white, row, col);
        this.UNICODE_PIECE = this.isWhite() ? "♖": "♜";
    }

    @Override
    public boolean canMove(Board board, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }

        boolean math = end.getCol() == getCurCol() || end.getRow() == getCurRow();
        return math && isValidSquare(end) && !pieceOnCross(end, board);
    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wR " : "bR ";
    }

}