package chess_part2.pieces;

import chess_part2.board.Board;
import chess_part2.board.Spot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Abstract base class representing a chess piece.
 * Provides shared functionality for all specific piece types (King, Queen, Rook, etc.).
 */
public abstract class Piece {
    protected boolean white;
    protected String name;
    public boolean hasMoved;
    public Piece hittingPiece;
    private int curRow;
    private int curCol;
    protected String UNICODE_PIECE;
    protected ImageIcon pieceImage;

    /**
     * Constructs a new Piece.
     *
     * @param white true if the piece is white, false if black
     * @param row   initial row position of the piece
     * @param col   initial column position of the piece
     */
    public Piece(boolean white, int row, int col){
        this.setWhite(white);
        this.hasMoved = false;
        this.setCurRow(row);
        this.setCurCol(col);
        this.setName();
        loadPieceImage();
    }
    /**
     * Returns whether the piece is white.
     *
     * @return true if white, false if black
     */
    public boolean isWhite(){
        return this.white;
    }

    /**
     * Sets the color of the piece.
     *
     * @param white true if white, false if black
     */
    public void setWhite(boolean white){
        this.white = white;
    }
    /**
     * Returns the name of the piece.
     *
     * @return the name of the piece
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks whether two spots represent the same square.
     *
     * @param start starting spot
     * @param end   ending spot
     * @return true if the squares are the same, false otherwise
     */
    public boolean sameSquare(Spot start, Spot end){ // handle outside of loop?
        return start.getCol() == end.getCol() && start.getRow() == end.getRow();
    }

    /**
     * Determines if the target square can be occupied by this piece.
     * A square is valid if it is empty or occupied by an opponent's piece.
     *
     * @param target the target spot to move to
     * @return true if the square can be occupied, false otherwise
     */
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

    /**
     * Checks if there is any piece blocking the movement along a straight (cross) path.
     *
     * @param end   the destination spot
     * @param board the current game board
     * @return true if there is a piece on the path, false otherwise
     */
    public boolean pieceOnCross(Spot end, Board board){
        //moving left?
        for(int col = this.getCurCol() - 1; col > end.getCol(); col--){
            for(Piece piece : board.pieces){
                if( piece.getCurCol() == col && piece.getCurRow() == end.getCol()){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        //moving right
        for(int col = this.getCurCol() + 1; col < end.getCol(); col++){
            for(Piece piece : board.pieces){
                if( piece.getCurCol() == col && piece.getCurRow() == end.getCol()){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        //moving up
        for(int row = this.getCurRow() - 1; row > end.getRow(); row--){
            for(Piece piece : board.pieces){
                if( piece.getCurCol() == end.getCol() && piece.getCurRow() == row){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        //moving down
        for(int row = this.getCurRow() + 1; row < end.getRow(); row++){
            for(Piece piece : board.pieces){
                if( piece.getCurCol() == end.getCol() && piece.getCurRow() == row){
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        // no piece on cross
        return false;
    }

    /**
     * Sets the name of the piece.
     * Must be implemented by each subclass (e.g., "Pawn", "Rook", etc.).
     */
    protected abstract void setName();
    /**
     * Determines whether the piece can move to the specified spot on the given board.
     *
     * @param board the current game board
     * @param end   the destination spot
     * @return true if the move is valid, false otherwise
     */
    public abstract boolean canMove(Board board, Spot end);

    /**
     * Gets the current row position of the piece.
     *
     * @return the row index
     */
    public int getCurRow() {
        return curRow;
    }

    /**
     * Sets the current row position of the piece.
     *
     * @param curRow the row index
     */
    public void setCurRow(int curRow) {
        this.curRow = curRow;
    }

    /**
     * Gets the current column position of the piece.
     *
     * @return the column index
     */
    public int getCurCol() {
        return curCol;
    }

    /**
     * Sets the current column position of the piece.
     *
     * @param curCol the column index
     */
    public void setCurCol(int curCol) {
        this.curCol = curCol;
    }

    /**
     * Returns the Unicode symbol representing this piece.
     *
     * @return Unicode string of the piece
     */
    public String getUNICODE_PIECE(){
        return this.UNICODE_PIECE;
    }

    /**
     * Loads the image for the piece from the local filesystem.
     * Looks for the image under the path "src/main/java/chess_part2/pieces/piecesimg/" + piece's name.
     */
    protected void loadPieceImage() {
        String path = "src/main/java/chess_part2/pieces/piecesimg/"+ this.getName().trim() +".png";
        File imgFile = new File(path);
        if (imgFile.exists()) {
            pieceImage = new ImageIcon(path);
        } else {
            pieceImage = null;
            System.out.println("Image not found!");
        }

    }
    /**
     * Returns the image icon of this piece, or null if not loaded.
     *
     * @return the ImageIcon for this piece
     */
    public ImageIcon getImage() {
        return pieceImage;
    }
}

