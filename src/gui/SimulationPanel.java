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
    private static int rs = 40;
    
    public SimulationPanel() {
        super();
        lines = new HashSet<>();
    }
    
    public void putLine(Line l) {
        this.lines.add(l);
    }
    
    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for(Line line : lines) {
            
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(10f));
            g2d.drawLine((int) line.getStart().getX() +rs, (int) line.getStart().getY() +rs, (int) line.getEnd().getX() +rs, (int) line.getEnd().getY() +rs);
            
            g2d.setStroke(new BasicStroke(4f));
            
            g2d.setColor(line.getStartColor());
            g2d.drawLine((int) line.getStart().getX() +rs, (int) line.getStart().getY() +rs, (int) line.getCenter().getX() +rs, (int) line.getCenter().getY()+rs);
            
            g2d.setColor(line.getEndColor());
            g2d.drawLine((int) line.getCenter().getX()+rs, (int) line.getCenter().getY()+rs, (int) line.getEnd().getX()+rs, (int) line.getEnd().getY()+rs);
        }
 
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLines(g);
    }

    public void removeAllComponents() {
        this.lines = new HashSet<>();
    }
    
    public void removeLineByRouterID(int rID) {
        for(Line line : lines) {
            if(line.routerStartID == rID || line.routerEndID == rID) {
                lines.remove(line);
            }
        }
    }

    public void regenerateLinks() {
        for(Line line : lines) {
            line.regnerate();
        }
    }
}
