import de.Core;
import de.database.DataKnot;

/**
 * Created by Pascal_Betz on 20.05.15.
 */


public class InfluenceFactors {

    private int nFactors[] = new int[14];


    public InfluenceFactors(Core pCore){

    if(pCore.loadConfig("InfluenceFactors.xml") == null) {

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

    DataKnot InfluenceFactors = new DataKnot("InfluenceFactors");




    pCore.saveConfig(InfluenceFactors, "InfluenceFactors.xml");
    }
    }

    public int getInfluenceFactor(int pFactor){

        return nFactors[pFactor];
    }

    public void setInfluenceFactor(int pFactor, int pValue){

        nFactors[pFactor] = pValue;
    }

    public void load(Core pCore){

        pCore.loadConfig("InfluenceFactors.xml");


    }

}
