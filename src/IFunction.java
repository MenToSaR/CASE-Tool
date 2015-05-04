/**
 * Created by Pascal-L on 04.05.2015.
 */
public interface IFunction {

    public void setID(int id);
    public int getID();
    public void setComplexity(int complexity); // einfach = 1, mittel = 2, komplex = 3
    public int getComplexity();
    public void setType(String type);
    public String getType();

}
