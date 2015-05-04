/**
 * Created by Pascal-L on 04.05.2015.
 */
public class Function implements IFunction {

    private int _complexity;
    private int _functionID;
    private String _type;


    @Override
    public void setID(int id) {
        _functionID=id;
    }

    @Override
    public int getID() {
        return _functionID;
    }

    @Override
    public void setComplexity(int complexity) {
        _complexity=complexity;
    }

    @Override
    public int getComplexity() {
        return _complexity;
    }

    @Override
    public void setType(String type) {
        _type=type;
    }

    @Override
    public String getType() {
        return _type;
    }
}

