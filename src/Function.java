/**
 * Created by Pascal-L on 04.05.2015.
 *
 *  Diese Klasse beschreibt den Aufbau einer einzelnen Produktfunktion mit Typ, Komplexität, ID, Name, und Gewichtung
 */
public class Function implements IFunction {

    public static int TYPE_ABFRAGE = 1;         // DEFAULT Value fuer Type
    public static int COMPLEXITY_MITTEL = 2;    // DEFAULT Value fuer Komplexitaet

    private int nComplexity = COMPLEXITY_MITTEL;
    private String theFunctionID;
    private int nType = TYPE_ABFRAGE;
    private String theName;


    @Override
    public void setID(String id) {
        theFunctionID =id;
    }

    @Override
    public String getID() {
        return theFunctionID;
    }

    public void setName(String name) {
        theName =name;
    }

    public String getName() {
        return theName;
    }

    @Override
    public boolean setComplexity(int pComplexity) {
        if(pComplexity > 3 || pComplexity < 1){
            return false; // fehlerhafte Komplexitaetsstufe wurde uebergeben
        }else{
            nComplexity = pComplexity;
            return true;
        }

    }

    @Override
    public int getComplexity() {
        return nComplexity;
    }

    @Override
    public boolean setType(int pType) {
        if(pType > 4 || pType < 0){
            return false; // fehlerhafter Typ wurde uebergeben
        }else{
            nType = pType;
            return true;
        }
    }

    public int getWeight() {  // Gibt Gewicht der Funktion zurueck. Werte sind entsprechend der Tabelle zur FunctionPoint Berechnung.
        int weight = 0;
        if (nType < 2) {
            switch(nComplexity){
                case 1: weight = 3;
                    break;
                case 2: weight = 4;
                    break;
                case 3: weight = 6;
                    break;
                default: break;
            }
        } else if(nType == 2) {
            switch(nComplexity){
                case 1: weight = 4;
                    break;
                case 2: weight = 5;
                    break;
                case 3: weight = 7;
                    break;
                default: break;
            }
        } else if(nType == 3) {
            switch(nComplexity){
                case 1: weight = 7;
                    break;
                case 2: weight = 10;
                    break;
                case 3: weight = 5;
                    break;
                default: break;
            }
        } else {
            switch(nComplexity){
                case 1: weight = 5;
                    break;
                case 2: weight = 7;
                    break;
                case 3: weight = 10;
                    break;
                default: break;
            }
        }
        return weight;
    }

    @Override
    public int getType() {
        return nType;
    }
}

