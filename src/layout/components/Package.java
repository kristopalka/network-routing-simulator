package layout.components;

import tools.IPConverter;

public class Package
{
    private long source;
    private long destination;
    private Object information;
    private String route = "";


    // ------------------------------------ constructors ------------------------------------

    public Package(long sourceID, long destinationID, Object information)
    {
        this.source = sourceID;
        this.destination = destinationID;
        this.information = information;
    }

    public Package(String sourceID, String destinationID, Object information)
    {
        this.source = IPConverter.strToNum(sourceID);
        this.destination = IPConverter.strToNum(destinationID);
        this.information = information;
    }


    // ------------------------------------ getters ------------------------------------

    public Object getInformation()
    {
        return information;
    }

    public long getSource()
    {
        return source;
    }

    public long getDestination()
    {
        return destination;
    }

    public String getRoute() { return route; }

    // ------------------------------------ setters ------------------------------------

    public void addToRoute(String portID)
    {
        route = route + " " + portID + "->";
    }
}
