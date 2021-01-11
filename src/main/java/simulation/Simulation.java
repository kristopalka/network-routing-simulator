package simulation;

import java.util.HashMap;

public class Simulation
{
    private static Layout layout;
    private boolean isRunning;
    private HashMap<String, Router> devices;

    public Simulation(HashMap<String, Router> devices)
    {
        isRunning = false;
        this.devices = devices;

        for(Router r: devices.values())
        {
            //r.start();
        }
    }
}
