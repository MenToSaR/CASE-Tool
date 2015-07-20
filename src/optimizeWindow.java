import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

/**
 * Created by Pascal_Betz on 18.06.15.
 */

/*
    Fenster zur Optimierung der Aufwandsberechnung. Die tatsaechlichen Lines of Code werden vom Benutzer eignegeben
    und die Einflussfaktoren daraufhin angepasst.
 */

public class optimizeWindow extends JDialog{
    private JButton jetztOptimierenButton;
    private JButton abbrechenButton;
    private JTextField realLoC;
    private JPanel thePanel;
    private FunctionPoint theFunctionPoint;


    public optimizeWindow(FunctionPoint pFunctionPoint) {
        theFunctionPoint = pFunctionPoint;

        setContentPane(thePanel);

        jetztOptimierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              setVisible(false);

            }
        });
        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                realLoC.setText("-1");

            }
        });
        realLoC.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
            }
        });
    }

    public void open(){     // Fenster wird geoeffnet

        this.setModal(true);
        setSize(this.getInsets().left + getInsets().right + 400, getInsets().top + getInsets().bottom + 300);
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - getSize().getWidth() / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - getSize().getHeight() / 2));
        this.setVisible(true);
    }

    public double getValue(){

        double result = 0;

        try{
            Double.parseDouble(realLoC.getText());
        }

        catch(NumberFormatException e) {
            result = -1;
        }

        return result;
    }
}
