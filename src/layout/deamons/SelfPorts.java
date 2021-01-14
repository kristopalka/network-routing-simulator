package layout.deamons;

import layout.components.Package;
import layout.components.Route;
import layout.components.Socket;

import java.util.HashMap;

public class SelfPorts extends Daemon
{
    protected HashMap<String, Socket> sockets;

    public SelfPorts(HashMap<String, Socket> sockets)
    {
        this.sockets = sockets;
    }

    @Override
    public boolean processPackage(Package p)
    {
        String log = "";
        for(Socket socket : sockets.values())
        {
            if(socket.getOuterSocket() != null)
            {
                //if (p.getDestination() == socket.getOuterSocket().getAddress())
                Socket outer = socket.getOuterSocket();
                if((outer.getNetmask()&outer.getAddress()) == (p.getDestination()&outer.getNetmask()))
                {
                    socket.sendPackageThruPort(p);
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public String config(String[] command)
    {
        //TODO
        return null;
    }
}
