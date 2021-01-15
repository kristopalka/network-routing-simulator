//package gui;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.RenderingHints;
//import java.awt.Stroke;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.geom.Line2D;
//import javax.swing.JComponent;
//
//public class Path extends JComponent {
//
//    // stroke of the line
//    private Stroke spessore =  new BasicStroke(SPESSORE);
//
//    private Point start, end;
//
//    public Path(int x, int y) {
//            super();
//
//            this.start = new Point(x, y);
//            this.end = new Point(x, y);
//
//            // this was only for test...
//            this.addMouseListener(new MouseListener() {
//                @Override
//                public void mouseClicked(MouseEvent arg0) {
//                    System.out.println("CLICK!");
//                }
//
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void mouseReleased(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void mouseEntered(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//
//                @Override
//                public void mouseExited(MouseEvent e) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
//            });
//    }
//
//    // drawing function   
//    @Override
//    protected void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//
//        Graphics2D antiAlias = (Graphics2D) g;
//        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        antiAlias.setColor(Color.red);
//
//        Line2D line = new Line2D.Double(coordinates[0], coordinates[1]);
//
//        antiAlias.draw(line);
//    }
//
//    // translate coordinates from superior jpanel to this object
//    private Point[] updateCoordinates() {
//
//        Point[] output = new Point[2];
//
//        Point p = new Point(start);
//        Point a = new Point(end);
//
//        double o_x = this.getBounds().getCenterX();
//        double o_y = this.getBounds().getCenterY();
//        Point origin = new Point ((int)o_x, (int)o_y);
//
//        output[0] = calculateCoordinates(p, origin);
//        output[1] = calculateCoordinates(a, origin);
//
//        return output;
//    }
//
//    private Point calculateCoordinates(Point p, Point origin) {
//
//            double new_x = p.x - origin.x;
//            double new_y = p.y - origin.y;
//
//            return new Point((int)new_x, (int)new_y);
//    }
//}