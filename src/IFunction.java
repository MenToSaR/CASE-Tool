/**
 * Created by Pascal-L on 04.05.2015.
 */
public interface IFunction {

    void setID(String pID);
    String getID();
    void setName(String pName);
    String getName();
    boolean setComplexity(int pComplexity); // einfach = 1, mittel = 2, komplex = 3
    int getComplexity();
    boolean setType(int pType);   // Abfrage = 0, Eingabe = 1, Ausgabe = 2, Interner Datenbestand = 3, Externer Datenbestand = 4
    int getType();

}
