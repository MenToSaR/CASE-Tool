package de.window;

import de.database.DataKnot;

import javax.swing.*;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public abstract class EditorPanelElement {

    private EditorPanelHolder theHolder;

    public EditorPanelElement(EditorPanelHolder pHolder) {
        theHolder = pHolder;
    }

    protected void delete() {
        theHolder.deleteElement(this);
    }

    protected void update() {
        theHolder.update();
    }

    public abstract JPanel getElement();

    public abstract DataKnot getData();

    public abstract void setData(DataKnot pKnot);
}
