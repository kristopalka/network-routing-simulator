package tools;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageIconGetter
{
    public ImageIcon getIcon(String name, int size)
    {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/png/" + name));
        Image image = imageIcon.getImage();
        return new ImageIcon(image.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH));
    } 
    
}
