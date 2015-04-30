package de;

import de.database.manager.Database;
import de.window.MainFrame;
import de.database.DataKnot;

/**
 * Created by Marcel on 20.04.2015.
 */
public class Core {

    public static void memoryTest() {
        DataKnot tempKnot = new DataKnot("Main");                                               // Lege Daten an
        tempKnot.addChild("KeinKind");
        for (int i = 0; i < 10; i++) {
            tempKnot.addChild("Kind").addData("Alter", "" + (int) (Math.random() * 25));
            tempKnot.setValue("Hallo");
        }

        for (String eachString : Database.getDatabase().getListOfPorter()) {
            System.out.println(eachString);
        }

        Database.getDatabase().load("SerialPorter").write("TestFile", tempKnot);

        DataKnot tKnot = Database.getDatabase().load("SerialPorter").read("TestFile");
        for (DataKnot eachKnot : tKnot) {
            System.out.println(eachKnot.getTag());
            System.out.println(eachKnot.getValue());
        }
    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();

        myFrame.open();

        memoryTest();
    }
}
