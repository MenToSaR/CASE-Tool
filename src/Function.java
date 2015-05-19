/**
 * Created by Pascal-L on 04.05.2015.
 */
public class Function implements IFunction {

    public static int TYPE_ABFRAGE = 1;
    public static int COMPLEXITY_MITTEL = 2;

    private int _complexity=COMPLEXITY_MITTEL;
    private String _functionID;
    private int _type=TYPE_ABFRAGE;
    private String _name;


    @Override
    public void setID(String id) {
        _functionID=id;
    }

    @Override
    public String getID() {
        return _functionID;
    }

    public void setName(String name) {
        _name=name;
    }

    public String getName() {
        return _name;
    }

    @Override
    public boolean setComplexity(int complexity) {
        if(complexity > 3 || complexity < 1){
            return false; // fehlerhafte Komplexitätsstufe wurde übergeben
        }else{
            _complexity=complexity;
            return true;
        }

    }

    @Override
    public int getComplexity() {
        return _complexity;
    }

    @Override
    public boolean setType(int type) {

        if(type > 4 || type < 0){
            return false; // fehlerhafter Typ wurde übergeben
        }else{
            _type=type;
            return true;
        }
    }

    public int getWeight() {
        int weight=0;
        if(_type < 2){
            switch(_complexity){
                case 1: weight=3;
                    break;
                case 2: weight=4;
                    break;
                case 3: weight=6;
                    break;
                default: break;
            }
        }else if(_type==2){
            switch(_complexity){
                case 1: weight=4;
                    break;
                case 2: weight=5;
                    break;
                case 3: weight=7;
                    break;
                default: break;
            }
        }else if(_type==3){
            switch(_complexity){
                case 1: weight=7;
                    break;
                case 2: weight=10;
                    break;
                case 3: weight=5;
                    break;
                default: break;
            }
        }else{
            switch(_complexity){
                case 1: weight=5;
                    break;
                case 2: weight=7;
                    break;
                case 3: weight=10;
                    break;
                default: break;
            }
        }
        return weight;
    }

    @Override
    public int getType() {
        return _type;
    }
}

