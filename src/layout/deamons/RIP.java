package layout.deamons;

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
    public String config(String[] command)
    {
        //TODO
        return null;
    }
}
