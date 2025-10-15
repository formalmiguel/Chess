package chess.pieces;
import chess.board.*;

public class Knight extends Piece {
    int[][] moveset = {
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };
    public Knight(boolean white){
        super(white);
        this.setName();
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        boolean empty = end.getPiece() == null;
        if ( !empty && end.getPiece().isWhite() == this.isWhite()) { // if end piece is same as white
            return false;
        } // maybe we check before

        int row = Math.abs(start.getRow() - end.getRow());
        int col = Math.abs(start.getCol() - end.getCol());
        return row * col == 2;
    }

    public void generateMoves(int row, int col, Board board){
        try {
            Spot cur = board.getSpot(row, col);
            for (int[] move : moveset) {
                int newRow = row + move[0];
                int newCol = col + move[1];
                try {
                    Spot attempt = board.getSpot(newRow, newCol);
                        if(this.canMove(board, cur, attempt)){
                            this.moves.add(attempt);
                        }
                    } catch (Exception e) {

                    }

                }

        } catch(Exception e) {
            System.out.println("Knight issue");
        }

    }


    @Override
    protected void setName(){
        this.name = this.isWhite() ? "wN " : "bN ";
    }


}