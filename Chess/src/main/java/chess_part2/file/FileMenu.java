package chess_part2.file;

import chess_part2.board.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Represents the menu bar for the chess game window.
 * Provides menu options for starting a new game, saving/loading games,
 * and accessing game settings.
 */
public class FileMenu {
    private Board board; // reference to your Board
    private JFrame curFrame;
    private SettingsWindow settingsWindow;
    /**
     * Constructs a FileMenu associated with the specified board and frame.
     *
     * @param board    the current game board
     * @param curFrame the frame where the game is displayed
     */
    public FileMenu(Board board, JFrame curFrame) {
        this.board = board;
        this.curFrame = curFrame;
    }

    /**
     * Creates and configures the menu bar for the game.
     * Adds options for creating a new game, saving/loading a game,
     * and opening the settings window.
     *
     * @return the fully constructed JMenuBar
     */
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu settingsMenu = new JMenu("Settings");

        JMenuItem showSettingMenu = new JMenuItem("Options");

        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem saveGame = new JMenuItem("Save Game");
        JMenuItem loadGame = new JMenuItem("Load Game");

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("new game");
                board.resetBoard();
            }
        });

        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveGame.save(board);
                System.out.println("save game");
            }
        });

        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadGame.load(board);
                System.out.println("load game");
            }
        });

        showSettingMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (settingsWindow == null) {
                    settingsWindow = new SettingsWindow(curFrame, board);
                }
                settingsWindow.showSettings();
            }
        });

        fileMenu.add(newGame);
        fileMenu.add(saveGame);
        fileMenu.add(loadGame);

        settingsMenu.add(showSettingMenu);

        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);

        return menuBar;
    }
}