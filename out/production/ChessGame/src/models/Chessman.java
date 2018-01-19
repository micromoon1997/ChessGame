package models;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2017-07-09.
 */

public class Chessman {
    public JLabel label;
    public int x, y;
    protected char side;
    protected int forward;
    protected boolean isAlive;
    protected List<Step> possibleMoves;
    protected List<Step> possibleCaps;

    public Chessman(char side) throws IOException{
        //this.label = new JLabel(new ImageIcon(ImageIO.read()));
        this.side = side;
        String fileName = this.getClass().getSimpleName() + "_" + this.side + ".png";
        File imgFile = new File("./pics/" + fileName);
        label = new JLabel(new ImageIcon(ImageIO.read(imgFile)));
        if (this.side == 'w')
            forward = -1;
        else
            forward = 1;
        isAlive = true;
        this.x = -1;
        this.y = -1;
        possibleMoves = new ArrayList<>();
        possibleCaps = new ArrayList<>();
        enableMouseEvent();
    }

    //TODO
    public void enableMouseEvent() {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("!!!");
            }
        });
    }

    // set the x, y position of the chessman
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
