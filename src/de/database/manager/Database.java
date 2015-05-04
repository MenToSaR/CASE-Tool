package de.database.manager;

import de.JarLoader;
import de.database.InOuter;
import java.util.ArrayList;

/**
 * Created by Marcel on 28.04.2015.
 */
public class Database {

    public ArrayList<String> getListOfPorter() {
        return JarLoader.getJarLoader().getListOfElements("Porter", "de.database.InOuter");
    }

    public InOuter load(String pS) {
        return (InOuter) JarLoader.getJarLoader().load(pS, "Porter");
    }
}
