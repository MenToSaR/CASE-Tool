import de.Core;
import de.calculator.Calcer;
import de.database.DataKnot;
import de.window.MessageBoxFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/**
 * Created by Marcel on 30.04.2015 in CASE.
 */
public class FunctionPoint extends Calcer {

    private Function functionArray[];
    private InfluenceFactors influenceFactors;
    private int fAnzahl=0; // Anzahl der Funktionen die von pKnot ?bergeben werden
    private int currentIndex=0;
    private Core _pCore;
    private JFrame _functionFrame;
    private JFrame _influenceFrame;

    private FunctionInput theFunctionInput;
    private InfluenceFactorInput theInfluenceFactorInput;

    // setSize(this.getInsets().left + getInsets().right + getSize().width, getInsets().top + getInsets().bottom + getSize().height);
    // setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getSize().getWidth() / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getSize().getHeight() / 2));

    @Override
    public void calculate(Core pCore, DataKnot pKnot) {
        _pCore=pCore;
        influenceFactors=new InfluenceFactors(_pCore);
        pKnot.printKnot();
        ArrayList<DataKnot> productFunctions = pKnot.getFirstChildByTag("data").getFirstChildByTag("productfunction").getChildrenByTag("element");
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
        theInfluenceFactorInput=new InfluenceFactorInput(this);
        loadFunctionGUI();
        openFunctionInput();

    }

    public void loadFunctionsFromCore(){
        DataKnot theKnot = _pCore.loadData("FunktionenFunctionPoint.dat");

        if (theKnot != null) {
            ArrayList<DataKnot> productFunctions = theKnot.getChildren();
            fAnzahl=productFunctions.size();
            functionArray=new Function[fAnzahl];
            int i=0;
            for (DataKnot eachKnot : theKnot.getChildren()) {
                functionArray[i] = new Function();
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

        _pCore = pCore;
        // greife auf loadData() zu, diese speichert die Berechnung in das functionArray
        // Optimierung ver?ndert nur die 14 Einflussfaktoren, sodass das tats?chliche Ergebnis auch wirklich mit der Berechnung ?bereinstimmt
        // Speichert die Einflussfaktoren ab

       // InfluenceFactors influenceFactors = new InfluenceFactors(_pCore);

        System.out.println("Test");

        optimizeWindow oWindow = new optimizeWindow(this);

       influenceFactors = new InfluenceFactors(pCore);
        loadFunctionsFromCore();

        String theInput = MessageBoxFactory.createTextMessageBox("Optimierung", "Bitte geben sie die tatsaechlichen Lines of Code ein:");
        if (theInput.equals("")) {
            MessageBoxFactory.createMessageBox("Meldung", "Keine Optimierung vorgenommen");
            return;
        }
        double realLoC;
        try {
            realLoC = Double.valueOf(theInput);
            if(realLoC <= 0){
                MessageBoxFactory.createMessageBox("Fehler", "Zu kleine Eingabe");
                return;
            }
        } catch (Exception ex) {
            MessageBoxFactory.createMessageBox("Fehler", "Falsche Eingabe");
            return;
        }


        int summeKat = calcSumme();        // liefert Summe der einzelnen Kategorien E1
        double realEinflussBew = 0;           // tatsaechliche Einflussbewertung E3
        double realInflFac = 0;             // Wert der Einflussfaktoren E2

        double oldInflFac = Double.parseDouble(pCore.loadData("Ergebnis.dat").getValue());              // alter Wert der Einflussfaktoren E2-old
        double EinflussBewDiff = 0;


        if(realLoC != -1) {

            oldInflFac = oldInflFac/(53*summeKat);      //jetzt E3

            oldInflFac = (oldInflFac-0.65) * 100;       // jetzt E2

            realEinflussBew = realLoC / (53 * summeKat);
            realInflFac = (realEinflussBew - 0.65) * 100;          //real-EInflussfaktoren

            oldInflFac = influenceFactors.getSum();

            EinflussBewDiff = oldInflFac - realInflFac;     // positiv, dann Einflussfaktoren zu hoch

            if (EinflussBewDiff > 0) {  // Einflussfaktoren verringern

                int temp = 0;

                for (int i = 0; i < 14 && EinflussBewDiff < 1; i++) {
                    System.out.println("Verringern");

                    temp = influenceFactors.getInfluenceFactor(i);
                    if (temp > 1) {
                        influenceFactors.setInfluenceFactor(i, temp - 1);
                        EinflussBewDiff--;
                    }
                }

                int i = 0;
                while (EinflussBewDiff >= 1) {

                    System.out.println("Verringern");

                    temp = influenceFactors.getInfluenceFactor(i);
                    if (temp > 1) {
                        influenceFactors.setInfluenceFactor(i, temp - 1);
                        EinflussBewDiff--;
                    }

                    if (i == 13) i = 0;
                    else i++;

                    if (influenceFactors.getSum() <= 14) break; // alle Einflussfaktoren auf 1, minimaler Wert
                }
            } else if (EinflussBewDiff < 0) {  // Einflussfaktoren vergroessern

                int temp = 0;
                int i = 0;
                while (EinflussBewDiff <= -1) {

                    System.out.println("Vergroessern");
                    temp = influenceFactors.getInfluenceFactor(i);
                    influenceFactors.setInfluenceFactor(i, temp + 1);
                    System.out.println(EinflussBewDiff);
                    EinflussBewDiff++;

                    if (i == 13) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }

            influenceFactors.save();
            System.out.println("Optimierung abgeschlossen!/n");
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

    public void openFunctionInput(){
        JFrame checkFrame = new JFrame();
        _functionFrame=checkFrame;
        _functionFrame.setContentPane(theFunctionInput.getPanel());
        _functionFrame.pack();
        _functionFrame.setVisible(true);
        _functionFrame.setSize(_functionFrame.getInsets().left + _functionFrame.getInsets().right + _functionFrame.getSize().width, _functionFrame.getInsets().top + _functionFrame.getInsets().bottom + _functionFrame.getSize().height);
        _functionFrame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - _functionFrame.getSize().getWidth() / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - _functionFrame.getSize().getHeight() / 2));

    }

    public JFrame getFunctionFrame(){
        return _functionFrame;
    }

    public void openInfluenceFactorInput(){
        JFrame checkFrame = new JFrame();
        _influenceFrame=checkFrame;
        checkFrame.setContentPane(theInfluenceFactorInput.getPanel());
        _influenceFrame.pack();
        _influenceFrame.setVisible(true);
        _influenceFrame.setSize(_influenceFrame.getInsets().left + _influenceFrame.getInsets().right + _influenceFrame.getSize().width, _influenceFrame.getInsets().top + _influenceFrame.getInsets().bottom + _influenceFrame.getSize().height);
        _influenceFrame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - _influenceFrame.getSize().getWidth() / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - _influenceFrame.getSize().getHeight() / 2));

    }

    public JFrame getInfluenceFrame(){
        return _influenceFrame;
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

        return summe;
    }

    public void loadFunctionArray(){


    }

    public InfluenceFactors getInfluenceFactors(){
        return influenceFactors;
    }

    public int calcLoC(){
        double einflussBewertung = influenceFactors.getEinflussbewertung();
        int summe = calcSumme();
        double loc = (double)summe*einflussBewertung*53;
        int intLoc = (int)loc;
        MessageBoxFactory.createMessageBox("Aufwandsschaetzung - Ergebnis:", "Lines of Code: " + intLoc);
        DataKnot ergebnisKnot = new DataKnot("ErgebnisLinesofCode");
        ergebnisKnot.setValue("" + loc);
        _pCore.saveData(ergebnisKnot, "Ergebnis.dat");
        return intLoc;
    }


}
