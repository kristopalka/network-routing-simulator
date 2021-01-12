package layout.components;

import java.util.LinkedList;
import java.util.Queue;

public class Socket
{
    private String routerID;
    private String socketID;
    private Queue<Package> inputBuff;
    private Socket outerSocket;
    private long address;                //TODO obsługa błędów, nienależenie do tej samej sieci, itd
    private long netmask;                //TODO gettery, settery

    // ------------------------------------ constructors ------------------------------------

    public Socket(String socketID, String routerID)
    {
        this.routerID = routerID;
        this.socketID = socketID;
        this.inputBuff = new LinkedList<>();
        this.outerSocket = null;
    }


    // ------------------------------------ getters ------------------------------------

    public String getID() { return socketID; }

    public String getPath() { return routerID + "." + socketID; }

    public Socket getOuterSocket() { return outerSocket; }

    // ------------------------------------ setters ------------------------------------

    public void setOuterSocket(Socket outerSocket)
    {
        this.outerSocket = outerSocket;
    }


    // ------------------------------------ sending and receiving packages ------------------------------------

    public void sendPackageThruPort(Package p)
    {
        outerSocket.pushPackageToBuff(p);
    }

    public void pushPackageToBuff(Package p)
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
        try
        {
            return inputBuff.poll();
        }
        catch(Exception e)
        {
            System.out.println("Cannot get package from buffer: " + e.getMessage());
            return null;
        }
    }

    public void clearBuff()
    {
        inputBuff.clear();
    }

}
