//package gui;
//
//import java.awt.BasicStroke;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.RenderingHints;
//import java.awt.Stroke;
//import java.awt.geom.Line2D;
//import javax.swing.JComponent;
//
//public class Path extends JComponent {
//
//    // stroke of the line
//    private Stroke spessore =  new BasicStroke(SPESSORE);
//
//    private double x, y, x_2, y_2;
//
//    public Path(double x, double y) {
//            super();
//
//            this.x = x;
//            this.y = y;
//
//            this.x_2 = x;
//            this.y_2 = y;
//
//            updateBoundsAndSize(zoom);
//
//            // this was only for test...
//            this.addMouseListener(new MouseListener(){
//
//        @Override
//        public void mouseClicked(MouseEvent arg0) {
//            System.out.println("CLICK!");
//                    }
//            });
//    }
//
//    // this function is called during the mouse dragging for drow the line.
//    // it gets the coordinates, convert them, save them and update the bounds and 
//    // size of the object
//    public void setArrivePoint(Point a, ZoomManager zoom) {
//            Point p = DrawableObjects.getScaledCoordinates(a.x, a.y, zoom);
//            this.x_2 = p.x;
//            this.y_2 = p.y;
//            updateBoundsAndSize(zoom);
//    }
//
//    // update the bounds of the object, the origin point of the rectangle is the
//    // top-left coordinate build with the original coordinates. The width and height of the rectangle are obtained by subtraction. 
//    private void updateBoundsAndSize(ZoomManager zoom) {
//
//            Point p = DrawableObjects.getPanelCoordinates(x, y, zoom);
//            Point a = DrawableObjects.getPanelCoordinates(x_2, y_2, zoom);
//
//            int min_x = (int)Math.min(p.x, a.x) - SPESSORE;
//            int min_y = (int)Math.min(p.y, a.y) - SPESSORE;
//
//            if (min_x < 0)
//                    min_x =0;
//
//            if (min_y < 0)
//                    min_y = 0;
//
//            int w = (int) (Math.max(a.x, p.x) - min_x) + SPESSORE;
//            int h = (int) (Math.max(a.y, p.y) - min_y) + SPESSORE;
//
//            setBounds(new Rectangle(min_x, min_y, w, h));
//            repaint();
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
//        antiAlias.setColor(DEFAULT_COLOR);
//        antiAlias.setStroke(spessore);
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
//        Point p = new Point((int) this.x, (int) this.y);
//        Point a = new Point((int) this.x_2, (int) this.y_2);
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