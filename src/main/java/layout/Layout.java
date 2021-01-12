package layout;

import layout.components.router.Router;
import layout.components.Socket;

import java.util.HashMap;

public class Layout
{
    private HashMap<String, Router> devices;

    // ------------------------------------ constructors ------------------------------------

    public Layout()
    {
        devices = new HashMap<>();
    }

    // ------------------------------------ getters ------------------------------------

    public Router router(String routerID)
    {
        return devices.get(routerID);
    }

    // ------------------------------------ configure  ------------------------------------

    public String addRouter(Router r)
    {
        if(devices.containsKey(r.getID())) return "Cannot add " + r.getID() + ": specified ID is already here";

        devices.put(r.getID(), r);
        return r.getID() + " added successfully";
    }

    public String remRouter(String routerID)
    {
        if(!devices.containsKey(routerID)) return "Cannot remove " + routerID + ": there is no item with specified ID";

        for(Socket socket : devices.get(routerID).getAllSockets().values())
        {
            if(socket.getOuterSocket() != null) { socket.getOuterSocket().setOuterSocket(null); }
        }

        devices.remove(routerID);

        return routerID + " removed successfully";
    }

    public String connect(Socket s1, Socket s2)
    {
        if(s1 == null) return "Cannot connect, first socket is null";
        if(s2 == null) return "Cannot connect, second socket is null";
        if(s1.getOuterSocket() != null ) return "Cannot connect, " + s1.getPath() + " is occupied";
        if(s2.getOuterSocket() != null ) return "Cannot connect, " + s2.getPath() + " is occupied";
        s1.setOuterSocket(s2);
        s2.setOuterSocket(s1);
        return "Connected successfully: " + s1.getPath() + " - " + s2.getPath();
    }

    public String disconnect(Socket s1, Socket s2)
    {
        if(s1.getOuterSocket() != s2 || s2.getOuterSocket() != s1) return "Cannot disconnect, this sockets are not connected";
        s1.setOuterSocket(null);
        s2.setOuterSocket(null);
        return "Disconnected successfully: " + s1.getPath() + " - " + s2.getPath();
    }

    public String repairUniformity(boolean returnLog)
    {
        String log = "";
        for (Router router : devices.values())
        {
            HashMap<String, Socket> sockets = router.getAllSockets();

            for(Socket socket : sockets.values())
            {
                if(socket.getOuterSocket() == null) {}
                else if(socket != socket.getOuterSocket().getOuterSocket())
                {
                    log += socket.getPath() + " pointed " + socket.getOuterSocket().getPath() + "\n";
                    socket.setOuterSocket(null);
                }
            }
        }

        if(returnLog) return "Connection uniformity repaired: " + log + "\n\n";
        else return null;
    }


    public String getConnectionsInfo()
    {
        String log = "";
        for (Router router : devices.values())
        {
            log += router.getID() + ":\n";
            HashMap<String, Socket> sockets = router.getAllSockets();

            for(Socket socket : sockets.values())
            {
                if(socket.getOuterSocket() == null)  log += "    " + socket.getID() + "\n";
                else  log += "    " + socket.getID() + " -> " + socket.getOuterSocket().getPath() + "\n";
            }
        }

        return "Connections info:\n" + log + "\n";
    }

}
