package gui;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JComponent;

public class Line extends JComponent {
    
    private Point start;
    private Point end;
    private Color startColor;
    private Color endColor;
    
    public Line(Point start, Color startColor, Point end, Color endColor) {
        
        this.start = start;
        this.end = end;
        this.startColor = startColor;
        this.endColor = endColor;
        
    }
    
    public int hashCode() {
        return (int) ((int) 53*(53*7 + this.start.getX() + this.end.getY()) + this.start.getY() + this.end.getY());
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Color getStartColor() {
        return startColor;
    }

    public Color getEndColor() {
        return endColor;
    }
    
}
