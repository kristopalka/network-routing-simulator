package layout.deamons;

import layout.components.Package;
import layout.components.Socket;

import java.util.HashMap;

public class StaticRouting extends Daemon
{
    private HashMap<String, Socket> sockets;

    public StaticRouting(HashMap<String, Socket> sockets)
    {
        this.sockets = sockets;
    }

    @Override
    public boolean processPackage(Package p)
    {
        System.out.println("Static routing: processing");

        return false;
    }
}
