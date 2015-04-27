package de;

import de.window.MainFrame;
import de.database.DataKnot;
import de.database.manager.serializer.SerialExporter;

/**
 * Created by Marcel on 20.04.2015.
 */
public class Core {

    public static void memoryTest() {
        DataKnot tempKnot = new DataKnot("Main");                                               // Lege Daten an
        tempKnot.addChild("KeinKind");
        for (int i = 0; i < 10; i++) {
            tempKnot.addChild("Kind").addData("Alter", "" + (int) (Math.random() * 25));
        }

        SerialExporter theSE = new SerialExporter();                                            // Daten sichern
        theSE.write("TestFile", tempKnot);

        System.out.println(theSE.read("TestFile").getChildrenByTag("Kind").size());             // Daten laden und ausgeben
        for (DataKnot eachKnot : theSE.read("TestFile").getChildrenByTag("Kind")) {
            System.out.println("Alter: " + eachKnot.getValueByKey("Alter"));
        }
    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();

    //    myFrame.

        memoryTest();
    }
}
