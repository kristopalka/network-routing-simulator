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
                return "Turning on garbage\n";
            }
            case "off":
            {
                this.isOn = false;
                return "Turning off garbage\n";
            }
            case "clear":
            {
                this.clearGaarbage();
                return "Garbage cleared\n";
            }
            case "print":
            {
                switch (command[1])
                {
                    case "all":
                    {
                        String log = "";
                        while(!garbage.isEmpty())
                        {
                            log += garbage.poll().toStringExtend() + "\n";
                        }
                        return log;
                    }
                    default:
                    {
                        return garbage.poll().toStringExtend() + "\n";
                    }
                }
            }
            default:
            {
                return "Garbage: invalid input\n";
            }
        }
    }
}
