package chess.player;

public class Player {
    private final Boolean color;

    public Player(boolean white){
        this.color = white;
    }

    public Boolean Color(){
        return this.color;
    }
}