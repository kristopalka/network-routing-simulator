package layout.components;

import layout.devices.Router;
import tools.IPConverter;

import java.util.LinkedList;
import java.util.Queue;

public class Socket implements Config
{
    private String socketName;
    private Queue<Package> inputBuff;
    private Socket outerSocket;
    private Router parentRouter;

    private long address;
    private long netmask;

    // ------------------------------------ constructors ------------------------------------

    public Socket(String socketName, Router parentRouter)
    {
        this.parentRouter = parentRouter;
        this.socketName = socketName;
        this.inputBuff = new LinkedList<>();
        this.outerSocket = null;

        // no concrete IP address (mask 32)
        address = IPConverter.strToNum("0.0.0.0");
        netmask = IPConverter.strToNum("255.255.255.255");
    }


    // ------------------------------------ getters ------------------------------------

    public String getName() { return socketName; }

    public String getFullName() { return parentRouter.getName() + "." + socketName; }

    public int getParentID() { return parentRouter.getID(); }

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


    public void setAddress(String address, int netmask)
    {
        this.address = IPConverter.strToNum(address);
        this.netmask = IPConverter.getMask(netmask);
    }

    private void setAddress(String address)
    {
        this.address = IPConverter.strToNum(address);
    }

    private void setNetmask(String netmask)
    {
        this.netmask = IPConverter.strToNum(netmask);
    }

    private void setNetmask(int length)
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

    @Override
    public String config(String[] command)
    {
        //TODO
        return null;
    }
}
