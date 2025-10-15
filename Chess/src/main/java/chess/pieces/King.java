package chess.pieces;
import chess.board.*;


public class King extends Piece {
    public King(boolean white, int row, int col, Board board){
        super(white);
        this.setName();
        generateMoves(row, col, board);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        }
        int x = Math.abs(start.getRow() - end.getRow());
        int y = Math.abs(start.getCol() - end.getCol());
        return (x <= 1 && y <= 1) && (x + y != 0);

    }

    public void generateMoves(int row, int col, Board board){
        try {
            Spot cur = board.getSpot(row, col);
            for (int i = -1; i <= 1; i++) {
                for(int j = -1; j <= 1; j++){
                    try{
                        Spot attempt = board.getSpot(row + i, col + j);
                        if(this.canMove(board, cur, attempt)){
                            this.moves.add(attempt);
                        }
                    } catch (Exception e) {
                        continue;
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("King issue");
        }

    }

    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wK " : "bK ";
    }
}