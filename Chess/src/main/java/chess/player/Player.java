package chess.player;
import chess.pieces.Piece;

public class Player {

    Piece[] pieces = new Piece[16];
    private final Boolean color;

    public Player(boolean white){
        this.color = white;
    }

    public Boolean Color(){
        return this.color;
    }

    public boolean makeMove(String move){
        //player will take a string that looks like this "a1 b2"
        // separate it by splitting on space
        // split[0][0] - 'a' == a - a = 0 fromRow
        // split [0][1] - 1 == 1 - 1 = 0 fromCol
        // split[1][0] - 'a' == a - a = 0 toRow
        // split[1][1] - 1 == 1 - 1 = 0 toCol

        return false;
    }


}