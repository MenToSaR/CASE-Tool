import de.Core;
import de.calculator.Calcer;
import de.database.DataKnot;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Marcel on 30.04.2015 in CASE.
 */
public class FunctionPoint extends Calcer {

    private Function functionArray[];
    private int fAnzahl=0; // Anzahl der Funktionen die von pKnot übergeben werden
    private int currentIndex=0;
    private Core _pCore;

    private FunctionInput theFunctionInput;

    @Override
    public void calculate(Core pCore, DataKnot pKnot) {
        _pCore=pCore;
        pKnot.printKnot();
        ArrayList<DataKnot> productFunctions = pKnot.getFirstChildByTag("productfunction").getChildrenByTag("element");
        fAnzahl=productFunctions.size();
        functionArray=new Function[fAnzahl];
        String id="";
        String name="";


        for (int i=0; i<fAnzahl; i++) {

            id=productFunctions.get(i).getDataByKey("ID");
            name=productFunctions.get(i).getDataByKey("TITLE");

            functionArray[i]=new Function();

            functionArray[i].setID(id);
            functionArray[i].setName(name);
        }
        theFunctionInput=new FunctionInput(this);
        loadFunctionGUI();
        openFrame();

    }

    public void loadFunctionsFromCore(){
        DataKnot theKnot = _pCore.loadData("FunktionenFunctionPoint.dat");

        if (theKnot != null) {
            ArrayList<DataKnot> productFunctions = theKnot.getChildren();
            fAnzahl=productFunctions.size();
            functionArray=new Function[fAnzahl];
            int i=0;
            for (DataKnot eachKnot : theKnot.getChildren()) {
                functionArray[i].setID(eachKnot.getDataByKey("ID"));
                functionArray[i].setName(eachKnot.getDataByKey("NAME"));
                functionArray[i].setType(Integer.valueOf(eachKnot.getDataByKey("TYPE")));
                functionArray[i].setComplexity(Integer.valueOf(eachKnot.getDataByKey("COMPLEXITY")));
                i++;
            }
        }
    }

    public void safeFunctionsToCore(){
        DataKnot funktionen = new DataKnot("root");

        for (Function eachFunction : functionArray) {
            DataKnot eachKnot = funktionen.addChild("Function");
            eachKnot.addData("ID", eachFunction.getID());
            eachKnot.addData("NAME", eachFunction.getName());
            eachKnot.addData("TYPE", "" + eachFunction.getType());
            eachKnot.addData("COMPLEXITY", "" + eachFunction.getComplexity());
        }

        _pCore.saveData(funktionen, "FunktionenFunctionPoint.dat");
    }

    @Override
    public void optimize(Core pCore) {
        // greife auf loadData() zu, diese speichert die Berechnung in das functionArray
        // Optimierung verändert nur die 14 Einflussfaktoren, sodass das tatsächliche Ergebnis auch wirklich mit der Berechnung übereinstimmt
        // Speichert die Einflussfaktoren ab

        InfluenceFactors influenceFactors = new InfluenceFactors(pCore);

        int summeKat = calcSumme();        // liefert Summe der einzelnen Kategorien
        int calcLoCLoC = 0; //= calcLoC();        // liefert berechnete int Summe an LoC
        int tmpLoC = 0;
        int realLoC = 0;
        double bewEinfluss;
        double facEinfluss = 0;
        int summeEinfluss = 0;

        tmpLoC = realLoC/53;
        bewEinfluss = tmpLoC/summeKat;
        facEinfluss = (influenceFactors.getSum()/100) + 0.65;



    }



    public void incCurrIndex(){
        if(currentIndex<fAnzahl-1){
            currentIndex++;
        }
    }

    public void decCurrIndex(){
        if(currentIndex>0){
            currentIndex--;
        }

    }

    public void openFrame(){
        JFrame checkFrame = new JFrame();

        checkFrame.setContentPane(theFunctionInput.getPanel());
        checkFrame.setVisible(true);
    }

    public void loadFunctionGUI(){
        Function tmpFunction=functionArray[currentIndex];
        String id=tmpFunction.getID();
        String name=tmpFunction.getName();
        int complexity = tmpFunction.getComplexity();
        int type = tmpFunction.getType();

        theFunctionInput.setID(id);
        theFunctionInput.setName(name);
        theFunctionInput.setComplexity(complexity);
        theFunctionInput.setType(type);

    }

    public void safeFunctionFromFunctionGUI(){
        Function tmpFunction=functionArray[currentIndex];
        int type=theFunctionInput.getType();
        int complexity = theFunctionInput.getComplexity();

        tmpFunction.setType(type);
        tmpFunction.setComplexity(complexity);

        safeFunctionsToCore();
    }

    public int calcSumme(){
        int summe=0;
        for (int i=0; i<fAnzahl; i++) {
            summe=summe+functionArray[i].getWeight();
        }
        System.out.println("Summe: " + summe);
        return summe;
    }


}
