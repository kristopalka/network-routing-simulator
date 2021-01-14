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



        // --------------------------------- configuring routers ---------------------------------
        /*// PC1 (1)
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


        // --------------------------------- sending package ---------------------------------

        l.router(1).config(new String[]{"ping", "192.168.2.2"});
         */

        // --------------------------------- configuring routers ---------------------------------
        // -------- PC1 (1) --------
        l.router(1).configure("interface blue set 192.168.0.2 255.255.255.0");


        // -------- R1 (2) --------
        l.router(2).configure("interface blue set 192.168.0.1 255.255.255.0");
        l.router(2).configure("interface red set 192.168.1.1 255.255.255.0");
        l.router(2).configure("static add 192.168.2.0 255.255.255.0 red");


        // -------- R2 (3) --------
        l.router(3).configure("interface blue set 192.168.2.1 255.255.255.0");
        l.router(3).configure("interface red set 192.168.1.1 255.255.255.0");
        l.router(3).configure("static add 192.168.0.1 255.255.255.0 red");


        // -------- PC2 (4) --------
        l.router(4).configure("interface blue set 192.168.2.2 255.255.255.0");


        // --------------------------------- sending package ---------------------------------
        l.router(1).configure("ping 192.168.2.2");


    }
}
