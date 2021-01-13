package layout.deamons;

import layout.components.Package;
import layout.components.Route;
import layout.components.Socket;
import tools.IPConverter;

import java.util.TreeSet;

public class StaticRouting extends Daemon
{
    TreeSet<Route> routes = new TreeSet<>();

    @Override
    public boolean processPackage(Package p)
    {
        for(Route route : routes)
        {
            //System.out.println(route.socket.getFullName() + ": " + Long.toBinaryString(route.address&route.netmask) + "  " + Long.toBinaryString(p.getDestination()&route.netmask));
            if((route.address&route.netmask) == (p.getDestination()&route.netmask))
            {
                route.socket.sendPackageThruPort(p);
                return true;
            }
        }
        return false;
    }

    public void addRoute(String ip, String mask, Socket socket)
    {
        Route r = new Route(IPConverter.strToNum(ip), IPConverter.strToNum(mask), socket);
        routes.add(r);
    }

    public void addRoute(String ip, int mask, Socket socket)
    {
        Route r = new Route(IPConverter.strToNum(ip), IPConverter.getMask(mask), socket);
        routes.add(r);
    }

    public void remRoute(String ip, String mask)
    {
        long address = IPConverter.strToNum(ip);
        long netmask = IPConverter.strToNum(mask);
        routes.removeIf(route -> route.netmask == netmask && route.address == address);
    }

    @Override
    public String config(String order)
    {
        //TODO
        return null;
    }
}
