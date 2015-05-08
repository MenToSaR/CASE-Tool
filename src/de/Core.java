package de;

import de.calculator.Calcbase;
import de.database.Database;
import de.window.MainFrame;
import de.database.DataKnot;
import de.window.MessageBox;
import de.window.MessageBoxFactory;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Marcel on 20.04.2015.
 */
public class Core {

    private Database theDatabase = new Database();
    private Calcbase theCalcbase = new Calcbase();

    MainFrame theFrame;

    public Core() {
        theFrame = new MainFrame(this);
        theFrame.open();
    }

    public void deleteProject() {
        if (MessageBoxFactory.createMessageBox("Achtung", "Projekt wirklich löschen?") == MessageBox.RESULT_OK) {
            if (theDatabase.deleteWorkingDir()) {
                theFrame.showTree(null);
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

                theFrame.showTree(theDatabase.readData(Database.PROJECT_CONFIG_FILE));
            } catch (FileNotFoundException e) {
                MessageBoxFactory.createMessageBox("Error", "Projekt existiert nicht mehr");
            }
        }
    }

    public void openProject() {
        JFileChooser theChooser = new JFileChooser(theDatabase.getWorkspace());
        theChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        theChooser.showOpenDialog(new JFrame());
        String tempDir = theChooser.getSelectedFile().getAbsolutePath();
        if (tempDir != null) {
            try {
                theDatabase.setWorkingDir(tempDir);

                theFrame.showTree(theDatabase.readData(Database.PROJECT_CONFIG_FILE));
            } catch (FileNotFoundException e) {
                // TODO FEHLERMELDUNG
            }
        }
    }

    public void createProject() {
        JFileChooser theChooser = new JFileChooser(theDatabase.getWorkspace());
        theChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        theChooser.showOpenDialog(new JFrame());
        String tempDir = theChooser.getSelectedFile().getAbsolutePath();
        if (tempDir != null) {
            String tempProjectName = MessageBoxFactory.createTextMessageBox("Achtung", "Namen des Projektes eingeben!");
            if (!tempProjectName.equals("")) {
                theDatabase.createWorkingDir(tempDir, tempProjectName);

                DataKnot tempKnot = new DataKnot("Projekt");
                tempKnot.setValue(tempProjectName);
                tempKnot.addChild("element").setValue("Funktionale Anforderungen");
                tempKnot.addChild("element").setValue("Nichtfunktionale Anforderungen");
                tempKnot.addChild("element").setValue("Glossar");

                theDatabase.writeData(Database.PROJECT_CONFIG_FILE, tempKnot);

                theFrame.showTree(theDatabase.readData(Database.PROJECT_CONFIG_FILE));
            }
        }
    }

    public void calculate() {
        DataKnot tempKnot = new DataKnot("Daten");
        for (String eachString : JarLoader.getJarLoader().getListOfElements("Calcer", "de.calculator.Calcer")) {
            tempKnot.addChild("Calcer").setValue(eachString);
        }
        theCalcbase.calculate(tempKnot);
    }

    public static void main(String[] args) {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){}
        new Core();
    }
}
