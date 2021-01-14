package tests;

import layout.Layout;
import layout.components.Package;
import layout.devices.PC;
import layout.devices.Router3;
import tools.Connection;

import java.util.ArrayList;

public class testingMain
{
    public static void main(String[] args)
    {
        Layout l = new Layout();


        // --------------------------------- building layout ---------------------------------
        l.addRouter(new PC(1,"PC1"));
        l.addRouter(new PC(4,"PC2"));

        l.addRouter(new Router3(2,"R1"));
        l.addRouter(new Router3(3,"R2"));

        l.connect(l.router(1).socket("blue"), l.router(2).socket("blue"));
        l.connect(l.router(2).socket("red"), l.router(3).socket("red"));
        l.connect(l.router(3).socket("blue"), l.router(4).socket("blue"));

        ArrayList<Connection> a = l.getConnectionsInfo();
        for(Connection c : a)
        {
            System.out.println(c.toString());
        }

        // --------------------------------- configuring routers ---------------------------------

        // PC1 (1)
        l.router(1).socket("blue").setAddress("192.168.0.2", 24);

        // R1 (2)
        l.router(2).socket("blue").setAddress("192.168.0.1", 24);
        l.router(2).socket("red").setAddress("192.168.1.1", 24);

        l.router(2).daemonStatic.addRoute("192.168.2.0", 24, l.router(2).socket("red"));

        // R2 (3)
        l.router(3).socket("blue").setAddress("192.168.2.1", 24);
        l.router(3).socket("red").setAddress("192.168.1.1", 24);

        l.router(3).daemonStatic.addRoute("192.168.0.1", 24, l.router(3).socket("red"));

        // PC2 (4)
        l.router(4).socket("blue").setAddress("192.168.2.2", 24);



        // --------------------------------- starting routers ---------------------------------

//        Thread pc1 = new Thread(l.router(1));
//        Thread r1 = new Thread(l.router(2));
//        Thread r2 = new Thread(l.router(3));
//        Thread pc2 = new Thread(l.router(4));
//        pc1.start();
//        r1.start();
//        r2.start();
//        pc2.start();


        // --------------------------------- sending package ---------------------------------

        Package p = new Package("192.168.0.2", "192.168.2.2", "ping");

        l.router(1).sendPackage(p);

    }
}
