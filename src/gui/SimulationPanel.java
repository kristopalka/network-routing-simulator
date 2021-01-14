package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashSet;
import javax.swing.JPanel;

public class SimulationPanel extends JPanel {
    
    private HashSet<Line> lines;
    
    public SimulationPanel() {
        super();
        lines = new HashSet<>();
        //lines.add(new Line(new Point(20, 30), new Color(0.4f, 0.5f, 0.6f), new Point(200, 400), new Color(0.9f, 0.8f, 0.7f)));
    }
    
    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for(Line line : lines) {
            
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(10f));
            g2d.drawLine((int) line.getStart().getX() +50, (int) line.getStart().getY() +50, (int) line.getEnd().getX() +50, (int) line.getEnd().getY() +50);
            
            g2d.setStroke(new BasicStroke(4f));
            
            g2d.setColor(line.getStartColor());
            g2d.drawLine((int) line.getStart().getX() +50, (int) line.getStart().getY() +50, (int) line.getCenter().getX() +50, (int) line.getCenter().getY()+50);
            
            g2d.setColor(line.getEndColor());
            g2d.drawLine((int) line.getCenter().getX()+50, (int) line.getCenter().getY()+50, (int) line.getEnd().getX()+50, (int) line.getEnd().getY()+50);
        }
 
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLines(g);
    }
    
}
