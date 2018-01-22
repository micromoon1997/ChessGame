package models;
import UI.Chessboard;
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
    protected boolean isAlive;
    protected List<Step> possibleSteps;
    public JLabel label;
    public int x, y;
    public boolean clickable;
    public int forward;
    public Player player;
    public int stepCounter;

    public Chessman(Player player) throws IOException{
        this.player = player;
        clickable = false;
        String fileName = this.getClass().getSimpleName() + "_" + player.side + ".png";
        File imgFile = new File("./pics/" + fileName);
        label = new JLabel(new ImageIcon(ImageIO.read(imgFile)));
        if (player.side == 'w')
            forward = -1;
        else
            forward = 1;
        isAlive = true;
        this.x = -1;
        this.y = -1;
        possibleSteps = new ArrayList<>();
        addMouseListener();
        player.addChessman(this);
       // label.setLayout(new OverlayLayout(label));
    }

    public List<Step> getPossibleSteps() {
        List<Step> possibleMoves = new ArrayList<>();
        for (Step move: this.possibleSteps) {
            int xx = this.x + move.x;
            int yy = this.y + this.forward * move.y;
            if (0 <= xx && xx <= 7 && 0 <= yy && yy <= 7)
                possibleMoves.add(move);
        }
        return possibleMoves;
    }


    private void addMouseListener() {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!clickable)
                    return;
                System.out.println(player.side);
                Chessboard.getInstance().currSelected = Chessman.this;
                Chessboard.getInstance().clearBlocks();
                Chessboard.getInstance().showValidSteps();
            }
        });
    }

    // set the x, y position of the chessman
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        for (Chessman c: Chessboard.getInstance().allChessmenOnBoard) {
            if (c instanceof Pawn)
                ((Pawn) c).canEnpassant = false;
        }
    }

}
