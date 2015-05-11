/**
 * Created by Pascal-L on 04.05.2015.
 */
public interface IFunction {

    public void setID(String id);
    public String getID();
    public void setName(String name);
    public String getName();
    public boolean setComplexity(int complexity); // einfach = 1, mittel = 2, komplex = 3
    public int getComplexity();
    public boolean setType(int type);   // Abfrage = 1, Eingabe = 2, Ausgabe = 3, Interner Datenbestand = 4, Externer Datenbestand = 5
    public int getType();

}
