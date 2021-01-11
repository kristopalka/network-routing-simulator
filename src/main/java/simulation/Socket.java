package simulation;

import java.util.LinkedList;
import java.util.Queue;

public class Socket
{
    private String socketID;
    private Queue<Package> inputBuff;
    private Socket outerSocket;
    private int address;                //TODO obsługa błędów, nienależenie do tej samej sieci, itd
    private int netmask;                //TODO gettery, settery

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

    public void sendPackage(Package p)
    {
        outerSocket.pushPackage(p);
    }

    private void pushPackage(Package p)
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

    protected Package getPackage()
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

}
