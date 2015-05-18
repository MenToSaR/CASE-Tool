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

    public Core() {
        theFrame = new MainFrame(this);

        theProjectManager = new ProjectManager(this, theFrame);

        theFrame.open();
    }

    public void deleteProject() {
        if (MessageBoxFactory.createMessageBox("Achtung", "Projekt wirklich löschen?") == MessageBox.RESULT_OK) {
            if (theDatabase.deleteWorkingDir()) {
                theFrame.showTree("", null);
            }
        }
    }

    public void reopenProject() {
        ArrayList<String> tempList = new ArrayList<String>();
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
                theDatabase.deleteProjectEntry(theResult);
            }
        }
    }

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

    public void createProject() {
        JFileChooser theChooser = new JFileChooser(theDatabase.getWorkspace());
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

    public void refreshProject(String pProjectName) {
        theFrame.showTree(pProjectName, theProjectManager.getTreeList());
        theProjectManager.loadProjectData(theDatabase);
    }

    public void saveData(DataKnot pKnot, String pFileName) {
        pKnot.printKnot();
        theDatabase.writeData(pFileName, pKnot);
    }

    public void calculate() {
        DataKnot tempKnot = new DataKnot("Daten");
        for (String eachString : JarLoader.getJarLoader().getListOfElements("Calcer", "de.calculator.Calcer")) {
            tempKnot.addChild("Calcer").setValue(eachString);
        }
        tempKnot.addChild(theProjectManager.getDataKnot());

        DataKnot leckMich = tempKnot.getFirstChildByTag("root");
        DataKnot leckMichagain = leckMich.addChild("productfunction");
        DataKnot llmaa=leckMichagain.addChild("element");
        llmaa.addData("ID","LF 10");
        llmaa.addData("TITLE","Eingabe iwas");
        DataKnot llmaa1=leckMichagain.addChild("element");
        llmaa1.addData("ID","LF 20");
        llmaa1.addData("TITLE","AUsgabe");
        DataKnot llmaa2=leckMichagain.addChild("element");
        llmaa2.addData("ID","LF 30");
        llmaa2.addData("TITLE","Abfrage");
        theCalcbase.calculate(tempKnot);

    }

    public void showPage(String name){
        theProjectManager.showPage(name, theFrame);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }  catch(Exception e){}
        new Core();
    }
}
