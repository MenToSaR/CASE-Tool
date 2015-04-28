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


   /*     XMLPorter theSE = new XMLPorter();                                                      // Daten sichern
        theSE.write("TestFileXML", tempKnot);

        for (DataKnot eachKnot : theSE.read("TestFileXML").getChildrenByTag("Kind")) {          // Daten laden und ausgeben
            System.out.println("Tag: " + eachKnot.getTag() + " Value: " + eachKnot.getValue());
            System.out.println("Alter: " + eachKnot.getValueByKey("Alter"));
        }

        DataKnot tKnot = theSE.read("TestFileXML");
        for (DataKnot eachKnot : tKnot) {
            System.out.println(eachKnot.getTag());
            System.out.println(eachKnot.getValue());
        }        */
    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();

    //    myFrame.

    //    memoryTest();

        Database.getDatabase().load("XMLPorter.jar");
    }
}
