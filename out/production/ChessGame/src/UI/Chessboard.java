package UI;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Chessman;
import models.Pawn;
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
    private static Chessboard instance;
    // variables
    private JFrame frame;
    private MyDrawPanel board;
    private Player player1, player2, currTurn, nextTurn;
    private Set<JLabel> blocks;
    public Set<Chessman> allChessmenOnBoard;
    public Chessman currSelected;


    // Constructor
    private Chessboard() {
        setup();
        allChessmenOnBoard = new HashSet<>();
    }

    // show the valid moves and captures of a chessman on the board
    public void showValidSteps() {
        List<Step> possibleSteps = currSelected.getPossibleSteps();
        // Special case for pawn
        if (currSelected instanceof Pawn) {
            showValidStepsPawnSpec(possibleSteps);
            return;
        }
        // other cases
        for (Step s : possibleSteps) {
            int xx = currSelected.x + s.x;
            int yy = currSelected.y + currSelected.forward * s.y;
            boolean canMove = true;
            for (Chessman c: allChessmenOnBoard) {
                if (isPtOnLine(c.x, c.y, currSelected.x, currSelected.y, xx, yy)) {
                    canMove = false;
                    break;
                }
            }
            if (canMove) {
                for (Chessman target: allChessmenOnBoard) {
                    if (target.x == xx && target.y == yy) {
                        if (target.player.side == currTurn.side)
                            canMove = false;
                        else
                            capture(target);
                    }
                }
            }
            if (canMove)
                move(xx, yy);
        }
    }

    private void showValidStepsPawnSpec(List<Step> possibleSteps) {
        for (Step s: possibleSteps) {
            int xx = currSelected.x + s.x;
            int yy = currSelected.y + currSelected.forward * s.y;
            if (currSelected.x == xx) {
                boolean canMove = true;
                for (Chessman target: allChessmenOnBoard) {
                    if (target.x == currSelected.x && target.y == currSelected.y)
                        continue;
                    if (isPtOnLine(target.x, target.y, currSelected.x, currSelected.y, xx, yy)
                            || (target.x == xx && target.y == yy))
                        canMove = false;
                }
                if (canMove)
                    move(xx, yy);
            } else {
                for (Chessman target: allChessmenOnBoard) {
                    if (target.x == xx && target.y == yy && target.player.side != currTurn.side)
                        capture(target);
                    if (target.x == xx && target.y == currSelected.y
                            && (currSelected.y == (int)(3.5+currSelected.forward*0.5))
                            && (target instanceof Pawn)
                            && ((Pawn) target).canEnpassant) {
                        Enpassant((Pawn) target);
                    }
                }
            }
        }
    }

    private void move(final int xx, final int yy) {
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
                currSelected.setPosition(xx, yy);
                switchTurn();
            }
        });
    }

    private void capture(final Chessman target) {
        final int xx = target.x;
        final int yy = target.y;
        JLabel block = new JLabel();
        block.setBounds(0, 0, CUBE_WIDTH, CUBE_WIDTH);
        block.setBorder(RED_BORDER);
        blocks.add(block);
        target.label.add(block);
        board.repaint();
        block.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                removeChessman(target);
                currSelected.setPosition(xx, yy);
                switchTurn();
            }
        });

    }

    private void Enpassant(final Pawn target) {
        final int xx = target.x;
        final int yy = target.y + currSelected.forward;
        JLabel block = new JLabel();
        block.setBounds(xx*CUBE_WIDTH, yy*CUBE_WIDTH, CUBE_WIDTH, CUBE_WIDTH);
        block.setBorder(RED_BORDER);
        blocks.add(block);
        board.add(block);
        board.repaint();
        block.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                removeChessman(target);
                currSelected.setPosition(xx, yy);
                switchTurn();
            }
        });
    }

    public void pawnPromotion(Pawn pawn) {
        //TODO
    }

    public void clearBlocks() {
        for (JLabel j: blocks)
            board.remove(j);
        for (Chessman c: nextTurn.allChessmen)
            c.label.removeAll();
        blocks.clear();
        board.repaint();
    }

    /**
     * Check whether a point is on the given line exclude two ends **/
    private boolean isPtOnLine(int px, int py, int lx1, int ly1, int lx2, int ly2) {
        int dxc = px - lx1;
        int dyc = py - ly1;
        int dxl = lx2 - lx1;
        int dyl = ly2 - ly1;
        int cross = dxc * dyl - dyc * dxl;
        if (cross != 0 )
            return false;
        if (Math.abs(dxl) >= Math.abs(dyl))
            return dxl > 0 ?
                    lx1 < px && px < lx2 :
                    lx2 < px && px < lx1;
        else
            return dyl > 0 ?
                    ly1 < py && py < ly2 :
                    ly2 < py && py < ly1;
    }

    public void removeChessman(Chessman c) {
        allChessmenOnBoard.remove(c);
        board.remove(c.label);
        c.player.allChessmen.remove(c);
        board.repaint(); //may be no need
    }

    public void startGame() {
        //add users
        player1 = new Player('w');
        player2 = new Player('b');
        //add all chessmen to panel
        allChessmenOnBoard.addAll(player1.allChessmen);
        allChessmenOnBoard.addAll(player2.allChessmen);
        System.out.println(allChessmenOnBoard.size());
        for (Chessman c: allChessmenOnBoard) {
            board.add(c.label);
        }
        render();
        currTurn = player1;
        nextTurn = player2;
        player1.enableAllMouseListeners();
    }

    private void switchTurn() {
        clearBlocks();
        currTurn.disableAllMouseListeners();
        Player temp;
        temp = currTurn;    //swap
        currTurn = nextTurn;
        nextTurn = temp;
        currTurn.enableAllMouseListeners();
        render();
    }

    // render all chessmen on the ChessBoard
    private void render() {
        for (Chessman c: allChessmenOnBoard) {
            //c.label.setBounds(c.x*CUBE_WIDTH, c.y*CUBE_WIDTH, CUBE_WIDTH, CUBE_WIDTH);
            c.label.setLocation(c.x*CUBE_WIDTH, c.y*CUBE_WIDTH);
            c.label.setSize(CUBE_WIDTH, CUBE_WIDTH);
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
    }

    /**
     * make sure there is only one ChessBoard instance  **/
    public static Chessboard getInstance() {
        return instance;
    }

    public static void createInstance() {
        instance = new Chessboard();
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
