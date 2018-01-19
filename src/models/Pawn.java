package models;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Adam on 2017-07-09.
 */
public class Pawn extends Chessman{
    private static int dir;

    public Pawn(File file) throws IOException{
        super (file);
        if (camp == 'W')
            dir = -1;
        else
            dir = 1;
        feasibleMoves = Arrays.asList(new Step(0, dir));
        feasibleCaptures = Arrays.asList(new Step(1, dir),
                                            new Step(-1, dir));
    }


}
