package org.example.Compulsory;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private MainFrame frame;
    final static int W = 800 , H = 500;

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    public DrawingPanel(MainFrame frame){
        this.frame = frame;
        initPanel();
    }
    private void initPanel(){
        setPreferredSize(new Dimension(W,H));
        setBorder(BorderFactory.createEtchedBorder());
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set up the coordinate system
        Color color = new Color(170, 0, 0, 0);

        int radius = 200;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        //g2d.fillOval(centerX, centerY, 15, 15);


        // Draw the big circle
        //g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Set the number of small circles
        int n =(int) this.frame.getConfigPanel().getDotsSpinner().getValue();
        System.out.println(" n este : " + n);
        int probability = (int) this.frame.getConfigPanel().getProbabilityCombo().getSelectedItem();
        System.out.println(" probability : "+ probability);
        // Draw the small circles
        int []coordX = new int[n];
        int []coordY = new int[n];
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n;
            int x = (int) (centerX + radius * Math.cos(angle));
            coordX[i]=x;
            int y = (int) (centerY + radius * Math.sin(angle));
            coordY[i]=y;
            int r = 10;
            g.fillOval(x - r, y - r, 2 * r, 2 * r);
        }

        for(int i = 0; i < n - 1; ++i){
            for( int j= i+1 ; j < n ; j ++){
                int randomProb = (int) (Math.random() * 100);
                if(randomProb <= probability){//create edge
                    g.drawLine(coordX[i],coordY[i],coordX[j],coordY[j]);
                }
            }
        }


    }
}