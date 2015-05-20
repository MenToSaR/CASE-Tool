/**
 * Created by Pascal_Betz on 20.05.15.
 */


public class InfluenceFactors {

    private int nFactors[] = new int[14];


    public InfluenceFactors(){

    nFactors[0] = 3;    //not used
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

    }

    public int setInfluenceFactor(int pFactor){

        return nFactors[pFactor];
    }

    public void setInfluenceFactor(int pFactor, int pValue){

        nFactors[pFactor] = pValue;
    }

}
