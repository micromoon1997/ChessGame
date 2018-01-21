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

    public Pawn(Player player) throws IOException{
        super (player);
        possibleMoves.add(move0);
        possibleMoves.add(move1);
        possibleCaps.add(cap0);
        possibleCaps.add(cap1);
    }

}
