package de.window;

import de.database.DataKnot;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
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

    public void addElement() {
        try {
            listElements.add((EditorPanelElement) classElement.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void addElement(DataKnot pKnot) {
        try {
            EditorPanelElement tempElement = (EditorPanelElement) classElement.newInstance();
            tempElement.setData(pKnot);
            listElements.add(tempElement);
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
            eachElement.getElement().setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
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
