package layout.deamons;

import layout.components.Package;

import java.util.LinkedList;
import java.util.Queue;

public class Garbage extends Daemon
{
    private Queue<Package> garbage = new LinkedList<>();

    @Override
    public boolean processPackage(Package p)
    {
        garbage.add(p);
        return true;
    }


    public void clearGaarbage() { garbage.clear(); }

    @Override
    public String config(String[] command)
    {
        switch (command[0])
        {
            case "on":
            {
                this.isOn = true;
                return "Turning on garbage";
            }
            case "off":
            case "of":
            {
                this.isOn = false;
                return "Turning off garbage";
            }
            case "clear":
            case "clea":
            case "cle":
            case "cl":
            {
                this.clearGaarbage();
                return "Garbage cleared";
            }
            case "show":
            case "sho":
            case "sh":
            {
                if(command.length == 1)
                {
                    try
                    {
                        return garbage.poll().toStringExtend();
                    }
                    catch (Exception e)
                    {
                        return "Nothink to show";
                    }
                }
                else if(command[1] == "all" || command[1] == "al")
                {
                    String log = "";
                    while(!garbage.isEmpty())
                    {
                        log += garbage.poll().toStringExtend();
                    }
                    if(log == "") return "Nothink to show";
                    return log;
                }
                else
                {
                    return "Invalid input";
                }

            }
            default:
            {
                return "Invalid input";
            }
        }
    }
}
