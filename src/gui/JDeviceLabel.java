package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import tools.OnScreenLocation;

public class JDeviceLabel extends JLabel {
    
    private int xPressed = 0;
    private int yPressed = 0;
    
    public JDeviceLabel(String text, Icon icon, OnScreenLocation location) {
        super();
        this.setPreferredSize(new Dimension(80, 80));
        this.setSize(this.getPreferredSize());
        this.setIcon(icon);
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setToolTipText(text);
        this.setOpaque(true);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.setText(text);
        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setLocation(location.getX(), location.getY());
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new PopClickListener());

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                //and when the Jlabel is dragged
                setLocation(e.getX() + JDeviceLabel.this.getX() - xPressed, e.getY() + JDeviceLabel.this.getY() - yPressed);
            }
        });
    }
//    
//    public void mouseDragged(MouseEvent e) {
//        Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), e);
//        e.getComponent().setLocation(p.x, p.y);
//    }

    class PopClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                doPop(e);
            }
            xPressed = e.getX();
            yPressed = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger())
                doPop(e);
            
        }

        private void doPop(MouseEvent e) {
            JDevicePopUpMenu menu = new JDevicePopUpMenu();
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}