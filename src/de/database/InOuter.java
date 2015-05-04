package de.database;

import java.io.FileNotFoundException;

/**
 * Created by Marcel on 20.04.2015.
 */
public abstract class InOuter {

    public abstract void write(String pFile, DataKnot pData);

    public abstract DataKnot read(String pFile) throws FileNotFoundException;
}
