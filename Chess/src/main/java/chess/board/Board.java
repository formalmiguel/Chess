package chess.board;
import chess.pieces.*;

public class Board {
    Spot [][] boxes = new Spot[8][8];
    public Board(){
        this.resetBoard();
    }
    public Spot getBox(int row, int col){
        if(row < 0 || row > 7 || col < 0 || col > 7){
//            throw new Exception("Index out of bounds");
            System.out.println("index out of bounds");
        }
        return boxes [row][col];
    }

    public void resetBoard(){

        boxes[0][0] = new Spot(0, 0, new Rook(false));
        boxes[0][1] = new Spot(0, 1, new Knight(false));
        boxes[0][2] = new Spot(0, 2, new Bishop(false));
        boxes[0][3] = new Spot(0, 3, new Queen(false));
        boxes[0][4] = new Spot(0, 4, new King(false));
        boxes[0][5] = new Spot(0, 5, new Bishop(false));
        boxes[0][6] = new Spot(0, 6, new Knight(false));
        boxes[0][7] = new Spot(0, 7, new Rook(false));
        //...


        // initialize black pieces
        boxes[7][0] = new Spot(7, 0, new Rook(true));
        boxes[7][1] = new Spot(7, 1, new Knight(true));
        boxes[7][2] = new Spot(7, 2, new Bishop(true));
        boxes[7][3] = new Spot(7, 3, new Queen(true));
        boxes[7][4] = new Spot(7, 4, new King(true));
        boxes[7][5] = new Spot(7, 5, new Bishop(true));
        boxes[7][6] = new Spot(7, 6, new Knight(true));
        boxes[7][7] = new Spot(7, 7, new Rook(true));

        // initialize remaining boxes without any piece
        for(int i = 0; i < 8; i++){
            boxes[1][i] = new Spot(1, i, new Pawn(false));
            boxes[6][i] = new Spot(6, i, new Pawn(true));
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
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
        System.out.println(stringBoard());
    }


}