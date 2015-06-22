import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pascal-L on 20.05.2015.
 */
public class InfluenceFactorInput {
    private JTextField t1;
    private JButton startButton;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    private JTextField t7;
    private JTextField t8;
    private JTextField t9;
    private JTextField t10;
    private JTextField t12;
    private JTextField t11;
    private JTextField t13;
    private JTextField t14;

    private JPanel panel1;
    private FunctionPoint theFunctionPoint;
    private InfluenceFactors influenceFactors;

    public InfluenceFactorInput(FunctionPoint pFunctionPoint) {
        theFunctionPoint = pFunctionPoint;
        influenceFactors = theFunctionPoint.getInfluenceFactors();
        influenceFactors.load();

        t1.setText("" + influenceFactors.getInfluenceFactor(0));
        t2.setText(""+influenceFactors.getInfluenceFactor(1));
        t3.setText(""+influenceFactors.getInfluenceFactor(2));
        t4.setText(""+influenceFactors.getInfluenceFactor(3));
        t5.setText(""+influenceFactors.getInfluenceFactor(4));
        t6.setText(""+influenceFactors.getInfluenceFactor(5));
        t7.setText(""+influenceFactors.getInfluenceFactor(6));
        t8.setText(""+influenceFactors.getInfluenceFactor(7));
        t9.setText(""+influenceFactors.getInfluenceFactor(8));
        t10.setText(""+influenceFactors.getInfluenceFactor(9));
        t11.setText(""+influenceFactors.getInfluenceFactor(10));
        t12.setText(""+influenceFactors.getInfluenceFactor(11));
        t13.setText(""+influenceFactors.getInfluenceFactor(12));
        t14.setText(""+influenceFactors.getInfluenceFactor(13));

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                influenceFactors.setInfluenceFactor(0,Integer.valueOf(t1.getText()));
                influenceFactors.setInfluenceFactor(1,Integer.valueOf(t2.getText()));
                influenceFactors.setInfluenceFactor(2,Integer.valueOf(t3.getText()));
                influenceFactors.setInfluenceFactor(3,Integer.valueOf(t4.getText()));
                influenceFactors.setInfluenceFactor(4,Integer.valueOf(t5.getText()));
                influenceFactors.setInfluenceFactor(5,Integer.valueOf(t6.getText()));
                influenceFactors.setInfluenceFactor(6,Integer.valueOf(t7.getText()));
                influenceFactors.setInfluenceFactor(7,Integer.valueOf(t8.getText()));
                influenceFactors.setInfluenceFactor(8,Integer.valueOf(t9.getText()));
                influenceFactors.setInfluenceFactor(9,Integer.valueOf(t10.getText()));
                influenceFactors.setInfluenceFactor(10,Integer.valueOf(t11.getText()));
                influenceFactors.setInfluenceFactor(11,Integer.valueOf(t12.getText()));
                influenceFactors.setInfluenceFactor(12,Integer.valueOf(t13.getText()));
                influenceFactors.setInfluenceFactor(13,Integer.valueOf(t14.getText()));

                theFunctionPoint.calcLoC();
                theFunctionPoint.getInfluenceFrame().setVisible(false);
                influenceFactors.save();
            }
        });
    }





    public JPanel getPanel(){
        return panel1;
    }
}
