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

    protected void addRoute(long ip, long mask, Socket socket)
    {
        Route r = new Route(ip, mask, socket);
        routes.add(r);
    }

    public void addRoute(String ip, int mask, Socket socket)
    {
        Route r = new Route(IPConverter.strToNum(ip), IPConverter.getMask(mask), socket);
        routes.add(r);
    }

    protected void remRoute(long ip, long mask)
    {
        routes.removeIf(route -> route.netmask == mask && route.address == ip);
    }

    @Override
    public String config(String[] command)
    {
        switch (command[0])
        {
            case "on":
            {
                this.isOn = true;
                return "Turning on static routing\n";
            }
            case "off":
            {
                this.isOn = false;
                return "Turning off static routing\n";
            }
            case "add":
            {
                try
                {
                    long address = IPConverter.strToNum(command[1]);
                    long netmask = IPConverter.strToNum(command[2]);
                    Socket socket = sockets.get(command[3]);
                    this.addRoute(address, netmask, socket);
                    return "Added static route " + command[1] + " " + command[2] + " -> " + command[3];
                }
                catch (Exception e)
                {
                    return "Adding route: invalid input\n";
                }
            }
            case "rem":
            {
                try
                {
                    long address = IPConverter.strToNum(command[1]);
                    long netmask = IPConverter.strToNum(command[2]);
                    this.remRoute(address, netmask);
                    return "Removed static route " + command[1] + " " + command[2] + "\n";
                }
                catch (Exception e)
                {
                    return "Removing route: invalid input\n";
                }
            }
            case "show":
            {
                String log = "";
                for(Route route: routes)
                {
                    log += route.toString() + "\n";
                }
                return log + "\n";
            }
            default:
            {
                return "Static routing: invalid input\n";
            }
        }
    }
}
