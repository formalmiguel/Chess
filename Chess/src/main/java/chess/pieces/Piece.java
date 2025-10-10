package chess.pieces;
import chess.board.*;
public abstract class Piece {
    protected boolean killed = false;
    protected boolean white = false;
    protected String name;

    public Piece(boolean white){
        this.setWhite(white);
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