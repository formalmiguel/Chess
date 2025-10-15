package chess.board;

import chess.pieces.Piece;

public class Spot {
    private Piece piece;
    private int row;
    private int col;
    public String name;
    private String tileName;

    public Spot(int row, int col, Piece piece){
        this.setPiece(piece);
        this.setRow(row);
        this.setCol(col);
        this.setTileName();
        this.setName(piece);
    }

    public void setPiece(Piece p){
        this.piece = p;
        this.setName(p);
    }
    public Piece getPiece(){
        return this.piece;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setName(Piece p){
        if(p != null) {
            name = p.getName();
        }else {
            name = this.tileName;
        }
    }

    public String getName() {
        return name;
    }
    public void setTileName(){
        if((this.col % 2 == 0) == (this.row % 2 == 0)){
            this.tileName = "   ";
        }else{
            this.tileName = "## ";
        }


    }
}