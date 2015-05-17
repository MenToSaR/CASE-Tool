package de.project;

import de.database.DataKnot;

import javax.swing.*;

/**
 * Created by Marcel on 17.05.2015 in CASE.
 */
public interface InputUnitElement {

    String getIdentifier();

    void loadData(DataKnot pKnot);

    JPanel getPanel();
}
