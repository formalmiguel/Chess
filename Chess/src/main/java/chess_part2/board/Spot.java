package chess_part2.board;

import chess_part2.pieces.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a single square (spot) on the chessboard.
 * Each spot knows its position (row and column), color, and the chess piece occupying it (if any).
 * This class is a JPanel that visually displays the piece on the board using either
 * an image icon or a Unicode character, depending on the rendering mode.
 */
public class Spot extends JPanel {
    private Piece piece;
    private int row;
    private int col;
    public String name;
    private String tileName;
    private Color color;
    private JLabel pieceLabel;

    /**
     * Constructs a new Spot with the specified row, column, and occupying piece.
     *
     * @param row   the row index of the spot
     * @param col   the column index of the spot
     * @param piece the chess piece placed on this spot (can be null)
     */
    public Spot(int row, int col, Piece piece){

        this.setRow(row);
        this.setCol(col);
        this.setSquareName();
        this.setName(piece);
        setFont(new Font("DialogInput", Font.BOLD, 64));


        setBackground(this.color);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(80, 80));

        pieceLabel = new JLabel("", SwingConstants.CENTER);
        pieceLabel.setFont(new Font("DialogInput", Font.BOLD, 64));
        add(pieceLabel, BorderLayout.CENTER);

        this.setPiece(piece);

    }

    /**
     * Sets the chess piece on this spot and updates its visual display.
     *
     * @param p the new chess piece to place on this spot (can be null)
     */
    public void setPiece(Piece p){
        this.piece = p;
        this.setName(p);
        removeAll();

        if (p != null && p.getImage() != null) {
            JLabel pieceLabel = new JLabel();
            ImageIcon icon = p.getImage();

            Image scaled = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            pieceLabel.setIcon(new ImageIcon(scaled));
            pieceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            pieceLabel.setVerticalAlignment(SwingConstants.CENTER);
            add(pieceLabel, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }

    /**
     * Returns the chess piece currently occupying this spot.
     *
     * @return the chess piece, or null if the spot is empty
     */
    public Piece getPiece() {
        return this.piece;
    }

    /** @return the column index of this spot */
    public int getCol() {
        return col;
    }

    /** @return the row index of this spot */
    public int getRow() {
        return row;
    }

    /**
     * Sets the column index of this spot.
     *
     * @param col the column index
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Sets the row index of this spot.
     *
     * @param row the row index
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets the display name for this spot based on the occupying piece.
     *
     * @param p the chess piece occupying this spot (can be null)
     */
    public void setName(Piece p){
        if(p != null) {
            name = p.getName();
        }else {
            name = this.tileName;
        }
    }

    /** @return the current name of this spot */
    public String getName() {
        return name;
    }

    /**
     * Initializes the tile’s default name and color based on its position.
     * Light tiles are beige, and dark tiles are brown, following a standard chessboard pattern.
     */
    public void setSquareName(){
        if((this.col % 2 == 0) == (this.row % 2 == 0)){
            this.tileName = "   ";
            this.color =  new Color(240, 217, 181);

        }else{
            this.tileName = "## ";
            this.color = new Color(181, 136, 99);
        }
    }

    /**
     * Sets the background color of this spot.
     *
     * @param color the new background color
     */
    public void setColor(Color color) {
        this.color = color;
        setBackground(this.color);
    }

    /**
     * Updates the visual display of the chess piece using a Unicode font representation
     * instead of an image.
     *
     * @param font the font to use for rendering the Unicode character
     */
    public void setPieceFont(Font font) {
        removeAll();
        JLabel pieceLabel = new JLabel("", SwingConstants.CENTER);

        Piece p = this.piece;
        this.setName(p);
        String cur = p == null ? "" : p.getUNICODE_PIECE();
        pieceLabel.setIcon(null);
        pieceLabel.setText(cur);
        pieceLabel.setFont(font);
        add(pieceLabel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Sets the text color used for displaying the Unicode chess piece.
     *
     * @param color the font color
     */
    public void setPieceFontColor(Color color) {
        if (pieceLabel != null) {
            pieceLabel.setForeground(color);
            revalidate();
            repaint();
        }
    }

    /**
     * Resizes the image of the chess piece to the specified width.
     *
     * @param w the new width and height (images are scaled proportionally)
     */
    public void resizeImg(int w) {
        removeAll();

        if (this.piece != null && this.piece.getImage() != null) {
            ImageIcon icon = this.piece.getImage();

            Image scaled = icon.getImage().getScaledInstance(w, w, Image.SCALE_SMOOTH);
            pieceLabel.setText("");
            pieceLabel.setIcon(new ImageIcon(scaled));
            pieceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            pieceLabel.setVerticalAlignment(SwingConstants.CENTER);
            add(pieceLabel, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }



}
