import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

/**
 * Created by Pascal_Betz on 18.06.15.
 */
public class optimizeWindow {
    private JButton jetztOptimierenButton;
    private JButton abbrechenButton;
    private JTextField realLoC;
    private FunctionPoint theFunctionPoint;


    public optimizeWindow(FunctionPoint pFunctionPoint) {
        theFunctionPoint = pFunctionPoint;

        jetztOptimierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              //  theFunctionPoint.optimize();

            }
        });
        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        realLoC.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
            }
        });
    }
}
