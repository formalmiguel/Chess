package chess.pieces;
import chess.board.*;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {
//    protected boolean killed = false;
    protected boolean white;
    protected String name;
    public boolean hasMoved;
    public Set<Spot> moves;

    public Piece(boolean white){
        this.setWhite(white);
        this.hasMoved = false;
        moves = new HashSet<Spot>();
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