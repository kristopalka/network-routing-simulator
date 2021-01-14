package gui;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.JComponent;

public class Line extends JComponent {
    
    private Point start;
    private Point end;
    private Color startColor;
    private Color endColor;
    
    public int routerStartID;
    public int routerEndID;
    
    public Line(Color startColor, Color endColor, int routerStartID, int routerEndID, HashMap<Integer, JDeviceLabel> screenMap) {
        this.start = screenMap.get(routerStartID).getLocation();
        this.end = screenMap.get(routerEndID).getLocation();
        this.startColor = startColor;
        this.endColor = endColor;
        this.routerStartID = routerStartID;
        this.routerEndID = routerEndID;
    }
    
    public int hashCode() {
        return (int) ((int) 53*(53*7 + this.start.getX() + this.end.getY()) + this.start.getY() + this.end.getY());
    }
    
    public Point getCenter() {
        return new Point(start.x + (end.x - start.x)/2, start.y + (end.y - start.y)/2);
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
