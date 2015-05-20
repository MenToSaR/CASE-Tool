/**
 * Created by Pascal_Betz on 20.05.15.
 */


public class InfluenceFactors {

    private int nFactors[] = new int[15];


    public InfluenceFactors(){

    nFactors[0] = 0;    //not used
    nFactors[1] = 3;
    nFactors[2] = 4;
    nFactors[3] = 4;
    nFactors[4] = 2;
    nFactors[5] = 2;
    nFactors[6] = 3;
    nFactors[7] = 4;
    nFactors[8] = 2;
    nFactors[9] = 2;
    nFactors[10] = 4;
    nFactors[11] = 2;
    nFactors[12] = 1;
    nFactors[13] = 4;
    nFactors[14] = 3;

    }

    public int setInfluenceFactor(int pFactor){

        return nFactors[pFactor];
    }

    public void setInfluenceFactor(int pFactor, int pValue){

        nFactors[pFactor] = pValue;
    }

}
