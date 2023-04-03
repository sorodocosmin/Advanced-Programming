package org.example.Compulsory;

import org.example.Homework.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.sql.SQLOutput;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JButton exitButton = new JButton("Exit");
    private JButton saveButton = new JButton("Save Game");
    private JButton savePngButton = new JButton("Save Game As Image");
    private JButton loadButton = new JButton("Load Previous Saved Game");
    private JButton resetButton = new JButton("Reset");

    public ControlPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){

        //setLayout(new GridLayout(3,2));
        setLayout(new FlowLayout());
        add(this.savePngButton);
        add(this.saveButton);
        add(this.loadButton);
        add(this.resetButton);
        add(this.exitButton);

        savePngButton.addActionListener(this::saveAsPngGame);

        saveButton.addActionListener(this::serializeGame);

        loadButton.addActionListener(this::deserializeGame);

        resetButton.addActionListener(this::resetGame);

        exitButton.addActionListener(this::exitGame);
    }

    private void exitGame(ActionEvent e){
        frame.dispose();
    }

    private void saveAsPngGame (ActionEvent e){
        try {
            ImageIO.write(this.frame.getCanvas().getImage(), "png", new File("graph.png"));
            System.out.println("The image saved successfully");
        }
        catch (IOException ex) {
            System.out.println("The image couldn't save );");
        }
    }
    private void resetGame(ActionEvent e){
        this.frame.getCanvas().getGame().resetGame();
        this.frame.getCanvas().setGame(this.frame.getCanvas().getGame());
    }

    private void serializeGame(ActionEvent e) {
        //we'll use for serialization XML format
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("game.xml")));
            encoder.writeObject(this.frame.getCanvas().getGame());
            encoder.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Couldn't serialize object");
        }

    }
    private void deserializeGame(ActionEvent e){
        try{
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("game.xml")));
            Game game = (Game) decoder.readObject();
            this.frame.getCanvas().setGame(game);
            System.out.println("The game was : " + game);
        }
        catch (FileNotFoundException ex){
            System.out.println("Couldn't deserialize object");
        }
    }
}
