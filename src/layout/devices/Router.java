package layout.devices;

import gui.ConsoleFrame;
import layout.components.Package;
import layout.components.Socket;
import layout.deamons.*;

import java.util.*;

public abstract class Router implements Runnable
{
    protected String routerID;
    protected HashMap<String, Socket> sockets = new HashMap<>();;
    protected ConsoleFrame console;

    protected boolean isRunning = false;
    protected int tickTime = 1000;
    protected int timeFromStart = 0;

    protected SelfPortsRouting daemonSelfPorts = new SelfPortsRouting(sockets);
    protected StaticRouting daemonStatic = null;
    protected RIP daemonRIP = null;
    protected Garbage daemonGarbage = new Garbage();


    // ------------------------------------ constructor ------------------------------------

    protected Router(String routerID, ArrayList<String> socketsNames)  // socket names: names of color in HTML eg: green, lime, maroon
    {
        this.routerID = routerID;
        console = new ConsoleFrame(routerID);


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

    public int getTimeFromStart()
    {
        return timeFromStart;
    }


    // ------------------------------------ run ------------------------------------

    @Override
    public void run()
    {
        // clear all ports buffers on the beggining
        clearSockets();
        timeFromStart = 0;

        while(true)
        {
            //System.out.println(routerID + " is running");

            // -------------- processing package --------------
            for(HashMap.Entry<String, Socket> one : sockets.entrySet())
            {
                Socket s = one.getValue();
                Package p = s.receivePackageFromPort();
                if(p != null) processPackage(p);
            }

            // -------------- waiting a while --------------
            try
            {
                Thread.sleep(tickTime);
                timeFromStart += tickTime;
            }
            catch(InterruptedException e) { return; }
        }
    }


    // ------------------------------------ processing ------------------------------------

    public void processPackage(Package p)
    {
        for(Socket socket : sockets.values())
        {
            if(socket.getAddress() == p.getDestination())
            {
                packageForMe(p);
                return;
            }
            if(socket.getOuterSocket() != null)
            {
                if (p.getDestination() == socket.getOuterSocket().getAddress())
                {
                    socket.sendPackageThruPort(p);
                    return;
                }
            }
        }

        if(daemonStatic.processPackage(p)) return;
        if(daemonRIP.processPackage(p)) return;
        daemonGarbage.processPackage(p);
    }

    public void packageForMe(Package p)
    {
        if(p.getInformation().toString() == "ping")

        System.out.println(routerID + " received package:\n" + p.getInformation().toString() + "\n" + p.getRoute() + "\n");
    }


    // ------------------------------------ others ------------------------------------

    public void showConsole() { console.setVisible(true); }

    public void hideConsole() { console.setVisible(false); }

    public void clearSockets()
    {
        for(HashMap.Entry<String, Socket> one : sockets.entrySet())
        {
            one.getValue().clearBuff();
        }
        daemonGarbage.clearGaarbage();
    }
}
