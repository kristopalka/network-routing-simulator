package layout.deamons;

import layout.components.Package;
import layout.components.Route;
import layout.components.Socket;
import tools.IPConverter;

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
                return "Turning on self ports routing\n";
            }
            case "off":
            {
                this.isOn = false;
                return "Turning off self ports routing\n";
            }
            default:
            {
                return "Self ports routing: invalid input\n";
            }
        }
    }
}
