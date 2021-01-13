package layout.devices;

import layout.deamons.RIP;
import layout.deamons.StaticRouting;

import java.util.ArrayList;

public class Router3 extends Router
{
    public Router3(int routerID, String routerName)
    {
        super(routerID, routerName, new ArrayList<String>()
        {{
            add("blue");
            add("green");
            add("red");
        }});

        daemonStatic = new StaticRouting();
        daemonRIP = new RIP(sockets);
    }
}