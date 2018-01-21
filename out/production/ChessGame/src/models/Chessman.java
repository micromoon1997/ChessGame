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
    protected List<Step> possibleMoves;
    protected List<Step> possibleCaps;
    public JLabel label;
    public int x, y;
    public boolean clickable, canBeCaptured;
    public int forward;
    public Player player;

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
        possibleMoves = new ArrayList<>();
        possibleCaps = new ArrayList<>();
        addMouseListener();
        player.addChessman(this);
    }

    public List<Step> getPossibleMoves() {
        List<Step> possibleMoves = new ArrayList<>();
        for (Step move: this.possibleMoves) {
            int xx = this.x + move.x;
            int yy = this.y + this.forward * move.y;
            if (0 <= xx && xx <= 7 && 0 <= yy && yy <= 7)
                possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public List<Step> getPossibleCaps() {
        List<Step> possibleCaps = new ArrayList<>();
        for (Step cap: this.possibleCaps) {
            int xx = this.x + cap.x;
            int yy = this.y + this.forward * cap.y;
            if (0 <= xx && xx <= 7 && 0 <= yy && yy <= 7)
                possibleCaps.add(cap);
        }
        return possibleCaps;
    }

    public void addMouseListener() {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!clickable)
                    return;
                if (canBeCaptured) {
                    for (Chessman c: player.allChessmen)
                        c.canBeCaptured = false;
                    Chessboard.getInstance().removeChessman(Chessman.this);
                    Chessboard.getInstance().currSlected.setPosition(x, y);
                    Chessboard.getInstance().switchTurn();
                    return;
                }
                System.out.println(player.side);
                //TODO
                Chessboard.getInstance().currSlected = Chessman.this;
                Chessboard.getInstance().clearBlocks();
                Chessboard.getInstance().showValidSteps(Chessman.this, getPossibleMoves(), getPossibleCaps());
            }
        });
    }


    // set the x, y position of the chessman
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
