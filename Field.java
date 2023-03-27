import javax.swing.JButton;
import java.awt.Color;

class Field extends JButton{
    boolean isMine = false;
    boolean opened = false;
    int number = 0;
    int x;
    int y;

    
    Field(int x, int y) {
        this.x = x;
        this.y = y; 

        // addActionListener(e -> boom());
        this.setBackground(Color.BLACK);
        this.setForeground(Color.BLACK);
        // this.setVisible(false);
    }

    void open() {
        if (opened) {
            return;
        }
        this.opened = true;
        if (!this.isMine) {
            this.setBackground(Color.WHITE);
        }
    }
// It should show all bombs and color the fields red
    // void boom() {
    //     if (this.isMine) {
    //         System.exit(0);
    //     }
    // }


    void setMined(boolean mined) {
        this.isMine = mined;
    }

    void addMine() {
        this.number += 1;
        if (!this.isMine) {
            this.setText("" + this.number);
        } else {
            this.setText("");

        }
    }

    boolean getIsMine() {
        return isMine;
    }

    int getMines() {
        return number;
    }

    int getCordX() {
        return x;
    }

    int getCordY() {
        return y;
    }

    boolean isOpened() {
        return opened;
    }

}