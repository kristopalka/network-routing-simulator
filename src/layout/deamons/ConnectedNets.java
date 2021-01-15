package layout.deamons;

import layout.components.Package;
import layout.components.Route;
import layout.components.Socket;
import tools.IPConverter;

import java.util.HashMap;

public class ConnectedNets extends Daemon
{
    protected HashMap<String, Socket> sockets;

    public ConnectedNets(HashMap<String, Socket> sockets)
    {
        this.sockets = sockets;
    }

    @Override
    public boolean processPackage(Package p)
    {
        if(!isOn) return false;
        for(Socket socket : sockets.values())
        {
            if(socket.getOuterSocket() != null)
            {
                Socket outer = socket.getOuterSocket();
                if((outer.getNetmask()&outer.getAddress()) == (p.destination&outer.getNetmask()))
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
        switch (command[0])
        {
            case "on":
            {
                this.isOn = true;
                return "Turning on Connected nets routing";
            }
            case "off":
            case "of":
            {
                this.isOn = false;
                return "Turning off Connected nets routing";
            }
            default:
            {
                return "Connected nets routing: invalid input";
            }
        }
    }
}
