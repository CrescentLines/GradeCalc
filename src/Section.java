import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by lazershockya on 11/17/2015.
 */
public class Section extends JPanel {
    private String name;
    private JButton plus;
    private JTextField weight = new JTextField();
    private ArrayList<JTextField> grades = new ArrayList<JTextField>();
    private JPanel centerSection;

    public Section(String s){
        this.name = s;
        JPanel topBar = new JPanel(new GridLayout(2,2));
        this.setLayout(new BorderLayout());
        topBar.add(new JLabel(this.name));
        this.plus = new JButton("+");
        this.plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAssign();
            }
        });
        topBar.add(plus);
        topBar.add(new JLabel("Weight"));
        topBar.add(weight);
        this.add(topBar, BorderLayout.NORTH);
        centerSection = new JPanel();
        centerSection.setLayout(new BoxLayout(centerSection,BoxLayout.Y_AXIS));
        this.add(centerSection,BorderLayout.CENTER);
    }
    private void addAssign(){
        JPanel row = new JPanel(new GridLayout(1,2));
        row.add(new JLabel(String.valueOf(grades.size())));
        grades.add(new JTextField());
        row.add(grades.get(grades.size()-1));
        centerSection.add(row, BorderLayout.CENTER);
        Window.packMod();
    }

    public String getName(){
        return name;
    }
    public double getWeight(){
        double dominoes = 0.0;
        try{
            dominoes = Double.parseDouble(weight.getText());
        } catch(NumberFormatException ex){}
        return dominoes;
    }

    public double getWeightedGrade(){
        int additiveScore = 0;
        for(JTextField tf : grades){
            try{
                additiveScore+=Double.parseDouble(tf.getText());
            } catch(NumberFormatException ex){}
        }
        additiveScore/=grades.size();
        additiveScore*=getWeight()/100;
        System.out.println(this.name+": "+String.valueOf(additiveScore));
        return additiveScore;
    }
}
