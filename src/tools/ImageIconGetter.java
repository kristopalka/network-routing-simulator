
package tools;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageIconGetter {
    public ImageIcon getIcon(String name, int size) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("images/router4.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    } 
    
}
