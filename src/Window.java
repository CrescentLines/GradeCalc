import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by lazershockya on 11/17/2015.
 */
public class Window {
    static JFrame frame = new JFrame();
    static JScrollPane mainScroll;
    static JPanel mainPanel = new JPanel(new BorderLayout());
    static JPanel centerPanel = new JPanel();
    static ArrayList<Section> sections = new ArrayList<Section>();
    static JMenuBar menuBar = new JMenuBar();
    static JMenu file = new JMenu("File");
    static JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
    static JTextField totalScore = new JTextField("100%");

    public static void main(String args[]){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(600,500));
        JMenuItem addSection = new JMenuItem("Add Section");
        addSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Enter Name of Section");
                sections.add(new Section(s));
                refreshWindow();
            }
        });
        file.add(addSection);
        menuBar.add(file);
        frame.setJMenuBar(menuBar);

        JButton calc = new JButton("Calculate!");
        calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateScore();
            }
        });
        totalScore.setEditable(false);
        bottomBar.add(calc);
        bottomBar.add(totalScore);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomBar, BorderLayout.SOUTH);
        frame.add(mainPanel);
        packMod();
        frame.setVisible(true);
    }
    public static void refreshWindow(){
        centerPanel.add(sections.get(sections.size()-1));
        frame.setPreferredSize(new Dimension());
        packMod();
    }
    public static void calculateScore(){
        double total = 0.0;
        for(Section s : sections){
            total+=s.getWeightedGrade();
        }
        totalScore.setText(String.valueOf(total)+"%");
    }

    public static void packMod(){
        int col = 0;
        int ro = 0;
        if(sections.isEmpty()){
            col = 200;
            ro = 200;
        }
        else {
            col = 100;
            ro = 200;
            for (Section s : sections) {
                col+=s.getWidth()+50;
                int y = s.getHeight()+150;
                if (y > ro) {
                    ro = y;
                }
            }
            if(col<200){
                col = 200;
            }
        }
        frame.setPreferredSize(new Dimension(col, ro));
        frame.pack();
    }
}
