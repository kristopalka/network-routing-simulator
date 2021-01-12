package simulation.Router;

public class Package
{
    private long source;
    private long destination;
    private Object information;


    // ------------------------------------ constructors ------------------------------------

    public Package(int sourceID, int destinationID, Object information)
    {
        this.source = sourceID;
        this.destination = destinationID;
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
}
