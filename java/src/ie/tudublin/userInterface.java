package ie.tudublin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class userInterface implements ActionListener {
    private static int clicks = 0;
    private JLabel label = new JLabel("Start!");
    private JFrame frame = new JFrame();
    private Visual visual = new Visual();

    public userInterface() {

        // the clickable button
        JButton buttonStart = new JButton("Start!");
        JButton buttonExit = new JButton("Exit!");
        buttonStart.addActionListener(this);
        buttonExit.addActionListener(this);
        clicks = this.clicks;

        // the panel with the button and text
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(buttonStart);
        panel.add(buttonExit);
        panel.add(label);

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



    // create one Frame
 
}

