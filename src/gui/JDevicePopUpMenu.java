package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import layout.Layout;

public class JDevicePopUpMenu extends JPopupMenu {
    
    JMenuItem showConsole;
    
    int routerID;
    Layout layout;
    
    public JDevicePopUpMenu(Layout l, int id) {
        this.layout = l;
        this.routerID = id;
        
        this.showConsole = new JMenuItem("Show console");
        
        showConsole.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                (layout.router(JDevicePopUpMenu.this.routerID)).showConsole();
            }
        });
        this.add(showConsole);
    }

}
