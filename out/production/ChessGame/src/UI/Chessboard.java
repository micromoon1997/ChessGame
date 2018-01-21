package UI;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Chessman;
import models.Player;
import models.Step;

/**
 * Created by Adam on 2017-07-09.
 */
public class Chessboard {

    // constants
    private static final int CUBE_WIDTH = 120;
    private static final int BOARD_WIDTH = 8 * CUBE_WIDTH;
    private static final Color BROWN = new Color(167, 86, 20);
    private static final Color BEIGE = new Color(235, 213, 176);
    private static final Border CYAN_BORDER = BorderFactory.createLineBorder(Color.cyan, 4);
    private static final Border RED_BORDER = BorderFactory.createLineBorder(Color.red, 4);
    // variables
    private static Chessboard instance;
    private JFrame frame;
    private MyDrawPanel board;
    private Player player1, player2, currTurn, nextTurn;
    private Set<JLabel> blocks;
    public Set<Chessman> allChessmenOnBoard;


    // Constructor
    private Chessboard() {
        setup();
        //add users
        player1 = new Player('w');
        player2 = new Player('b');
        //add all chessmen to panel
        allChessmenOnBoard = new HashSet<>();
        allChessmenOnBoard.addAll(player1.allChessmen);
        allChessmenOnBoard.addAll(player2.allChessmen);
        System.out.println(allChessmenOnBoard.size());
        for (Chessman c: allChessmenOnBoard) {
            board.add(c.label);
        }
        render();
    }

    // show the valid moves and captures of a chessman on the board
    public void showValidSteps(final Chessman c, List<Step> possibleMoves, List<Step> possibleCaps) {
        for (Step s: possibleMoves) {
            final int xx = c.x + s.x;
            final int yy = c.y + c.forward*s.y;
            if (!isBlockTaken(c.x, c.y, xx, yy)) {
                JLabel block = new JLabel();
                block.setBounds(xx*CUBE_WIDTH, yy*CUBE_WIDTH, CUBE_WIDTH, CUBE_WIDTH);
                block.setBorder(CYAN_BORDER);
                blocks.add(block);
                board.add(block);
                board.repaint();
                block.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        c.setPosition(xx, yy);
                        render();
                        clearBlocks();
                        switchTurn();
                    }
                });
            }
        }
        for (Step s: possibleCaps) {
            final int xx = c.x + s.x;
            final int yy = c.y + c.forward*s.y;
            for (Chessman cc:nextTurn.allChessmen) {
                if (c.x == cc.x && c.y == cc.y) {
                    cc.label.setBorder(RED_BORDER);
                }
            }
        }
    }

    public boolean isBlockTaken(int x0, int y0, int x1, int y1) {
        for (Chessman c: allChessmenOnBoard) {
            if (c.x == x0 && c.y == y0)
                continue;
            if (c.x == x1 && c.y == y1)
                return true;
            if (x0 == x1)
                if ((y0 < c.y && c.y < y1) || (y1 < c.y && c.y < y0)) {
                    return true;
                } else
                    continue;
            if (c.x == x0)
                continue;
            if ((float)((y1-y0)/(x1-x0)) == ((float)((c.y-y0)/(c.x-x0))) && x0 < c.x && c.x < x1)
                return true;
        }
        return false;
    }

    public void clearBlocks() {
        for (JLabel j: blocks) {
            board.remove(j);
        }
        blocks.clear();
    }

    // make sure there is only one ChessBoard instance
    public static Chessboard getInstance() {
        if (instance == null)
            instance = new Chessboard();
        return instance;
    }

    public void startGame() {
        currTurn = player1;
        nextTurn = player2;
        player1.enableAllMouseListeners();
    }

    public void switchTurn() {
        currTurn.disableAllMouseListeners();
        Player temp;
        temp = currTurn;    //swap
        currTurn = nextTurn;
        nextTurn = temp;
        currTurn.enableAllMouseListeners();
    }

    // render all chessmen on the ChessBoard
    public void render() {
        for (Chessman c: allChessmenOnBoard) {
            //c.label.setBounds(c.x*CUBE_WIDTH, c.y*CUBE_WIDTH, CUBE_WIDTH, CUBE_WIDTH);
            c.label.setLocation(c.x*CUBE_WIDTH, c.y*CUBE_WIDTH);
            c.label.setSize(CUBE_WIDTH, CUBE_WIDTH);
            board.repaint();
        }
    }

    private void setup() {
        // set up a frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set up a panel
        board = new MyDrawPanel();
        board.setLayout(null);
        frame.getContentPane().add(board);
        frame.setSize(BOARD_WIDTH +16, BOARD_WIDTH +39);
        frame.setVisible(true);
        // set up blocks
        blocks = new HashSet<>();
//        for (int i = 0; i < 20; i++ ) {
//            JLabel block = new JLabel();
//            block.setLocation(-1, -1);
//            block.setVisible(false);
//            block.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    super.mouseClicked(e);
//                }
//            });
//            blocks.add(block);
//            board.add(block);
//        }
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
