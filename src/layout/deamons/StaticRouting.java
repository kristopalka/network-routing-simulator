package layout.deamons;

import layout.components.Package;
import layout.components.Route;
import layout.components.Socket;
import tools.IPConverter;

import java.util.HashMap;
import java.util.TreeSet;

public class StaticRouting extends Daemon
{
    TreeSet<Route> routes = new TreeSet<>();
    protected HashMap<String, Socket> sockets;

    public StaticRouting(HashMap<String, Socket> sockets)
    {
        this.sockets = sockets;
    }

    @Override
    public boolean processPackage(Package p)
    {
        if(!isOn) return false;
        for(Route route : routes)
        {
            if((route.address&route.netmask) == (p.destination&route.netmask))
            {
                route.socket.sendPackageThruPort(p);
                return true;
            }
        }
        return false;
    }

    protected void addRoute(long address, long netmask, Socket socket)
    {
        Route r = new Route(address, netmask, socket);
        routes.add(r);
    }

    public boolean addRoute(String address, int netmask, Socket socket)
    {
        Route r = new Route(IPConverter.strToNum(address), IPConverter.getMask(netmask), socket);
        if(routes.contains(r)) return false;
        routes.add(r);
        return true;
    }

    public TreeSet<Route> getRoutes() { return routes; }

    protected void remRoute(long address, long netmask)
    {
        routes.removeIf(route -> route.netmask == netmask && route.address == address);
    }

    protected boolean isRoute(long address, long netmask)
    {
        for(Route r: routes)
        {
            if(r.address == address && r.netmask == netmask) return true;
        }
        return false;
    }


    @Override
    public String config(String[] command)
    {
        switch (command[0])
        {
            case "on":
            {
                this.isOn = true;
                return "Turning on static routing";
            }
            case "off":
            case "of":
            {
                this.isOn = false;
                return "Turning off static routing";
            }
            case "add":
            case "ad":
            {
                if(command.length < 4) return "Incomplete command";
                try
                {
                    long address = IPConverter.strToNum(command[1]);
                    long netmask = IPConverter.strToMask(command[2]);
                    Socket socket = sockets.get(command[3]);
                    if(socket == null) return "Invalid socket";

                    if(isRoute(address, netmask)) return "Route exist";
                    this.addRoute(address, netmask, socket);
                    return "Added static route " + command[1] + " " + command[2] + " -> " + command[3];
                }
                catch (Exception e)
                {
                    return "Invalid input";
                }
            }
            case "remove":
            case "remov":
            case "remo":
            case "rem":
            case "re":
            {
                if(command.length < 3) return "Incomplete command";
                try
                {
                    long address = IPConverter.strToNum(command[1]);
                    long netmask = IPConverter.strToMask(command[2]);
                    this.remRoute(address, netmask);
                    return "Removed static route " + command[1] + " " + command[2];
                }
                catch (Exception e)
                {
                    return "Invalid input";
                }
            }
            case "show":
            case "sho":
            case "sh":
            {
                String log = "";
                for(Route route: routes)
                {
                    log += route.toString() + "\n";
                }
                return log;
            }
            default:
            {
                return "Invalid input";
            }
        }
    }
}
