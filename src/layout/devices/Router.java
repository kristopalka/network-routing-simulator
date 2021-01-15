package layout.devices;

import gui.ConsoleFrame;
import layout.components.Config;
import layout.components.Package;
import layout.components.Route;
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



    protected ForMe daemonForMe = new ForMe(sockets);
    protected SelfPorts daemonSelf = new SelfPorts(sockets);
    protected StaticRouting daemonStatic = new StaticRouting(sockets);
    protected RIP daemonRIP = new RIP(sockets);
    protected Garbage daemonGarbage = new Garbage();


    // ------------------------------------ constructor ------------------------------------

    protected Router(int routerID, String routerName, ArrayList<String> socketsNames)  // socket names: names of color in HTML eg: green, lime, maroon
    {
        this.routerName = routerName;
        this.routerID = routerID;
        console = new ConsoleFrame(routerName);
        console.commands = inputText ->
        {
            console.printLine(callCommand(inputText));
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


    // ------------------------------------ setters ------------------------------------

    public void stopThread() { this.isRunning = false; }

    private void setRouterName(String name)
    {
        this.routerName = name;
        console.setTitle(name);
        console.ROUTER_NAME = name;
    }


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

                console.printLine("Reply from " + p.source + " time=" + (timeFromStart - p.time) + "[ms]");
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


    // ------------------------------------ config ------------------------------------

    public String callCommand(String input)
    {
        String[] command = InputAnalyzer.parseInputCommand(input);
        return config(command);
    }


    @Override
    public String config(String[] command)
    {
        if(command.length == 1) return "Incomplete command";

        switch (command[0])
        {
            // ----------------- daemond -----------------
            case "forme":
            case "form":
            case "for":
            case "fo":
                return daemonForMe.config(Arrays.copyOfRange(command, 1, command.length));
            case "garbage":
            case "garbag":
            case "garba":
            case "garb":
            case "gar":
            case "ga":
                return daemonGarbage.config(Arrays.copyOfRange(command, 1, command.length));
            case "rip":
            case "ri":
                return daemonRIP.config(Arrays.copyOfRange(command, 1, command.length));
            case "selfports":
            case "selfport":
            case "selfpor":
            case "selfpo":
            case "selfp":
            case "self":
            case "sel":
                return daemonSelf.config(Arrays.copyOfRange(command, 1, command.length));
            case "static":
            case "stati":
            case "stat":
            case "sta":
            case "st":
                return daemonStatic.config(Arrays.copyOfRange(command, 1, command.length));

            // ----------------- sockets -----------------
            case "interface":
            case "interfac":
            case "interfa":
            case "interf":
            case "inter":
            case "inte":
            case "int":
            case "in":
            {
                Socket s = sockets.get(command[1]);
                if(s == null) return "There is no socket with specified ID";
                if(command.length == 2) return "Incomplete command";
                return s.config(Arrays.copyOfRange(command, 2, command.length));
            }

            // ----------------- router config -----------------
            case "set":
            {
                switch (command[1])
                {
                    case "name":
                    case "nam":
                    case "na":
                    {
                        if(command.length == 2) return "Incomplete command";
                        setRouterName(command[2]);
                        return "New name: " + command[2];
                    }
                }
            }

            case "show":
            case "sho":
            case "sh":
            {
                switch (command[1])
                {
                    case "time":
                    case "tim":
                    case "ti":
                        return "Time from start: " + Long.toString(timeFromStart/1000) + " [s]";

                    case "name":
                    case "nam":
                    case "na":
                        return "Name: " + routerName;

                    case "interfaces":
                    case "interface":
                    case "interfac":
                    case "interfa":
                    case "interf":
                    case "inter":
                    case "inte":
                    case "int":
                    case "in":
                    {
                        String log = "interfaces:\n";
                        for(Socket s : sockets.values())
                        {
                            log +=  "    " + s.getName() + ": \n" +
                                    "        " + IPConverter.numToStr(s.getAddress()) + "\n" +
                                    "        " + IPConverter.numToStr(s.getNetmask()) + "\n";
                        }
                        return log;
                    }

                    case "config":
                    case "confi":
                    case "conf":
                    case "con":
                    case "co":
                    {
                        String log = "";

                        log += "name:\n" +
                                "    " + routerName + "\n";

                        log += "time:\n" +
                                "    " + timeFromStart + "\n";

                        log += callCommand("show interfaces");

                        log += "daemons:\n" +
                                "    for me:\n" +
                                "        " + InputAnalyzer.boolToStr(daemonForMe.isOn()) + "\n" +
                                "    self ports:\n" +
                                "        " + InputAnalyzer.boolToStr(daemonSelf.isOn()) + "\n" +
                                "    static routing:\n" +
                                "        " + InputAnalyzer.boolToStr(daemonStatic.isOn()) + "\n" +
                                "        routes:\n";

                        for(Route route: daemonStatic.getRoutes())
                        {
                            log += "            " + route.toString() + "\n";
                        }


                        log +=  "    RIP:\n" +
                                "        " + InputAnalyzer.boolToStr(daemonRIP.isOn()) + "\n" +
                                "    garbage:\n" +
                                "        " + InputAnalyzer.boolToStr(daemonGarbage.isOn()) + "\n";





                        return log;
                    }

                }
            }
            case "ping":
            case "pin":
            case "pi":
            {
                long dest;
                try {dest = IPConverter.strToNum(command[1]);}
                catch (Exception e) {return "Invalid address";}

                Package ping = new Package(0, dest);
                ping.time = this.timeFromStart;
                ping.type = "ping";

                sendPackage(ping);

                return "Pinging " + command[1] + " ...";
            }


            // ----------------- default -----------------
            default:
            {
                return "Invalid input";
            }
        }
    }

    // ------------------------------------ others ------------------------------------

    public void showConsole() { console.setVisible(true); }

    public void hideConsole() { console.setVisible(false); }

}
