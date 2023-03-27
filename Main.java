import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

//Dodać dźwięk przegrania "wybuch"
public class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MinesweeperGUI gui = new MinesweeperGUI();

        JPanel menu = gui.createMenu(frame);

        frame.add(menu);
        frame.pack();
        frame.setVisible(true);
    }
}