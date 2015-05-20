package de.window;

import de.database.DataKnot;

import javax.swing.*;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public abstract class EditorPanelElement {

    private EditorPanelHolder theHolder;
    private String theID = "";
    private SomethingChangedObserver theObserver = new SomethingChangedObserver();

    public EditorPanelElement(EditorPanelHolder pHolder, String pID) {
        theHolder = pHolder;
        theID = pID;
    }

    public void addSomethingChangedListener(SomethingChangedListener pListener) {
        theObserver.addSomethingChangedListener(pListener);
    }

    public void removeSomethingChangedListener(SomethingChangedListener pListener) {
        theObserver.removeSomethingChanedListener(pListener);
    }

    protected void somethingChanged() {
        theObserver.somethingChanged();
    }

    protected void delete() {
        theHolder.deleteElement(this);
    }

    protected void update() {
        theHolder.update();
    }

    protected String getID() {
        return theID;
    }

    public abstract JPanel getElement();

    public abstract DataKnot getData();

    public abstract void setData(DataKnot pKnot);
}
