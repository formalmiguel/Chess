package chess_part1;

import chess_part1.board.*;
import chess_part1.player.Player;
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
            board.printBoard();

                System.out.print(currentPlayer.Color() ? "White, ": "Black, ");
                System.out.print("please enter your move: ");
                String input = scanner.nextLine();
                if(input.equals("quit")){
                    System.out.println((currentPlayer.Color() ? "White": "Black") + " quit.");
                    return;
                }
            try{
                if(input.length() != 5 || input.charAt(2) != ' '){
                    System.out.println("Use a format like 'e2 e4'");
                    continue;
                }
                int startCol = input.charAt(0) - 'a';
                int startRow = 8 - (input.charAt(1) - '0');

                int endCol = input.charAt(3) - 'a';
                int endRow = 8 - (input.charAt(4) - '0');
                // add checking for input
                Spot startSpot = board.getSpot(startRow, startCol);
                Spot destination =  board.getSpot(endRow,endCol);

                if(startSpot.getPiece() == null || startSpot.getPiece().isWhite() != currentPlayer.Color()){
                    System.out.println("Not your piece or You choose an empty square");
                    continue;
                }

                board.MovePiece(startSpot, destination); //both will be valid

                boolean opponent = !currentPlayer.Color();
                if(board.isCheckmate(opponent)){
                    board.printBoard();
                    System.out.println("Checkmate! " + (currentPlayer.Color() ? "White": "Black") + " wins.");
                    break;
                } else if (board.isInCheck(opponent)) {
                    System.out.println((opponent ? "White": "Black") + " is in check.");
                }

                //switch user
                currentPlayer = (currentPlayer == white) ? black : white;

            } catch (Exception e) {
                System.out.println(e);

            }


        }

    }
}
