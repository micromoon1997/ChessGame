package models;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2017-07-09.
 */
public class Pawn extends Chessman{
    private static final Step move0 = new Step(0, 1);
    private static final Step move1 = new Step(0, 2);
    private static final Step cap0 = new Step(1, 1);
    private static final Step cap1 = new Step(-1, 1);

    public Pawn(char side) throws IOException{
        super (side);
        possibleMoves.add(move0);
        possibleMoves.add(move1);
        possibleCaps.add(cap0);
        possibleCaps.add(cap1);
    }

    public List<Step> getValidMoves() {
        List<Step> validSteps = new ArrayList<>();
        for (Step move: possibleMoves) {
            int xx = this.x + this.forward * move.x;
            int yy = this.x + this.forward * move.y;
            if (0 <= xx && xx <= 7 && 0 <= yy && yy <= 7)
                validSteps.add(move);
        }
        return validSteps;
    }


}
