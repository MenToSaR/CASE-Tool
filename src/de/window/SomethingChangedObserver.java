package de.window;

import java.util.ArrayList;

/**
 * Created by Marcel on 20.05.2015 in CASE.
 */
public class SomethingChangedObserver {

    private ArrayList<SomethingChangedListener> listListener = new ArrayList<>();

    public void somethingChanged() {
        for (SomethingChangedListener eachListener : listListener) {
            eachListener.somethingChanged();
        }
    }

    public void addSomethingChangedListener(SomethingChangedListener pListener) {
        listListener.add(pListener);
    }

    public void removeSomethingChanedListener(SomethingChangedListener pListener) {
        listListener.remove(pListener);
    }
}
