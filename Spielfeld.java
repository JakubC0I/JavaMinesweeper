import java.util.*;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;


class Spielfeld {
    Field[][] fields;
    ArrayList<Field> minedFields = new ArrayList<Field>();
    int mines;
    int size;
    Image img;

    Image getImg() {
        try {
            Image img = ImageIO.read(getClass().getResource("./mine-icon.png"));
            return img;
        } catch(Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    int getMines() {
        return mines;
    }

    Field getField(int x, int y) {
        return fields[x][y];
    }

    int getSize() {
        return size;
    }

    List<Field> getNeighbours(Field tile) {
        List<Field> neighbours = new ArrayList<>();
        int[] points = new int[]{
            -1, -1,
            -1, 0,
            -1, 1,
            0, -1,
            0, 1,
            1, -1,
            1, 0,
            1, 1
        };
        for(int i = 0; i < points.length; i++) {
            int x = tile.getCordX() + points[i];
            int y = tile.getCordY() + points[++i];
            if (x >= 0 && x < size && y >= 0 && y < size) {
                Field field = this.getField(x, y);
                if(!field.getIsMine()) {
                    neighbours.add(field);
                }
            }
        }
        return neighbours;
    }

    void boom() {
        for (Field f : minedFields) {
            f.setBackground(Color.WHITE);
            f.setIcon(new ImageIcon(img));
        }
    }

    void leftClick(int x, int y) {
        if(fields[x][y].getIsMine()) {
            boom();
            fields[x][y].setBackground(Color.RED);
        }

        if (fields[x][y].getMines() >= 0 && !fields[x][y].isOpened()) {
            fields[x][y].open();
            if (fields[x][y].getMines() == 0) {
                List<Field> fs = getNeighbours(fields[x][y]);
                for(Field f : fs) {
                    leftClick(f.getCordX(), f.getCordY());
                }
            }
        }
    }
    
    Spielfeld(int size, int mines) {
        this.size = size;
        fields = new Field[size][size];
        this.mines = mines;
        this.img = getImg();
    } 

    void createBoard() {
        for(int x = 0; x < fields.length; x++) {
            for(int y = 0; y < fields.length; y++) {
                fields[x][y] = new Field(x, y);
                final int newX = x;
                final int newY = y;
                fields[x][y].addActionListener(e -> leftClick(newX, newY));
            }
        }
        //Set up mines and number fields
        for(int i = 0; i < this.mines; i++) {
            Field f = genMine();
            if (f != null) {
                List<Field> neigh = this.getNeighbours(f);
                minedFields.add(f);
                for(Field field : neigh) {
                    field.addMine();
                }
            } else {
                i--;
            }
        }
    }

    Field genMine() {
        Random rand = new Random();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        Field tile = fields[x][y];
        if(!tile.getIsMine()) {
            tile.setMined(true);
            tile.setText("");

            return tile;
        } else {
            return null;
        }
    }
}