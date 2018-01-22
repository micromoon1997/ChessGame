package models;
import java.io.IOException;

/**
 * Created by Adam on 2017-07-09.
 */
public class Bishop extends Chessman{
    private static final Step s0 = new Step(1,  1);
    private static final Step s1 = new Step(2, 2);
    private static final Step s2 = new Step(3, 3);
    private static final Step s3 = new Step(4, 4);
    private static final Step s4 = new Step(5, 5);
    private static final Step s5 = new Step(6, 6);
    private static final Step s6 = new Step(7, 7);
    private static final Step s7 = new Step(-1,  1);
    private static final Step s8 = new Step(-2, 2);
    private static final Step s9 = new Step(-3, 3);
    private static final Step s10 = new Step(-4, 4);
    private static final Step s11 = new Step(-5, 5);
    private static final Step s12 = new Step(-6, 6);
    private static final Step s13 = new Step(-7, 7);
    private static final Step s14 = new Step(1,  -1);
    private static final Step s15 = new Step(2, -2);
    private static final Step s16 = new Step(3, -3);
    private static final Step s17 = new Step(4, -4);
    private static final Step s18 = new Step(5, -5);
    private static final Step s19 = new Step(6, -6);
    private static final Step s20 = new Step(7, -7);
    private static final Step s21 = new Step(-1,  -1);
    private static final Step s22 = new Step(-2, -2);
    private static final Step s23 = new Step(-3, -3);
    private static final Step s24 = new Step(-4, -4);
    private static final Step s25 = new Step(-5, -5);
    private static final Step s26 = new Step(-6, -6);
    private static final Step s27 = new Step(-7, -7);


    public Bishop(Player player) throws IOException{
        super(player);
        possibleSteps.add(s0);
        possibleSteps.add(s1);
        possibleSteps.add(s2);
        possibleSteps.add(s3);
        possibleSteps.add(s4);
        possibleSteps.add(s5);
        possibleSteps.add(s6);
        possibleSteps.add(s7);
        possibleSteps.add(s8);
        possibleSteps.add(s9);
        possibleSteps.add(s10);
        possibleSteps.add(s11);
        possibleSteps.add(s12);
        possibleSteps.add(s13);
        possibleSteps.add(s14);
        possibleSteps.add(s15);
        possibleSteps.add(s16);
        possibleSteps.add(s17);
        possibleSteps.add(s18);
        possibleSteps.add(s19);
        possibleSteps.add(s20);
        possibleSteps.add(s21);
        possibleSteps.add(s22);
        possibleSteps.add(s23);
        possibleSteps.add(s24);
        possibleSteps.add(s25);
        possibleSteps.add(s26);
        possibleSteps.add(s27);
    }
}
