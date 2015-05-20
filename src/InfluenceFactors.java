import com.sun.jdi.IntegerValue;
import de.Core;
import de.database.DataKnot;
import sun.jvm.hotspot.jdi.IntegerValueImpl;

/**
 * Created by Pascal_Betz on 20.05.15.
 */


public class InfluenceFactors {

    private int nFactors[] = new int[14];


    public InfluenceFactors(Core pCore){

    if(pCore.loadConfig("InfluenceFactors.dat") == null) {

        nFactors[0] = 3;
        nFactors[1] = 4;
        nFactors[2] = 4;
        nFactors[3] = 2;
        nFactors[4] = 2;
        nFactors[5] = 3;
        nFactors[6] = 4;
        nFactors[7] = 2;
        nFactors[8] = 2;
        nFactors[9] = 4;
        nFactors[10] = 2;
        nFactors[11] = 1;
        nFactors[12] = 4;
        nFactors[13] = 3;

    DataKnot influenceFactors = new DataKnot("InfluenceFactors");
        for (int i = 0; i < 14; i++){
            influenceFactors.addData( "" + i, "" + nFactors[i]);
        }

        pCore.saveConfig(influenceFactors, "InfluenceFactors.dat");

    }else{

        load(pCore);
    }

    }

    public int getInfluenceFactor(int pFactor){

        return nFactors[pFactor];
    }

    public void setInfluenceFactor(int pFactor, int pValue){

        nFactors[pFactor] = pValue;


    }

    public void load(Core pCore){

        DataKnot influenceFactors;
        influenceFactors = pCore.loadConfig("InfluenceFactors.dat");

        for (int i = 0; i < 14; i++){
            nFactors[i] = Integer.parseInt(influenceFactors.getDataByKey("i"));
        }


    }

    public int getSum(){

        int summe = 0;

        for (int i = 0; i < 14; i++){
            summe += nFactors[i];
        }
        return summe;
    }

}
