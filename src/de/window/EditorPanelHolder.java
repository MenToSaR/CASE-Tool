package de.window;

import de.database.DataKnot;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class EditorPanelHolder {

    private ArrayList<EditorPanelElement> listElements = new ArrayList<>();
    private int nID = 0;
    private Class classElement;
    private String theIDPrefix;
    private EditorPanel theEditor;

    public EditorPanelHolder(Class pElement, String pIDPrefix) {
        classElement = pElement;
        theIDPrefix = pIDPrefix;
    }

    public void setID(int pID) {
        nID = pID;
    }

    public int getID() {
        return nID;
    }

    public void setEditor(EditorPanel pEditor) {
        theEditor = pEditor;
    }

    public void deleteElement(EditorPanelElement pElement) {
        listElements.remove(pElement);
        print();
    }

    public void reset() {
        listElements.clear();
        print();
    }

    public void addElement() {
        try {
            EditorPanelElement tempElement = (EditorPanelElement) classElement.getConstructors()[0].newInstance(this, theIDPrefix + getNextID());
            tempElement.addSomethingChangedListener(new SomethingChangedListener() {
                @Override
                public void somethingChanged() {
                    update();
                }
            });
            listElements.add(tempElement);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void addElement(DataKnot pKnot) {
        try {
            EditorPanelElement tempElement = (EditorPanelElement) classElement.getConstructors()[0].newInstance(this, theIDPrefix + pKnot.getDataByKey("ID"));
            tempElement.setData(pKnot);
            tempElement.addSomethingChangedListener(new SomethingChangedListener() {
                @Override
                public void somethingChanged() {
                    update();
                }
            });
            listElements.add(tempElement);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int getNextID() {
        nID = nID + 10;
        return nID;
    }

    public void print() {
        theEditor.getContentPanel().removeAll();
        theEditor.getContentPanel().setLayout(new BoxLayout(theEditor.getContentPanel(), BoxLayout.Y_AXIS));
        theEditor.getContentPanel().setPreferredSize(new Dimension((int) theEditor.getContentPanel().getPreferredSize().getWidth(), 0));
        for (EditorPanelElement eachElement : listElements) {
            eachElement.getElement().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            theEditor.getContentPanel().add(eachElement.getElement());
            theEditor.getContentPanel().setPreferredSize(new Dimension((int) eachElement.getElement().getPreferredSize().getWidth(), (int) theEditor.getContentPanel().getPreferredSize().getHeight() + (int) eachElement.getElement().getPreferredSize().getHeight()));
        }
        theEditor.update();
    }

    public void update() {
        theEditor.update();
    }

    public ArrayList<EditorPanelElement> getElements() {
        return listElements;
    }

    public Class getElementType() {
        return classElement;
    }
}
