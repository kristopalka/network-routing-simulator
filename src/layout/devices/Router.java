package layout.devices;

import gui.ConsoleFrame;
import layout.components.Config;
import layout.components.Package;
import layout.components.Socket;

import java.util.ArrayList;
import java.util.HashMap;
import tools.InputAnalyzer;

public abstract class Router implements Runnable
{
    private String routerID;
    private HashMap<String, Socket> sockets;
    private ConsoleFrame console;
    private Config config;
    private boolean powerOn;


    // ------------------------------------ constructors ------------------------------------

    protected Router(String routerID, ArrayList<String> socketsNames)  // socket names: names of color in HTML eg: green, lime, maroon
    {
        this.routerID = routerID;
        this.powerOn = false;
        sockets = new HashMap<>();
        console = new ConsoleFrame(routerID);
        console.commands = (String command) -> {
            handleCommand(command);
        };
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
            try{
                Thread.sleep(200);
            }
            catch(InterruptedException e) { return; }
        }
    }
    
    public void processPackage(Package p)
    {
        //TODO: decide what to do

        // own ports
        for(HashMap.Entry<String, Socket> one : sockets.entrySet())
        {
            one.getValue().clearBuff();
        }
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

    private void handleCommand(String text) {
        String parsed = InputAnalyzer.parseInputCommand(text);
    }
}
