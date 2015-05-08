package de.window;

import de.database.DataKnot;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class EditorPanelHolder {

    private ArrayList<EditorPanelElement> listElements = new ArrayList<>();
    private Class classElement;

    public EditorPanelHolder(Class pElement) {
        classElement = pElement;
    }

    public void addElement(EditorPanelElement pElement) {
        listElements.add(pElement);
    }

    public void addElement() {
        try {
            listElements.add((EditorPanelElement) classElement.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void printToPanel(JPanel pPanel) {
        pPanel.removeAll();
        pPanel.setLayout(new BoxLayout(pPanel, BoxLayout.Y_AXIS));
        pPanel.setPreferredSize(new Dimension((int) pPanel.getPreferredSize().getWidth(), 0));
        for (EditorPanelElement eachElement : listElements) {
            pPanel.add(eachElement.getElement());
            pPanel.setPreferredSize(new Dimension((int) eachElement.getElement().getPreferredSize().getWidth(), (int) pPanel.getPreferredSize().getHeight() + (int) eachElement.getElement().getPreferredSize().getHeight()));
        }
    }

    public ArrayList<EditorPanelElement> getElements() {
        return listElements;
    }

    public Class getElementType() {
        return classElement;
    }
}
