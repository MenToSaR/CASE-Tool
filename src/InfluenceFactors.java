import de.Core;
import de.database.DataKnot;

/**
 * Created by Pascal_Betz on 20.05.15.
 */

/*
    Klasse "Influence Factors" beinhaltet die 14 Faktoren und deren Gewichtung. Diese sind als Array in "nFactors[x]"
    gespeichert.

 */

public class InfluenceFactors {

    private int nFactors[] = new int[14];
    private Core _core;


    /*
    Konstruktor - Erstellt die Klasse mit den Standardwerten für die 14 Einflussfaktoren.
    Diese werden im DataKnot "InfluenceFactors.dat" gespeichert.
     */

    public InfluenceFactors(Core pCore) {
        _core = pCore;
        if (_core.loadConfig("InfluenceFactors.dat") == null) {



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
                for (int i = 0; i < 14; i++) {
                    influenceFactors.addData("n" + i, "" + nFactors[i]);
                }

                _core.saveConfig(influenceFactors, "InfluenceFactors.dat");

            } else {    // falls schon Einflussfaktoren gespeichert wurden, werden diese geladen

                load();
            }


    }

    /*
    Liefert einen bestimmten Einflussfaktor zurück
     */
    public int getInfluenceFactor(int pFactor){

        return nFactors[pFactor];
    }

    /*
    Setzt einen bestimmten Einflussfaktor
     */
    public void setInfluenceFactor(int pFactor, int pValue){


        if (pValue <= 0){
            pValue = 1;
        }
        nFactors[pFactor] = pValue;
    }

    /*
    Laedt die gespeicherten Einflussfaktoren und speichert sie in das Array der Klasse.
     */
    public void load(){

        DataKnot influenceFactors;
        influenceFactors = _core.loadConfig("InfluenceFactors.dat");
        influenceFactors.printKnot();

        for (int i = 0; i < 14; i++){
            nFactors[i] = Integer.parseInt(influenceFactors.getDataByKey("n" + i));  // attributsbezeichnungen in xml duerfen nicht nur aus einer zahl bestehen!
        }


    }

    /*
    Speichert das aktuelle Array mit den Einflussfaktoren im DataKnot "InfluenceFactors.dat"
     */
    public void save(){

        DataKnot influenceFactors = new DataKnot("InfluenceFactors");
        for (int i = 0; i < 14; i++){
            influenceFactors.setData(("n" + i), "" + nFactors[i]);
        }
        _core.saveConfig(influenceFactors,"InfluenceFactors.dat");

    }

    /*
    Berechnet die Summe aller 14 Einflusfaktoren und gibt diese zurück
     */
    public int getSum(){

        int summe = 0;

        for (int i = 0; i < 14; i++){
            summe += nFactors[i];
        }
        return summe;
    }

    /*
    Berechnet anhand der Einflussfaktoren den Wert der Einflussbewertung und gibt ihn zurueck.
     */
    public double getEinflussbewertung(){

        int summe = getSum();
        double retValue = summe/100.0 + 0.65;

        return retValue;
    }

}
