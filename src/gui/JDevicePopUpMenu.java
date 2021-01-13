package gui;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class JDevicePopUpMenu extends JPopupMenu {
    
    JMenuItem showConsole;
    JMenuItem power;
    JMenuItem remove;
    
    public JDevicePopUpMenu() {
        showConsole = new JMenuItem("Show console");
        power = new JMenuItem("Power on");
        remove = new JMenuItem("Remove");
        this.add(showConsole);
        this.add(power);
        this.add(remove);
    }
    
}
