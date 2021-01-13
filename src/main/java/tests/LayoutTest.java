package tests;

import layout.components.routerTypes.PC;
import layout.components.routerTypes.Router3;
import org.junit.jupiter.api.Test;
import layout.Layout;

import static org.junit.jupiter.api.Assertions.*;

class LayoutTest
{
    @Test
    void testLayout()
    {
        Layout l = new Layout();

        System.out.println(l.addRouter(new PC("PC1")));
        System.out.println(l.addRouter(new PC("PC2")));

        System.out.println(l.addRouter(new Router3("R1")));

        System.out.println(l.connect(l.router("R1").socket("red"), l.router("PC1").socket("blue")));

        System.out.println(l.connect(l.router("R1").socket("green"), l.router("PC2").socket("blue")));
        System.out.println(l.disconnect(l.router("R1").socket("green"), l.router("PC2").socket("blue")));
        System.out.println(l.connect(l.router("R1").socket("blue"), l.router("PC2").socket("blue")));

        System.out.println(l.remRouter("R1"));

        System.out.println(l.connect(l.router("PC1").socket("blue"), l.router("PC2").socket("blue")));

        System.out.println("\n" + l.getConnectionsInfo());


        assertEquals("Connections info:\nPC2:\n    blue -> PC1.blue\nPC1:\n    blue -> PC2.blue\n\n", l.getConnectionsInfo());
    }

}