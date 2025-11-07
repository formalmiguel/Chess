package chess_part2.file;
import chess_part2.board.Board;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JDialog {
    private Board board; // reference to the chess board to update settings

    private Color priorLightColor = new Color(240, 217, 181);
    private Color priorDarkColor = new Color(181, 136, 99);
    private Color curLighColor;
    private Color curDarkColor;

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

        settingsPanel.add(new JLabel("Piece font:"));
        String[] fonts = {"Monospaced", "Serif", "DialogInput"};
        JComboBox<String> fontCombo = new JComboBox<>(fonts);
        settingsPanel.add(fontCombo);

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
                curLighColor = color;
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

        applyBtn.addActionListener(e -> {
            // Apply settings to the board
            priorLightColor = curLighColor;
            priorDarkColor = curDarkColor;
            dispose();
        });

        cancelBtn.addActionListener(e -> {
            // Reset to prior settings
            board.setLightSquareColor(priorLightColor);
            board.setDarkSquareColor(priorDarkColor);
            dispose();
        });
    }

    public void showSettings() {
        setVisible(true);
    }
}
