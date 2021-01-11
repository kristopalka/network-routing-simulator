package simulation.Router;

public class Package
{
    private int source;
    private int destination;
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

    public int getSource()
    {
        return source;
    }

    public int getDestination()
    {
        return destination;
    }
}
