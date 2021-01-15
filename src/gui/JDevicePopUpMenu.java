package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import layout.Layout;

public class JDevicePopUpMenu extends JPopupMenu {
    
    JMenuItem showConsole;
    JMenuItem remove;
    
    int routerID;
    Layout layout;
    
    public JDevicePopUpMenu(Layout l, int id) {
        this.layout = l;
        this.routerID = id;
        
        this.showConsole = new JMenuItem("Show console");
        this.remove = new JMenuItem("Remove");
        
        showConsole.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                (layout.router(JDevicePopUpMenu.this.routerID)).showConsole();
            }
        });
        
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.INSTANCE.setActualMode(5);
                MainFrame.INSTANCE.deleteRouter(routerID);
            }
        });
        
        this.add(showConsole);
        this.add(remove);
    }

}
