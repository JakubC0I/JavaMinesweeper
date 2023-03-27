import javax.swing.*;
import java.awt.*;

class MinesweeperGUI {
    JPanel createMenu(JFrame frame) {
        JPanel panel = new JPanel();
        JButton newGame = new JButton("NEW GAME");

        JButton exit = new JButton("EXIT");
        panel.add(newGame, BorderLayout.PAGE_START);
        panel.add(exit, BorderLayout.PAGE_END);
        panel.setVisible(true);

        newGame.addActionListener(e -> swapPanel(panel, difficultyMenu(frame), frame));

        exit.addActionListener(e -> System.exit(0));

        return panel;
    }

    void swapPanel(JPanel oldPanel, JPanel newPanel, JFrame mainFrame) {
        oldPanel.setVisible(false);
        newPanel.setVisible(true);
        mainFrame.add(newPanel);
    }

    JPanel difficultyMenu(JFrame frame) {
        GridLayout grid = new GridLayout(4,1);
        JPanel panel = new JPanel(grid);
        JButton easy = new JButton("EASY");
        easy.addActionListener(e -> swapPanel(panel, startGame(10, 5), frame));
        JButton normal = new JButton("NORMAL");
        normal.addActionListener(e -> swapPanel(panel, startGame(10, 20), frame));
        JButton hard = new JButton("HARD");
        hard.addActionListener(e -> swapPanel(panel, startGame(20, 50), frame));
        JButton custom = new JButton("CUSTOM");
        JPanel customPanel = customMenu(frame);
        custom.addActionListener(e -> swapPanel(panel, customPanel, frame));
        panel.add(easy);
        panel.add(normal);
        panel.add(hard);
        panel.add(custom);
        return panel;
    }

    JPanel customMenu(JFrame frame) {
        JPanel panel = new JPanel();
        JTextField sizeField = new JTextField(2);
        JTextField minesField = new JTextField(2);


        JButton confirm = new JButton("CONFIRM");

        panel.add(sizeField);
        panel.add(minesField);
        panel.add(confirm);

        confirm.addActionListener(e -> swapPanel(panel, startGame(Integer.parseInt(sizeField.getText()), Integer.parseInt(minesField.getText())), frame));

        return panel;
        }

    JPanel startGame(int size, int mines) {
        Spielfeld board = new Spielfeld(size, mines);
        JPanel panel = new JPanel(new GridLayout(board.getSize(), board.getSize()));

        board.createBoard();
        for(int x = 0; x < board.getSize(); x++) {
            for(int y = 0; y < board.getSize(); y++) {
                Field field = board.getField(x,y);
                panel.add(field);
            }
        }
        return panel;
    }
}