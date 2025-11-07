package chess_part2.file;
import chess_part2.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JDialog {
    private Board board; // reference to the chess board to update settings

    private Color priorLightColor = new Color(240, 217, 181);
    private Color priorDarkColor = new Color(181, 136, 99);
    private Color curLightColor = priorLightColor;
    private Color curDarkColor = priorDarkColor;
    private Dimension priorSize = new Dimension(800, 800);
    private Dimension curSize = priorSize;
    private Font priorFont = new Font("DialogInput", Font.BOLD, 64);
    private Font curFont = priorFont;
    private Color priorPieceColor = Color.black;
    private Color curPieceColor = priorPieceColor;

    public SettingsWindow(JFrame parent, Board board) {
        super(parent, "Settings", true);
        this.board = board;

        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(parent);


        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(0, 2, 10, 10));

        settingsPanel.add(new JLabel("Light square color:"));
        JButton lightColorBtn = new JButton("Choose...");
        settingsPanel.add(lightColorBtn);

        settingsPanel.add(new JLabel("Dark square color:"));
        JButton darkColorBtn = new JButton("Choose...");
        settingsPanel.add(darkColorBtn);

        settingsPanel.add(new JLabel("Piece color:"));
        JButton pieceColorBtn = new JButton("Choose...");
        settingsPanel.add(pieceColorBtn);

        settingsPanel.add(new JLabel("Board Size:"));
        String[] sizes = {"Medium", "Small", "Large"};
        JComboBox<String> sizesCombo = new JComboBox<>(sizes);
        settingsPanel.add(sizesCombo);


        JPanel buttonPanel = new JPanel();
        JButton applyBtn = new JButton("Apply");
        JButton cancelBtn = new JButton("Cancel");
        buttonPanel.add(applyBtn);
        buttonPanel.add(cancelBtn);

        add(settingsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        lightColorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Light Square Color", Color.LIGHT_GRAY);
            if (color != null) {
                curLightColor = color;
                board.setLightSquareColor(color);
            }
        });

        darkColorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Dark Square Color", Color.DARK_GRAY);
            if (color != null) {
                curDarkColor = color;
                board.setDarkSquareColor(color);
            }
        });

        pieceColorBtn.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Choose Piece Color", Color.BLACK);
            if (color != null) {
                curPieceColor = color;
                board.setPiecesFontColor(color);
            }
        });

        sizesCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSize = (String) sizesCombo.getSelectedItem();

                switch (selectedSize) {
                    case "Small":
                        System.out.println("User chose Small board");
                        Dimension small = new Dimension(400, 400);
                        Font smallFont = new Font("DialogInput", Font.BOLD, 42);
                        parent.setSize(small);
                        board.setPiecesFont(smallFont);
                        curSize = small;
                        curFont = smallFont;
                        break;
                    case "Medium":
                        System.out.println("User chose Medium board");
                        Dimension medium = new Dimension(800, 800);
                        Font mediumFont = new Font("DialogInput", Font.BOLD, 64);
                        parent.setSize(medium);
                        board.setPiecesFont(mediumFont);
                        curSize = medium;
                        curFont = mediumFont;
                        break;
                    case "Large":
                        System.out.println("User chose Large board");
                        Dimension large = new Dimension(1200,1200);
                        Font largeFont = new Font("Monospaced", Font.BOLD, 120);
                        parent.setSize(large);
                        board.setPiecesFont(largeFont);
                        curSize = large;
                        curFont = largeFont;
                        break;
                    default:
                        break;
                }
                System.out.println(priorSize);
                System.out.println(curSize);
                 board.revalidate();
                 board.repaint();
            }
        });




        applyBtn.addActionListener(e -> {
            priorSize = curSize;
            priorLightColor = curLightColor;
            priorDarkColor = curDarkColor;
            priorFont = curFont;
            priorPieceColor = curPieceColor;
            dispose();
        });

        cancelBtn.addActionListener(e -> {
            parent.setSize(priorSize);
            board.setLightSquareColor(priorLightColor);
            board.setDarkSquareColor(priorDarkColor);
            board.setPiecesFont(priorFont);
            board.setPiecesFontColor(priorPieceColor);
            curSize = priorSize;
            curLightColor = priorLightColor;
            curDarkColor = priorDarkColor;
            curFont = priorFont;
            curPieceColor = priorPieceColor;
            dispose();
        });


    }

    public void showSettings() {
        setVisible(true);
    }
}
