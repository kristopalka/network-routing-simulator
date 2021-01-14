package layout.devices;

import gui.ConsoleFrame;
import layout.components.Config;
import layout.components.Package;
import layout.components.Socket;
import layout.deamons.*;

import java.util.*;

public abstract class Router implements Runnable, Config
{
    private String routerName;
    protected int routerID;
    protected HashMap<String, Socket> sockets = new HashMap<>();
    protected ConsoleFrame console;

    protected boolean isRunning = true;
    protected boolean printStat = true;
    protected int tickTime = 1000;
    protected int timeFromStart = 0;



    public IsForMe daemonForMe = new IsForMe(sockets);
    public SelfPorts daemonSelf = new SelfPorts(sockets);
    public StaticRouting daemonStatic = null;
    public RIP daemonRIP = null;
    public Garbage daemonGarbage = new Garbage();


    // ------------------------------------ constructor ------------------------------------

    protected Router(int routerID, String routerName, ArrayList<String> socketsNames)  // socket names: names of color in HTML eg: green, lime, maroon
    {
        this.routerName = routerName;
        this.routerID = routerID;
        console = new ConsoleFrame(routerName);


        for(String socketName : socketsNames)
        {
            sockets.put(socketName, new Socket(socketName, this));
        }
    }


    // ------------------------------------ getters ------------------------------------

    public int getID()
    {
        return routerID;
    }

    public String getName() { return routerName; }

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

    // ------------------------------------ getters ------------------------------------

    public void setName(String routerName)
    {
        this.routerName = routerName;
    }

    public void stopThread() { this.isRunning = false; }

    // ------------------------------------ run ------------------------------------

    @Override
    public void run()
    {
        // clear all ports buffers on the beggining
        clearSockets();
        timeFromStart = 0;

        while(isRunning)
        {
            if(printStat) System.out.println(routerName + ": ~");

            // -------------- processing package --------------
            for(HashMap.Entry<String, Socket> one : sockets.entrySet())
            {
                Socket s = one.getValue();
                Package p = s.receivePackageFromPort();
                if(p != null)
                {
                    String log = proceedPackage(p);
                    if(printStat) System.out.println(routerName + ": received package " + p.toString() + " " + log);
                }
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

    public void sendPackage(Package p)
    {
        p.onStart("From " + routerName);
        String log = proceedPackage(p);
        System.out.println(routerName + ": send package " + p.toString() + " " + log);
    }



    private String proceedPackage(Package p)
    {
        String log = "";

        if(daemonForMe.processPackage(p))
        {
            log += "for me " + p.getLog();
            packageForMe(p);
            return log;
        }

        if(daemonSelf.processPackage(p))
        {
            log += "send by CONNECTED PORTS";
            return log;
        }

        if(daemonStatic.processPackage(p))
        {
            log += "send by STATIC ROUTING";
            return log;
        }

        if(daemonRIP.processPackage(p))
        {
            log += "send by RIP";
            return log;
        }

        daemonGarbage.processPackage(p);
        log += "CANNOT SEND " + p.getLog();
        return log;
    }

    private void packageForMe(Package p)
    {
        if(p.getInformation().toString() == "ping")
        {
            Package re = new Package(p.getDestination(), p.getSource(), "ping repy");
            sendPackage(re);
        }
    }

    private void clearSockets()
    {
        for(HashMap.Entry<String, Socket> one : sockets.entrySet())
        {
            one.getValue().clearBuff();
        }
        daemonGarbage.clearGaarbage();
    }


    // ------------------------------------ others ------------------------------------

    public void showConsole() { console.setVisible(true); }

    public void hideConsole() { console.setVisible(false); }

    @Override
    public String config(String[] command)
    {
        if(command[0] == "set")
        {
            if(command[1] == "name") this.setName(command[2]);
        }

        if(command[0] == "run") ;//todo
        if(command[0] == "stop") ;//todo

        if(command[0] == "ping")
        {
            Package p = new Package(command[1], command[2], command[3], 123);
        }

        return null;
    }

}
