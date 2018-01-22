package models;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Player{
    private Pawn p0, p1, p2, p3, p4, p5, p6, p7;
    private Rook r0, r1;
    private Knight n0, n1;
    private Bishop b0, b1;
    private King k0;
    private Queen q0;
    public char side;
    public List<Chessman> allChessmen;

    // A player object
    public Player(char side)  {
        this.side = side;
        allChessmen = new ArrayList<>();
        initChessmen();
    }

    private void initChessmen() {
        try {
            p0 = new Pawn(this);
            p1 = new Pawn(this);
            p2 = new Pawn(this);
            p3 = new Pawn(this);
            p4 = new Pawn(this);
            p5 = new Pawn(this);
            p6 = new Pawn(this);
            p7 = new Pawn(this);
            r0 = new Rook(this);
            n0 = new Knight(this);
            b0 = new Bishop(this);
            q0 = new Queen(this);
            k0 = new King(this);
            b1 = new Bishop(this);
            n1 = new Knight(this);
            r1 = new Rook(this);
            initChessmenPosition();
        } catch (IOException e) {
            //todo
        }
    }


    private void initChessmenPosition() {
        int forward;
        if (this.side == 'w')
            forward = -1;
        else
            forward = 1;
        int firstRow = (int) Math.round(3.5 - forward * 2.5);
        int secondRow = (int) Math.round(3.5 - forward * 3.5);
        p0.setPosition(0, firstRow);
        p1.setPosition(1, firstRow);
        p2.setPosition(2, firstRow);
        p3.setPosition(3, firstRow);
        p4.setPosition(4, firstRow);
        p5.setPosition(5, firstRow);
        p6.setPosition(6, firstRow);
        p7.setPosition(7, firstRow);
        r0.setPosition(0, secondRow);
        n0.setPosition(1, secondRow);
        b0.setPosition(2, secondRow);
        q0.setPosition(3, secondRow);
        k0.setPosition(4, secondRow);
        b1.setPosition(5, secondRow);
        n1.setPosition(6, secondRow);
        r1.setPosition(7, secondRow);
    }

    public void addChessman(Chessman chessman) {
        this.allChessmen.add(chessman);
    }

    public void disableAllMouseListeners() {
        for (Chessman c: allChessmen) {
            c.clickable = false;
        }
    }

    public void enableAllMouseListeners() {
        for (Chessman c: allChessmen) {
            c.clickable = true;
        }
    }

}
