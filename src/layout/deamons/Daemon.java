package layout.deamons;

import layout.components.Config;
import layout.components.Package;
import layout.components.Socket;

import java.util.HashMap;

public abstract class Daemon implements Config
{
    protected boolean isOn = true;

    public boolean processPackage(Package p)
    {
        // if solved
        // send and return true

        // if not solved
        return false;
    }

    public void processOwnTasks()
    {
        // if do nothing
        return;
    }

    public boolean isOn()
    {
        return isOn;
    }
}