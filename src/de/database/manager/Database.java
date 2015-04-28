package de.database.manager;

import de.database.InOuter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Marcel on 28.04.2015.
 */
public class Database {

    private static Database database;

    private Database() {

    }

    public void load(String pModule) {
        try {

            URL url = new File(pModule).toURL();
            JarFile theJarFile = new JarFile(pModule);
            Enumeration e = theJarFile.entries();

            URL[] urls = { new URL("jar:file:" + theJarFile.getName()+ "!/") };

            URLClassLoader cl = URLClassLoader.newInstance(urls);
//            Class theIO = cl.loadClass("XMLPorter");

  //          System.out.println(theIO);

            System.out.println(urls[0]);

            while (e.hasMoreElements()) {
                JarEntry je = (JarEntry) e.nextElement();
                System.out.println(je.getName());
                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }
                // -6 because of .class
                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace("/", ".");
                System.out.println(className);
                Class c = cl.loadClass(className);
            }



         //   ClassLoader cl = new URLClassLoader(urls);

        //    System.out.println(cl.);

        //    Class cls = cl.loadClass("prog."+cStart+pS.substring(1).toLowerCase());

        //    mapProg.put(pS.toLowerCase(), (Prog) (cls.newInstance()));
        } catch (Exception e) {
            e.printStackTrace();
        //    Log.getLog().writeToLog(Log.INT_EXCEPTION, e.toString());
        //    return -1;
        }
    }

    public static Database getDatabase() {
        if (database != null) {
            return database;
        } else {
            return database = new Database();
        }
    }
}
