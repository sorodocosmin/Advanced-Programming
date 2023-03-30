package org.example.Compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JButton exitButton = new JButton("Exit");
    private JButton saveButton = new JButton("Load");
    private JButton loadButton = new JButton("Save");
    private JButton resetButton = new JButton("Reset");

    public ControlPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){

        setLayout(new GridLayout(2,3));

        add(this.saveButton);
        add(this.loadButton);
        add(this.resetButton);
        add(this.exitButton);

        exitButton.addActionListener(this::exitGame);
    }
    private void exitGame(ActionEvent e){
        frame.dispose();
    }

}
