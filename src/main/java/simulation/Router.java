package simulation;

import gui.ConsoleFrame;

import java.util.HashMap;
import java.util.Map;

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
        this.powerOn = true;
        sockets = new HashMap<>();
        console = new ConsoleFrame(routerID);
        config = new Config();

        for(int i=0; i<numberOfSockets; i++)
        {
            String socketID = String.valueOf(i);
            sockets.put(socketID, new Socket(socketID));
        }
    }

    public Router(Router r) // clone constructor
    {
        this.routerID = r.routerID;
        this.sockets = r.sockets;
        this.console = new ConsoleFrame(routerID);
        this.config = new Config();
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

    /*@Override
    public void run()
    {
        while(true)
        {
            for(Map.Entry<String, Socket> one : sockets.entrySet())
            {
                Socket s = one.getValue();
                Package p = s.getPackage();
                if(p != null) processPackage(p);
            }
        }
    }*/
    
    @Override
    public void run() {
        int x = 0;
        while(true)
        {
            if(powerOn) {
                System.out.println("XD");
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
