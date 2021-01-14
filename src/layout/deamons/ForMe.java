package layout.deamons;

import layout.components.Package;
import layout.components.Route;
import layout.components.Socket;

import java.util.HashMap;

public class ForMe extends Daemon
{
    protected HashMap<String, Socket> sockets;

    public ForMe(HashMap<String, Socket> sockets)
    {
        this.sockets = sockets;
    }

    @Override
    public boolean processPackage(Package p)
    {
        for(Socket socket : sockets.values())
        {
            if(socket.getAddress() == p.destination)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String config(String[] command)
    {
        switch (command[0])
        {
            case "on":
            {
                this.isOn = true;
                return "Turning on processing packages for me\n";
            }
            case "off":
            {
                this.isOn = false;
                return "Turning off processing packages for me\n";
            }
            default:
            {
                return "For me: invalid input\n";
            }
        }
    }
}
