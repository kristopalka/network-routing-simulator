package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Icon;
import javax.swing.JLabel;
import layout.Layout;

public class JLinkLabel extends JLabel {
    
    public JLinkLabel(String text, Icon icon, Point location, int routerID, Layout layout) {
        super();
        this.setBackground(new java.awt.Color(153, 255, 153));
        this.setFont(new java.awt.Font("Noto Sans", 1, 10));
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setText("LINK");
        this.setToolTipText("LINK");
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.setMaximumSize(new java.awt.Dimension(42, 20));
        this.setMinimumSize(new java.awt.Dimension(42, 20));
        this.setOpaque(true);
        this.setPreferredSize(new java.awt.Dimension(42, 20));

//        this.setLocation((int) location.getX(), (int) location.getY());
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        this.routerID = routerID;
    }
}
