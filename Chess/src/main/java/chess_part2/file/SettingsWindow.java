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
    private int priorPieceSize = 80;
    private int curPieceSize = priorPieceSize;
    private boolean priorFontMode = false; // false = Image, true = Unicode
    private boolean curFontMode = priorFontMode;

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

        settingsPanel.add(new JLabel("Piece Style:"));
        String[] styles = {"Image", "Unicode"};
        JComboBox<String> stylesCombo = new JComboBox<>(styles);
        settingsPanel.add(stylesCombo);

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

//        pieceColorBtn.addActionListener(e -> {
//            Color color = JColorChooser.showDialog(this, "Choose Piece Color", Color.BLACK);
//            if (color != null) {
//                curPieceColor = color;
//                board.setPiecesFontColor(color);
//            }
//        });

        sizesCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSize = (String) sizesCombo.getSelectedItem();

                switch (selectedSize) {
                    case "Small":
                        curSize = new Dimension(400, 400);
                        curFont = new Font("DialogInput", Font.BOLD, 42);
                        curPieceSize = 45;
                        break;
                    case "Medium":
                        curSize = new Dimension(800, 800);
                        curFont = new Font("DialogInput", Font.BOLD, 64);
                        curPieceSize = 80;
                        break;
                    case "Large":
                        curSize = new Dimension(1200, 1200);
                        curFont = new Font("Monospaced", Font.BOLD, 120);
                        curPieceSize = 150;
                        break;
                }
                parent.setSize(curSize);

                if (curFontMode) {
                    board.setPiecesFont(curFont);
                } else {
                    board.resizeImg(curPieceSize);
                }

                board.revalidate();
                board.repaint();
            }
        });

        stylesCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStyle = (String) stylesCombo.getSelectedItem();

                switch (selectedStyle) {
                    case "Image":
                        board.resizeImg(priorPieceSize);
                        curFontMode = false;

                        break;
                    case "Unicode":
                        board.setPiecesFont(priorFont);
                        curFontMode = true;
                        break;
                    default:
                        break;
                }
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
            priorPieceSize = curPieceSize;
            priorFontMode = curFontMode;
            dispose();
        });

        cancelBtn.addActionListener(e -> {
            parent.setSize(priorSize);
            board.setLightSquareColor(priorLightColor);
            board.setDarkSquareColor(priorDarkColor);
            board.setPiecesFont(priorFont);
            board.setPiecesFontColor(priorPieceColor);
            if (priorFontMode) {
                board.setPiecesFont(priorFont);
            } else {
                board.resizeImg(priorPieceSize);
            }

            curPieceSize = priorPieceSize;
            curSize = priorSize;
            curLightColor = priorLightColor;
            curDarkColor = priorDarkColor;
            curFont = priorFont;
            curPieceColor = priorPieceColor;
            curFontMode = priorFontMode;
            dispose();
        });


    }

    public void showSettings() {
        setVisible(true);
    }
}
