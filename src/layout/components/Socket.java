package layout.components;

import tools.IPConverter;

import java.util.LinkedList;
import java.util.Queue;

public class Socket
{
    private String routerID;
    private String socketID;
    private Queue<Package> inputBuff;
    private Socket outerSocket;

    private long address;
    private long netmask;

    // ------------------------------------ constructors ------------------------------------

    public Socket(String socketID, String routerID)
    {
        this.routerID = routerID;
        this.socketID = socketID;
        this.inputBuff = new LinkedList<>();
        this.outerSocket = null;


        //todo starting addresses??????
        address = IPConverter.strToNum("0.0.0.0");
        netmask = IPConverter.getMask(32);
    }


    // ------------------------------------ getters ------------------------------------

    public String getID() { return socketID; }

    public String getPathID() { return routerID + "." + socketID; }

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
        p.addToRoute(getPathID());
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
            p.addToRoute(getPathID());
            return p;
        }
        else return null;
    }

    public void clearBuff()
    {
        inputBuff.clear();
    }

}
