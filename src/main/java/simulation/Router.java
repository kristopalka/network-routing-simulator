package simulation;

import gui.ConsoleFrame;
import tools.InputAnalyzer;
import java.util.ArrayList;
import java.util.Queue;

public class Router implements Runnable {
    
    private boolean powerOn;
    
    private String routerID;
    private ArrayList<Socket> sockets;
    private Queue<Package> interganBuffer;
    private ConsoleFrame console;
    private InputAnalyzer analyzer;
    private int action;
    
    public String getID()
    {
        return routerID;
    }
    
    public void showConsole() {
        console.setVisible(true);
    }
    
    public void processPackage(Package p)
    {
        //TODO: decide and do something
    }
    
    private void doInputCommand(int action) {
        // TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Router(String routerID)
    {
        this.routerID = routerID;
        sockets = new ArrayList<>();
        console = new ConsoleFrame(routerID);
        analyzer = new InputAnalyzer();
        //config = new Config();
    }

    @Override
    public void run() {
        int x = 0;
        while(true)
        {
            if(powerOn) {
                try {
                    action = this.analyzer.parseInputCommand(this.console.getInputAction());
                } catch(Exception ex) {
                    action = 0;
                }
                if(action != 0) {
                    this.doInputCommand(action);
                    action = 0;
                }

    //            for(Socket s: sockets)
    //            {
    //                Package p = s.getPackage();
    //                if(p != null) processPackage(p);
    //            }
            }
            try {
                System.out.println("hello" + x);
                x++;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
