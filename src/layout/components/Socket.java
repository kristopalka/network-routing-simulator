package layout.components;

import tools.IPConverter;

import java.util.LinkedList;
import java.util.Queue;

public class Socket
{
    private String routerName;
    private String socketName;
    private Queue<Package> inputBuff;
    private Socket outerSocket;

    private long address;
    private long netmask;

    // ------------------------------------ constructors ------------------------------------

    public Socket(String socketName, String routerName)
    {
        this.routerName = routerName;
        this.socketName = socketName;
        this.inputBuff = new LinkedList<>();
        this.outerSocket = null;

        // no concrete IP address (mask 32)
        address = IPConverter.strToNum("0.0.0.0");
        netmask = IPConverter.strToNum("255.255.255.255");
    }


    // ------------------------------------ getters ------------------------------------

    public String getName() { return socketName; }

    public String getFullName() { return routerName + "." + socketName; }

    public Socket getOuterSocket() { return outerSocket; }

    public long getAddress()
    {
        return address;
    }

    public long getNetmask()
    {
        return netmask;
    }

    // ------------------------------------ setters ------------------------------------

    public void setOuterSocket(Socket outerSocket)
    {
        this.outerSocket = outerSocket;
    }

    public void setAddress(String address)
    {
        this.address = IPConverter.strToNum(address);
    }

    public void setAddress(String address, int netmask)
    {
        this.address = IPConverter.strToNum(address);
        this.netmask = IPConverter.getMask(netmask);
    }

    public void setNetmask(String netmask)
    {
        this.netmask = IPConverter.strToNum(netmask);
    }

    public void setNetmask(int length)
    {
        this.netmask = IPConverter.getMask(length);
    }


    // ------------------------------------ sending and receiving packages ------------------------------------

    public void sendPackageThruPort(Package p)
    {
        p.onGoThruPort(getFullName());
        outerSocket.pushPackageToBuff(p);
    }

    private void pushPackageToBuff(Package p)
    {
        try
        {
            inputBuff.add(p);
        }
        catch(Exception e)
        {
            System.out.println("Cannot add package to buffer: " + e.getMessage());
        }
    }

    public Package receivePackageFromPort()
    {
        Package p = inputBuff.poll();
        if(p != null)
        {
            p.onGoThruPort(getFullName());
            return p;
        }
        else return null;
    }

    public void clearBuff()
    {
        inputBuff.clear();
    }

}
