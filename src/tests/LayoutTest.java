package tests;

import layout.*;
import layout.components.Package;
import layout.devices.PC;
import layout.devices.Router3;
import org.junit.jupiter.api.Test;
import layout.Layout;
import tools.Connection;
import tools.IPConverter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LayoutTest
{
    @Test
    void testLayout()
    {
        Layout l = new Layout();

        System.out.println(l.addRouter(new PC(1,"PC1")));
        System.out.println(l.addRouter(new PC(2,"PC2")));

        System.out.println(l.addRouter(new Router3(3,"R1")));

        System.out.println(l.connect(l.router(3).socket("red"), l.router(1).socket("blue")));

        System.out.println(l.connect(l.router(3).socket("green"), l.router(2).socket("blue")));
        System.out.println(l.disconnect(l.router(3).socket("green"), l.router(2).socket("blue")));
        System.out.println(l.connect(l.router(3).socket("blue"), l.router(2).socket("blue")));

        System.out.println(l.remRouter(3));

        System.out.println(l.connect(l.router(1).socket("blue"), l.router(2).socket("blue")));


        System.out.println("FINISH");
    }


}