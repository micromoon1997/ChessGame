package UI;
import models.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public char side;
    public Pawn p0, p1, p2, p3, p4, p5, p6, p7;
    public Rook r0, r1;
    public Knight n0, n1;
    public Bishop b0, b1;
    public King k0;
    public Queen q0;
    public List<Chessman> allChessmen;

    // A user object
    public Player(char side) throws IOException{
        this.side = side;
        p0 = new Pawn(side);
        allChessmen = new ArrayList<>();
        allChessmen.add(p0);
        if (side == 'w')
            initPositionWhite();
        else
            initPositionBlack();
    }


    // call this method to initiate all chessmen's position if the
    // player is on white side
    // TODO
    private void initPositionWhite() {
        p0.setPosition(0, 6);
    }

    // call this method to initiate all chessmen's position if the
    // player is on black side
    // TODO
    private void initPositionBlack() {
        p0.setPosition(0, 1);
    }
}
