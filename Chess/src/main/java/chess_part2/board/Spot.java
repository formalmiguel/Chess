package chess_part2.board;

import chess_part2.pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class Spot extends JPanel {
    private Piece piece;
    private int row;
    private int col;
    public String name;
    private String tileName;
    private Color color;
    private JLabel pieceLabel;

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

    public void setPiece(Piece p){
        this.piece = p;
        this.setName(p);
        String cur = p == null ? "" : p.getUNICODE_PIECE();

        pieceLabel.setText(cur);
        revalidate();
        repaint();
    }

    public Piece getPiece(){
        return this.piece;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setName(Piece p){
        if(p != null) {
            name = p.getName();
        }else {
            name = this.tileName;
        }
    }

    public String getName() {
        return name;
    }

    public void setSquareName(){
        if((this.col % 2 == 0) == (this.row % 2 == 0)){
            this.tileName = "   ";
            this.color =  new Color(240, 217, 181);

        }else{
            this.tileName = "## ";
            this.color = new Color(181, 136, 99);
        }
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(this.color);
    }

    public void setPieceFont(Font font) {
        pieceLabel.setFont(font);
        revalidate();
        repaint();
    }

}
