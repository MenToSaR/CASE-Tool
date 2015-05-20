package de.database;

import de.JarLoader;
import de.window.MessageBox;
import de.window.MessageBoxFactory;
import de.window.TextMessageBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Marcel on 28.04.2015.
 */
public class Database {

    public static String DEFAULT_PORTER_TAG = "defporter";
    public static String PROJECT_LIST_TAG = "projects";
    public static String PROJECT_LAST_PROJECT = "lastproject";
    public static String DEFAULT_WORKSPACE_TAG = "defworkspace";
    public static String PROJECT_CONFIG_FILE = "Project.cfg";

    private String theDefaultPorter = "XMLPorter";
    private String theConfigFileName = "Config.cfg";

    private String theWorkingDir;
    private String theWorkspaceDir;

    private DataKnot theConfigKnot;

    public Database() {
        theConfigKnot = readConfig();
        if (theConfigKnot == null) {
            System.out.println("No config found, creating new");
            createNewConfig();
            writeConfig();
        }
        theDefaultPorter = theConfigKnot.getFirstChildByTag(DEFAULT_PORTER_TAG).getValue();
        theWorkspaceDir = theConfigKnot.getFirstChildByTag(DEFAULT_WORKSPACE_TAG).getValue();
    }

    public void createNewConfig() {
        theConfigKnot = new DataKnot("config");
        theConfigKnot.addChild(PROJECT_LIST_TAG);
        theConfigKnot.addChild(DEFAULT_PORTER_TAG).setValue(theDefaultPorter);
        theConfigKnot.addChild(DEFAULT_WORKSPACE_TAG).setValue(System.getProperty("user.home") + "/Desktop");
    }

    public void editConfigEntry(String pKey, String pValue) {
        DataKnot tempKnot = theConfigKnot.getFirstChildByTag(pKey);
        if (tempKnot == null) {
            tempKnot = theConfigKnot.addChild(pKey);
        }

        tempKnot.setValue(pValue);

        writeConfig();
    }

    public String getConfigEntry(String pKey) {
        return theConfigKnot.getFirstChildByTag(pKey) != null ? theConfigKnot.getFirstChildByTag(pKey).getValue() : "";
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

    public DataKnot readConfigFile(String pFileName) {
        try {
            return ((InOuter) JarLoader.getJarLoader().load(theDefaultPorter, "Porter")).read(pFileName);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void writeConfigFile(DataKnot pKnot, String pFileName) {
        ((InOuter) JarLoader.getJarLoader().load(theDefaultPorter, "Porter")).write(pFileName, pKnot);
    }

    public DataKnot readData(String pFile) {
        try {
            return ((InOuter) JarLoader.getJarLoader().load(theDefaultPorter, "Porter")).read(theWorkingDir + "/" + pFile);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public void writeData(String pFile, DataKnot pKnot) {
        ((InOuter) JarLoader.getJarLoader().load(theDefaultPorter, "Porter")).write(theWorkingDir + "/" + pFile, pKnot);
    }

    public ArrayList<String> getListOfPorter() {
        return JarLoader.getJarLoader().getListOfElements("Porter", "de.database.InOuter");
    }

    public InOuter loadPorter(String pS) {
        return (InOuter) JarLoader.getJarLoader().load(pS, "Porter");
    }

    public void createWorkingDir(String pDir, String pName) {
        new File(pDir + "/" + pName).mkdirs();
        theWorkingDir = pDir + "/" + pName;
        theWorkingDir = theWorkingDir.replace("\\", "/");
        DataKnot tempKnot = new DataKnot("project");
        tempKnot.addData("name", pName);
        tempKnot.setValue(pDir.replace("\\", "/"));
        theConfigKnot.getFirstChildByTag(PROJECT_LIST_TAG).addChild(tempKnot);
        writeConfig();
    }

    public void setWorkingDir(String pDir) throws FileNotFoundException {
        theWorkingDir = pDir;
        theWorkingDir = theWorkingDir.replace("\\", "/");
        if (!new File(pDir).exists()) {
            throw new FileNotFoundException("Angegebener Pfad nicht vorhanden: " + pDir);
        }
    }

    public boolean deleteWorkingDir() {
        if (theWorkingDir == null || theWorkingDir.equals("")) {
            MessageBoxFactory.createMessageBox("Fehler", "Kein Projekt geoeffnet");
            return false;
        }
        if (MessageBoxFactory.createMessageBox("Obacht", "Soll der Pfad: " + theWorkingDir + " wirklich geloescht werden?") == MessageBox.RESULT_OK) {
            System.out.println("Start deleting" + new File(theWorkingDir).getAbsolutePath());
            if(deleteFile(new File(theWorkingDir))) {
                new File(theWorkingDir).delete();
                MessageBoxFactory.createMessageBox("Success", "Projekt wurde gel�scht");
                for (DataKnot eachKnot : theConfigKnot.getFirstChildByTag(PROJECT_LIST_TAG).getChildren()) {
                    if (theWorkingDir.equals(eachKnot.getValue() + "/" + eachKnot.getDataByKey("name"))) {
                        theConfigKnot.getFirstChildByTag(PROJECT_LIST_TAG).removeChild(eachKnot);
                        break;
                    }
                }
                theWorkingDir = "";
                writeConfig();
                return true;
            } else {
                MessageBoxFactory.createMessageBox("Achtung", "Projekt konnte nicht gel�scht werden");
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteProjectEntry(String pName) {
        for (DataKnot eachKnot : theConfigKnot.getFirstChildByTag(PROJECT_LIST_TAG).getChildren()) {
            if (pName.equals(eachKnot.getDataByKey("name"))) {
                theConfigKnot.getFirstChildByTag(PROJECT_LIST_TAG).removeChild(eachKnot);
                writeConfig();
                return true;
            }
        }
        return false;
    }

    private boolean deleteFile(File f) {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                if (!deleteFile(c)) {
                    return false;
                }
            }
            return f.delete();
        } else {
            return f.delete();
        }
    }

    public String getWorkspace() {
        return theWorkspaceDir;
    }

    public String getWorkingDir() {
        return theWorkingDir;
    }

    public void setWorkspace(String pDir) {
        theWorkspaceDir = pDir;
    }

    public void setDefaultPorter(String pPorter) {
        theDefaultPorter = pPorter;
    }

    public String getDefaultPorter() {
        return theDefaultPorter;
    }
}
