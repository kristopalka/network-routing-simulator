package gui;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import javax.swing.JComponent;

public class Line extends JComponent {
    
    private Point start;
    private Point end;
    private Color startColor;
    private Color endColor;
    
    public int routerStartID;
    public int routerEndID;
    public String socketStartID;
    public String socketEndID;
    
    private int lineID;
    
    public Line(String startColor, String endColor, int routerStartID, int routerEndID) {
        this.start = MainFrame.INSTANCE.screenMap.get(routerStartID).getLocation();
        this.end = MainFrame.INSTANCE.screenMap.get(routerEndID).getLocation();
        this.startColor = this.getColorByString(startColor);
        this.endColor = this.getColorByString(endColor);
        this.routerStartID = routerStartID;
        this.routerEndID = routerEndID;
        this.socketStartID = startColor;
        this.socketEndID = endColor;
        Random r = new Random();
        this.lineID = (routerStartID + (int) r.nextInt(1000))*3 + routerEndID;
    }
    
    public void regnerate() {
        this.start = MainFrame.INSTANCE.screenMap.get(routerStartID).getLocation();
        this.end = MainFrame.INSTANCE.screenMap.get(routerEndID).getLocation();
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
    
    public int getLineID() {
        return lineID;
    }
    
    public Color getColorByString(String colour) {
        return switch (colour) {
            case "red" -> Color.red;
            case "green" -> Color.green;
            case "blue" -> Color.blue;
            default -> Color.yellow;
        };
    }
}
