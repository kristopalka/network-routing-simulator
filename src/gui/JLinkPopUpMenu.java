package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import layout.Layout;

public class JLinkPopUpMenu extends JPopupMenu {
    
    ArrayList<JMenuItem> socketsItem;
    
    int routerID;
    Layout layout;
    ArrayList<String> notConnected;
    
    public JLinkPopUpMenu(Layout l, int id) {
        this.layout = l;
        this.routerID = id;
        
        notConnected = l.notConnectedPorts(routerID);
        
        for(String socket : notConnected) {
            
            JMenuItem item = new JMenuItem(socket);
            
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MainFrame.INSTANCE.addLink(routerID, socket);
                }
            });
            
            this.add(item);
        }
    }
}
