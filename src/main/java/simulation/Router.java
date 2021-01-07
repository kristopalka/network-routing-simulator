package simulation;

import java.util.ArrayList;
import java.util.Queue;

public class Router implements Runnable {
    
    private boolean powerOn;
    
    private String routerID;
    private ArrayList<Socket> sockets;
    private Queue<Package> interganBuffer;
    private Console console;
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
    
    private void doConfig() {
        
        // TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Router(String routerID)
    {
        this.routerID = routerID;
        sockets = new ArrayList<>();
        console = new Console(routerID);
        analyzer = new InputAnalyzer();
        //config = new Config();
    }

    @Override
    public void run() {
        while(powerOn)
        {
            try {
                action = this.analyzer.parseInput(this.console.getInputAction());
            } catch(NullPointerException ex) {
                action = 0;
            }
            if(action != 0) {
                this.doConfig();
                action = 0;
            }
            
            for(Socket s: sockets)
            {
                //Package p = s.getPackage();
                //if(p != null) processPackage(p);
            }
            
        }
    }
}
