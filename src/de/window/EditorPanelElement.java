package de.window;

import de.database.DataKnot;

import javax.swing.*;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public abstract class EditorPanelElement {

    public abstract JPanel getElement();

    public abstract DataKnot getData();

    public abstract void setData(DataKnot pKnot);
}
