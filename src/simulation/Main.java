package simulation;

import gui.*;
import java.util.ArrayList;

import layout.Layout;
import layout.components.Package;
import layout.devices.*;
import tools.IPConverter;

public class Main {

    private ArrayList<Router> routers;
    private MainFrame mainFrame; 

    public static void main(String[] args)
    {
        Layout l = new Layout();


        // --------------------------------- building layout ---------------------------------
        l.addRouter(new PC("PC1"));
        l.addRouter(new PC("PC2"));

        l.addRouter(new Router3("R1"));

        l.connect(l.router("R1").socket("red"), l.router("PC1").socket("blue"));
        l.connect(l.router("R1").socket("green"), l.router("PC2").socket("blue"));

        // --------------------------------- configuring routers ---------------------------------

        l.router("R1").socket("red").setAddress("192.168.0.1");
        l.router("R1").socket("red").setNetmask(24);

        l.router("PC1").socket("blue").setAddress("192.168.0.2");
        l.router("PC1").socket("blue").setNetmask(24);


        l.router("R1").socket("red").setAddress("192.168.1.1");
        l.router("R1").socket("red").setNetmask(24);

        l.router("PC2").socket("blue").setAddress("192.168.1.2");
        l.router("PC2").socket("blue").setNetmask(24);

        // --------------------------------- starting routers ---------------------------------

        Thread r1 = new Thread(l.router("R1"));
        Thread pc1 = new Thread(l.router("PC1"));
        Thread pc2 = new Thread(l.router("PC2"));
        r1.start();
        pc1.start();
        pc2.start();


        // --------------------------------- sending package ---------------------------------

        Package p = new Package("192.168.0.2", "192.168.1.2", "package :)");

        l.router("PC1").socket("blue").sendPackageThruPort(p);

    }
    
    public static String getInfo() {
        return "Sim v0";
    }
    
}
