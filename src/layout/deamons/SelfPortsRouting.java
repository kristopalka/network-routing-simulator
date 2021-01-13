package layout.deamons;

import layout.components.Package;
import layout.components.Socket;

import java.util.HashMap;

public class SelfPortsRouting extends Daemon
{
    private HashMap<String, Socket> sockets;

    public SelfPortsRouting(HashMap<String, Socket> sockets)
    {
        this.sockets = sockets;
    }

    @Override
    public boolean processPackage(Package p)
    {
        for(Socket socket : sockets.values())
        {
            if(socket.getOuterSocket() != null)
            {
                if (p.getDestination() == socket.getOuterSocket().getAddress())
                {
                    socket.sendPackageThruPort(p);
                    return true;
                }
            }
        }
        return false;
    }

}
