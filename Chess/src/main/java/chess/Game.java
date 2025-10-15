package chess;

import chess.board.*;
import chess.player.Player;
import java.util.Scanner;

public class Game {
    Board board = new Board();
    Player white = new Player(true);
    Player black = new Player(false);
    Player currentPlayer = white;
    boolean CheckMate = false;

    public void start(){
//        initializes a game.
//        give each player its game pieces ?

    }
    public void end(){
//        ends a game a declares a winner or draw
    }

    public void play(){
//        main game loop
        Scanner scanner = new Scanner(System.in);

        while(!CheckMate){
            //get player input form "a1 b1"

            try{
                board.printBoard();
                System.out.print("Please enter your move: ");
                String input = scanner.nextLine();

                int startCol = input.charAt(0) - 'a';
                int startRow = 8 - (input.charAt(1) - '0');

                int endCol = input.charAt(3) - 'a';
                int endRow = 8 - (input.charAt(4) - '0');
                // add checking for input
                Spot startSpot = board.getSpot(startRow, startCol);
                if(startSpot.getPiece() == null || startSpot.getPiece().isWhite() != currentPlayer.Color()){
                    System.out.println("Not your piece");
                    continue;
                }

                Spot destination =  board.getSpot(endRow,endCol);
                board.MovePiece(startSpot, destination);
                //switch user
                currentPlayer = (currentPlayer == white) ? black : white;

            } catch (Exception e) {
                System.out.println("issue occurred");

            }


        }

    }
}
