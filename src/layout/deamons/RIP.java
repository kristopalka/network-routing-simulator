package layout.deamons;

import layout.components.Package;
import layout.components.Socket;

import java.util.HashMap;

public class RIP extends Daemon
{
    private HashMap<String, Socket> sockets;

    public RIP(HashMap<String, Socket> sockets)
    {
        this.sockets = sockets;
    }

    @Override
    public boolean processPackage(Package p)
    {
        //TODO routing
        return false;
    }

    @Override
    public void processOwnTasks()
    {
        //TODO processing
        return;
    }

    @Override
    public String config(String[] command)
    {
        switch (command[0])
        {
            case "on":
            {
                this.isOn = true;
                return "Turning on RIP";
            }
            case "off":
            case "of":
            {
                this.isOn = false;
                return "Turning off RIP";
            }
            default:
            {
                return "Probably not implemented yet to RIP";
            }
        }
    }
}
