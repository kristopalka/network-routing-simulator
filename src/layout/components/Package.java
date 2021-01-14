package layout.components;

import tools.IPConverter;

public class Package
{
    public long source;
    public long destination;

    final public static int TTLmax = 32;
    public int TTL = TTLmax;
    public long time = 0;
    public String type = "";

    public String log = "";
    public Object information;

    // ------------------------------------ constructors ------------------------------------

    public Package(long sourceID, long destinationID)
    {
        this.source = sourceID;
        this.destination = destinationID;
    }

    // ------------------------------------ getters ------------------------------------

    public String getLog() { return "(" + log + ")"; }

    @Override
    public String toString()
    {
        return "{from: " + IPConverter.numToStr(source) + "  to: " + IPConverter.numToStr(destination) + "  type: "+ type + "}";
    }

    public String toStringExtend()
    {
        String info;
        try { info = information.toString(); }
        catch(Exception e) { info = ""; }
        return  "{\n" +
                "   source: " + IPConverter.numToStr(source) + "\n" +
                "   destination: " + IPConverter.numToStr(destination) + "\n" +
                "   TTL: " + TTL + "\n" +
                "   sended time: \"" + time + "\"\n" +
                "   type: \"" + type + "\"\n" +
                "   log: \"" + log + "\"\n" +
                "   value: {" + info + "}\n" +
                "}";
    }


    // ------------------------------------ setters ------------------------------------

    public void onGoThruPort(String portID)
    {
        log = log + " -> " + portID;
        TTL--;
    }

}
