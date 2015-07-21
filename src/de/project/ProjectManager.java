package de.project;

import de.Core;
import de.database.DataKnot;
import de.database.Database;
import de.window.*;
import jdk.internal.util.xml.impl.Input;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marcel on 08.05.2015 in CASE.
 */
public class ProjectManager {

    private static String theFunctionDataFileName = "FUNCTION_DATA.dat";

    private DataKnot theProjectData = new DataKnot("root");

    private MainFrame theFrame;
    private Core theCore;

    private InputUnitManager theInputUnitManager = new InputUnitManager();

    private SomethingChangedObserver theObserver = new SomethingChangedObserver();

    /**
     * Klasse die f�r die Projektverwaltung verantwortlich ist
     *
     * Model in Architektur
     *
     * @param pCore
     * @param pFrame
     */

    public ProjectManager(Core pCore, MainFrame pFrame) {
        theCore = pCore;
        theFrame = pFrame;

        initInputUnitManager();
    }

    public ArrayList<String> getTreeList(){
        return theInputUnitManager.getNames();
    }

    public void addSomethingChangedListener(SomethingChangedListener pListener) {
        theObserver.addSomethingChangedListener(pListener);
    }
    public void removeSomethingChangedListener(SomethingChangedListener pListener) {
        theObserver.removeSomethingChanedListener(pListener);
    }

    public void somethingChanged(){
        theFrame.enableSaveButton(true);
    }

    /**
     * Erzeugt alle Komponenten der Oberflaeche
     *
     * Koennte in Datei ausgelagert werden um Flexibilitaet zu erhoehen
     * und bei laufzeit geladen werden
     */

    private void initInputUnitManager(){
        theInputUnitManager.addHolder(new InputUnitHolder("Zielbestimmungen", new TextPanel(this, "projecttarget")));
        theInputUnitManager.addHolder(new InputUnitHolder("Produkteinsatz", new TextPanel(this, "productusage")));
        theInputUnitManager.addHolder(new InputUnitHolder("Umgebung", new TextPanel(this, "environment")));
        theInputUnitManager.addHolder(new InputUnitHolder("Produktfunktionen", new EditorPanel(this, new EditorPanelHolder(IDTitleTitleTextPanel.class, "LF ", "Gesch�ftsprozess", "Akteur", "Beschreibung"), "productfunction")));
        theInputUnitManager.addHolder(new InputUnitHolder("Produktdaten", new EditorPanel(this, new EditorPanelHolder(IDTitleTextPanel.class, "LD ", "Titel", "Beschreibung"), "productdata")));
        theInputUnitManager.addHolder(new InputUnitHolder("Nichtfunktionale Anforderungen", new EditorPanel(this, new EditorPanelHolder(IDTitleTextPanel.class, "LL ", "Titel", "Beschreibung"), "nonfunctional")));
        theInputUnitManager.addHolder(new InputUnitHolder("Qualitaetsanforderungen", new EditorPanel(this, new EditorPanelHolder(TitleSliderTextPanel.class, "", "Anforderung", "Prioritaet", "Beschreibung"), "quality")));
        theInputUnitManager.addHolder(new InputUnitHolder("Ergaenzungen", new TextPanel(this, "miscellaneous")));
        theInputUnitManager.addHolder(new InputUnitHolder("Glossar", new EditorPanel(this, new EditorPanelHolder(TitleTextPanel.class, "", "Begriff", "Erkl�rung"), "glossary")));

        theFrame.enableSaveButton(false);
    }

    /**
     * Laedt Projektdaten aus der Database
     */

    public void loadProjectData(Database pDatabase) {
        theProjectData.clear();
        theProjectData.addChild(pDatabase.readData(Database.PROJECT_CONFIG_FILE));

        theInputUnitManager.clear();
        initInputUnitManager();

        DataKnot tempKnot = pDatabase.readData(theFunctionDataFileName);
        if (tempKnot == null) {                       // Erzeugt neuen Knoten falls keine Daten gefunden
            tempKnot = new DataKnot("data");
        }
        theProjectData.addChild(tempKnot);

        for (InputUnitHolder eachHolder : theInputUnitManager.getElements()) {
            eachHolder.getElement().loadData(theProjectData.getFirstChildByTag("data").getFirstChildByTag(eachHolder.getElement().getIdentifier()));
        }

        theFrame.enableSaveButton(false);
        theProjectData.printKnot();
    }

    /**
     * Aktualisiert die Oberflaeche
     */

    public void updateInterface() {
        theFrame.revalidate();
        theFrame.repaint();
    }

    /**
     * Speichert saemtliche Komponenten
     */

    public void saveCompleteProject() {
        for (InputUnitHolder eachHolder : theInputUnitManager.getElements()) {
            eachHolder.save();
        }
    }

    /**
     * Speicher einzelne Komponente
     * @param pHolder Komponente
     * @param pID Hoechste ID der Eintraege (z.B: LF110)
     */

    public void saveData(EditorPanelHolder pHolder, String pID) {
        DataKnot tempKnot = theProjectData.getFirstChildByTag("data").getFirstChildByTag(pID);
        theProjectData.getFirstChildByTag("data").removeChild(tempKnot);

        tempKnot = new DataKnot(pID);
        tempKnot.addData("ID", "" + pHolder.getID());
        for (EditorPanelElement eachElement : pHolder.getElements()) {
            DataKnot tempChild = tempKnot.addChild("element");

            HashMap<String, String> tempMap = eachElement.getData().getData();
            for (String eachKey : tempMap.keySet()) {
                tempChild.addData(eachKey, tempMap.get(eachKey));
            }
        }

        theProjectData.getFirstChildByTag("data").addChild(tempKnot);

        theCore.saveData(theProjectData.getFirstChildByTag("data"), theFunctionDataFileName);
    }

    /**
     * Speichert Text einer Komponente
     * @param pString Text der Komponente
     * @param pID ID der Komponente
     */

    public void saveData(String pString, String pID) {
        DataKnot tempKnot = theProjectData.getFirstChildByTag("data").getFirstChildByTag(pID);
        theProjectData.getFirstChildByTag("data").removeChild(tempKnot);

        DataKnot tempChild = new DataKnot(pID);
        tempChild.addData("TEXT", pString);

        theProjectData.getFirstChildByTag("data").addChild(tempChild);

        theCore.saveData(theProjectData.getFirstChildByTag("data"), theFunctionDataFileName);
    }

    /**
     * Laedt einzelne Daten aus den Datenbankknoten
     * @deprecated Wird nichtmehr verwendet
     * @param pID
     * @return
     */

    public DataKnot loadData(String pID) {
        return theProjectData.getFirstChildByTag(pID);
    }

    /**
     * uebergibt Datenbankknoten
     * @return
     */

    public DataKnot getDataKnot(){
        return theProjectData;
    }

    /**
     * Zeigt gewuenschte Seite auf der Oberflaeche an
     * @param pName
     * @param pFrame
     */

    public void showPage(String pName, MainFrame pFrame) {
        JPanel tempPanel = theInputUnitManager.getPanelByName(pName);
        pFrame.showPanel(tempPanel);
    }
}


