package UI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import models.Chessman;

/**
 * Created by Adam on 2017-07-09.
 */
public class Chessboard {

    // constants
    private static final int CUBE_WIDTH = 120;
    private static final int BOARD_WIDTH = 8 * CUBE_WIDTH;
    private static final Color BROWN = new Color(167, 86, 20);
    private static final Color BEIGE = new Color(235, 213, 176);
    // variables
    private static Chessboard instance;
    private JFrame frame;
    private MyDrawPanel board;
    private Player player1, player2;


    // Constructor
    private Chessboard() {
        // set up a frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set up a panel
        board = new MyDrawPanel();
        board.setLayout(null);
        frame.getContentPane().add(board);
        frame.setSize(BOARD_WIDTH +16, BOARD_WIDTH +39);
        frame.setVisible(true);
        //add users
        try {
            player1 = new Player('w');
            player2 = new Player('b');
        } catch (IOException e) {
            System.out.println("Unhandled exception"); //TODO
        }
        //add all chessmen to panel
        for (Chessman c: player1.allChessmen) {
            board.add(c.label);
        }
        for (Chessman c: player2.allChessmen) {
            board.add(c.label);
        }
        render();
    }

    // make sure there is only one ChessBoard instance
    public static Chessboard getInstance() {
        if (instance == null)
            instance = new Chessboard();
        return instance;
    }

    // render all players' all chessmen on the ChessBoard
    public void render() {
        for (Chessman c: player1.allChessmen) {
            c.label.setBounds(c.x*CUBE_WIDTH, c.y*CUBE_WIDTH, CUBE_WIDTH, CUBE_WIDTH);
        }
        for (Chessman c: player2.allChessmen) {
            c.label.setBounds(c.x*CUBE_WIDTH, c.y*CUBE_WIDTH, CUBE_WIDTH, CUBE_WIDTH);
        }
        board.repaint();
    }

    private static class MyDrawPanel extends JPanel {
        public void paintComponent(Graphics G) {
            // draw the chessboard
            G.setColor(BROWN);
            G.fillRect(0, 0, BOARD_WIDTH, BOARD_WIDTH);
            G.setColor(BEIGE);
            for (int l = 0; l < 8; l++)
                for (int r = 0; r < 8; r++)
                    if ((l - r) % 2 == 0)
                        G.fillRect(l * CUBE_WIDTH, r * CUBE_WIDTH, CUBE_WIDTH, CUBE_WIDTH);
        }
    }

}
