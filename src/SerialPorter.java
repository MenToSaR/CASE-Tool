import de.database.DataKnot;
import de.database.InOuter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Marcel on 27.04.2015.
 */
public class SerialPorter extends InOuter {

    /**
     * Klasse zur Serialisierung von DataKnot's
     * @param pFile
     * @param pData
     */

    @Override
    public void write(String pFile, DataKnot pData) {
        try {
            ObjectOutputStream tempOut = new ObjectOutputStream(new FileOutputStream(pFile));
            tempOut.writeObject(pData);
            tempOut.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public DataKnot read(String pFile) {
        try {
            ObjectInputStream tempIn = new ObjectInputStream(new FileInputStream(pFile));
            DataKnot tempKnot = (DataKnot) tempIn.readObject();
            tempIn.close();
            return tempKnot;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
