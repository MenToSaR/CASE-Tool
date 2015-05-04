package de.database;

import de.JarLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Marcel on 28.04.2015.
 */
public class Database {

    private String theDefaultPorter = "XMLPorter";
    private String theConfigFileName = "Config.cfg";

    private String theWorkingDir;

    private DataKnot theConfigKnot;

    public Database() {
        theConfigKnot = readConfig();
        if (theConfigKnot == null) {
            System.out.println("No config found, creating new");
            createNewConfig();
            writeConfig();
        }
        theDefaultPorter = theConfigKnot.getFirstChildByTag("defporter").getValue();
    }

    public void createNewConfig() {
        theConfigKnot = new DataKnot("config");
        theConfigKnot.addChild("projects");
        theConfigKnot.addChild("defporter").setValue(theDefaultPorter);
    }

    public DataKnot getConfig() {
        return theConfigKnot;
    }

    private DataKnot readConfig() {
        try {
            return ((InOuter) JarLoader.getJarLoader().load(theDefaultPorter, "Porter")).read(theConfigFileName);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private void writeConfig() {
        ((InOuter) JarLoader.getJarLoader().load(theDefaultPorter, "Porter")).write(theConfigFileName, theConfigKnot);
    }

    public DataKnot readData(String pFile) {
        try {
            return ((InOuter) JarLoader.getJarLoader().load(theDefaultPorter, "Porter")).read(theWorkingDir + pFile);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void writeData(String pFile, DataKnot pKnot) {
        ((InOuter) JarLoader.getJarLoader().load(theDefaultPorter, "Porter")).write(theWorkingDir + pFile, pKnot);
    }

    public ArrayList<String> getListOfPorter() {
        return JarLoader.getJarLoader().getListOfElements("Porter", "de.database.InOuter");
    }

    public InOuter loadPorter(String pS) {
        return (InOuter) JarLoader.getJarLoader().load(pS, "Porter");
    }

    public void createWorkingDir(String pDir, String pName) {
        new File(pDir + "/" + pName).mkdirs();
        theWorkingDir = pDir;
        theConfigKnot.getFirstChildByTag("projects").addChild(pName).setValue(pDir);
        writeConfig();
    }

    public void setWorkingDir(String pDir) throws FileNotFoundException {
        theWorkingDir = pDir;
        if (!new File(pDir).exists()) {
            throw new FileNotFoundException("Angegebener Pfad nicht vorhanden: " + pDir);
        }
    }

    public void setDefaultPorter(String pPorter) {
        theDefaultPorter = pPorter;
    }

    public String getDefaultPorter() {
        return theDefaultPorter;
    }
}
