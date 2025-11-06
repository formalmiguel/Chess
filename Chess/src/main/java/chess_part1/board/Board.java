package chess_part1.board;
import chess_part1.pieces.*;

import java.util.HashSet;
import java.util.Set;

public class Board {
    Spot [][] boxes = new Spot[8][8];
    public Set<Piece> pieces = new HashSet<>();
//    public Set<Piece> temp = new HashSet<>();

    public Board(){
        this.resetBoard();
    }

    private boolean isIllegal(Spot start, Spot end){
        Piece movingPiece = start.getPiece();
        Piece capturedPiece = end.getPiece();
        //simulate an attempt
        Set<Piece> temp = new HashSet<>(pieces);

        start.setPiece(null);
        end.setPiece(movingPiece);
        movingPiece.setCurRow(end.getRow());
        movingPiece.setCurCol(end.getCol());
        if(capturedPiece != null){
            temp.remove(capturedPiece);
        }

        Set<Piece> realPieces = pieces;
        pieces = temp;

        boolean isKingInCheck = isInCheck(movingPiece.isWhite());

        //undo attempt
        pieces = realPieces;
        start.setPiece(movingPiece);
        end.setPiece(capturedPiece);
        movingPiece.setCurRow(start.getRow());
        movingPiece.setCurCol(start.getCol());

        return isKingInCheck;
    }

    public boolean isInCheck(boolean white){
        Piece king = null;
        for(Piece p : pieces){
            if(p.isWhite() == white && p.getName().charAt(1) == 'K'){
                king = p;
                break;
            }
        }
        try{
            assert king != null;
            Spot kingSpot = getSpot(king.getCurRow(), king.getCurCol()); //maybe we should just use spot array?
            for(Piece p : pieces){
                if(p.isWhite() != white && p.canMove(this, kingSpot)){
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("king isn't on board");
        }

        return false;
    }

    public boolean isCheckmate(boolean white){
        if(!isInCheck(white)){
            return false; // can't be in checkmate without being in check
        }

        for(Piece p : pieces){
            if(p.isWhite() == white){
                int row = p.getCurRow();
                int col = p.getCurCol();
                //try every piece's every move
                for(int r = 0; r<8;r++){
                    for(int c = 0; c < 8; c++){
                        Spot start = boxes[row][col];
                        Spot end = boxes[r][c];
                        if(!p.canMove(this,end) || start.equals(end)){
                            continue; //can't move to here
                        }
                        if(!isIllegal(start,end)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public Spot getSpot(int row, int col) throws Exception {
        if(row < 0 || row > 7 || col < 0 || col > 7){
            throw new Exception("Index out of bounds");
        }

        return boxes [row][col];
    }
    public void MovePiece(Spot start, Spot end){
        Piece startPiece = start.getPiece();
        if(startPiece.canMove(this, end) && !isIllegal(start, end)){
            Piece capturedPiece = end.getPiece();
            if(capturedPiece != null){
                pieces.remove(capturedPiece);
            }

            start.setPiece(null);
            end.setPiece(startPiece);
            startPiece.hasMoved = true;


            //can add captured pieces here.
            startPiece.setCurCol(end.getCol());
            startPiece.setCurRow(end.getRow());
        } else{
            throw new RuntimeException("Was not able to make move");
        }

        //else throw an error that piece can not make that move.

    }

    public void resetBoard(){

        boxes[0][0] = new Spot(0, 0, new Rook(false, 0, 0));
        boxes[0][1] = new Spot(0, 1, new Knight(false, 0, 1));
        boxes[0][2] = new Spot(0, 2, new Bishop(false, 0, 2));
        boxes[0][3] = new Spot(0, 3, new Queen(false, 0, 3));
        boxes[0][4] = new Spot(0, 4, new King(false, 0, 4));
        boxes[0][5] = new Spot(0, 5, new Bishop(false, 0 , 5));
        boxes[0][6] = new Spot(0, 6, new Knight(false, 0 , 6));
        boxes[0][7] = new Spot(0, 7, new Rook(false, 0, 7));
        //...


        // initialize black pieces
        boxes[7][0] = new Spot(7, 0, new Rook(true, 7, 0));
        boxes[7][1] = new Spot(7, 1, new Knight(true, 7, 1));
        boxes[7][2] = new Spot(7, 2, new Bishop(true, 7, 2));
        boxes[7][3] = new Spot(7, 3, new Queen(true, 7 , 3));
        boxes[7][4] = new Spot(7, 4, new King(true, 7, 4));
        boxes[7][5] = new Spot(7, 5, new Bishop(true, 7, 5));
        boxes[7][6] = new Spot(7, 6, new Knight(true, 7 , 6));
        boxes[7][7] = new Spot(7, 7, new Rook(true, 7, 7));

        // initialize remaining boxes without any piece
        for(int i = 0; i < 8; i++){
            boxes[1][i] = new Spot(1, i, new Pawn(false, 1, i)); //black pawn
            boxes[6][i] = new Spot(6, i, new Pawn(true, 6, i));
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }

      for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = boxes[i][j].getPiece();
                if(p != null){
                    if (pieces != null) {
                        pieces.add(p);
                    }
                }
            }
        }

    }

    public String stringBoard(){
//        System.out.println("  A  B  C  D  E  F  G  H");
        String res = "  A  B  C  D  E  F  G  H\n";
        for (int i = 0; i < 8; i++) {
            res += (8-i)+ " ";
            for (int j = 0; j < 8; j++) {
                res += boxes[i][j].getName();
            }
            res += "\n";
        }
        return res;
    }

    public void printBoard(){
        System.out.println(this.stringBoard());
    }

}