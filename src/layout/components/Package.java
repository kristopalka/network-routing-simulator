package layout.components;

import tools.IPConverter;

public class Package
{
    public long source;
    public long destination;
    public int TTL = 32;
    public int ID = 0;
    public String type;
    public String log = "";
    public Object information;

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

    public Package(String sourceID, String destinationID, Object information, int ID)
    {
        this.source = IPConverter.strToNum(sourceID);
        this.destination = IPConverter.strToNum(destinationID);
        this.information = information;
        this.ID = ID;
    }


    // ------------------------------------ getters ------------------------------------

    public Object getInformation()
    {
        return information;
    }

    public int getID() { return ID; }

    public long getSource()
    {
        return source;
    }

    public long getDestination()
    {
        return destination;
    }

    public String getLog() { return "(" + log + ")"; }

    @Override
    public String toString()
    {
        return "(\"" + information.toString() + "\" from " + IPConverter.numToStr(source) + " to " + IPConverter.numToStr(destination) + ")";
    }

    public String toStringExtend()
    {
        return "{\n" +
                "   value: \"" + information.toString() + "\"\n" +
                "   source: " + IPConverter.numToStr(source) + "\n" +
                "   destination: " + IPConverter.numToStr(destination) + "\n" +
                "   TTL: " + TTL + "\n" +
                "}\n";
    }

    public int getTTL() { return TTL; }

    // ------------------------------------ setters ------------------------------------

    public void onGoThruPort(String portID)
    {
        log = log + " -> " + portID;
        TTL--;
    }

    public void onStart(String log) { this.log = log; }

}
