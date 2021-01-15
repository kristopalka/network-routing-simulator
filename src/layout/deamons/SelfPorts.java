package layout.deamons;

import layout.components.Package;
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
                return "Turning on processing packages for self ports\n";
            }
            case "off":
            case "of":
            {
                this.isOn = false;
                return "Turning off processing packages for self ports\n";
            }
            default:
            {
                return "Invalid input\n";
            }
        }
    }
}
