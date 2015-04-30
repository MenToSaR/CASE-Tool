package de.calculator;

import de.database.DataKnot;

/**
 * Created by Marcel on 30.04.2015 in CASE.
 */
public class Calcbase {

    private CalcFrame theFrame = new CalcFrame();

    public void calculate(DataKnot pKnot) {
        theFrame.open(pKnot.getChildrenByTag("Calcer"));
    }
}
