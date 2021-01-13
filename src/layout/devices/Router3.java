package layout.devices;

import layout.deamons.RIP;
import layout.deamons.StaticRouting;

import java.util.ArrayList;

public class Router3 extends Router
{
    public Router3(String routerID)
    {
        super(routerID, new ArrayList<String>()
        {{
            add("blue");
            add("green");
            add("red");
        }});

        daemonStatic = new StaticRouting(sockets);
        daemonRIP = new RIP(sockets);
    }
}