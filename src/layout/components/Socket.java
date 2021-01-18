package layout.components;

import layout.devices.Router;
import tools.IPConverter;
import tools.InputAnalyzer;

import java.util.LinkedList;
import java.util.Queue;

public class Socket implements Config
{
    private String socketName;
    private Queue<Package> inputBuff = new LinkedList<>();
    private Socket outerSocket = null;
    private Router parentRouter;
    private boolean isOn = true;

    private long address = IPConverter.strToNum("0.0.0.0");
    private long netmask = IPConverter.strToNum("255.255.255.255");

    // ------------------------------------ constructors ------------------------------------

    public Socket(String socketName, Router parentRouter)
    {
        this.parentRouter = parentRouter;
        this.socketName = socketName;
    }


    // ------------------------------------ getters ------------------------------------

    public boolean isFree()
    {
        if(outerSocket == null) return true;
        else return false;
    }

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

    @Override
    public String toString()
    {
        return IPConverter.numToStr(this.address) + " " + IPConverter.numToStr(this.netmask);
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


    // ------------------------------------ sending and receiving packages ------------------------------------

    public void sendPackageThruPort(Package p)
    {
        if(!isOn) return;

        if(p.TTL == p.TTLmax) //this is first node
        {
            p.log = "From " + parentRouter.getName();
            p.source = this.address;
        }

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
        if(!isOn) return null;

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


    // ------------------------------------ config ------------------------------------

    @Override
    public String config(String[] command)
    {
        switch (command[0])
        {
            case "on":
            {
                this.isOn = true;
                return "Turning on socket " + this.socketName;
            }
            case "off":
            case "of":
            {
                this.isOn = false;
                return "Turning off socket " + this.socketName;
            }
            case "address":
            case "addres":
            case "addre":
            case "addr":
            case "add":
            case "ad":
            {
                try
                {
                    long address = IPConverter.strToNum(command[1]);
                    long netmask = IPConverter.strToMask(command[2]);
                    this.address = address;
                    this.netmask = netmask;
                    return "Adress setted";
                }
                catch (Exception e)
                {
                    return "Invalid input";
                }
            }
            case "show":
            case "sho":
            case "sh":
            {
                return this.socketName + ": " + this.toString();
            }
            default:
            {
                return "Invalid input";
            }
        }
    }
}
