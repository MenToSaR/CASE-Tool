package de.calculator;

import de.Core;
import de.database.DataKnot;

/**
 * Created by Marcel on 30.04.2015 in CASE.
 */
public abstract class Calcer {

    public abstract void calculate(Core pCore, DataKnot pKnot);

    public abstract void optimize(Core pCore);
}
