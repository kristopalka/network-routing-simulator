package simulation;

import gui.*;
import java.util.ArrayList;

public class Main {

    private ArrayList<Router> routers;
    private MainFrame mainFrame; 

    public static void main(String[] args) {
        
        Main mainProgram = new Main();
        
        mainProgram.mainFrame = new MainFrame();
        mainProgram.mainFrame.setSize(1280, 720);
        mainProgram.mainFrame.setVisible(true);
        
//        Router r = new Router("Router");
//        Thread t = new Thread(r);
//        t.start();
//        r.showConsole();
        
    }
    
    public static String getInfo() {
        return "Sim v0";
    }
    
}
