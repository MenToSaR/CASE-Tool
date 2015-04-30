package de;

import de.calculator.Calcbase;
import de.database.manager.Database;
import de.window.MainFrame;
import de.database.DataKnot;

/**
 * Created by Marcel on 20.04.2015.
 */
public class Core {

    private Database theDatabase = new Database();
    private Calcbase theCalcbase = new Calcbase();

    public Core() {
        MainFrame myFrame = new MainFrame(this);

        myFrame.open();

        memoryTest();
    }

    public void memoryTest() {
        DataKnot tempKnot = new DataKnot("Main");                                               // Lege Daten an
        tempKnot.addChild("KeinKind");
        for (int i = 0; i < 10; i++) {
            tempKnot.addChild("Kind").addData("Alter", "" + (int) (Math.random() * 25));
            tempKnot.setValue("Hallo");
        }

        theDatabase.load("SerialPorter").write("TestFile", tempKnot);

        DataKnot tKnot = theDatabase.load("SerialPorter").read("TestFile");
        tKnot.printKnot();
    }

    public void calculate() {
        DataKnot tempKnot = new DataKnot("Daten");
        for (String eachString : JarLoader.getJarLoader().getListOfElements("Calcer")) {
            tempKnot.addChild("Calcer").setValue(eachString);
        }
        theCalcbase.calculate(tempKnot);
    }

    public static void main(String[] args) {
        new Core();
    }
}
