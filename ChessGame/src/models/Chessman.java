package models;


import UI.Chessboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2017-07-09.
 */

public class Chessman {
    private JLabel img;
    private int X, Y;
    protected char camp;
    protected List<Step> feasibleMoves;
    protected List<Step> feasibleCaptures;

    public Chessman(File file) throws IOException{
        this.img = new JLabel(new ImageIcon(ImageIO.read(file)));
        this.X = 0;
        this.Y = 0;
        String filename = file.getName();
        camp = filename.charAt(filename.length() - 5);

    }
}
