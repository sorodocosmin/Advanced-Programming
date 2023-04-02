package org.example.Compulsory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private JLabel dotsLabel, probabilityLabel;
    private JSpinner dotsSpinner;
    private JComboBox<Integer> probabilityCombo;

    public JSpinner getDotsSpinner() {
        return dotsSpinner;
    }

    public JComboBox<Integer> getProbabilityCombo() {
        return probabilityCombo;
    }

    private JButton createButton;
    public ConfigPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        //create the label and the spineer
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6,3,100,1));

        probabilityLabel = new JLabel("Probability for existing an edge between 2 nodes (? %) : ");
        //probabilitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);


        probabilityCombo = new JComboBox<>(new Integer[] {25,50,75,100});
        probabilityCombo.setSelectedItem(100);


        this.createButton = new JButton("Create Graph");
        createButton.addActionListener(this::onGenerateGraph);

        add(dotsLabel);
        add(dotsSpinner);

        add(probabilityLabel);
        add(probabilityCombo);

        add(createButton);
    }
    private void onGenerateGraph(ActionEvent e){
        //this.frame.getCanvas().setShouldRepaint(true);
        this.frame.getCanvas().createBoard();
        //this.frame.getCanvas().setShouldRepaint(false);
    }

}
