package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import layout.Layout;

public class JLinkPopUpMenu extends JPopupMenu {
    
    ArrayList<JMenuItem> socketsItem;
    
    int routerID;
    Layout layout;
    ArrayList<String> notConnected, connected;
    
    public JLinkPopUpMenu(Layout l, int id) {
        this.layout = l;
        this.routerID = id;
        
        notConnected = l.notConnectedPorts(routerID);
        connected = l.connectedPorts(routerID);
        
        if(!notConnected.isEmpty()) {
            JMenuItem item = new JMenuItem("Disconnected");
            item.setEnabled(false);
            this.add(item);
        }
        
        
        for(String socket : notConnected) {
            
            JMenuItem item = new JMenuItem(socket);
            
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MainFrame.INSTANCE.addLink(routerID, socket);
                    MainFrame.INSTANCE.setActualMode(4);
                }
            });
            
            this.add(item);
        }
        if(!connected.isEmpty()) {
            JMenuItem item = new JMenuItem("Connected");
            item.setEnabled(false);
            item.setOpaque(true);
            this.add(item);
        }

        for(String socket : connected) {
            
            JMenuItem item = new JMenuItem(socket);
            
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MainFrame.INSTANCE.deleteLink(routerID, socket);
                    MainFrame.INSTANCE.setActualMode(0);
                    MainFrame.INSTANCE.clearLinkBuffer();
                }
            });
            
            this.add(item);
        }
    }
}
