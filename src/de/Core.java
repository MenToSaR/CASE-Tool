package de;

import de.calculator.Calcbase;
import de.database.manager.Database;
import de.window.MainFrame;
import de.database.DataKnot;

import javax.swing.*;

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
