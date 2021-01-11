package simulation.Router;

import gui.ConsoleFrame;

import java.util.HashMap;

public class Router implements Runnable
{
    private String routerID;
    private HashMap<String, Socket> sockets;
    private ConsoleFrame console;
    private Config config;
    private boolean powerOn;


    // ------------------------------------ constructors ------------------------------------

    public Router(String routerID, int numberOfSockets)
    {
        this.routerID = routerID;
        this.powerOn = false;
        sockets = new HashMap<>();
        console = new ConsoleFrame(routerID);
        config = new Config();

        for(int i=0; i<numberOfSockets; i++)
        {
            String socketID = String.valueOf(i);
            sockets.put(socketID, new Socket(socketID));
        }
    }

    public Router(String routerID, String[] socketsNames)
    {
        this.routerID = routerID;
        this.powerOn = false;
        sockets = new HashMap<>();
        console = new ConsoleFrame(routerID);
        config = new Config();

        for(String socketName : socketsNames)
        {
            sockets.put(socketName, new Socket(socketName));
        }
    }


    // ------------------------------------ getters ------------------------------------

    public String getRouterID()
    {
        return routerID;
    }

    public HashMap<String, Socket> getAllSockets()
    {
        return sockets;
    }

    public Socket getSocket(String socketID)
    {
        return sockets.get(socketID);
    }


    // ------------------------------------ processing ------------------------------------

    
    @Override
    public void run()
    {
        // clear all ports buffers on the beggining
        for(HashMap.Entry<String, Socket> one : sockets.entrySet()) { one.getValue().clearBuff(); }

        int x = 0;
        while(true)
        {
            for(HashMap.Entry<String, Socket> one : sockets.entrySet())
            {
                Socket s = one.getValue();
                Package p = s.receivePackageFromPort();
                if(p != null) processPackage(p);
            }

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

    public void clearSockets()
    {

    }

}
