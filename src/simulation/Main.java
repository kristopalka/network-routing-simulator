package simulation;

import gui.*;
import java.util.ArrayList;

import layout.Layout;
import layout.components.Package;
import layout.devices.*;
import tools.IPConverter;

public class Main
{
    private ArrayList<Router> routers;
    private MainFrame mainFrame; 

    public static void main(String[] args)
    {
        Main mainProgram = new Main();

        mainProgram.mainFrame = new MainFrame(new Layout());
        mainProgram.mainFrame.setSize(1280, 720);
        mainProgram.mainFrame.setVisible(true);
    }
}
