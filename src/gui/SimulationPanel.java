package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import javax.swing.JPanel;

public class SimulationPanel extends JPanel {
    
    public HashMap<Integer, Line> lines;
    private static int rs = 40;
    
    public boolean antiAlias = true;
    
    public SimulationPanel() {
        super();
        lines = new HashMap<>();
        this.setDoubleBuffered(true);
    }
    
    public void putLine(Line l) {
        this.lines.put(l.getLineID(), l);
    }
    
    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if(this.antiAlias) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        
        for(Line line : lines.values()) {
            
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
        this.lines = new HashMap<>();
    }
    
    public void removeLineByRouterID(int rID) {
        HashMap<Integer, Line> newMap = new HashMap<>();
        for(Line line : lines.values()) {
            if(line.routerStartID != rID && line.routerEndID != rID) {
                newMap.put(line.getLineID(), line);
            }
        }
        lines = newMap;
        MainFrame.INSTANCE.refreshMap();
    }
    
    public void removeLineBySocketID(int rID, String socket) {
        HashMap<Integer, Line> newMap = new HashMap<>();
        for(Line line : lines.values()) {
            if(line.routerStartID != rID && line.routerEndID != rID) {
                newMap.put(line.getLineID(), line);
            }
            else {
                if(!line.socketStartID.equals(socket) && !line.socketEndID.equals(socket)) {
                    newMap.put(line.getLineID(), line);
                }
            }
        }
        lines = newMap;
        MainFrame.INSTANCE.refreshMap();
    }

    public void regenerateLinks() {
        for(Line line : lines.values()) {
            line.regnerate();
        }
    }

    public void removeLineByLineID(int linkID) {
        HashMap<Integer, Line> newMap = new HashMap<>();
        for(Line line : lines.values()) {
            if(line.getLineID() != linkID) {
                newMap.put(line.getLineID(), line);
            }
        }
        lines = newMap;
        MainFrame.INSTANCE.refreshMap();
    }
}
