package simulation;

import gui.*;
import java.util.ArrayList;
import layout.devices.*;

public class Main {
    /*Router r1 = new Router("r1", 5);
        Router r2 = new Router("r2", 2);

        Layout l = new Layout();
        l.addRouter(r1);
        l.addRouter(r2);

        String log = "";

        log += l.connect(r1.getSocket("0"), r2.getSocket("0"));
        log += l.disconnect(r1.getSocket("0"), r2.getSocket("0"));
        log += l.connect(r1.getSocket("0"), r2.getSocket("1"));


        System.out.println(log);

        Simulation sim = new Simulation(l.cloneDevices());
        sim = null;



        Simulation sim2 = new Simulation(l.cloneDevices());*/

    private ArrayList<Router> routers;
    private MainFrame mainFrame; 

    public static void main(String[] args) {
        
        Main mainProgram = new Main();
        
        mainProgram.mainFrame = new MainFrame();
        mainProgram.mainFrame.setSize(1280, 720);
        mainProgram.mainFrame.setVisible(true);
        
        Router4 r = new Router4("Router");
        Thread t = new Thread(r);
        t.start();
        r.showConsole();
        
    }
    
    public static String getInfo() {
        return "Sim v0";
    }
    
}
