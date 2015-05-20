/**
 * Created by Pascal_Betz on 20.05.15.
 */


public class InfluenceFactors {

    private int nFactors[] = new int[28];


    public InfluenceFactors(){

    nFactors[0] = 1;        //1.
    nFactors[1] = 3;
    nFactors[2] = 2;        //2.
    nFactors[3] = 4;
    nFactors[4] = 3;        //3.
    nFactors[5] = 4;
    nFactors[6] = 4;        //4.
    nFactors[7] = 2;
    nFactors[8] = 5;        //5.
    nFactors[9] = 2;
    nFactors[10] = 6;       //6.
    nFactors[11] = 3;
    nFactors[12] = 7;       //7.
    nFactors[13] = 4;
    nFactors[14] = 8;       //8.
    nFactors[15] = 2;
    nFactors[16] = 9;       //9.
    nFactors[17] = 2;
    nFactors[18] = 10;      //10.
    nFactors[19] = 4;
    nFactors[20] = 11;      //11.
    nFactors[21] = 2;
    nFactors[22] = 12;      //12.
    nFactors[23] = 1;
    nFactors[24] = 13;      //13.
    nFactors[25] = 3;
    nFactors[26] = 14;      //14.
    nFactors[27] = 2;

    }

    public int setInfluenceFactor(int pFactor){

        return nFactors[pFactor];
    }

    public void setInfluenceFactor(int pFactor, int pValue){

        nFactors[pFactor] = pValue;
    }

}
