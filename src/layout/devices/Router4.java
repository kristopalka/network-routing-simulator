package layout.devices;

import layout.deamons.Garbage;
import layout.deamons.RIP;
import layout.deamons.StaticRouting;

import java.util.ArrayList;

public class Router4 extends Router
{
    public Router4(int routerID, String routerName)
    {
        super(routerID, routerName, new ArrayList<String>()
        {{
            add("blue");
            add("green");
            add("red");
            add("yellow");
        }});

        daemonStatic = new StaticRouting(sockets);
        daemonRIP = new RIP(sockets);
    }
}