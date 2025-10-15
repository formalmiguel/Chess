package chess.pieces;
import chess.board.*;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
//    protected boolean killed = false;
    protected boolean white;
    protected String name;
    public boolean hasMoved;
    public Piece hittingPiece;
    public int curRow;
    public int curCol;
//    public Set<Spot> moves;

    public Piece(boolean white, int row, int col){
        this.setWhite(white);
        this.hasMoved = false;
        this.curRow = row;
        this.curCol = col;
//        moves = new HashSet<Spot>();
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


    protected abstract void setName();
    public abstract boolean canMove(Board board, Spot start, Spot end);
//    public abstract Set<Spot> legaMoves(Board board);
}

//
//class A {
//    private int a;
//    public A(int a) { this.a = a; }
//    public int getA() {return a;}
//}
//
//class B extends A {
//    public B(int b) { super(b); }
//    public int getB() {return getA();}
//}
//
//int result = new B(10).getA();