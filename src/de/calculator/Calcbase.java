package de.calculator;

import de.Core;
import de.JarLoader;
import de.database.DataKnot;
import de.window.MessageBoxFactory;

import java.util.ArrayList;

/**
 * Created by Marcel on 30.04.2015 in CASE.
 */
public class Calcbase {

    public void calculate(Core pCore, DataKnot pKnot) {
        ArrayList<String> tempList = new ArrayList<>();
        for (DataKnot eachKnot : pKnot.getChildrenByTag("Calcer")) {
            tempList.add(eachKnot.getValue());
        }
        String tempResult = MessageBoxFactory.createListMessageBox("Choose...", "Verfügbare Porter:", tempList);
        ((Calcer)JarLoader.getJarLoader().load(tempResult, "Calcer")).calculate(pCore, pKnot.getFirstChildByTag("root"));
    }

    public void optimize(Core pCore, DataKnot pKnot) {
        ArrayList<String> tempList = new ArrayList<>();
        for (DataKnot eachKnot : pKnot.getChildrenByTag("Calcer")) {
            tempList.add(eachKnot.getValue());
        }
        String tempResult = MessageBoxFactory.createListMessageBox("Choose...", "Verfügbare Porter:", tempList);
        ((Calcer)JarLoader.getJarLoader().load(tempResult, "Calcer")).optimize(pCore);
    }
}
