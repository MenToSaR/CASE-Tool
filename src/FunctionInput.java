import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pascal-L on 11.05.2015.
 *
 *
 * Fenster zum Bewerten der Produktfunktionen mit Typ (Abfrage, Eingabe, Ausgabe, Interner & Externer Datenbestand) und Komplexität (einfach,mittel,komplex)
 */


public class FunctionInput {

    private JPanel panel1;

    private JComboBox comboBox1;                // comboBox fuer Auswahl der Typen

    private JRadioButton einfachRadioButton;    // Radiobutton zur Auswahl der Komplexitaet
    private JRadioButton mittelRadioButton;
    private JRadioButton komplexRadioButton;

    private JButton zurueckButton;              // Buttons zum durch die verschiedenen Produktfunktionen navigieren
    private JButton weiterButton;
    private JButton fertigButton;

    private JLabel lblName;                     // Namens-Label
    private JLabel lblID;                       // ID-Label


    private FunctionPoint theFunctionPoint;     // Verweis auf Erzeuger

    public FunctionInput(FunctionPoint pFunctionPoint) {

        // Verweis auf FunctionPoint Klasse, die den FunctionInput erzeugt hat
        theFunctionPoint=pFunctionPoint;

        // Fülle Combobox mit Typen
        comboBox1.addItem("Abfrage");
        comboBox1.addItem("Eingabe");
        comboBox1.addItem("Ausgabe");
        comboBox1.addItem("Interner Datenbestand");
        comboBox1.addItem("Externer Datenbestand");

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theFunctionPoint.safeFunctionFromFunctionGUI(); // sichere Eingaben
                theFunctionPoint.decCurrIndex();                // dekrementiere den Index um die vorherige Funktion zu laden
                theFunctionPoint.loadFunctionGUI();             // lade Funktion an der Stelle des aktuellen Index (currentIndex in FunctionPoint)
            }
        });
        weiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theFunctionPoint.safeFunctionFromFunctionGUI(); // sichere Eingaben
                theFunctionPoint.incCurrIndex();                // inkrementiere den Index um die naechste Funktion zu laden
                theFunctionPoint.loadFunctionGUI();             // lade Funktion an der Stelle des aktuellen Index (currentIndex in FunctionPoint)
            }
        });


        fertigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theFunctionPoint.safeFunctionFromFunctionGUI();         // sichere Eingaben
                theFunctionPoint.openInfluenceFactorInput();            // oeffne Fenster um die Influenzfaktoren einzugeben bzw. zu veraendern
                theFunctionPoint.getFunctionFrame().setVisible(false);  // FunctionInput Fenster nicht mehr anzeigen

            }
        });
    }



    public int getType(){ // liefert Index des ausgewaehlten Index in der ComboBox
        System.out.println("getType:"+comboBox1.getSelectedIndex());
        return comboBox1.getSelectedIndex();
    }

    public void setType(int type){ // Funktion um die ComboBox auf einen bestimmten Index (bzw. Typ) zu setzen, z.B. einen DEFAULT-Typ
        System.out.println("setType:"+type);
        comboBox1.setSelectedIndex(type);
    }

    public int getComplexity(){ // gibt Komplexitaet einer Produktfunktion zueruck (einfach = 1, mittel = 2, schwer = 3)
        if(einfachRadioButton.isSelected()){
            return 1;
        }else if(mittelRadioButton.isSelected()){
            return 2;
        }else{
            return 3;
        }
    }

    public void setComplexity(int complexity){ // Funktion um die Komplexitaet im RadioButton im Fenster zu setzen
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
    } //liefert Panel zurueck

    public void setID(String pID){ // setzt ID-Label mit Text
        lblID.setText(pID);
    }

    public void setName(String pName){
        lblName.setText(pName);
    }   // setzt Namens-Label mit Text
}
