package chess_part2.board;

import chess_part2.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class Board extends JPanel {
    private final Spot [][] boxes = new Spot[8][8];
    public Spot selectedSpot = null;
    public Set<Piece> pieces = new HashSet<>();
//    public Set<Piece> temp = new HashSet<>();
    private boolean whiteTurn = true;

    public Board(){
        setLayout(new GridLayout(8,8));
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

            startPiece.setCurCol(end.getCol());
            startPiece.setCurRow(end.getRow());
        } else{
            throw new RuntimeException("Was not able to make move");
        }


    }

    public void resetBoard(){
        removeAll();
        pieces.clear();

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

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Spot spot = boxes[row][col];
                spot.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleMouseClick(spot);
                    }
                });
                add(spot);

            }
        }

        revalidate();
        repaint();

    }

//    public String stringBoard(){
////        System.out.println("  A  B  C  D  E  F  G  H");
//        String res = "  A  B  C  D  E  F  G  H\n";
//        for (int i = 0; i < 8; i++) {
//            res += (8-i)+ " ";
//            for (int j = 0; j < 8; j++) {
//                res += boxes[i][j].getName();
//            }
//            res += "\n";
//        }
//        return res;
//    }
//
//    public void printBoard(){
//        System.out.println(this.stringBoard());
//    }

    private void handleMouseClick(Spot clickedSpot) {
        // no piece selected yet
        if (selectedSpot == null) {
            //spot clicked has a piece
            if (clickedSpot.getPiece() != null && clickedSpot.getPiece().isWhite() == whiteTurn) {
                selectedSpot = clickedSpot;
                clickedSpot.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            } else if (clickedSpot.getPiece() != null && clickedSpot.getPiece().isWhite() != whiteTurn) {
                JOptionPane.showMessageDialog(this,
                        "NOT " + (whiteTurn ? "Black" : "White") + "'s TURN!");
            }
            else{
                JOptionPane.showMessageDialog(this,
                        "Please choose a piece");
            }
            //we return since a piece has been picked
            return;
        }

        // same spot clicked again so, deselect
        if (selectedSpot == clickedSpot) {
            selectedSpot.setBorder(null);
            selectedSpot = null;
            return;
        }

        try {
            MovePiece(selectedSpot, clickedSpot);
            // add move here.
            // move was successful switch players
            whiteTurn = !whiteTurn;

            if (isCheckmate(whiteTurn)) {
                JOptionPane.showMessageDialog(this,
                        "Checkmate! " + (whiteTurn ? "Black" : "White") + " wins!");
                System.exit(0);
            } else if (isInCheck(whiteTurn)) {
                System.out.println((whiteTurn ? "White" : "Black") + " is in check!");
            }

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid move: " + ex.getMessage());
        }

        selectedSpot.setBorder(null);
        selectedSpot = null;

        revalidate();
        repaint();
    }

    public boolean isWhiteTurn(){
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn){
        this.whiteTurn = whiteTurn;
    }

    public void addPiece(Piece p, int row, int col){
        Spot spot = boxes[row][col];
        spot.setPiece(p);
        p.setCurRow(row);
        p.setCurCol(col);
        pieces.add(p);
    }

    public void clearBoard(){
        removeAll();
        pieces.clear();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                boxes[i][j] = new Spot(i, j, null);
                Spot spot = boxes[i][j];
                spot.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleMouseClick(spot);
                    }
                });
                add(spot);
            }
        }
        revalidate();
        repaint();
    }
}

