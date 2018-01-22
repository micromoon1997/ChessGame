package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Adam on 2017-07-09.
 */
public class King extends Chessman{
    private static final Step s0 = new Step(1, 0);
    private static final Step s1 = new Step(1, 1);
    private static final Step s2 = new Step(-1, 0);
    private static final Step s3 = new Step(-1, 1);
    private static final Step s4 = new Step(0, 1);
    private static final Step s5 = new Step(0, -1);
    private static final Step s6 = new Step(1, -1);
    private static final Step s7 = new Step(-1,  -1);

    public King(Player player) throws IOException{
        super(player);
        possibleSteps.add(s0);
        possibleSteps.add(s1);
        possibleSteps.add(s2);
        possibleSteps.add(s3);
        possibleSteps.add(s4);
        possibleSteps.add(s5);
        possibleSteps.add(s6);
        possibleSteps.add(s7);
    }
}
