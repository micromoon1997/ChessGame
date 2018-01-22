package models;
import UI.Chessboard;

import java.io.IOException;

/**
 * Created by Adam on 2017-07-09.
 */
public class Pawn extends Chessman{
    private static final Step move0 = new Step(0, 1);
    private static final Step move1 = new Step(0, 2);
    private static final Step cap0 = new Step(1, 1);
    private static final Step cap1 = new Step(-1, 1);
    public boolean canEnpassant;

    public Pawn(Player player) throws IOException{
        super (player);
        possibleSteps.add(move0);
        possibleSteps.add(move1);
        possibleSteps.add(cap0);
        possibleSteps.add(cap1);
        stepCounter = -1;
    }


    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        stepCounter++;
        if (stepCounter == 1) {
            possibleSteps.remove(move1);
            canEnpassant = true;
        }
    }
}
