package simulation.Router;

import java.util.HashMap;

public class Layout
{
    HashMap<String, Router> devices;

    // ------------------------------------ constructors ------------------------------------

    public Layout()
    {
        devices = new HashMap<>();
    }



    // ------------------------------------ configure simulation.layout ------------------------------------

    public String addRouter(Router r)
    {
        if(devices.containsKey(r.getRouterID())) return "Cannot add " + r.getRouterID() + ": specified ID is already here";

        devices.put(r.getRouterID(), r);
        return r.getRouterID() + " added successfully to simulation";
    }

    public String remRouter(String routerID)
    {
        if(!devices.containsKey(routerID)) return "Cannot remove " + routerID + ": there is no item with specified ID";

        devices.remove(routerID);

        repairUniformity(false);
        return routerID + " removed successfully from simulation";
    }

    public String connect(Socket s1, Socket s2)
    {
        if(s1 == null) return "Cannot connect, first socket is null";
        if(s2 == null) return "Cannot connect, second socket is null";
        if(s1.getOuterSocket() != null || s2.getOuterSocket() != null ) return "Cannot connect, one of sockets is occupied";
        s1.setOuterSocket(s2);
        s2.setOuterSocket(s1);
        return "Connected successfully";
    }

    public String disconnect(Socket s1, Socket s2)
    {
        if(s1.getOuterSocket() != s2 || s2.getOuterSocket() != s1) return "Cannot disconnect, this sockets are not connected";
        s1.setOuterSocket(null);
        s2.setOuterSocket(null);
        return "Disconnected successfully";
    }

    public String repairUniformity(boolean returnLog)
    {
        String log = "";
        for (Router router : devices.values())
        {
            HashMap<String, Socket> sockets = router.getAllSockets();

            for(Socket socket : sockets.values())
            {
                if(socket != socket.getOuterSocket().getOuterSocket())
                {
                    log += "(" + router.getRouterID() + "." + socket.getSocketID() + ") ";
                    socket.setOuterSocket(null);
                }
            }
        }

        if(returnLog) return "Connection uniformity repaired: " + log;
        else return "Connection uniformity repaired";
    }

    //TODO: could throw exception because getoutersocket can be null

}
