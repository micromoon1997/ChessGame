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


    public King(File file) throws IOException{
        super(file);
        feasibleMoves = Arrays.asList(new Step(-1, 0),
                                        new Step(-1, -1),
                                        new Step(-1, 1),
                                        new Step(0, -1),
                                        new Step(1, 0),
                                        new Step(1, 1),
                                        new Step(1, -1),
                                        new Step(0, 1));
    }
}
