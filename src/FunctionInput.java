import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pascal-L on 11.05.2015.
 */
public class FunctionInput {
    private JPanel panel1;
    private JComboBox comboBox1;
    private JRadioButton einfachRadioButton;
    private JRadioButton mittelRadioButton;
    private JRadioButton komplexRadioButton;
    private JButton zurückButton;
    private JButton weiterButton;
    private JLabel lblName;
    private JLabel lblID;
    private JButton fertigButton;

    private FunctionPoint theFunctionPoint;

    public FunctionInput(FunctionPoint pFunctionPoint) {

        theFunctionPoint=pFunctionPoint;

        comboBox1.addItem("Abfrage");
        comboBox1.addItem("Eingabe");
        comboBox1.addItem("Ausgabe");
        comboBox1.addItem("Interner Datenbestand");
        comboBox1.addItem("Externer Datenbestand");

        zurückButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theFunctionPoint.safe();
                theFunctionPoint.decCurrIndex();
                theFunctionPoint.load();
            }
        });
        weiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theFunctionPoint.safe();
                theFunctionPoint.incCurrIndex();
                theFunctionPoint.load();
            }
        });


        fertigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theFunctionPoint.safe();
                theFunctionPoint.calcSumme();
            }
        });
    }



    public int getType(){

        System.out.println("getType:"+comboBox1.getSelectedIndex());
        return comboBox1.getSelectedIndex();
    }

    public void setType(int type){
        System.out.println("setType:"+type);
        comboBox1.setSelectedIndex(type);
    }

    public int getComplexity(){
        if(einfachRadioButton.isSelected()){
            return 1;
        }else if(mittelRadioButton.isSelected()){
            return 2;
        }else{
            return 3;
        }
    }

    public void setComplexity(int complexity){
        switch(complexity){
            case 1: einfachRadioButton.setSelected(true);
                break;
            case 2: mittelRadioButton.setSelected(true);
                break;
            case 3: komplexRadioButton.setSelected(true);
                break;
            default: break;
        }

    }

    public JPanel getPanel(){
        return panel1;
    }

    public void setID(String pID){
        lblID.setText(pID);
    }

    public void setName(String pName){
        lblName.setText(pName);
    }
}
