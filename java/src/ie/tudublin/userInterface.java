package ie.tudublin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class userInterface implements ActionListener {
    private static int clicks = 0;

    private JLabel qLabel = new JLabel("Press Q for Quantum Mode");
    private JLabel sLabel = new JLabel("Press S For Solid Mode (Default");
    private JLabel oLabel = new JLabel("Press D For OneDimensional Mode");
    private JFrame frame = new JFrame();
    private Visual visual = new Visual();
    JButton buttonStart = new JButton("Start!");
    JButton buttonExit = new JButton("Exit!");


    public JButton getButtonStart() {
        return this.buttonStart;
    }

    public void setButtonStart(JButton buttonStart) {
        this.buttonStart = buttonStart;
    }

    public JButton getButtonExit() {
        return this.buttonExit;
    }

    public void setButtonExit(JButton buttonExit) {
        this.buttonExit = buttonExit;
    }
  

    public userInterface() {

        // the clickable button
       
        buttonStart.addActionListener(this);
        buttonExit.addActionListener(this);


        // the panel with the button and text
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(buttonStart);
        panel.add(buttonExit);
        panel.add(qLabel);
        panel.add(sLabel);
        panel.add(oLabel);

        setUpButtonListeners(buttonStart, buttonExit);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public int getClicks() {
    return clicks;
 }
    public void setUpButtonListeners(JButton start, JButton exit){
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clicks++;
                
            }
        };
        ActionListener buttonListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               System.exit(0);
            }
        };

        start.addActionListener(buttonListener);
        exit.addActionListener(buttonListener2);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }



 
}

