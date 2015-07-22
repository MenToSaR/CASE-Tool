package de;

import de.database.InOuter;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Marcel on 30.04.2015 in CASE.
 */
public class JarLoader {

    private static JarLoader jarLoader;

    /**
     * Singleton um Jar-Dateien einzulesen
     */

    private JarLoader() {

    }

    /**
     * Laedt angegebene Jar-Datei
     * @param pModule Modul der zu ladenden Datei (package)
     * @param pFolder Ordner in der Jar-Dateien gesucht werden sollen
     * @return
     * @throws RuntimeException
     */

    public Object load(String pModule, String pFolder) throws RuntimeException{ //Factory Method
        try {
            File theFolder = new File(pFolder);
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
                                return cl.loadClass(theEntry.getName().split(".class")[0]).newInstance();
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

    /**
     * gibt liste alle Komponenten in Jar-Datei zurueck
     * @param pFolder Ordner in dem gesucht werden soll
     * @param pType package der anzugebenen Klassen
     * @return
     */

    public ArrayList<String> getListOfElements(String pFolder, String pType) {
        ArrayList<String> listPorter = new ArrayList<>();
        try {
            File theFolder = new File(pFolder);
            File[] theFiles = theFolder.listFiles();

            for (File eachFile : theFiles) {
                if (eachFile.isFile() && eachFile.getName().endsWith(".jar")) {
                    JarFile theJarFile = new JarFile(eachFile.getAbsolutePath());
                    Enumeration theEnum = theJarFile.entries();

                    URL[] urls = {new URL("jar:file:" + theJarFile.getName().split(".class")[0] + "!/")};

                    while (theEnum.hasMoreElements()) {
                        JarEntry theEntry = (JarEntry) theEnum.nextElement();
                        if (!theEntry.isDirectory() && theEntry.getName().endsWith(".class")) {
                            String className = theEntry.getName().split(".class")[0];
                            URLClassLoader cl = URLClassLoader.newInstance(urls);
                            if (Class.forName(pType).isAssignableFrom(cl.loadClass(theEntry.getName().split(".class")[0]))) {
                                listPorter.add(className);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listPorter;
    }

    public static JarLoader getJarLoader() {
        if (jarLoader == null) {
            return jarLoader = new JarLoader();
        }
        return jarLoader;
    }
}
