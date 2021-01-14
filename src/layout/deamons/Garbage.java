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

    public Package getFormGarbage()
    {
        return garbage.poll();
    }

    public void clearGaarbage() { garbage.clear(); }

    @Override
    public String config(String[] command)
    {
        //TODO
        return null;
    }
}
