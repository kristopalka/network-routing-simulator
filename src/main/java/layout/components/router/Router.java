package layout.components.router;

import gui.ConsoleFrame;
import layout.components.Config;
import layout.components.Package;
import layout.components.Socket;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Router implements Runnable
{
    private String routerID;
    private HashMap<String, Socket> sockets;
    private ConsoleFrame console;
    private Config config;
    private boolean powerOn;


    // ------------------------------------ constructors ------------------------------------

    private Router(String routerID, int numberOfSockets)
    {
        this.routerID = routerID;
        this.powerOn = false;
        sockets = new HashMap<>();
        console = new ConsoleFrame(routerID);
        config = new Config();

        for(int i=0; i<numberOfSockets; i++)
        {
            String socketID = String.valueOf(i);
            sockets.put(socketID, new Socket(socketID, routerID));
        }
    }

    protected Router(String routerID, ArrayList<String> socketsNames)  // socket names: names of color in HTML eg: green, lime, maroon
    {
        this.routerID = routerID;
        this.powerOn = false;
        sockets = new HashMap<>();
        console = new ConsoleFrame(routerID);
        config = new Config();

        for(String socketName : socketsNames)
        {
            sockets.put(socketName, new Socket(socketName, routerID));
        }
    }


    // ------------------------------------ getters ------------------------------------

    public String getID()
    {
        return routerID;
    }

    public HashMap<String, Socket> getAllSockets()
    {
        return sockets;
    }

    public Socket socket(String socketID)
    {
        return sockets.get(socketID);
    }


    // ------------------------------------ processing ------------------------------------

    @Override
    public void run()
    {
        // clear all ports buffers on the beggining
        clearSockets();

        int x = 0;
        while(true)
        {
            // processing package
            for(HashMap.Entry<String, Socket> one : sockets.entrySet())
            {
                Socket s = one.getValue();
                Package p = s.receivePackageFromPort();
                if(p != null) processPackage(p);
            }


            // waiting a while
            try{ wait(20); }
            catch(InterruptedException e) { return; }
        }
    }
    
    public void processPackage(Package p)
    {
        //TODO: decide and do something
    }


    // ------------------------------------ others ------------------------------------

    public void showConsole()
    {
        console.setVisible(true);
    }

    public void hideConsole()
    {
        console.setVisible(false);
    }

    public void clearSockets()
    {
        for(HashMap.Entry<String, Socket> one : sockets.entrySet())
        {
            one.getValue().clearBuff();
        }
    }
}
