package de;

import de.Window.MainFrame;
import de.database.DataKnot;
import de.database.manager.serializer.SerialExporter;

/**
 * Created by Marcel on 20.04.2015.
 */
public class Core {

    public static void memoryTest() {
        DataKnot tempKnot = new DataKnot("Main", "Ich bin ein Knoten");
        tempKnot.addChild("Kind", "Kind1");
        tempKnot.addChild("Kind", "Kind2");
        tempKnot.addChild("KeinKind", "Ich bin kein kind");
        for (int i = 0; i < 10; i++) {
            tempKnot.getChildrenByName("Kind").add(new DataKnot("Alter", "" + i));
        }

        SerialExporter theSE = new SerialExporter();
        theSE.write("TestFile", tempKnot);

        for (DataKnot eachKnot : theSE.read("TestFile").getChildrenByName("Kind")) {
            System.out.println("Alter" + eachKnot.getValue());
        }
    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();

    //    myFrame.

        memoryTest();

    }
}
