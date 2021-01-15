package layout.devices;

import layout.components.Package;
import layout.components.Socket;
import layout.deamons.StaticRouting;

import java.util.ArrayList;
import java.util.HashMap;

public class PC extends Router
{
    public PC(int routerID, String routerName)
    {
        super(routerID, routerName , new ArrayList<String>()
        {{
            add("blue");
        }});

        // default gateway
        this.daemonStatic = new StaticRouting(sockets);
        this.daemonStatic.addRoute("0.0.0.0", 0, socket("blue"));
    }


}
