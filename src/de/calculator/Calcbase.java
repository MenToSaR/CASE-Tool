package de.calculator;

import de.database.DataKnot;
import de.window.MessageBoxFactory;

import java.util.ArrayList;

/**
 * Created by Marcel on 30.04.2015 in CASE.
 */
public class Calcbase {

    public void calculate(DataKnot pKnot) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (DataKnot eachKnot : pKnot.getChildrenByTag("Calcer")) {
            tempList.add(eachKnot.getValue());
        }
        String tempResult = MessageBoxFactory.createListMessageBox("Choose...", "Verfügbare Porter:", tempList);
    }
}
