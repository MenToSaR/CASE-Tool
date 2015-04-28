package de.database.manager;

import de.database.InOuter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarException;
import java.util.jar.JarFile;

/**
 * Created by Marcel on 28.04.2015.
 */
public class Database {

    private static Database database;

    private Database() {

    }

    public InOuter load(String pModule) throws RuntimeException{
        try {
            File theFolder = new File("Porter");
            File[] theFiles = theFolder.listFiles();

            for (File eachFile : theFiles) {
                if (eachFile.isFile() && eachFile.getName().endsWith(".jar")) {
                    JarFile theJarFile = new JarFile(eachFile.getAbsolutePath());
                    Enumeration theEnum = theJarFile.entries();

                    while (theEnum.hasMoreElements()) {
                        JarEntry theEntry = (JarEntry) theEnum.nextElement();
                        if (!theEntry.isDirectory() && theEntry.getName().endsWith(".class")) {
                            if (theEntry.getName().equals(pModule + ".class")) {
                                URL[] urls = {new URL("jar:file:" + theJarFile.getName().split(".class")[0] + "!/")};
                                URLClassLoader cl = URLClassLoader.newInstance(urls);
                                return (InOuter) cl.loadClass(theEntry.getName().split(".class")[0]).newInstance();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new RuntimeException("Module " + pModule + " was not found!");
    }

    public ArrayList<String> getListOfPorter() {
        ArrayList<String> listPorter = new ArrayList<>();
        try {
            File theFolder = new File("Porter");
            File[] theFiles = theFolder.listFiles();

            for (File eachFile : theFiles) {
                if (eachFile.isFile() && eachFile.getName().endsWith(".jar")) {
                    JarFile theJarFile = new JarFile(eachFile.getAbsolutePath());
                    Enumeration theEnum = theJarFile.entries();

                    URL[] urls = {new URL("jar:file:" + theJarFile.getName() + "!/")};

                    while (theEnum.hasMoreElements()) {
                        JarEntry theEntry = (JarEntry) theEnum.nextElement();
                        if (!theEntry.isDirectory() && theEntry.getName().endsWith(".class")) {
                            String className = theEntry.getName().split(".class")[0];
                            listPorter.add(className);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPorter;
    }

    public static Database getDatabase() {
        if (database != null) {
            return database;
        } else {
            return database = new Database();
        }
    }
}
