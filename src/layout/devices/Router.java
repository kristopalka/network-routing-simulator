package layout.devices;

import gui.ConsoleFrame;
import layout.components.Config;
import layout.components.Package;
import layout.components.Socket;
import layout.deamons.*;
import tools.IPConverter;
import tools.InputAnalyzer;

import java.util.*;

public abstract class Router implements Runnable, Config
{
    protected String routerName;
    protected int routerID;
    protected HashMap<String, Socket> sockets = new HashMap<>();
    protected ConsoleFrame console;

    protected boolean isRunning = true;
    protected boolean printStat = true;
    protected int tickTime = 1000;
    protected long timeFromStart = 0;

    public ForMe daemonForMe = new ForMe(sockets);
    public SelfPorts daemonSelf = new SelfPorts(sockets);
    public StaticRouting daemonStatic = new StaticRouting(sockets);
    public RIP daemonRIP = new RIP(sockets);
    public Garbage daemonGarbage = new Garbage();


    // ------------------------------------ constructor ------------------------------------

    protected Router(int routerID, String routerName, ArrayList<String> socketsNames)  // socket names: names of color in HTML eg: green, lime, maroon
    {
        this.routerName = routerName;
        this.routerID = routerID;
        console = new ConsoleFrame(routerName);
        console.commands = inputText ->
        {
            // operacja na tekście z inputu, 'inputText' jest stringiem
        };

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


    // ------------------------------------ getters ------------------------------------

    public void stopThread() { this.isRunning = false; }


    // ------------------------------------ main loop ------------------------------------

    @Override
    public void run()
    {
        // clear all ports buffers on the beggining
        clearSockets();
        timeFromStart = 0;

        while(isRunning)
        {
            // -------------- processing package --------------
            for(HashMap.Entry<String, Socket> one : sockets.entrySet())
            {
                Socket s = one.getValue();
                Package p = s.receivePackageFromPort();
                if(p != null)
                {
                    if(printStat) System.out.print(routerName + ": received package " + p.toString());
                    proceedPackage(p);
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
        System.out.print(routerName + ": " + p.toString());
        proceedPackage(p);
    }

    protected void proceedPackage(Package p)
    {
        if(p.TTL <= 0)
        {
            System.out.println( " TTL=0" + p.getLog());
            return;
        }
        if(daemonForMe.processPackage(p))
        {
            System.out.println(" get FOR ME");
            packageForMe(p);
            return;
        }
        if(daemonSelf.processPackage(p))
        {
            System.out.println( " send by CONNECTED PORTS");
            return;
        }
        if(daemonStatic.processPackage(p))
        {
            System.out.println( " send by STATIC ROUTING");
            return;
        }
        if(daemonRIP.processPackage(p))
        {
            System.out.println( " send by RIP");
            return;
        }

        daemonGarbage.processPackage(p);
        System.out.println( " cannot send" + p.getLog());
    }

    protected void packageForMe(Package p)
    {
        switch (p.type)
        {
            case "ping":
            {
                System.out.println(this.routerName + ": ping me: generating answer ");
                Package ping = new Package(p.destination, p.source);
                ping.time = p.time;
                ping.type = "ping-answer";

                sendPackage(ping);
                return;
            }
            case "ping-answer":
            {
                System.out.println(this.routerName + ": ping answer for me:\n" + p.toStringExtend());

                //todo print ping answer on graphic console
                return;
            }
            default:
            {
                return;
            }
        }
    }

    protected void clearSockets()
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

    // ------------------------------------ config ------------------------------------

    public String configure(String input)
    {
        String[] command = InputAnalyzer.parseInputCommand(input);
        return config(command);
    }


    @Override
    public String config(String[] command)
    {
        switch (command[0])
        {
            // ----------------- daemond -----------------
            case "forme":
                return daemonForMe.config(Arrays.copyOfRange(command, 1, command.length));
            case "garbage":
                return daemonGarbage.config(Arrays.copyOfRange(command, 1, command.length));
            case "rip":
                return daemonRIP.config(Arrays.copyOfRange(command, 1, command.length));
            case "selfports":
                return daemonSelf.config(Arrays.copyOfRange(command, 1, command.length));
            case "static":
                return daemonStatic.config(Arrays.copyOfRange(command, 1, command.length));

            // ----------------- sockets -----------------
            case "interface":
            case "inter":
            case "inte":
            case "int":
            {
                Socket s = sockets.get(command[1]);
                if(s == null) return "There is no socket with specified ID\n";
                else return s.config(Arrays.copyOfRange(command, 2, command.length));
            }

            // ----------------- router config -----------------
            case "set":
            {
                switch (command[1])
                {
                    case "name":
                    {
                        this.routerName = command[2];
                        return "Name setted\n";
                    }
                }
            }
            case "show":
            {
                switch (command[1])
                {
                    case "time":
                        return Long.toString(timeFromStart/1000) + " [s]\n";
                    case "name":
                        return routerName + "\n";

                }
            }
            case "ping":
            {
                long dest = IPConverter.strToNum(command[1]);

                Package ping = new Package(0, dest);
                ping.time = this.timeFromStart;
                ping.type = "ping";

                sendPackage(ping);
            }


            // ----------------- default -----------------
            default:
            {
                return "Invalid input";
            }
        }
    }

}
