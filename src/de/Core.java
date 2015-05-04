package de;

import de.calculator.Calcbase;
import de.database.Database;
import de.window.MainFrame;
import de.database.DataKnot;
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

    public Core() {
        MainFrame myFrame = new MainFrame(this);

        myFrame.open();
    }

    public void reopenProject() {
        ArrayList<String> tempList = new ArrayList<String>();
        for (DataKnot eachKnot : theDatabase.getConfig().getFirstChildByTag("projects").getChildren()) {
            tempList.add(eachKnot.getTag());
        }
        MessageBoxFactory.createListMessageBox("Choose...", "Projekte:", tempList);
    }

    public void openProject() {
        JFileChooser theChooser = new JFileChooser();
        theChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        theChooser.showOpenDialog(new JFrame());
        String tempDir = theChooser.getSelectedFile().getAbsolutePath();
        if (tempDir != null) {
            try {
                theDatabase.setWorkingDir(tempDir);
            } catch (FileNotFoundException e) {
                // TODO FEHLERMELDUNG
            }
        }
    }

    public void createProject() {
        JFileChooser theChooser = new JFileChooser();
        theChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        theChooser.showOpenDialog(new JFrame());
        String tempDir = theChooser.getSelectedFile().getAbsolutePath();
        if (tempDir != null) {
            String tempProjectName = MessageBoxFactory.createTextMessageBox("Achtung", "Namen des Projektes eingeben!");
            if (!tempProjectName.equals("")) {
                theDatabase.createWorkingDir(tempDir, tempProjectName);
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
