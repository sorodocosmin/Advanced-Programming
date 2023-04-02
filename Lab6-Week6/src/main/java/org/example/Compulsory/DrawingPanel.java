package org.example.Compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {

    private final MainFrame frame;
    final static int W = 800, H = 500, RADIUS_VERTEX = 10;
    private int nrVertices, probability;
    private int coordX[], coordY[];

    private BufferedImage image;
    private Graphics2D graphics;

    public DrawingPanel(MainFrame frame){
        this.frame = frame;
        createOffScreenImage();
        initPanel();
        createBoard();
        setBackground(Color.gray);
    }

    private void initPanel(){
        setPreferredSize(new Dimension(W,H));
        setBorder(BorderFactory.createEtchedBorder());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                System.out.println("mouse pressed at : " + e.getX() + " - " + e.getY());
                repaint();
            }
        });
    }

    private void createOffScreenImage(){
        this.image = new BufferedImage(W,H,BufferedImage.TYPE_INT_ARGB);
        this.graphics = image.createGraphics();


        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.GRAY);

        graphics.fillRect(0,0,1000,1000);
    }

    final void createBoard(){

        System.out.println("Board created");
        this.nrVertices = (Integer) this.frame.getConfigPanel().getDotsSpinner().getValue();
        this.probability = (Integer) this.frame.getConfigPanel().getProbabilityCombo().getSelectedItem();

        this.createOffScreenImage();

        this.createVertices();
        this.drawLines();
        this.drawVertices();

        repaint();




    }

    private void createVertices(){
        int x0 = W/2 ; int y0 = H/2;
        int radius = H/2 - 10;

        double alpha = 2 * Math.PI / nrVertices; //the angle
        this.coordX = new int[ this.nrVertices];
        this.coordY = new int[ this.nrVertices];

        for( int i = 0; i< this.nrVertices ; ++i) {
            this.coordX[i] = x0 + (int) (radius * Math.cos(alpha * i));
            this.coordY[i] = y0 + (int) (radius * Math.sin(alpha * i));


        }


    }

    private void drawVertices(){
        this.graphics.setColor(Color.BLACK);
        for(int i = 0; i < this.nrVertices ; ++i){
            this.graphics.fillOval(this.coordX[i] - RADIUS_VERTEX, this.coordY[i] - RADIUS_VERTEX, 2 * RADIUS_VERTEX, 2 * RADIUS_VERTEX);
        }
        this.graphics.setColor(Color.GRAY);
    }

    private void drawLines(){
        this.graphics.setColor(Color.BLACK);
        for (int i = 0; i < this.nrVertices - 1; ++i) {
            for (int j = i + 1; j < this.nrVertices; j++) {
                int randomProb = (int) (Math.random() * 100);
                if (randomProb <= probability) {//create edge
                    this.graphics.drawLine(coordX[i], coordY[i], coordX[j], coordY[j]);
                }
            }
        }
        this.graphics.setColor(Color.GRAY);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("Painted");
        g.drawImage(this.image,0,0,this);
    }

    //    private MainFrame frame;
//    final static int W = 900 , H = 500;
//
//    private boolean shouldRepaint = false;
//
//    public void setFrame(MainFrame frame) {
//        this.frame = frame;
//    }
//
//    public void setShouldRepaint( boolean shouldRepaint){
//        this.shouldRepaint = shouldRepaint;
//    }
//
//    public DrawingPanel(MainFrame frame){
//        this.frame = frame;
//        initPanel();
//    }
//    private void initPanel(){
//        setPreferredSize(new Dimension(W,H));
//        setBorder(BorderFactory.createEtchedBorder());
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if ( this.shouldRepaint) {
//            Graphics2D g2d = (Graphics2D) g;
//
//
//            // Set up the coordinate system
//            Color color = new Color(170, 0, 0, 0);
//
//            int radius = 200;
//            int centerX = getWidth() / 2;
//            int centerY = getHeight() / 2;
//
//            //g2d.fillOval(centerX, centerY, 15, 15);
//
//
//            // Draw the big circle
//            //g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
//
//            // Set the number of small circles
//            int n = (int) this.frame.getConfigPanel().getDotsSpinner().getValue();
//            System.out.println(" n este : " + n);
//
//            int probability = (int) this.frame.getConfigPanel().getProbabilityCombo().getSelectedItem();
//
//            System.out.println(" probability : " + probability);
//            // Draw the small circles
//            int[] coordX = new int[n];
//            int[] coordY = new int[n];
//            for (int i = 0; i < n; i++) {
//                double angle = 2 * Math.PI * i / n;
//                int x = (int) (centerX + radius * Math.cos(angle));
//                coordX[i] = x;
//                int y = (int) (centerY + radius * Math.sin(angle));
//                coordY[i] = y;
//                int r = 10;
//                g.fillOval(x - r, y - r, 2 * r, 2 * r);
//            }
//
//            for (int i = 0; i < n - 1; ++i) {
//                for (int j = i + 1; j < n; j++) {
//                    int randomProb = (int) (Math.random() * 100);
//                    if (randomProb <= probability) {//create edge
//                        g.drawLine(coordX[i], coordY[i], coordX[j], coordY[j]);
//                    }
//                }
//            }
//        }
//
//
//    }
}