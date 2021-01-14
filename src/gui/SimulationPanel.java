package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.HashSet;
import javax.swing.JPanel;

public class SimulationPanel extends JPanel {
    
    private HashSet<Line> lines;
    
    public SimulationPanel() {
        super();
        lines = new HashSet<>();
        lines.add(new Line(new Point(20, 30), new Color(0.4f, 0.5f, 0.6f), new Point(200, 400), new Color(0.9f, 0.8f, 0.7f)));
    }
    
    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        for(Line line : lines) {
            g2d.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(), (int) line.getEnd().getX(), (int) line.getEnd().getY());
        }
 
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLines(g);
    }
    
}
