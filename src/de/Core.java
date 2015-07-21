package de;

import de.calculator.Calcbase;
import de.database.Database;
import de.project.ProjectManager;
import de.window.*;
import de.database.DataKnot;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Marcel on 20.04.2015.
 */
public class Core {

    private Database theDatabase = new Database();
    private Calcbase theCalcbase = new Calcbase();
    private ProjectManager theProjectManager;

    MainFrame theFrame;

    /**
     *  Architekturmodell: Model-View-Controller
     *  Model -> ProjectManager
     *  View -> MainFrame
     *  Controller -> Core
     */

    /**
     *  Hauptklasse
     *
     *  Gleichzeitig Controller für das Projekt
     *
     *  Verantwortlich für Kommunikation zwischen Model und View
     */

    public Core() {
        theFrame = new MainFrame(this);

        theProjectManager = new ProjectManager(this, theFrame);

        theFrame.open();

        loadLastProject();
    }

    /**
     * Läd das letzte Projekt
     *
     * @throws FileNotFoundException -> Fehlerfenster falls kein Projekt gefunden Wurde
     */

    private void loadLastProject() {
        if (theDatabase.getConfigEntry(Database.PROJECT_LAST_PROJECT) != null) {
            try {
                theDatabase.setWorkingDir(theDatabase.getConfigEntry(Database.PROJECT_LAST_PROJECT));
                refreshProject(new File(theDatabase.getConfigEntry(Database.PROJECT_LAST_PROJECT)).getName());
            } catch (FileNotFoundException e) {
                MessageBoxFactory.createMessageBox("Error", "Projekt existiert nicht mehr");
                theDatabase.deleteProjectEntry(new File(theDatabase.getConfigEntry(Database.PROJECT_LAST_PROJECT)).getName());
            }
        }
    }

    /**
     * Speicher das Gesamte Projekt und alle Komponenten (Funktionale anforderungen, nichtfunktion.....)
     */

    public void saveCompleteProject() {
        theProjectManager.saveCompleteProject();
        theFrame.enableSaveButton(false);
    }

    /**
     *  Löscht das Projekt und setzt Oberfläche zurück
     */

    public void deleteProject() {
        if (theDatabase.deleteWorkingDir()) {
            theDatabase.editConfigEntry(Database.PROJECT_LAST_PROJECT, "");
            theFrame.reset();
        }
    }

    /**
     * Öffnet bereits angelegtes Projekt
     * Wird von Oberfläche angestoßen
     */

    public void reopenProject() {
        ArrayList<String> tempList = new ArrayList<>();
        for (DataKnot eachKnot : theDatabase.getConfig().getFirstChildByTag("projects").getChildren()) {
            tempList.add(eachKnot.getDataByKey("name"));
        }
        String theResult = MessageBoxFactory.createListMessageBox("Choose...", "Projekte:", tempList);
        if (!theResult.equals("")) {
            try {
                for (DataKnot eachKnot : theDatabase.getConfig().getFirstChildByTag(Database.PROJECT_LIST_TAG).getChildren()) {
                    if (eachKnot.getDataByKey("name").equals(theResult)) {
                        theDatabase.setWorkingDir(eachKnot.getValue() + "/" + theResult);
                    }
                }

                refreshProject(theResult);
            } catch (FileNotFoundException e) {
                MessageBoxFactory.createMessageBox("Error", "Projekt existiert nicht mehr");
                theDatabase.editConfigEntry(Database.PROJECT_LAST_PROJECT, "");
                theDatabase.deleteProjectEntry(theResult);
            }
        }
    }

    /**
     * Öffnet bereits angelegtes jedoch nicht bereits verwaltetes Projekt
     * wird von Oberfläche angestoßen
     */

    public void openProject() {
        JFileChooser theChooser = new JFileChooser(theDatabase.getWorkspace());
        theChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        theChooser.showOpenDialog(new JFrame());
        File tempFile = theChooser.getSelectedFile();
        if (tempFile != null) {
            String tempDir = tempFile.getAbsolutePath();
            if (tempDir != null) {
                try {
                    theDatabase.setWorkingDir(tempDir);

                    refreshProject(new File(tempDir).getName());
                } catch (FileNotFoundException e) {
                    // TODO FEHLERMELDUNG
                }
            }
        }
    }

    /**
     * Legt neues Projekt an
     */

    public void createProject() {
        JFileChooser theChooser = new JFileChooser(theDatabase.getWorkspace());
        theChooser.setDialogTitle("Erstellen");
        theChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        theChooser.showOpenDialog(new JFrame());
        File tempFile = theChooser.getSelectedFile();
        if (tempFile != null) {
            String tempDir = tempFile.getAbsolutePath();
            if (!tempDir.equals("")) {
                String tempProjectName = MessageBoxFactory.createTextMessageBox("Achtung", "Namen des Projektes eingeben!");
                if (!tempProjectName.equals("")) {
                    theDatabase.createWorkingDir(tempDir, tempProjectName);

                    DataKnot tempKnot = new DataKnot("Projekt");
                    tempKnot.setValue(tempProjectName);
                    theDatabase.writeData(Database.PROJECT_CONFIG_FILE, tempKnot);

                    refreshProject(tempProjectName);
                }
            }
        }
    }

    /**
     * Aktualisiert die Oberfläche
     * @param pProjectName Name des Projektes
     */

    public void refreshProject(String pProjectName) {
        theProjectManager.loadProjectData(theDatabase);
        theFrame.reset();

        if (!pProjectName.equals("")) {
            theFrame.showTree(pProjectName, theProjectManager.getTreeList());
            theFrame.projectLoaded();

            theDatabase.editConfigEntry(Database.PROJECT_LAST_PROJECT, theDatabase.getWorkingDir());
        }
    }

    /**
     * Speichert locale Daten (in Projekt Verzeichnis)
     *
     * @param pKnot Zu Speichernder Knoten
     * @param pFileName Speichername
     */

    public void saveData(DataKnot pKnot, String pFileName) {                    // In Projekt Ordner (local)
        pKnot.printKnot();
        theDatabase.writeData(pFileName, pKnot);
    }

    /**
     * Läd Projektspezifische Daten
     */

    public DataKnot loadData(String pFileName) {
        return theDatabase.readData(pFileName);
    }

    /**
     * Speichert globale Daten (in Programm Verzeichnis)
     *
     * @param pKnot Zu Speichernder Knoten
     * @param pFileName Speichername
     */

    public void saveConfig(DataKnot pKnot, String pFileName) {                  // In Programm Ordner (global)
        theDatabase.writeConfigFile(pKnot, pFileName);
    }

    /**
     * Läd Programmspezifische Daten
     * @param pFileName
     * @return
     */

    public DataKnot loadConfig(String pFileName) {
        return theDatabase.readConfigFile(pFileName);
    }

    /**
     * Startet Berechnung des Aufwandes
     * wird von Oberfläche angestoßen
     */

    public void calculate() {
        DataKnot tempKnot = new DataKnot("Daten");
        for (String eachString : JarLoader.getJarLoader().getListOfElements("Calcer", "de.calculator.Calcer")) {
            tempKnot.addChild("Calcer").setValue(eachString);
        }
        tempKnot.addChild(theProjectManager.getDataKnot());

        theCalcbase.calculate(this, tempKnot);
    }

    /**
     * Startet Optimierung des Aufwandes
     * wird von Oberfläche angestoßen
     */

    public void optimize() {
        DataKnot tempKnot = new DataKnot("Daten");
        for (String eachString : JarLoader.getJarLoader().getListOfElements("Calcer", "de.calculator.Calcer")) {
            tempKnot.addChild("Calcer").setValue(eachString);
        }
        tempKnot.addChild(theProjectManager.getDataKnot());

        theCalcbase.optimize(this, tempKnot);
    }

    /**
     * Vordert die übergebene Seite auf der Oberfläche an
     * @param name Name der Seite
     */

    public void showPage(String name){
        theProjectManager.showPage(name, theFrame);
    }

    /**
     * Main Methode
     *
     * Stellt das Look and Feel ein
     *
     * @param args Kommandozeilenparameter
     */

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }  catch(Exception e){}
        new Core();
    }
}
