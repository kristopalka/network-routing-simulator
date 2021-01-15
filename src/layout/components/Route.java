package layout.components;

import layout.components.Socket;
import layout.devices.Router;
import tools.IPConverter;

public class Route implements Comparable<Route>
{
    public long address;
    public long netmask;
    public Socket socket;

    public Route(long address, long netmask, Socket socket)
    {
        this.address = address;
        this.netmask = netmask;
        this.socket = socket;
    }

    @Override
    public int compareTo(Route o)
    {
        return (int)(o.netmask - this.netmask);
    }

    @Override
    public String toString()
    {
        return IPConverter.numToStr(address) + " " + IPConverter.numToStr(netmask) + " -> " + socket.getName();
    }
}
