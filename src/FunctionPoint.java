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

    private FunctionInput theFunctionInput;

    @Override
    public void calculate(Core pCore, DataKnot pKnot) {
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
        load();
        openFrame();


        pCore.saveData(new DataKnot("hallo"), "FunktionenFunctionPoint.txt");

        DataKnot theKnot = pCore.loadData("FunktionenFunctionPoint.txt");
        if (theKnot != null) {
            // TODO hier scheiße einfügen
        }
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

    public void load(){
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

    public void safe(){
        Function tmpFunction=functionArray[currentIndex];
        int type=theFunctionInput.getType();
        int complexity = theFunctionInput.getComplexity();

        tmpFunction.setType(type);
        tmpFunction.setComplexity(complexity);
    }
}
