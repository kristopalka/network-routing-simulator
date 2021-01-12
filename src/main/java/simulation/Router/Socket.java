package simulation.Router;

import java.util.LinkedList;
import java.util.Queue;

public class Socket
{
    private String socketID;
    private Queue<Package> inputBuff;
    private Socket outerSocket;
    private long address;                //TODO obsługa błędów, nienależenie do tej samej sieci, itd
    private long netmask;                //TODO gettery, settery

    // ------------------------------------ constructors ------------------------------------

    public Socket(String socketID)
    {
        this.socketID = socketID;
        this.inputBuff = new LinkedList<>();
        this.outerSocket = null;
    }


    // ------------------------------------ getters ------------------------------------

    public String getSocketID() { return socketID; }

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

    protected Package receivePackageFromPort()
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

    protected void clearBuff()
    {
        inputBuff.clear();
    }

}
