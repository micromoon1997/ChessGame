package UI;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Adam on 2017-07-09.
 */
public class Chessboard {

    private static Chessboard instance;
    public static int cubewidth = 120;
    private static int boardwidth = 8 * cubewidth;
    private static Color BROWN = new Color(167, 86, 20);
    private static Color BEIGE = new Color(235, 213, 176);
    private JFrame frame;
    private MyDrawPanel board;

    // Constructor
    public Chessboard() {

        // set up a frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up a panel
        board = new MyDrawPanel();
        board.setLayout(null);
        frame.getContentPane().add(board);
        frame.setSize(boardwidth+20, boardwidth+50);
        frame.setVisible(true);
    }

    public static Chessboard getInstance() {
        if (instance == null)
            instance = new Chessboard();
        return instance;
    }

    private static class MyDrawPanel extends JPanel {
        public void paintComponent(Graphics G) {
            // draw the chessboard
            G.setColor(BROWN);
            G.fillRect(0, 0, boardwidth, boardwidth);
            G.setColor(BEIGE);
            for (int l = 0; l < 8; l++)
                for (int r = 0; r < 8; r++)
                    if ((l - r) % 2 == 0)
                        G.fillRect(l * cubewidth, r * cubewidth, cubewidth, cubewidth);
        }
    }

}
