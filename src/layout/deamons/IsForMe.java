package layout.deamons;

import layout.components.Package;
import layout.components.Route;
import layout.components.Socket;

import java.util.HashMap;

public class IsForMe extends Daemon
{
    protected HashMap<String, Socket> sockets;

    public IsForMe(HashMap<String, Socket> sockets)
    {
        this.sockets = sockets;
    }

    @Override
    public boolean processPackage(Package p)
    {
        String log = "";
        for(Socket socket : sockets.values())
        {
            if(socket.getAddress() == p.getDestination())
            {
                return true;
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
